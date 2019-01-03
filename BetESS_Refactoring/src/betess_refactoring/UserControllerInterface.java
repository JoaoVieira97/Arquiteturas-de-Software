/**
 *
 * @author João Vieira & Simão Barbosa
 */
package betess_refactoring;
public interface UserControllerInterface {
    
    public void setView(BetESSView v);
    
    public void setModel(BetESSModel m);
    
    public void startFlow(String email);

}