package betess_patterns;

import java.util.Comparator;
import java.io.Serializable;

public class SortPossiveisGanhos implements StrategyA, Serializable{
    
    public void sort(ConjuntoApostas apostas){
        apostas.getComponents().sort(Comparator.comparing(ApostaComponent :: possiveisGanhos).reversed());
    }

}