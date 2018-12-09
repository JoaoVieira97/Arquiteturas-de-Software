package betess_patterns;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class Evento implements Subject, Serializable{
    
    private int id;
    private String equipa_1;
    private String equipa_2;
    private String competicao;
    private double[] odds;
    private boolean disponibilidade;
    private int num_apostas;
    private List<Observer> observers;
    private double balanco;
    
    public Evento(int id, String equipa_1, String equipa_2, String competicao, double odd_1, double odd_x, double odd_2, boolean disponibilidade, int num_apostas){
        this.id = id;
        this.equipa_1 = equipa_1;
        this.equipa_2 = equipa_2;
        this.competicao = competicao;
        this.odds = new double[3];
        this.odds[0] = odd_1; this.odds[1] = odd_x; this.odds[2] = odd_2;
        this.disponibilidade = disponibilidade;
        this.observers = new ArrayList<>();
        this.num_apostas = num_apostas;
        this.balanco = 0;
    }
    
    @Override
    public String toString() {
        String d = (this.disponibilidade) ? "disponível" : "indisponível";
        return "id = " + id + "      " + equipa_1 + "(" + odds[0] + ")  X(" + odds[1] + ")  " + equipa_2 + "(" + odds[2] + ") , " + competicao + ", " + d + ", nºapostas = " + num_apostas;
    }
    
    public void registerObserver(Observer o){
        if(!this.observers.contains(o))
            this.observers.add(o);
    }
    
    
    public void removeObserver(Observer o){
        this.observers.remove(o);
    }
    
    public void notifyObservers(int idEvento, int resultado){
        for (Observer o : this.observers)
            if (o instanceof Apostador) o.update(idEvento, resultado);
        for (Observer o : this.observers)
            if (o instanceof Funcionario) o.update(idEvento, this.equipa_1, this.equipa_2, this.balanco);
    }
    
    public void changeBalanco(double quantia){
        this.balanco += quantia;
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

    public void setOdds(double odd_1, double odd_x, double odd_2){
        this.odds[0] = odd_1; this.odds[1] = odd_x; this.odds[2] = odd_2;
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

    public List<Observer> getObservers() {
        return this.observers;
    }

    public void setObservers(List<Observer> observers) {
        this.observers = observers;
    }

    void incNumApostas() {
        this.num_apostas++;
    }
    
}
