package betess_patterns.model.iterator;

import betess_patterns.model.Evento;
import java.util.Iterator;
import java.util.List;
import java.io.Serializable;

public class AggregateECompeticao implements AggregateE, Serializable {
   
    public Iterator<Evento> createIterator(List<Evento> eventos, String competicao) {
        return new IteratorCompeticao(eventos.iterator(),competicao);
    }
    
}

