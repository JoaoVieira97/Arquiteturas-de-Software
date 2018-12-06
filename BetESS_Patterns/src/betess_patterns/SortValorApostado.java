package betess_patterns;

import java.util.Comparator;
import java.util.List;

public class SortValorApostado implements StrategyA{

    public void sort(ConjuntoApostas apostas){
        apostas.getComponents().sort(Comparator.comparing(ApostaComponent :: getQuantia ).reversed() );
    }
}
