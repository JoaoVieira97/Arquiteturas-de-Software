package betess;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author João Vieira & Simão Barbosa
 */
public class Apostador extends Utilizador implements Serializable{
    
    private int idAposta;
    private String nome;
    private double saldo;
    private List<Aposta> apostas;

    public Apostador(String email, String password, String nome, double saldo){
        super(email,password,nome);
        this.saldo = saldo;
        this.apostas = new ArrayList<Aposta>();
        this.idAposta = 1;
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
    
    public int newAposta (Integer resultado_evento, Integer resultado_aposta, double quantia, double odd, Evento evento){
        if(saldo >= quantia){
            Aposta aposta = new Aposta(this.idAposta, resultado_evento, resultado_aposta, quantia, odd, evento);
            this.apostas.add(aposta);
            this.idAposta++;
            this.saldo -= quantia;
            return 0;
        }
        return 1;
    }

    public boolean saldoSufiente(double quantia) {
        if(saldo >= quantia) return true;
        else return false;
    }
    
    public void eventoTerminado(int idEvento, int resultado){
        for (Aposta aposta : this.apostas){
            if (aposta.getIdEvento() == idEvento){
                aposta.setResultado_evento(resultado);
                if (aposta.getResultado_evento() == aposta.getResultado_aposta())
                    this.saldo += aposta.getQuantia() * aposta.getOdd();
            }
        }
    }
}
