package betess_patterns;

import java.util.ArrayList;
import java.util.List;

public class ConjuntoApostas implements ApostaComponent{
    
    // id = -1 -> conjunto inicial de apostas
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
    
    public double terminaEvento(int idEvento, int resultado){

        double saldo=0;
        List<Double> resultados = new ArrayList<>() ;
        for (ApostaComponent ap :this.components ){
                resultados.add(ap.terminaEvento(idEvento, resultado));
        }
        if (this.id == -1){
            for (Double res : resultados) if(res>0) saldo+=res;
            return saldo;
        }
        else {
            
            for (Double res : resultados){
                if(res==-1) return -1 ; //perdeu aposta
                if(res==0) return 0 ; //aposta aberta
            }
        
            saldo = this.quantia * this.odd() ;
            return saldo;
        }
    }
    
    public double odd(){
        double odd = 1;
        for (ApostaComponent a : this.components){
            odd *= ((ApostaSimples) a).getOdd();
        }
        return odd;
    }
    
    public void show(){
        if (this.id != -1){
            System.out.println("\nid = " + this.id + ", quantia = " + this.quantia + ", odd = " + this.odd());
        }
        this.components.forEach(ApostaComponent::show);
    }
    
}
