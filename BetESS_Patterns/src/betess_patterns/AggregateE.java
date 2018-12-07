
package betess_patterns;

import java.util.Iterator;
import java.util.List;

interface AggregateE {
    public Iterator<Evento> createIterator(List<Evento> eventos, String string);
}
