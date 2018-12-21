package betess_refactoring;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author João Vieira & Simão Barbosa
 */
public class Controller_Apostador implements UserControllerInterface{
    
    private BetESSModel model;
    private BetESSView view;
    
    public void setView(BetESSView v){
        this.view = v;
    }
    
    public void setModel(BetESSModel m){
        this.model = m;
    }
    
    public void startFlow(String email) {
        Menu menu = view.getMenu(2);
        Apostador a = (Apostador) this.model.getUtilizador(email);
        String opcao;
        do {
            List<String> noti = a.getNotificacoes();
            if (noti.isEmpty()) menu.show();
            else this.view.menuApostadorNotificacoes(noti.size()).show();
            Scanner scan = new Scanner(System.in);
            opcao = scan.next();
            opcao = opcao.toUpperCase();
            switch(opcao) {
                case "N" :
                    if (noti.isEmpty()){ System.out.println("Opcão Inválida!"); break;}
                    this.view.printNotificacoes(noti);
                    a.cleanNotificacoes();
                    break;
                case "E" :
                    mostrarEventos();
                    break;
                case "V" :
                    verApostasRealizadas(email);
                    break;
                case "A" :
                    novaAposta(email);
                    break;
                case "C" :
                    imprimeSaldo(email);
                    break;
                case "I" :
                    carregarConta(email);
                    break;
                case "S": 
                    break;
                default: view.println("Opcão Inválida !"); break;
            }
        } while(!opcao.equals("S"));
        view.println("Até à próxima visita " + model.getUtilizador(email).getNome() + "!");
    }
    
    private void mostrarEventos() {
        view.printEventos(this.model.getListaEventos());
    }

    private void verApostasRealizadas(String email) {
        view.printApostas(this.model.getApostas(email));
    }

    private void novaAposta(String email) {
        String opcao;
        view.println("Insira o id do evento em que pretende apostar:");
        Scanner scanI = new Scanner(System.in);
        int id = scanI.nextInt();
        if (!this.model.existeEvento(id)){
            view.println("O evento com o id inserido não existe");
            return;
        }
        else if (!this.model.getEvento(id).getDisponibilidade()){
            view.println("O evento escolhido não está disponível para apostas de momento!");
            return;
        }
        Apostador apostador = (Apostador) this.model.getUtilizador(email);
        view.println("Indique a quantia que pretende a apostar: (Pode apostar até " + apostador.getSaldo() + " ESScoins)");
        Scanner scanD = new Scanner(System.in);
        double quantia = scanD.nextDouble();
        if (!apostador.saldoSufiente(quantia)){
            view.println("O seu saldo é insuficiente para realizar a aposta desejada");
            return;
        }
        Evento evento = this.model.getEvento(id);                      
        do{
            this.view.menuEquipas(evento, quantia);
            view.println("Insira a sua escolha:");
            Scanner scan = new Scanner(System.in);
            opcao = scan.next();
            opcao = opcao.toUpperCase();
            Integer[] resultados= new Integer[2];
            resultados[0]= -1;
            double[] quantiaOdd = new double[2];
            switch(opcao) {
                case "1" :
                    resultados[1] = 0;
                    quantiaOdd[0] = quantia;
                    quantiaOdd[1]= evento.getOdds()[0];
                    apostador.newAposta(resultados,quantiaOdd, evento); view.println("Aposta realizada na equipa " + evento.getEquipa_1() + "!");
                    evento.addApostador(email);
                    return;
                case "X" :
                    resultados[1] = 1;
                    quantiaOdd[0] = quantia;
                    quantiaOdd[1]= evento.getOdds()[1];
                    apostador.newAposta(resultados,quantiaOdd, evento); view.println("Aposta realizada no empate!");
                    evento.addApostador(email);
                    return;
                case "2" :
                    resultados[1] = 2;
                    quantiaOdd[0] = quantia;
                    quantiaOdd[1]= evento.getOdds()[2];
                    apostador.newAposta(resultados,quantiaOdd, evento); view.println("Aposta realizada na equipa " + evento.getEquipa_2() + "!");
                    evento.addApostador(email);
                    return;
                case "S":
                    break;
                default:
                    view.println("Opcão Inválida! Tente de novo.");
                    break;
            }
        } while(!opcao.equals("S"));  
    }

    private void imprimeSaldo(String email){
        view.println("O saldo atual da conta é de " + ((Apostador) this.model.getUtilizador(email)).getSaldo() + " ESScoins");
    }
    
    private void carregarConta(String email){
        Scanner scan = new Scanner(System.in);
        view.println("Insira a quantidade monetária que pretende carregar na sua conta");
        view.print("Quantia : ");
        double q = scan.nextDouble();
        double novo_saldo = ((Apostador) this.model.getUtilizador(email)).getSaldo() + q;
        ((Apostador) this.model.getUtilizador(email)).setSaldo(novo_saldo);
        view.println("Carregamento efetuado com sucesso! O seu saldo é de agora " + novo_saldo + " ESScoins");
    }
    
}
