package betess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author João Vieira & Simão Barbosa
 */
public class BetESSModel {
    
    private Map<String,Utilizador> utilizadores;
    private Map<Integer,Evento> eventos;
    private int id_proximoEvento;
    
    public BetESSModel(){
        this.utilizadores = new HashMap<String,Utilizador>();
        this.eventos = new HashMap<Integer,Evento>();
        this.id_proximoEvento = 1;
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
    
    public void addEvento(String equipa_1, String equipa_2, double odd_1, double odd_x, double odd_2, boolean disponibilidade){
        Evento novoEvento = new Evento(id_proximoEvento, equipa_1, equipa_2, odd_1, odd_x, odd_2, disponibilidade);
        this.eventos.put(this.id_proximoEvento, novoEvento);
        this.id_proximoEvento++;
    }
        
    public void addEvento(Evento evento){
        evento.setId(id_proximoEvento);
        this.eventos.put(this.id_proximoEvento, evento);
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
    
    // 1 - modificado com sucesso, 0 - evento não existe
    public int mudarOddsEvento(int idEvento, double odd_1, double odd_x, double odd_2){
        if (!this.eventos.containsKey(idEvento)) return 0;
        else{
            this.eventos.get(idEvento).setOdds(odd_1, odd_x, odd_2);
            return 1;
        }
    }
    
    // 1 - modificado com sucesso, 0 - evento não existe
    public int mudarEquipasEvento(int idEvento, String equipa_1, String equipa_2){
        if (!this.eventos.containsKey(idEvento)) return 0;
        else{
            Evento e = this.eventos.get(idEvento);
            e.setEquipa_1(equipa_1);
            e.setEquipa_2(equipa_2);
            return 1;
        }
    }
    public List<Aposta> getApostas (String email) {
        if (this.utilizadores.containsKey(email)){
            Apostador apostador = (Apostador) this.utilizadores.get(email);
            return apostador.getApostas();
        }
        else return new ArrayList<>();
    }

    public Map<String, Utilizador> getUtilizadores() {
        return utilizadores;
    }

    public void setUtilizadores(Map<String, Utilizador> utilizadores) {
        this.utilizadores = utilizadores;
    }

    public Map<Integer, Evento> getEventos() {
        return eventos;
    }

    public void setEventos(Map<Integer, Evento> eventos) {
        this.eventos = eventos;
    }

    public int getId_proximoEvento() {
        return id_proximoEvento;
    }

    public void setId_proximoEvento(int id_proximoEvento) {
        this.id_proximoEvento = id_proximoEvento;
    }
    
}
