package betess;

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

    public void startFlow (){
       this.view.initView();
       this.flowInit();
    }
    public void flowInit () {
        this.view.getMenus().getMenus().get(1).show();
        String opcao;
        do {
            opcao = Input.lerString(); 
            opcao = opcao.toUpperCase();
            switch(opcao) {
                case "L" : flowLogin(); break;
                case "R" : flowRegistar(); break;
                case "S": break;
                default: System.out.println("Opcão Inválida !"); break;
            }
        }
        while(!opcao.equals("S"));  
       
    }

    private void flowLogin() {
        System.out.print("Email:");
        String opcao;
        do {
            opcao = Input.lerString(); 
            if (this.model.getUtilizadores().containsKey(opcao)) {
                
                String email = opcao;
                System.out.print("\nPassword:");
                while (true){
                    opcao = Input.lerString();
                    if (this.model.getUtilizadores().get(email).getPassword().equals(opcao)) break;
                    else {
                        System.out.println("Password Inválida !");
                        System.out.print("Password:");
                    }
                }
                
                if (this.model.getUtilizadores().get(email) instanceof Apostador){
                    flowApostador(email);
                }
                else{
                    flowFuncionario();
                    
                }
                break;
            }
            else{
                System.out.println("Opcão Inválida !");
            }
        }
        while(true);  
    }

    private void flowRegistar() {
        
    }

    private void flowApostador(String email) {
        
        String opcao;
        do {
            this.view.getMenus().getMenus().get(2).show();
            opcao = Input.lerString(); 
            opcao = opcao.toUpperCase();
            switch(opcao) {
                case "L" : flowEventos(); break;
                case "R" : flowApostas(email); break;
                case "S": break;
                default: System.out.println("Opcão Inválida !"); break;
            }
        }
        while(!opcao.equals("S"));  
    }

    private void flowFuncionario() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void flowEventos() {
           System.out.println(this.model.getEventos().toString());

    }

    private void flowApostas(String email) {
        System.out.println(this.model.getApostas(email).toString());
    }
}
