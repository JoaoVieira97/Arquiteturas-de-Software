package betess_patterns.model.iterator;

import betess_patterns.model.Evento;
import java.util.Iterator;
import java.util.List;

public interface AggregateE {
    
    public Iterator<Evento> createIterator(List<Evento> eventos, String string);

}
