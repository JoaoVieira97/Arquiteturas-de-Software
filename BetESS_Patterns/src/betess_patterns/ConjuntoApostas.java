package betess_patterns;

import java.util.ArrayList;
import java.util.List;

public class ConjuntoApostas implements ApostaComponent{
    
    private int id;
    private int idComposite;
    private double quantia;
    private int resultado_apostado;
    private List<ApostaComponent> components;
    
    public ConjuntoApostas(){
        this.components = new ArrayList<>();
    }
    
    public ConjuntoApostas(int id, int idComposite,double quantia){
        this.id = id;
        this.idComposite = idComposite;
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
