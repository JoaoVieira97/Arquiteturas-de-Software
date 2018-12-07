package betess_patterns;

import java.io.Serializable;

public class StrategyApostasContext implements Serializable{
    
    private StrategyA strategy;
    
    public StrategyApostasContext(){}
    
    public StrategyApostasContext(StrategyA s){
        this.strategy = s;
    }
            
    public void setStrategy(StrategyA s){
        this.strategy = s;
    }
    
    public void sortApostas(ConjuntoApostas apostas){
        this.strategy.sort(apostas);
    }
}
