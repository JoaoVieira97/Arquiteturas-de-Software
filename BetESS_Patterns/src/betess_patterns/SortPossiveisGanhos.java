package betess_patterns;

import java.util.Comparator;

public class SortPossiveisGanhos implements StrategyA{
    public void sort(ConjuntoApostas apostas){
        apostas.getComponents().sort(Comparator.comparing(ApostaComponent :: possiveisGanhos ).reversed() );
    }
}