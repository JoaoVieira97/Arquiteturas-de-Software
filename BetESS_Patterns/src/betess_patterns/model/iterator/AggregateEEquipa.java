package betess_patterns.model.iterator;

import betess_patterns.model.Evento;
import java.util.Iterator;
import java.util.List;
import java.io.Serializable;

public class AggregateEEquipa implements AggregateE, Serializable{

    public Iterator<Evento> createIterator(List<Evento> eventos, String equipa) {
        return new IteratorEquipa(eventos.iterator(),equipa);
    }
    
}

