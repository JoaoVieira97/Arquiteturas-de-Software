package betess_patterns;

import java.util.Comparator;
import java.util.List;

public class SortNumApostas implements StrategyE{
    
    public void sort(List<Evento> eventos){
        eventos.sort(Comparator.comparing(Evento::getNum_apostas).reversed());
    }
}
