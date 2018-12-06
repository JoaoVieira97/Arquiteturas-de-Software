package betess_patterns;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    
    public void addEvento(String equipa_1, String equipa_2, String competicao, double odd_1, double odd_x, double odd_2, boolean disponibilidade, int num_apostas){
        Evento novoEvento = new Evento(id_proximoEvento, equipa_1, equipa_2, competicao, odd_1, odd_x, odd_2, disponibilidade, num_apostas);
        this.eventos.put(this.id_proximoEvento, novoEvento);
        this.id_proximoEvento++;
    }
    
    // 1 - modificado com sucesso, 0 - evento não existe
    public int mudarDisponibilidadeEvento(int idEvento, boolean disponibilidade){
        if (!this.eventos.containsKey(idEvento)) return 0;
        else{
            this.eventos.get(idEvento).setDisponibilidade(disponibilidade);
            return 1;
        }
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
    
    public List<Evento> getListaEventos() {
        List<Evento> lista_eventos = new ArrayList<Evento>();
        for (Evento e : this.eventos.values())
            lista_eventos.add(e);
        return lista_eventos;
    }
    
    public boolean existeEvento(int id){
        return this.eventos.containsKey(id);
    }
    
    public Evento getEvento(int id){
        return this.eventos.get(id);
    }
    
}
