package betess_patterns;

import java.util.List;

public class Apostador extends Utilizador implements Observer{
    
    private double saldo;
    private ApostaComponent apostas;
    private FactoryApostas fabricaAp;
    
    public Apostador(String email, String password, String nome, double saldo){
        super(email,password,nome);
        this.saldo = saldo;
        this.apostas = new ConjuntoApostas();
        this.fabricaAp = new FactoryApostas(apostas);
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
    
    public void showApostas( StrategyA sortstrat ) {
        sortstrat.sort((ConjuntoApostas) this.apostas);
        this.apostas.show();
    }
    public void showApostas( ) {
        this.apostas.show();
    }
}
