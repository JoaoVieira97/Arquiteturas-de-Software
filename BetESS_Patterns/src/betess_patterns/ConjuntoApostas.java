package betess_patterns;

import java.util.ArrayList;
import java.util.List;

public class ConjuntoApostas implements ApostaComponent{
    
    // id = -1 -> conjunto inicial de apostas
    private int id;
    private double quantia;
    private List<ApostaComponent> components;
    private int resultadoAposta ;
    // -1 -> perdida , 0-> em aberto , 1 -> ganha
    public ConjuntoApostas(){
        this.id = -1;
        this.components = new ArrayList<>();
        resultadoAposta = 0;
    }
    
    public ConjuntoApostas(int id,double quantia){
        this.id = id;
        this.quantia = quantia;
        this.components = new ArrayList<>();
        resultadoAposta = 0;

    }

    public List<ApostaComponent> getComponents() {
        return components;
    }
    
    public double getQuantia(){
        return this.quantia;
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
        int apostasAcabadas=0;
        List<Double> resultados = new ArrayList<>();
        for (ApostaComponent ap :this.components ){
                resultados.add(ap.terminaEvento(idEvento, resultado));
        }
        if (this.id == -1){
            for (Double res : resultados) if(res>0) saldo+=res;
            return saldo;
        }
        else {
            
            for (Double res : resultados){
                if(res==-1){
                    resultadoAposta = -1;//perdeu aposta
                    return -1 ;
                } 
                if(res==0) return 0 ; //aposta aberta
                if(res==-2) apostasAcabadas++; // apostaSimples ganha noutro evento
            }
            if (apostasAcabadas>=resultados.size()) return 0;
            saldo = this.quantia * this.odd();
            resultadoAposta= 1; // ganhou aposta
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

    @Override
    public double possiveisGanhos() {
        if (this.resultadoAposta!=0) return 0; // não há possiveis ganhos se o evento já terminou
        return this.odd() * this.quantia;
    }

    @Override
    public double ganhoOuPerda() {
        if (this.resultadoAposta==0) return 0; // evento em aberto 
        if (this.resultadoAposta==1) return  this.odd() * this.quantia;
        else return this.quantia * -1 ;    }
    
}
