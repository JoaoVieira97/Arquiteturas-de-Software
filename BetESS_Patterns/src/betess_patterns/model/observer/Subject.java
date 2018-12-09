package betess_patterns.model.observer;

import java.util.List;

public interface Subject {
    
    public void registerObserver(Observer o);

    public void removeObserver(Observer o);

    public void notifyObservers(int idEvento, int resultado);
    
    public void notifyObservers(List<String> notificacoes);
}
