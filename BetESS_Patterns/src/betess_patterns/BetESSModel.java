package betess_patterns;

import java.util.HashMap;
import java.util.Map;

public class BetESSModel {
    
    private Map<Integer, Evento> eventos;
    private Map<String, Utilizador> utilizadores;
    private int id_proximoEvento;
    
    public BetESSModel(){
        this.utilizadores = new HashMap<String,Utilizador>();
        this.eventos = new HashMap<Integer,Evento>();
        this.id_proximoEvento = 1;
    }
}
