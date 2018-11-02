package betess;
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

    public void startFlow (){
       this.view.initView();
       this.flowInit();
    }
    public void flowInit () {
        this.view.getMenus().getMenus().get(1).show();
        String opcao;
        do {
            Scanner scan = new Scanner(System.in);
            opcao = scan.next();
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
            Scanner scan = new Scanner(System.in);
            opcao = scan.next();
            if (this.model.getUtilizadores().containsKey(opcao)) {
                
                String email = opcao;
                System.out.print("\nPassword:");
                while (true){
                    opcao = scan.next();
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
            Scanner scan = new Scanner(System.in);
            opcao = scan.next();
            opcao = opcao.toUpperCase();
            switch(opcao) {
                case "L" : flowEventos(); break;
                case "R" : flowApostas(email); break;
                case "A" : flowNovaAposta(email); break;
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

    private void flowNovaAposta(String email) {
        String opcao;
        System.out.println("Id do Evento:");
        int id=0;
        while(true){
            Scanner scan = new Scanner(System.in);
            id = scan.nextInt();
            if (this.model.getEventos().containsKey(id)){
                break;
            }
            else System.out.println("Evento inesxistente");
        }
        Apostador apostador = (Apostador) this.model.getUtilizadores().get(email);
        System.out.println("Quantia a apostar:");
        double quantia=0;
        while(true){
            Scanner scan = new Scanner(System.in);
            quantia = scan.nextDouble();
            if (apostador.saldoSufiente(quantia)){
                break;
            }
            else System.out.println("Saldo Insuficiente");
        }
        
        
        Evento evento = this.model.getEventos().get(id);
        String equipa1 = evento.getEquipa_1();
        String equipa2 = evento.getEquipa_2();
        do {                        
            this.view.menuEquipas(equipa1,equipa2).show();
            Scanner scan = new Scanner(System.in);
            opcao = scan.next();
            switch(opcao) {
                case "0" : apostador.newAposta( -1,0, quantia, evento); System.out.println("Aposta confirmada na equipa "+equipa1+"!"); return;
                case "1" : apostador.newAposta( -1,1, quantia, evento); System.out.println("Aposta confirmada na equipa "+equipa2+"!"); return;
                case "2" : apostador.newAposta( -1,2, quantia, evento); System.out.println("Aposta confirmada no empate!");return;
                case "S": break;
                default: System.out.println("Opcão Inválida !"); break;
            }
        }
        while(!opcao.equals("S"));  
    }
}
