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
        System.out.println("Insira os seus dados:");
        System.out.print("Email: ");
        String email = scan.nextLine();
        System.out.print("Password: ");
        String password = scan.nextLine();
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
        Scanner scan = new Scanner(System.in);
        System.out.println("Insira o seu email:");
        String email = scan.next();
        if (this.model.existeUtilizador(email)){
            System.out.println("Já existe um utilizador com o email inserido");
            return;
        }
        System.out.println("Insira o seu nome:");
        String nome = scan.next();
        System.out.println("Insira a sua password:");
        String password = scan.next();
        System.out.println("Qual o valor que pretende carregar na sua conta?");
        Scanner scanD = new Scanner(System.in);
        double saldo = scanD.nextDouble();
        model.addApostador(email, password, nome, saldo);
        System.out.println("Registo efetuado com sucesso");
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
                    break;
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
    
    private void adicionarEvento(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Insira o nome das 2 equipas envolvidas no jogo:");
        System.out.print("Equipa nº1 : ");
        String equipa_1 = scan.nextLine();
        System.out.print("Equipa nº2 : ");
        String equipa_2 = scan.nextLine();
        Scanner scanD = new Scanner(System.in);
        System.out.println("Insira as odd's para os 3 possíveis resultados:");
        System.out.print("Vitória do/da " + equipa_1 + ": ");
        double odd_1 = scanD.nextDouble();
        System.out.print("Empate: ");
        double odd_x = scanD.nextDouble();
        System.out.print("Vitória do/da " + equipa_2 + ": ");
        double odd_2 = scanD.nextDouble();
        System.out.print("O evento pode estar disponível de momento (S/N): ");
        String d = scan.nextLine().toUpperCase();
        boolean disponibilidade;
        switch(d){
            case "S": disponibilidade = true; break;
            case "N": disponibilidade = false; break;
            default: System.out.println("Opção inválida"); return;
        }
        this.model.addEvento(equipa_1, equipa_2, odd_1, odd_x, odd_2, disponibilidade);
        System.out.println("Evento adicionado com sucesso");
    }
    
    private void flowModificar(){
        Scanner scanI = new Scanner(System.in);
        System.out.print("Insira o id do evento que pretende modificar: ");
        int id = scanI.nextInt();
        if (!this.model.existeEvento(id)){
            System.out.println("Não exister o evento com o id inserido");
            return;
        }
        Evento evento = this.model.getEvento(id);
        Menu menu = view.getMenu(4);
        String opcao;
        do {
            menu.show();
            Scanner scan = new Scanner(System.in);
            opcao = scan.next();
            opcao = opcao.toUpperCase();
            switch(opcao) {
                case "D" :
                    modificarDisponibilidadeEvento(evento);
                    break;
                case "O" :
                    modificarOddsEvento(evento);
                    break;
                case "E" :
                    modificarEquipasEvento(evento);
                    break;
                case "S": 
                    break;
                default: System.out.println("Opcão Inválida !"); break;
            }
        } while(!opcao.equals("S"));    
    }
    
    private void modificarDisponibilidadeEvento(Evento evento){
        Scanner scan = new Scanner(System.in);
        System.out.println("Insira 'D' para colocar o evento como disponível ou 'I' para colocar o evento como indisponível");
        System.out.print("Opção : ");
        String opcao = scan.nextLine().toUpperCase();
        switch (opcao){
            case "D":
                evento.setDisponibilidade(true);
                System.out.println("Evento colocado como disponível");
                break;
            case "I":
                evento.setDisponibilidade(false);
                System.out.println("Evento colocado como indisponível");
                break;
            default:
                System.out.println("Opção inválida");
                break;
        }
    }
    
    private void modificarOddsEvento(Evento evento){
        Scanner scan = new Scanner(System.in);
        System.out.println("Insira as odd's para os 3 resultados possíveis do evento:");
        System.out.print("Vitória do/da " + evento.getEquipa_1() + " : ");
        double odd_1 = scan.nextDouble();
        System.out.print("Empate : ");
        double odd_x = scan.nextDouble();
        System.out.print("Vitória do/da " + evento.getEquipa_2() + " : ");
        double odd_2 = scan.nextDouble();
        evento.setOdds(odd_1, odd_x, odd_2);
        System.out.println("Odd's do evento modificadas com sucesso");
    }
    
    private void modificarEquipasEvento(Evento evento){
        Scanner scan = new Scanner(System.in);
        System.out.println("De momento as duas equipas para aposta no evento são\nEquipa nº1 : " + evento.getEquipa_1() + "\nEquipa nº2 : " + evento.getEquipa_2());
        System.out.println("Insira o nome das equipas para editar o evento:");
        System.out.print("Equipa nº1 : ");
        String equipa_1 = scan.nextLine();
        System.out.print("Equipa nº2 : ");
        String equipa_2 = scan.nextLine();
        evento.setEquipa_1(equipa_1);
        evento.setEquipa_2(equipa_2);
        System.out.println("Equipas do evento modificadas com sucesso");
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
        System.out.println("Lista de eventos BetESS:");
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
        else if (!this.model.getEvento(id).getDisponibilidade()){
            System.out.println("O evento escolhido não está disponível para apostas de momento!");
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
            this.view.menuEquipas(evento, quantia);
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
