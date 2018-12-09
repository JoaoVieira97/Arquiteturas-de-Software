package betess;

import java.io.Serializable;

/**
 *
 * @author João Vieira & Simão Barbosa
 */
public class Aposta implements Serializable{
    private int id;
    private int resultado_evento;// -1 - aberto, 0 - vitória equipa_1, 1 - empate, 2 - vitória equipa_2 
    private int resultado_aposta; // 0 - vitória equipa_1, 1 - empate, 2 - vitória equipa_2
    private double quantia;
    private double odd;
    private Evento evento;

    public Aposta(int id, Integer resultado_evento, Integer resultado_aposta, double quantia, double odd, Evento evento) {
        this.id = id;
        this.resultado_evento = resultado_evento;
        this.resultado_aposta = resultado_aposta;
        this.quantia = quantia;
        this.odd = odd;
        this.evento = evento;
    }

    @Override
    public String toString() {
        String ra;
        switch (this.resultado_aposta){
            case 0: ra = "1"; break;
            case 1: ra = "X"; break;
            case 2: ra = "2"; break;
            default: ra = ""; break;
        }
        double ganhos;
        ganhos = this.odd * this.quantia;
        return "id = " + this.id + ", jogo: " + this.evento.getEquipa_1() + " X " + this.evento.getEquipa_2() + ", aposta realizada = " + ra + ", odd = " + this.odd + ", quantia = " + this.quantia + " ESScoins" + ", possíveis ganhos = " + ganhos + " ESScoins, estado = " + estado();
    }
    
    private String estado(){
        if (this.resultado_evento == -1) return "em aberto";
        else{
            if (this.resultado_evento == this.resultado_aposta) return "aposta ganha";
            else if (this.resultado_evento == 0) return "aposta perdida (1)";
            else if (this.resultado_evento == 1) return "aposta perdida (X)";
            else if (this.resultado_evento == 2) return "aposta perdida (2)";
        }
        return "";
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getResultado_evento() {
        return resultado_evento;
    }

    public void setResultado_evento(Integer resultado_evento) {
        this.resultado_evento = resultado_evento;
    }

    public Integer getResultado_aposta() {
        return resultado_aposta;
    }

    public void setResultado_aposta(Integer resultado_aposta) {
        this.resultado_aposta = resultado_aposta;
    }

    public double getQuantia() {
        return quantia;
    }

    public void setQuantia(double quantia) {
        this.quantia = quantia;
    }
    
    public double getOdd() {
        return this.odd;
    }
    
    public void setOdd(double odd) {
        this.odd = odd;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }
    
    public int getIdEvento(){
        return this.evento.getId();
    }
}
