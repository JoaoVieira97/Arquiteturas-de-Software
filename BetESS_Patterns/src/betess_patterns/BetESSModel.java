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
    
    public void addEvento(String equipa_1, String equipa_2, String competicao, double odd_1, double odd_x, double odd_2, boolean disponibilidade){
        Evento novoEvento = new Evento(id_proximoEvento, equipa_1, equipa_2, competicao, odd_1, odd_x, odd_2, disponibilidade);
        this.eventos.put(this.id_proximoEvento, novoEvento);
        this.id_proximoEvento++;
    }
    
    // 1 - login com sucesso apostador, 2 - login com sucesso funcionário, 0 - password errada, -1 - email incorreto
    public int login(String email, String password){
        if (!this.utilizadores.containsKey(email)) return -1;
        Utilizador u = this.utilizadores.get(email);
        if (u.getPassword().equals(password)){
            if (u instanceof Apostador) return 1;
            else if (u instanceof Funcionario) return 2;
        }
        return 0;
    }
    
    public boolean existeUtilizador(String email){
        return this.utilizadores.containsKey(email);
    }
    
    public Utilizador getUtilizador(String email){
        return this.utilizadores.get(email);
    }
    
}
