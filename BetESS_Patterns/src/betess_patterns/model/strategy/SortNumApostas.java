package betess_patterns.model.strategy;

import betess_patterns.model.Evento;
import java.util.Comparator;
import java.util.List;
import java.io.Serializable;

public class SortNumApostas implements StrategyE, Serializable{
    
    public void sort(List<Evento> eventos){
        eventos.sort(Comparator.comparing(Evento::getNum_apostas).reversed());
    }
}
