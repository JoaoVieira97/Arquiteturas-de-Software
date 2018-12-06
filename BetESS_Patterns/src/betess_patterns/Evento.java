package betess_patterns;

import java.util.ArrayList;
import java.util.List;

public class Evento implements Subject{
    
    private int id;
    private String equipa_1;
    private String equipa_2;
    private String competicao;
    private double[] odds;
    private boolean disponibilidade;
    private int num_apostas;
    private List<Observer> apostadores;
    
    public Evento(int id, String equipa_1, String equipa_2, String competicao, double odd_1, double odd_x, double odd_2, boolean disponibilidade, int num_apostas){
        this.id = id;
        this.equipa_1 = equipa_1;
        this.equipa_2 = equipa_2;
        this.competicao = competicao;
        this.odds = new double[3];
        this.odds[0] = odd_1; this.odds[1] = odd_x; this.odds[2] = odd_2;
        this.disponibilidade = disponibilidade;
        this.apostadores = new ArrayList<>();
        this.num_apostas = num_apostas;
    }
    
    @Override
    public String toString() {
        String d = (this.disponibilidade) ? "disponível" : "indisponível";
        return "id = " + id + "      " + equipa_1 + "(" + odds[0] + ")  X(" + odds[1] + ")  " + equipa_2 + "(" + odds[2] + ") , " + competicao + ", " + d + ", nºapostas = " + num_apostas;
    }
    
    public void registerObserver(Observer o){
        this.apostadores.add(o);
    }
    
    public void removeObserver(Observer o){
        this.apostadores.remove(o);
    }
    
    public void notifyObservers(){
        System.out.println("To implement");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEquipa_1() {
        return equipa_1;
    }

    public void setEquipa_1(String equipa_1) {
        this.equipa_1 = equipa_1;
    }

    public String getEquipa_2() {
        return equipa_2;
    }

    public void setEquipa_2(String equipa_2) {
        this.equipa_2 = equipa_2;
    }
    
    public String getCompeticao(){
        return competicao;
    }
    
    public void setCompeticao(String competicao){
        this.competicao = competicao;
    }

    public double[] getOdds() {
        return odds;
    }

    public void setOdds(double[] odds) {
        this.odds = odds;
    }

    public boolean getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public int getNum_apostas() {
        return num_apostas;
    }

    public void setNum_apostas(int num_apostas) {
        this.num_apostas = num_apostas;
    }

    public List<Observer> getApostadores() {
        return apostadores;
    }

    public void setApostadores(List<Observer> apostadores) {
        this.apostadores = apostadores;
    }
    
}
