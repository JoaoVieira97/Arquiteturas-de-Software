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
            opcao = scan.next().toUpperCase();
            switch(opcao) {
                case "L" :
                    login(); break;
                case "R" :
                    registar(); break;
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
        switch (login) {
            case 1:
                loginUtilizador(1,email); break;
            case 2:
                loginUtilizador(2,email); break;
            case 0:
                view.println("Password inserida está incorreta"); break;
            case -1:
                view.println("Não existe o utlizador com o email inserido"); break;
            default: break;
        }
    }
    
    // mode = 1 -> apostador, mode = 2 -> funcionário
    private void loginUtilizador(int mode, String email){
        String nome = model.getUtilizador(email).getNome();
        String user = (mode == 1) ? "apostador" : "funcionário";
        view.println("Login como " + user + " efetuado com sucesso. Bem-vindo " + nome + "!");
        if (mode == 1) this.apostadorController.startFlow(email);
        else this.funcionarioController.startFlow(email);
    }
    
    private void registar() {
        Scanner scan = new Scanner(System.in); Scanner scanD = new Scanner(System.in);
        view.println("Insira o seu email:");
        String email = scan.nextLine();
        if (this.model.existeUtilizador(email)){
            view.println("Já existe um utilizador com o email inserido"); return;
        }
        view.println("Insira o seu nome:");
        String nome = scan.nextLine();
        view.println("Insira a sua password:");
        String password = scan.nextLine();
        view.println("Qual o valor que pretende carregar na sua conta?");
        double saldo = scanD.nextDouble();
        model.addApostador(new String[] {email, password, nome}, saldo);
        view.println("Registo efetuado com sucesso");
    }

}
