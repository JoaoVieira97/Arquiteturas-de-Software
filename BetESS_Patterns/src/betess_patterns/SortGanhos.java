package betess_patterns;

import java.util.Comparator;

public class SortGanhos implements StrategyA{
    public void sort(ConjuntoApostas apostas){
        apostas.getComponents().sort(Comparator.comparing(ApostaComponent :: ganhoOuPerda ).reversed() );
    }
}
