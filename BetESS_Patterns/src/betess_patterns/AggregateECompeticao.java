
package betess_patterns;

import java.util.Iterator;
import java.util.List;
import java.io.Serializable;

class AggregateECompeticao implements AggregateE,Serializable {

   
    public Iterator<Evento> createIterator(List<Evento> eventos, String competicao) {
        return new IteratorCompeticao(eventos.iterator(),competicao);
    }
    
}

