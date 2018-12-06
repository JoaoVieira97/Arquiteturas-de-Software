package betess_patterns;

import java.util.ArrayList;
import java.util.List;

public class ConjuntoApostas implements ApostaComponent{
    
    private int id;
    private double quantia;
    private List<ApostaComponent> components;
    
    public ConjuntoApostas(){
        this.id = -1;
        this.components = new ArrayList<>();
    }
    
    public ConjuntoApostas(int id,double quantia){
        this.id = id;
        this.quantia = quantia;
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
