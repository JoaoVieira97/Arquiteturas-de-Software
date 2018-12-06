package betess_patterns;

public class ApostaSimples implements ApostaComponent{
    
    private int id;
    private double quantia;
    private double odd;
    // 0 - vitória equipa_1, 1 - empate, 2 - vitória equipa_2
    private int resultado_apostado;
    // -1 - aberto, 0 - vitória equipa_1, 1 - empate, 2 - vitória equipa_2
    private int resultado_final;
    private Evento evento;
    
    public ApostaSimples(int id, int resultado_apostado, double quantia, double odd, Evento evento) {
        this.id = id;
        this.resultado_apostado = resultado_apostado;
        this.resultado_final = -1;
        this.quantia = quantia;
        this.odd = odd;
        this.evento = evento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getQuantia() {
        return quantia;
    }

    public void setQuantia(double quantia) {
        this.quantia = quantia;
    }

    public double getOdd() {
        return odd;
    }

    public void setOdd(double odd) {
        this.odd = odd;
    }

    public int getResultado_apostado() {
        return resultado_apostado;
    }

    public void setResultado_apostado(int resultado_apostado) {
        this.resultado_apostado = resultado_apostado;
    }

    public int getResultado_final() {
        return resultado_final;
    }

    public void setResultado_final(int resultado_final) {
        this.resultado_final = resultado_final;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }
    
    public void add(ApostaComponent a){
        throw new UnsupportedOperationException("Not supported.");
    }

    public void remove(ApostaComponent a){
        throw new UnsupportedOperationException("Not supported.");
    }

    public void getChild(int i){
        throw new UnsupportedOperationException("Not supported.");
    }
    
    public void terminaEvento(){
        System.out.println("To be implemented.");
    }
}
