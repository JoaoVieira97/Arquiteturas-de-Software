package betess_refactoring;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author João Vieira & Simão Barbosa
 */
public class BetESSController{
   
    private BetESSModel model;
    private BetESSView view;
    private UserControllerInterface apostadorController;
    private UserControllerInterface funcionarioController;

    public BetESSController(){}

    public void setView(BetESSView v){
        this.view = v;
    }

    public void setModel(BetESSModel m){
        this.model = m;
    }
    
    public void setApostadorController(UserControllerInterface ci){
        this.apostadorController = ci;
    }
    
    public void setFuncionarioController(UserControllerInterface ci){
        this.funcionarioController = ci;
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
                this.apostadorController.startFlow(email);
                break;
            case 2:
                nome = model.getUtilizador(email).getNome();
                view.println("Login como funcionário efetuado com sucesso. Bem-vindo " + nome + "!");
                this.funcionarioController.startFlow(email);
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
        String email = scan.nextLine();
        if (this.model.existeUtilizador(email)){
            view.println("Já existe um utilizador com o email inserido");
            return;
        }
        view.println("Insira o seu nome:");
        String nome = scan.nextLine();
        view.println("Insira a sua password:");
        String password = scan.nextLine();
        view.println("Qual o valor que pretende carregar na sua conta?");
        Scanner scanD = new Scanner(System.in);
        double saldo = scanD.nextDouble();
        String[] dados = new String[3];
        dados[0] = email;
        dados[1] = password;
        dados[2] = nome;
        model.addApostador(dados, saldo);
        view.println("Registo efetuado com sucesso");
    }

}
