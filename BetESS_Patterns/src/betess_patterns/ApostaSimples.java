package betess_patterns;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ApostaSimples implements ApostaComponent,Subject,Serializable{
    
    // se id = -1 -> aposta faz parte de uma múltipla
    private int id;
    private double quantia;
    private double odd;
    // 0 - vitória equipa_1, 1 - empate, 2 - vitória equipa_2
    private int resultado_apostado;
    // -1 - aberto, 0 - vitória equipa_1, 1 - empate, 2 - vitória equipa_2
    private int resultado_final;
    private Evento evento;
    private String emailApostador;
    private List<Observer> observers;
    
    public ApostaSimples(int id, int resultado_apostado, double quantia, double odd, Evento evento, String email) {
        this.id = id;
        this.resultado_apostado = resultado_apostado;
        this.resultado_final = -1;
        this.quantia = quantia;
        this.odd = odd;
        this.evento = evento;
        this.emailApostador=email;
        this.observers= new ArrayList<>();
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
    
    public double terminaEvento(int idEvento, int resultado_evento, List<String> notificacoes){
        double saldo=0;
        if (resultado_final!=-1){
                if (this.getResultado_final() == this.getResultado_apostado()) return -2; // evento já terminado  e aposta foi ganha
                else return -3; // evento terminado e aposta perdida
            
        }
        if (this.evento.getId() == idEvento){
                this.setResultado_final(resultado_evento);
                if (this.getResultado_final() == this.getResultado_apostado()){
                    saldo += this.getQuantia() * this.getOdd(); // retorna quantia a aumentar ao saldo
                    if (this.id != -1){
                        notificacoes.add("Ganhou a aposta com o id " + this.id
                                         + ", respetiva ao evento " + this.evento.getEquipa_1()
                                         + " X " + this.evento.getEquipa_2()
                                         + ", o seu saldo foi incrementado em " + saldo + " ESScoins");
                        this.evento.changeBalanco(-saldo);
                        this.notifyObservers(notificacoes);
                    }
                    return saldo;
                }
                else{
                    if (this.id != -1){
                    notificacoes.add("Perdeu a aposta com o id " + this.id
                                     + ", respetiva ao evento " + this.evento.getEquipa_1()
                                     + " X " + this.evento.getEquipa_2()
                                     + ", na qual apostou " + this.quantia + " ESScoins");
                    this.notifyObservers(notificacoes);
                    }
                    return -1;
                } //perdeu aposta
        } 
        return -4 ; // aposta aberta
    }

    public void show(){
        String ra;
        switch(this.resultado_apostado){
            case 0: ra = "1"; break;
            case 1: ra = "X"; break;
            case 2: ra = "2"; break;
            default: ra = ""; break;
        }
        if (this.id != -1){
            double ganhos = this.odd * this.quantia;
            System.out.println("\nid = " + this.id + ", jogo: " + this.evento.getEquipa_1()
                                + " X " + this.evento.getEquipa_2() + ", aposta realizada = " + ra
                                + ", odd = " + this.odd + ", quantia = " + this.quantia + " ESScoins"
                                + ", possíveis ganhos = " + ganhos + " ESScoins, estado = " + estado());
        }
        else{
            System.out.println("\tJogo: " + this.evento.getEquipa_1() + " X " + this.evento.getEquipa_2() 
                                + ", aposta realizada = " + ra + ", odd = " + this.odd + ", estado = " + estado());
        }
    }
    
    private String estado(){
        if (this.resultado_final == -1) return "em aberto";
        else{
            if (this.resultado_apostado == this.resultado_final) return "aposta ganha";
            else if (this.resultado_final == 0) return "aposta perdida (1)";
            else if (this.resultado_final == 1) return "aposta perdida (X)";
            else if (this.resultado_final == 2) return "aposta perdida (2)";
        }
        return "";
    }

    @Override
    public double possiveisGanhos() {
        if (this.resultado_final!=-1) return 0; // não há possiveis ganhos se o evento já terminou
        return this.odd*this.quantia;
    }

    @Override
    public double ganhoOuPerda() {
        if (this.resultado_final==-1) return 0; // evento em aberto 
        if (this.resultado_final == this.resultado_apostado) return this.odd *this.quantia;
        else return this.quantia * -1 ;
    }

    @Override
    public String emailApostador() {
        return this.emailApostador;
    }

    public void registerObserver(Observer o){
        if(!this.observers.contains(o))
            this.observers.add(o);
    }
    
    
    public void removeObserver(Observer o){
        this.observers.remove(o);
    }
    
    public void notifyObservers(List<String> notificacoes){
        List<String> n = new ArrayList<>();
        for (String noti : notificacoes)
            n.add(this.emailApostador + ": " + noti);
        for (Observer o : this.observers)
            if (o instanceof Funcionario) o.update(n);
    }

    @Override
    public void notifyObservers(int idEvento, int resultado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
