package betess_patterns.model;

import betess_patterns.model.composite.ApostaComponent;
import betess_patterns.model.composite.ConjuntoApostas;
import betess_patterns.model.factory.FactoryApostas;
import betess_patterns.model.strategy.StrategyApostasContext;
import betess_patterns.model.strategy.SortPossiveisGanhos;
import betess_patterns.model.strategy.SortGanhos;
import betess_patterns.model.strategy.SortValorApostado;
import betess_patterns.model.observer.Observer;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Apostador extends Utilizador implements Observer, Serializable{
    
    private double saldo;
    private ApostaComponent apostas;
    private FactoryApostas fabricaAp;
    private StrategyApostasContext sac;
    private List<String> notificacoes;
    
    public Apostador(String email, String password, String nome, double saldo){
        super(email,password,nome);
        this.saldo = saldo;
        this.apostas = new ConjuntoApostas();
        this.fabricaAp = new FactoryApostas();
        this.sac = new StrategyApostasContext();
        this.notificacoes = new ArrayList<>();
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public ApostaComponent getApostas() {
        return apostas;
    }

    public void setApostas(ApostaComponent apostas) {
        this.apostas = apostas;
    }

    public FactoryApostas getFabricaAp() {
        return fabricaAp;
    }

    public void setFabricaAp(FactoryApostas fabricaAp) {
        this.fabricaAp = fabricaAp;
    }
    
    public List<String> getNotificacoes(){
        return this.notificacoes;
    }
    
    public void cleanNotificacoes(){
        this.notificacoes.clear();
    }
    
    public void addNotificacao(String notificacao){
        this.notificacoes.add(notificacao);
    }
    
    public void update(int idEvento, int resultado){
        List<String> novas_notificacoes = new ArrayList<>();
        this.saldo+= this.getApostas().terminaEvento(idEvento, resultado, novas_notificacoes);
        novas_notificacoes.forEach(n -> this.notificacoes.add(n));
    }
    
    public void update(int idEvento, String equipa_1, String equipa_2, double valor){
        throw new UnsupportedOperationException("Not supported.");
    }
    
    public boolean saldoSufiente(double quantia) {
        if(saldo >= quantia) return true;
        else return false;
    }
    
    public void removeQuantia(double quantia){
        this.saldo -= quantia;
    }
    
    public ApostaComponent getApostas(int strategy){
        if (strategy == 1) this.sac.setStrategy(new SortPossiveisGanhos());
        else if (strategy == 2) this.sac.setStrategy(new SortGanhos());
        else this.sac.setStrategy(new SortValorApostado());
            
        this.sac.sortApostas((ConjuntoApostas) this.apostas);
        return this.apostas;
    }
    
    
    public void apostaSimples(int resultado_apostado ,double quantia, double odd, Evento evento){
        this.fabricaAp.factoryApSimples(this.apostas, resultado_apostado, quantia, odd, evento,this.getEmail());
    }
    
    public void apostaMultipla(double quantia, int[] resultados, Evento[] eventos){
        this.fabricaAp.factoryApMultiplas(this.apostas, quantia, resultados, eventos,this.getEmail());
    }

    @Override
    public void update(List<String> notificacoes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
