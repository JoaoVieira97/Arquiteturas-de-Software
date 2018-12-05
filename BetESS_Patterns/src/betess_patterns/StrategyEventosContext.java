package betess_patterns;

import java.util.List;

public class StrategyEventosContext {
    
    private StrategyE strategy;
    
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
