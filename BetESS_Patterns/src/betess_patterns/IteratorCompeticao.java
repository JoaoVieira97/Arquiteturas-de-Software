package betess_patterns;

import java.util.Iterator;

public class IteratorCompeticao implements Iterator<Evento>{
    
    private Iterator<Evento> eventos;
    private Evento e;
    private String competicao;

    public IteratorCompeticao(Iterator<Evento> eventos, String competicao){
        this.eventos = eventos;
        this.e = null;
        this.competicao = competicao;
    }
    
    @Override
    public boolean hasNext(){
        while (this.eventos.hasNext()){
            this.e = this.eventos.next();
            if (this.e.getCompeticao().toLowerCase().contains(competicao.toLowerCase())) return true;
        }
        return false;
    }

    @Override
    public Evento next(){
        return this.e;
    }
    
}
