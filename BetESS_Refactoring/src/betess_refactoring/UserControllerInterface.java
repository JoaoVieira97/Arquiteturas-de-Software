package betess_refactoring;

/**
 *
 * @author João Vieira & Simão Barbosa
 */
public interface UserControllerInterface {
    
    public void setView(BetESSView v);
    
    public void setModel(BetESSModel m);
    
    public void startFlow(String email);

}