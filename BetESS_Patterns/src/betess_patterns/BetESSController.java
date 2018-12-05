package betess_patterns;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class BetESSController {
    
    private BetESSModel model;
    private BetESSView view;
    private Iterator<Evento> iterator_result;
    private StrategyEventosContext sec = new StrategyEventosContext(new SortNumApostas());
    
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
                    System.out.println("Opcão Inválida!");
                    break;
            }
        } while(!opcao.equals("S"));
    }
    
    private void login(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Insira os seus dados:");
        System.out.print("Email: ");
        String email = scan.nextLine();
        System.out.print("Password: ");
        String password = scan.nextLine();
        int login = this.model.login(email, password);
        String nome;
        switch (login) {
            case 1:
                nome = model.getUtilizador(email).getNome();
                System.out.println("Login como apostador efetuado com sucesso. Bem-vindo " + nome + "!");
                flowApostador(email);
                break;
            case 2:
                nome = model.getUtilizador(email).getNome();
                System.out.println("Login como funcionário efetuado com sucesso. Bem-vindo " + nome + "!");
                flowFuncionario(email);
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
    
    private void registar() {
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
    
    //------------------------------APOSTADOR------------------------------
    
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
                    mostrarEventos();
                    break;
                case "V" :
                    System.out.println("To be implemented");
                    //verApostasRealizadas(email);
                    break;
                case "A" :
                    System.out.println("To be implemented");
                    //novaAposta(email);
                    break;
                case "C" :
                    imprimeSaldo(email);
                    break;
                case "I" :
                    carregarConta(email);
                    break;
                case "S": 
                    break;
                default: System.out.println("Opcão Inválida!"); break;
            }
        } while(!opcao.equals("S"));
        System.out.println("Até à próxima visita " + model.getUtilizador(email).getNome() + "!");
    }
    
    public void mostrarEventos(){
        List<Evento> eventos = this.model.getListaEventos();
        Menu menu = this.view.getMenu(5);
        String opcao;
        do{
            menu.show();
            Scanner scan = new Scanner(System.in);
            opcao = scan.next();
            opcao = opcao.toUpperCase();
            switch(opcao) {
                case "T":
                    this.view.printCollectionEventos("Todos os eventos:", eventos);
                    break;
                case "C":
                    System.out.println("Qual a competição que pretende procurar?");
                    String competicao = scan.next();
                    this.iterator_result = new IteratorCompeticao(eventos.iterator(),competicao);
                    this.view.printIteratorEventos("Eventos pela procura de competição '" + competicao + "':", this.iterator_result);
                    break;
                case "E":
                    System.out.println("Qual a equipa que pretende procurar?");
                    String equipa = scan.next();
                    this.iterator_result = new IteratorEquipa(eventos.iterator(),equipa);
                    this.view.printIteratorEventos("Eventos pela procura de equipa '" + equipa + "':", this.iterator_result);
                    break;
                case "M":
                    sec.sortEventos(eventos);
                    this.view.printCollectionEventos("Eventos ordenados pelo nº de apostas:", eventos);
                    break;
                case "S":
                    break;
                default: System.out.println("Opcão Inválida!"); break;
                
            }
        } while(!opcao.equals("R"));
    }
    
    private void imprimeSaldo(String email){
        System.out.println("O saldo atual da conta é de " + ((Apostador) this.model.getUtilizador(email)).getSaldo());
    }
    
    private void carregarConta(String email){
        Scanner scan = new Scanner(System.in);
        System.out.println("Insira a quantidade monetária que pretende carregar na sua conta");
        System.out.print("Quantia : ");
        double q = scan.nextDouble();
        double novo_saldo = ((Apostador) this.model.getUtilizador(email)).getSaldo() + q;
        ((Apostador) this.model.getUtilizador(email)).setSaldo(novo_saldo);
        System.out.println("Carregamento efetuado com sucesso! O seu saldo é de agora " + novo_saldo + " BetESSCoins");
    }
    
    //-----------------------------FUNCIONÁRIO-----------------------------
    
    public void flowFuncionario(String email){
        System.out.println("To be implemented");
    }
    
}
