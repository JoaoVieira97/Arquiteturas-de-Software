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
                    //login();
                    break;
                case "R" :
                    //registar();
                    break;
                case "S":
                    break;
                default:
                    System.out.println("Opcão Inválida !");
                    break;
            }
        } while(!opcao.equals("S"));
    }
}
