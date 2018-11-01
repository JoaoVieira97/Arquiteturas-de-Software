package betess;

/**
 *
 * @author João Vieira & Simão Barbosa
 */
public class Main {
    
    public static void main(String[] args){
        BetESSModel model = new BetESSModel();

        BetESSView view = new BetESSView();

        BetESSController control = new BetESSController();
        control.setModel(model);
        control.setView(view);

        control.startFlow();
    }
    
}
