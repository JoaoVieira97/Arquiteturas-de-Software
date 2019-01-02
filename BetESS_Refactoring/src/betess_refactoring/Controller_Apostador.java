/**
 *
 * @author João Vieira & Simão Barbosa
 */
package betess_refactoring;

import java.util.List;
import java.util.Scanner;

public class Controller_Apostador implements UserControllerInterface{
    
    private BetESSModel model;
    private BetESSView view;
    
    public void setView(BetESSView v){
        this.view = v;
    }
    
    public void setModel(BetESSModel m){
        this.model = m;
    }
    
    public void startFlow(String email){
        Menu menu = view.getMenu(2);
        Apostador a = (Apostador) this.model.getUtilizador(email);
        String opcao;
        do {
            List<String> noti = a.getNotificacoes();
            opcao = lerOpcao(menu, noti);
            switch(opcao) {
                case "N" :
                    verNotificacoes(noti, a); break;
                case "E" :
                    mostrarEventos(); break;
                case "V" :
                    verApostasRealizadas(email); break;
                case "A" :
                    novaAposta(email); break;
                case "C" :
                    imprimeSaldo(email); break;
                case "I" :
                    carregarConta(email); break;
                case "S": break;
                default: view.println("Opcão Inválida !"); break;
            }
        } while(!opcao.equals("S"));
        view.println("Até à próxima visita " + model.getNomeUtilizador(email) + "!");
    }
    
    private String lerOpcao(Menu menu, List<String> noti){
        if (noti.isEmpty()) menu.show();
        else this.view.menuApostadorNotificacoes(noti.size()).show();
        Scanner scan = new Scanner(System.in);
        String opcao = scan.next().toUpperCase();
        return opcao;
    }
    
    private void verNotificacoes(List<String> noti, Apostador a){
        if (noti.isEmpty()){
            System.out.println("Opcão Inválida!");
            return;
        }
        this.view.printNotificacoes(noti);
        a.cleanNotificacoes();
    }
    
    private void mostrarEventos() {
        view.printEventos(this.model.getListaEventos());
    }

    private void verApostasRealizadas(String email) {
        view.printApostas(this.model.getApostas(email));
    }

    private void novaAposta(String email){
        view.println("Insira o id do evento em que pretende apostar:");
        int id = lerIdEvento();
        if (id == -1) return;
        Apostador apostador = (Apostador) this.model.getUtilizador(email);
        view.println("Indique a quantia que pretende a apostar: (Pode apostar até " + apostador.getSaldo() + " ESScoins)");
        double quantia = lerQuantia(apostador);
        if (quantia == -1) return;
        Evento evento = this.model.getEvento(id);                      
        lerAposta(apostador, evento, quantia); 
    }
    
    private int lerIdEvento(){
        Scanner scanI = new Scanner(System.in);
        int id = scanI.nextInt();
        if (!this.model.existeEvento(id)){
            view.println("O evento com o id inserido não existe");
            return -1;
        }
        else if (!this.model.getEvento(id).getDisponibilidade()){
            view.println("O evento escolhido não está disponível para apostas de momento!");
            return -1;
        }
        return id;
    }
    
    private double lerQuantia(Apostador a){
        Scanner scanD = new Scanner(System.in);
        double quantia = scanD.nextDouble();
        if (!a.saldoSufiente(quantia)){
            view.println("O seu saldo é insuficiente para realizar a aposta desejada");
            return -1;
        }
        return quantia;
    }
    
    private void lerAposta(Apostador apostador, Evento evento, double quantia){
        String opcao;
        do{
            this.view.menuEquipas(evento, quantia);
            view.println("Insira a sua escolha:");
            Scanner scan = new Scanner(System.in);
            opcao = scan.next().toUpperCase();
            switch(opcao) {
                case "1" :
                    apostaRealizada(apostador, evento, quantia, 0); return;
                case "X" :
                    apostaRealizada(apostador, evento, quantia, 1); return;
                case "2" :
                    apostaRealizada(apostador, evento, quantia, 2); return;
                case "S": break;
                default: view.println("Opcão Inválida! Tente de novo."); break;
            }
        } while(!opcao.equals("S")); 
    }
    
    private void apostaRealizada(Apostador a, Evento e, double quantia, int i){
        a.newAposta(new Integer[]{-1,i}, new double[]{quantia, e.getOdds()[i]}, e);
        switch (i){
            case 0:
                view.println("Aposta realizada na equipa " + e.getEquipa_1() + "!"); break;
            case 1:
                view.println("Aposta realizada no empate!"); break;
            default:
                view.println("Aposta realizada na equipa " + e.getEquipa_2() + "!"); break;
        }
        e.addApostador(a.getEmail());
    }

    private void imprimeSaldo(String email){
        view.println("O saldo atual da conta é de " + this.model.getSaldoApostador(email) + " ESScoins");
    }
    
    private void carregarConta(String email){
        Scanner scan = new Scanner(System.in);
        view.println("Insira a quantidade monetária que pretende carregar na sua conta");
        view.print("Quantia : ");
        double q = scan.nextDouble();
        double novo_saldo = this.model.getSaldoApostador(email) + q;
        this.model.setSaldoApostador(email, novo_saldo);
        view.println("Carregamento efetuado com sucesso! O seu saldo é de agora " + novo_saldo + " ESScoins");
    }
    
}
