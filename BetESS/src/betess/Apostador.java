package betess;

import java.util.List;

/**
 *
 * @author João Vieira & Simão Barbosa
 */
public class Apostador extends Utilizador {
    
    private String nome;
    private double saldo;
    private List<Aposta> apostas;

    public Apostador(String nome, double saldo, List<Aposta> apostas, String email, String password) {
        super(email, password);
        this.nome = nome;
        this.saldo = saldo;
        this.apostas = apostas;
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
    
}
