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
    
    public void addApostador(String email, String password, String nome, double saldo){
        this.utilizadores.put(email, new Apostador(email, password, nome, saldo));
    }
    
    public void addFuncionario(String email, String password, String nome){
        this.utilizadores.put(email, new Funcionario(email, password, nome));
    }
    
    public void addEvento(String equipa_1, String equipa_2, double odd_1, double odd_x, double odd_2, boolean disponibilidade){
        Evento novoEvento = new Evento(id_proximoEvento, equipa_1, equipa_2, odd_1, odd_x, odd_2, disponibilidade);
        this.eventos.put(this.id_proximoEvento, novoEvento);
        this.id_proximoEvento++;
    }
    
}
