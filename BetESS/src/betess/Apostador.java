package betess;

/**
 *
 * @author João Vieira & Simão Barbosa
 */
public class Apostador extends Utilizador {
    
    private String nome;
    private double saldo;
    
    public Apostador(String email, String password, String nome, double saldo){
        super(email, password);
        this.nome = nome;
        this.saldo = saldo;
    }
    
}
