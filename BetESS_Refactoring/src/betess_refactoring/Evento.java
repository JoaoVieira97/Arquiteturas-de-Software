package betess_refactoring;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author João Vieira & Simão Barbosa
 */
public class Evento implements Serializable{
    
    private int id;
    private String equipa_1;
    private String equipa_2;
    private double[] odds;
    private boolean disponibilidade;
    private List<String> apostadores;
    
    public Evento(int id, String equipa_1, String equipa_2, double odd_1, double odd_x, double odd_2, boolean disponibilidade){
        this.id = id;
        this.equipa_1 = equipa_1;
        this.equipa_2 = equipa_2;
        this.odds = new double[3];
        this.odds[0] = odd_1; this.odds[1] = odd_x; this.odds[2] = odd_2;
        this.disponibilidade = disponibilidade;
        this.apostadores = new ArrayList<>();
    }

    @Override
    public String toString() {
        String d = (this.disponibilidade) ? "disponível" : "indisponível";
        return "id = " + id + "      " + equipa_1 + "(" + odds[0] + ")  X(" + odds[1] + ")  " + equipa_2 + "(" + odds[2] + ") , " + d;
    }
    
    public void setDisponibilidade(boolean disponibilidade){
        this.disponibilidade = disponibilidade;
    }
    
    public void setOdds(double odd_1, double odd_x, double odd_2){
        this.odds[0] = odd_1; this.odds[1] = odd_x; this.odds[2] = odd_2;
    }

    public void setEquipa_1(String equipa_1){
        this.equipa_1 = equipa_1;
    }

    public void setEquipa_2(String equipa_2){
        this.equipa_2 = equipa_2;
    }

    public int getId() {
        return id;
    }

    public String getEquipa_1() {
        return equipa_1;
    }

    public String getEquipa_2() {
        return equipa_2;
    }

    public double[] getOdds() {
        return odds;
    }

    public boolean getDisponibilidade() {
        return disponibilidade;
    }

    void setId(int id) {
        this.id= id;
    }
    
    public List<String> getApostadores(){
        return this.apostadores;
    }
    
    public void addApostador(String email){
        this.apostadores.add(email);
    }
    
}
