package betess_patterns;

import java.io.Serializable;

public class Apostador extends Utilizador implements Observer, Serializable{
    
    private double saldo;
    private ApostaComponent apostas;
    private FactoryApostas fabricaAp;
    private StrategyApostasContext sac;
    
    public Apostador(String email, String password, String nome, double saldo){
        super(email,password,nome);
        this.saldo = saldo;
        this.apostas = new ConjuntoApostas();
        this.fabricaAp = new FactoryApostas();
        this.sac = new StrategyApostasContext();
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
    
    public void update(int idEvento, int resultado){
        this.saldo+= this.getApostas().terminaEvento(idEvento, resultado);

    }
    
    public boolean saldoSufiente(double quantia) {
        if(saldo >= quantia) return true;
        else return false;
    }
    
    public void removeQuantia(double quantia){
        this.saldo -= quantia;
    }
    
    public void showApostas(int strategy){
        if (strategy == 1) this.sac.setStrategy(new SortPossiveisGanhos());
        else if (strategy == 2) this.sac.setStrategy(new SortGanhos());
        else this.sac.setStrategy(new SortValorApostado());
            
        this.sac.sortApostas((ConjuntoApostas) this.apostas);
        this.apostas.show();  
    }
    
    public void showApostas( ) {
        this.apostas.show();
    }
    
    public void apostaSimples(int resultado_apostado ,double quantia, double odd, Evento evento){
        this.fabricaAp.factoryApSimples(this.apostas, resultado_apostado, quantia, odd, evento);
    }
    
    public void apostaMultipla(double quantia, int[] resultados, Evento[] eventos){
        this.fabricaAp.factoryApMultiplas(this.apostas, quantia, resultados, eventos);
    }
    
}
