package betess;

/**
 *
 * @author João Vieira & Simão Barbosa
 */
public class Aposta {
    private int id;
    private Integer resultado_evento;// -1-aberto   0-vitoria equipa1, 1-vitoria equipa2, 2-empate 
    private Integer resultado_aposta; //0-vitoria equipa1, 1-vitoria equipa2, 2-empate
    private double quantia;
    private Evento evento;

    public Aposta(int id, Integer resultado_evento, Integer resultado_aposta, double quantia, Evento evento) {
        this.id = id;
        this.resultado_evento = resultado_evento;
        this.resultado_aposta = resultado_aposta;
        this.quantia = quantia;
        this.evento = evento;
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

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }
    
}
