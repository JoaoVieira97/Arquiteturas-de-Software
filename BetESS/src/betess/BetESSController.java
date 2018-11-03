package betess;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author João Vieira & Simão Barbosa
 */
public class BetESSController {
   
    private BetESSModel model;
    private BetESSView view;

    public BetESSController(){}

    public void setView(BetESSView v){
        this.view = v;
    }

    public void setModel(BetESSModel m){
        this.model = m;
    }

    public void startFlow() {
        Menu menu = view.getMenu(1);
        String opcao;
        do {
            menu.show();
            Scanner scan = new Scanner(System.in);
            opcao = scan.next();
            opcao = opcao.toUpperCase();
            switch(opcao) {
                case "L" :
                    flowLogin();
                    break;
                case "R" :
                    flowRegistar();
                    break;
                case "S":
                    break;
                default:
                    System.out.println("Opcão Inválida !"); break;
            }
        } while(!opcao.equals("S"));
    }

    private void flowLogin(){
        Scanner scan = new Scanner(System.in);
        String email,password;
        System.out.println("Email:");
        email = scan.next();
        System.out.println("Password:");
        password = scan.next();
        int login = this.model.login(email, password);
        switch (login) {
            case 1:
                System.out.println("Login como apostador efetuado com sucesso");
                flowApostador(email);
                break;
            case 2:
                System.out.println("Login como funcionário efetuado com sucesso");
                flowFuncionario();
                break;
            case 0:
                System.out.println("Password inserida está incorreta");
                break;
            case -1:
                System.out.println("Não existe o utlizador com o email inserido");
                break;
            default:
                break;
        }
    }

    private void flowRegistar() {
        System.out.println("Falta implementar");
    }

    private void flowApostador(String email) {
        Menu menu = view.getMenu(2);
        String opcao;
        do {
            menu.show();
            Scanner scan = new Scanner(System.in);
            opcao = scan.next();
            opcao = opcao.toUpperCase();
            switch(opcao) {
                case "E" :
                    flowEventos();
                    break;
                case "V" :
                    flowApostas(email);
                    break;
                case "A" :
                    flowNovaAposta(email);
                    break;
                case "C" :
                    flowSaldo(email);
                    break;
                case "S": 
                    break;
                default: System.out.println("Opcão Inválida !"); break;
            }
        } while(!opcao.equals("S"));  
    }

    private void flowSaldo(String email){
        System.out.println("O saldo atual da conta é de " + ((Apostador) this.model.getUtilizador(email)).getSaldo());
    }
    
    private void flowFuncionario() {
        Menu menu = view.getMenu(3);
        String opcao;
        do {
            menu.show();
            Scanner scan = new Scanner(System.in);
            opcao = scan.next();
            opcao = opcao.toUpperCase();
            switch(opcao) {
                case "E" :
                    flowEventos();
                    break;
                case "A" :
                    adicionarEvento();
                case "M" :
                    flowModificar();
                    break;
                case "T" :
                    flowTerminarEvento();
                    break;
                case "S": 
                    break;
                default: System.out.println("Opcão Inválida !"); break;
            }
        } while(!opcao.equals("S")); 
    }
    
    private void flowModificar(){
        System.out.println("Falta implementar");
    }
    
    private void adicionarEvento(){
        System.out.println("Falta implementar");
    }
    
    private void flowTerminarEvento(){
        System.out.println("Insira o id do evento que pretende encerrar:");
        Scanner scanI = new Scanner(System.in);
        int id = scanI.nextInt();
        int m = this.model.mudarDisponibilidadeEvento(id, false);
        if (m == 0){
            System.out.println("Não existe o evento com o id inserido");
            return;
        }
        else if (m == 1){
            this.model.eventoTerminado(id);
            System.out.println("Evento encerrado com sucesso");
        }
    }

    private void flowEventos() {
        List<Evento> lista_eventos = this.model.getListaEventos();
        for (Evento e : lista_eventos)
            System.out.println(e.toString());
    }

    private void flowApostas(String email) {
        for (Aposta a : this.model.getApostas(email))
            System.out.println(a.toString());
    }

    private void flowNovaAposta(String email) {
        String opcao;
        System.out.println("Insira o id do evento em que pretende apostar:");
        Scanner scanI = new Scanner(System.in);
        int id = scanI.nextInt();
        if (!this.model.existeEvento(id)){
            System.out.println("O evento com o id inserido não existe");
            return;
        }
        Apostador apostador = (Apostador) this.model.getUtilizador(email);
        System.out.println("Indique a quantia que pretende a apostar: (Pode apostar até " + apostador.getSaldo() + " BetESSCoins)");
        Scanner scanD = new Scanner(System.in);
        double quantia = scanD.nextDouble();
        if (!apostador.saldoSufiente(quantia)){
            System.out.println("O seu saldo é insuficiente para realizar a aposta desejada");
            return;
        }
        Evento evento = this.model.getEvento(id);                      
        do{
            this.view.menuEquipas(evento);
            System.out.println("Insira a sua escolha:");
            Scanner scan = new Scanner(System.in);
            opcao = scan.next();
            opcao = opcao.toUpperCase();
            switch(opcao) {
                case "1" :
                    apostador.newAposta(-1, 0, quantia, evento.getOdds()[0], evento); System.out.println("Aposta realizada na equipa " + evento.getEquipa_1() + "!");
                    return;
                case "X" :
                    apostador.newAposta(-1, 1, quantia, evento.getOdds()[1], evento); System.out.println("Aposta realizada no empate!");
                    return;
                case "2" :
                    apostador.newAposta(-1, 2, quantia, evento.getOdds()[2], evento); System.out.println("Aposta realizada na equipa " + evento.getEquipa_2() + "!");
                    return;
                case "S":
                    break;
                default:
                    System.out.println("Opcão Inválida! Tente de novo.");
                    break;
            }
        } while(!opcao.equals("S"));  
    }
}
