package betess_patterns;

public class Apostador extends Utilizador implements Observer{
    
    private int id_proximaAposta;
    private double saldo;
    private ApostaComponent apostas;
    
    public Apostador(String email, String password, String nome, double saldo){
        super(email,password,nome);
        this.saldo = saldo;
        this.apostas = new ConjuntoApostas();
        this.id_proximaAposta = 1;
    }

    public int getId_proximaAposta() {
        return id_proximaAposta;
    }

    public void setId_proximaAposta(int id_proximaAposta) {
        this.id_proximaAposta = id_proximaAposta;
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
    
    public void update(){
        System.out.println("To implement");
    }
    
}
