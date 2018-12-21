package betess_refactoring;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author João Vieira & Simão Barbosa
 */
public class Apostador extends Utilizador implements Serializable{
    
    private int idAposta;
    private double saldo;
    private List<Aposta> apostas;
    private List<String> notificacoes;

    public Apostador(String[] dados, double saldo){
        super(dados[0],dados[1],dados[2]);
        this.saldo = saldo;
        this.apostas = new ArrayList<Aposta>();
        this.idAposta = 1;
        this.notificacoes = new ArrayList<String>();
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
    
    public List<String> getNotificacoes(){
        return this.notificacoes;
    }
    
    public void cleanNotificacoes(){
        this.notificacoes.clear();
    }
    
    public int newAposta (Integer[] resultados, double[] quantiaOdd, Evento evento){
        if(saldo >= quantiaOdd[0]){
            Aposta aposta = new Aposta(this.idAposta, resultados,quantiaOdd, evento);
            this.apostas.add(aposta);
            this.idAposta++;
            this.saldo -= quantiaOdd[0];
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
                if (ganhouAposta(aposta)){
                    this.saldo += ganhosAposta(aposta);
                    adicionaNotificacaoVitoria(aposta);
                }
                else{
                    adicionaNotificacaoDerrota(aposta);
                }
            }
        }
    }
    
    public boolean ganhouAposta(Aposta aposta){
        return aposta.getResultado_evento() == aposta.getResultado_aposta();
    }
    
    public double ganhosAposta(Aposta aposta){
        return aposta.getQuantia() * aposta.getOdd();
    }
    
    public void adicionaNotificacaoVitoria(Aposta aposta){
        this.notificacoes.add("Ganhou a aposta com o id " + aposta.getId()
                              + ", respetiva ao evento " + aposta.getEquipa_1Evento()
                              + " X " + aposta.getEquipa_2Evento()
                              + ", o seu saldo foi incrementado em " + aposta.getQuantia() * aposta.getOdd() + " ESScoins");
    }
    
    public void adicionaNotificacaoDerrota(Aposta aposta){
        this.notificacoes.add("Perdeu a aposta com o id " + aposta.getId()
                              + ", respetiva ao evento " + aposta.getEquipa_1Evento()
                              + " X " + aposta.getEquipa_2Evento()
                              + ", na qual apostou " + aposta.getQuantia() + " ESScoins");
    }
    
}

