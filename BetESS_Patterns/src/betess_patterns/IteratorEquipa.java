package betess_patterns;

import java.util.Iterator;
import java.io.Serializable;

public class IteratorEquipa implements Iterator<Evento>{

    private Iterator<Evento> eventos;
    private Evento e;
    private String equipa;

    public IteratorEquipa(Iterator<Evento> eventos, String equipa){
        this.eventos = eventos;
        this.e = null;
        this.equipa = equipa;
    }

    @Override
    public boolean hasNext(){
        while (this.eventos.hasNext()){
            this.e = this.eventos.next();
            if (this.e.getEquipa_1().toLowerCase().contains(equipa.toLowerCase())
             || this.e.getEquipa_2().toLowerCase().contains(equipa.toLowerCase()))
                return true;
        }
        return false;
    }

    @Override
    public Evento next(){
        return this.e;
    }
    
}
