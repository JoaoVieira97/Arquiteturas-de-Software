package betess_refactoring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.Serializable;

/**
 *
 * @author João Vieira & Simão Barbosa
 */
public class BetESSModel implements Serializable{
    
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
    
    public void addApostador(String[] dados, double saldo){
        this.utilizadores.put(dados[0], new Apostador(dados, saldo));
    }
    
    public void addFuncionario(String email, String password, String nome){
        this.utilizadores.put(email, new Funcionario(email, password, nome));
    }
    
    public void addEvento(String[] equipas, double[] odds, boolean disponibilidade){
        Evento novoEvento = new Evento(id_proximoEvento, equipas , odds , disponibilidade);
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
    
    public Utilizador getUtilizador(String email){
        return this.utilizadores.get(email);
    }
    
    public boolean existeUtilizador(String email){
        return this.utilizadores.containsKey(email);
    }
    
    public boolean existeEvento(int id){
        return this.eventos.containsKey(id);
    }
    
    public Evento getEvento(int id){
        return this.eventos.get(id);
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

    public Map<Integer,Evento> getEventos(){
        return eventos;
    }
    
    public List<Evento> getListaEventos() {
        List<Evento> lista_eventos = new ArrayList<Evento>();
        for (Evento e : this.eventos.values())
            lista_eventos.add(e);
        return lista_eventos;
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
    
    public String getNomeUtilizador(String email){
        return this.utilizadores.get(email).getNome();
    }
    
    public double getSaldoApostador(String email){
        return ((Apostador) this.utilizadores.get(email)).getSaldo();
    }
    
    public void setSaldoApostador(String email, double saldo){
        ((Apostador) this.utilizadores.get(email)).setSaldo(saldo);
    }
    
}
