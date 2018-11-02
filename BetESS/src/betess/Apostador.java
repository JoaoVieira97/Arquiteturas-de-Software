package betess;

import java.util.List;

/**
 *
 * @author João Vieira & Simão Barbosa
 */
public class Apostador extends Utilizador {
    
    private int idAposta;
    private String nome;
    private double saldo;
    private List<Aposta> apostas;

    public Apostador(String nome, double saldo, List<Aposta> apostas, String email, String password) {
        super(email, password);
        this.nome = nome;
        this.saldo = saldo;
        this.apostas = apostas;
        this.idAposta=0;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public List<Aposta> getApostas() {
        return apostas;
    }

    public void setApostas(List<Aposta> apostas) {
        this.apostas = apostas;
    }
    
    public int newAposta (Integer resultado_evento, Integer resultado_aposta, double quantia, Evento evento){
        if(saldo>=quantia){
            Aposta ap = new Aposta( idAposta, resultado_evento ,  resultado_aposta,  quantia,  evento);
            apostas.add(ap);
            idAposta++;
            saldo-=quantia;
            return 0;
        }
        return 1;
    }

    public boolean saldoSufiente(double quantia) {
        if(saldo>=quantia) return true;
        else return false;
    }
}
