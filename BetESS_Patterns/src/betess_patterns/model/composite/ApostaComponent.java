package betess_patterns.model.composite;

import betess_patterns.model.observer.Observer;
import java.util.List;

public interface ApostaComponent {
    
    public void show();
    
    public double terminaEvento(int idEvento, int resultado, List<String> notificacoes);
    
    public void add(ApostaComponent a);
    
    public void remove(ApostaComponent a);
    
    public void getChild(int i);
    
    public double getQuantia();
    
    public double possiveisGanhos();
    
    public double ganhoOuPerda();
    
    public String emailApostador();
   
    public int getId();

    public void registerObserver(Observer func);
}
