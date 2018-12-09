package betess_patterns.model.strategy;

import betess_patterns.model.composite.ApostaComponent;
import betess_patterns.model.composite.ConjuntoApostas;
import java.util.Comparator;
import java.io.Serializable;

public class SortGanhos implements StrategyA, Serializable{
    
    public void sort(ConjuntoApostas apostas){
        apostas.getComponents().sort(Comparator.comparing(ApostaComponent :: ganhoOuPerda ).reversed() );
    }
    
}
