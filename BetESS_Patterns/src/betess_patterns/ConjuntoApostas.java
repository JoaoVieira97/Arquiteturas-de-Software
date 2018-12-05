package betess_patterns;

import java.util.ArrayList;
import java.util.List;

public class ConjuntoApostas implements ApostaComponent{
    
    private int id;
    private double quantia;
    private int resultado_apostado;
    private List<ApostaComponent> components;
    
    public ConjuntoApostas(){
        this.components = new ArrayList<>();
    }
    
    public ConjuntoApostas(int id, double quantia, int resultado_apostado){
        this.id = id;
        this.quantia = quantia;
        this.resultado_apostado = resultado_apostado;
        this.components = new ArrayList<>();
    }
    
    public void add(ApostaComponent a){
        this.components.add(a);
    }
    
    public void remove(ApostaComponent a){
        this.components.remove(a);
    }
    
    public void getChild(int i){
        this.components.get(i);
    }
    
    public void terminaEvento(){
        System.out.println("To be implemented");
    }
    
}
