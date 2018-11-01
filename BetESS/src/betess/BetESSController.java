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
        System.out.println("TODO");
    }
}
