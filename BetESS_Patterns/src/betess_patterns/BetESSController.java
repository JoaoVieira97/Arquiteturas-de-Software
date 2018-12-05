package betess_patterns;

import java.util.Scanner;

public class BetESSController {
    
    private BetESSModel model;
    private BetESSView view;
    
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
    
    public void flowApostador(String email){
        System.out.println("To be implemented");
    }
    
    //-----------------------------FUNCIONÁRIO-----------------------------
    
    public void flowFuncionario(String email){
        System.out.println("To be implemented");
    }
    
}
