
package betess_patterns;

import java.util.Iterator;
import java.util.List;
import java.io.Serializable;

class AggregateEEquipa implements AggregateE, Serializable{

   
    public Iterator<Evento> createIterator(List<Evento> eventos, String equipa) {
        return new IteratorEquipa(eventos.iterator(),equipa);
    }
    
}

