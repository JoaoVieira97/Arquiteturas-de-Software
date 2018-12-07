package betess_patterns;

import java.util.List;
import java.io.Serializable;

public class StrategyEventosContext implements Serializable{
    
    private StrategyE strategy;
    
    public StrategyEventosContext(){}
    
    public StrategyEventosContext(StrategyE s){
        this.strategy = s;
    }
            
    public void setStrategy(StrategyE s){
        this.strategy = s;
    }
    
    public void sortEventos(List<Evento> eventos){
        this.strategy.sort(eventos);
    }
}
