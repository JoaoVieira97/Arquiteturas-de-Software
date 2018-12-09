package betess_patterns.model.observer;

public interface Subject {
    
    public void registerObserver(Observer o);

    public void removeObserver(Observer o);

    public void notifyObservers(int idEvento, int resultado);
    
}
