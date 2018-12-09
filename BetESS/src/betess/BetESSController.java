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
                    login();
                    break;
                case "R" :
                    registar();
                    break;
                case "S":
                    break;
                default:
                    view.println("Opcão Inválida !"); break;
            }
        } while(!opcao.equals("S"));
    }

    private void login(){
        Scanner scan = new Scanner(System.in);
        view.println("Insira os seus dados:");
        view.print("Email: ");
        String email = scan.nextLine();
        view.print("Password: ");
        String password = scan.nextLine();
        int login = this.model.login(email, password);
        String nome;
        switch (login) {
            case 1:
                nome = model.getUtilizador(email).getNome();
                view.println("Login como apostador efetuado com sucesso. Bem-vindo " + nome + "!");
                flowApostador(email);
                break;
            case 2:
                nome = model.getUtilizador(email).getNome();
                view.println("Login como funcionário efetuado com sucesso. Bem-vindo " + nome + "!");
                flowFuncionario(email);
                break;
            case 0:
                view.println("Password inserida está incorreta");
                break;
            case -1:
                view.println("Não existe o utlizador com o email inserido");
                break;
            default:
                break;
        }
    }

    private void registar() {
        Scanner scan = new Scanner(System.in);
        view.println("Insira o seu email:");
        String email = scan.next();
        if (this.model.existeUtilizador(email)){
            view.println("Já existe um utilizador com o email inserido");
            return;
        }
        view.println("Insira o seu nome:");
        String nome = scan.next();
        view.println("Insira a sua password:");
        String password = scan.next();
        view.println("Qual o valor que pretende carregar na sua conta?");
        Scanner scanD = new Scanner(System.in);
        double saldo = scanD.nextDouble();
        model.addApostador(email, password, nome, saldo);
        view.println("Registo efetuado com sucesso");
    }
    
    //------------------------------APOSTADOR------------------------------
    
    private void flowApostador(String email) {
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
        view.println("Indique a quantia que pretende a apostar: (Pode apostar até " + apostador.getSaldo() + " BetESSCoins)");
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
            switch(opcao) {
                case "1" :
                    apostador.newAposta(-1, 0, quantia, evento.getOdds()[0], evento); view.println("Aposta realizada na equipa " + evento.getEquipa_1() + "!");
                    evento.addApostador(email);
                    return;
                case "X" :
                    apostador.newAposta(-1, 1, quantia, evento.getOdds()[1], evento); view.println("Aposta realizada no empate!");
                    evento.addApostador(email);
                    return;
                case "2" :
                    apostador.newAposta(-1, 2, quantia, evento.getOdds()[2], evento); view.println("Aposta realizada na equipa " + evento.getEquipa_2() + "!");
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
        view.println("O saldo atual da conta é de " + ((Apostador) this.model.getUtilizador(email)).getSaldo());
    }
    
    private void carregarConta(String email){
        Scanner scan = new Scanner(System.in);
        view.println("Insira a quantidade monetária que pretende carregar na sua conta");
        view.print("Quantia : ");
        double q = scan.nextDouble();
        double novo_saldo = ((Apostador) this.model.getUtilizador(email)).getSaldo() + q;
        ((Apostador) this.model.getUtilizador(email)).setSaldo(novo_saldo);
        view.println("Carregamento efetuado com sucesso! O seu saldo é de agora " + novo_saldo + " BetESSCoins");
    }
    
    //------------------------------FUNCIONÁRIO------------------------------
    
    private void flowFuncionario(String email) {
        Menu menu = view.getMenu(3);
        String opcao;
        do {
            menu.show();
            Scanner scan = new Scanner(System.in);
            opcao = scan.next();
            opcao = opcao.toUpperCase();
            switch(opcao) {
                case "E" :
                    mostrarEventos();
                    break;
                case "A" :
                    adicionarEvento();
                    break;
                case "M" :
                    flowModificar();
                    break;
                case "T" :
                    terminarEvento();
                    break;
                case "S": 
                    break;
                default: view.println("Opcão Inválida !"); break;
            }
        } while(!opcao.equals("S"));
        view.println("Até à próxima visita " + model.getUtilizador(email).getNome() + "!");
    }
    
    private void adicionarEvento(){
        Scanner scan = new Scanner(System.in);
        view.println("Insira o nome das 2 equipas envolvidas no jogo:");
        view.print("Equipa nº1 : ");
        String equipa_1 = scan.nextLine();
        view.print("Equipa nº2 : ");
        String equipa_2 = scan.nextLine();
        Scanner scanD = new Scanner(System.in);
        view.println("Insira as odd's para os 3 possíveis resultados:");
        view.print("Vitória do/da " + equipa_1 + ": ");
        double odd_1 = scanD.nextDouble();
        view.print("Empate: ");
        double odd_x = scanD.nextDouble();
        view.print("Vitória do/da " + equipa_2 + ": ");
        double odd_2 = scanD.nextDouble();
        view.print("O evento pode estar disponível de momento (S/N): ");
        String d = scan.nextLine().toUpperCase();
        boolean disponibilidade;
        switch(d){
            case "S": disponibilidade = true; break;
            case "N": disponibilidade = false; break;
            default: view.println("Opção inválida"); return;
        }
        this.model.addEvento(equipa_1, equipa_2, odd_1, odd_x, odd_2, disponibilidade);
        view.println("Evento adicionado com sucesso");
    }
    
    private void flowModificar(){
        Scanner scanI = new Scanner(System.in);
        view.print("Insira o id do evento que pretende modificar: ");
        int id = scanI.nextInt();
        if (!this.model.existeEvento(id)){
            view.println("Não exister o evento com o id inserido");
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
                default: view.println("Opcão Inválida !"); break;
            }
        } while(!opcao.equals("S"));    
    }
    
    private void modificarDisponibilidadeEvento(Evento evento){
        Scanner scan = new Scanner(System.in);
        view.println("Insira 'D' para colocar o evento como disponível ou 'I' para colocar o evento como indisponível");
        view.print("Opção : ");
        String opcao = scan.nextLine().toUpperCase();
        switch (opcao){
            case "D":
                evento.setDisponibilidade(true);
                view.println("Evento colocado como disponível");
                break;
            case "I":
                evento.setDisponibilidade(false);
                view.println("Evento colocado como indisponível");
                break;
            default:
                view.println("Opção inválida");
                break;
        }
    }
    
    private void modificarOddsEvento(Evento evento){
        Scanner scan = new Scanner(System.in);
        view.println("Insira as odd's para os 3 resultados possíveis do evento:");
        view.print("Vitória do/da " + evento.getEquipa_1() + " : ");
        double odd_1 = scan.nextDouble();
        view.print("Empate : ");
        double odd_x = scan.nextDouble();
        view.print("Vitória do/da " + evento.getEquipa_2() + " : ");
        double odd_2 = scan.nextDouble();
        evento.setOdds(odd_1, odd_x, odd_2);
        view.println("Odd's do evento modificadas com sucesso");
    }
    
    private void modificarEquipasEvento(Evento evento){
        Scanner scan = new Scanner(System.in);
        view.println("De momento as duas equipas para aposta no evento são\nEquipa nº1 : " + evento.getEquipa_1() + "\nEquipa nº2 : " + evento.getEquipa_2());
        view.println("Insira o nome das equipas para editar o evento:");
        view.print("Equipa nº1 : ");
        String equipa_1 = scan.nextLine();
        view.print("Equipa nº2 : ");
        String equipa_2 = scan.nextLine();
        evento.setEquipa_1(equipa_1);
        evento.setEquipa_2(equipa_2);
        view.println("Equipas do evento modificadas com sucesso");
    }
    
    private void terminarEvento(){
        view.println("Insira o id do evento que pretende encerrar:");
        Scanner scanI = new Scanner(System.in);
        int id = scanI.nextInt();
        int m = this.model.mudarDisponibilidadeEvento(id, false);
        if (m == 0){
            view.println("Não existe o evento com o id inserido");
            return;
        }
        else if (m == 1){
            Evento evento = this.model.getEvento(id);
            view.println("Qual o resultado com que o evento terminou?");
            view.println("1 - Vitória do/da " + evento.getEquipa_1());
            view.println("X - Empate");
            view.println("2 - Vitória do/da " + evento.getEquipa_2());
            view.print("Opção : ");
            Scanner scan = new Scanner(System.in);
            String opcao = scan.nextLine().toUpperCase();
            int resultado = -1;
            switch (opcao){
                case "1": resultado = 0; break;
                case "X": resultado = 1; break;
                case "2": resultado = 2; break;
                default: System.out.println("Resultado inválido!"); return;
            }
            Apostador apostador;
            for (String a : evento.getApostadores()){
                apostador = (Apostador) this.model.getUtilizador(a);
                apostador.eventoTerminado(evento.getId(),resultado);
            }
            view.println("Evento encerrado com sucesso");
        }
    }

}
