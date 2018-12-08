package betess_patterns;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class ConjuntoApostas implements ApostaComponent, Serializable{
    
    // id = -1 -> conjunto inicial de apostas
    private int id;
    private double quantia;
    private List<ApostaComponent> components;
    // -1 -> perdida , 0-> em aberto , 1 -> ganha
    private int resultado_final;
    
    public ConjuntoApostas(){
        this.id = -1;
        this.components = new ArrayList<>();
        this.resultado_final = 0;
    }
    
    public ConjuntoApostas(int id,double quantia){
        this.id = id;
        this.quantia = quantia;
        this.components = new ArrayList<>();
        this.resultado_final = 0;

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
        for (ApostaComponent ap : this.components){
                resultados.add(ap.terminaEvento(idEvento, resultado));
        }
        if (this.id == -1){
            for (Double res : resultados) if (res > 0) saldo += res;
            return saldo;
        }
        else {
            for (Double res : resultados){
                if(res == -1){
                    this.resultado_final = -1; //perdeu aposta
                    return -1 ;
                } 
                if(res == 0) return 0 ; //aposta aberta
                if(res == -2) apostasAcabadas++; // apostaSimples ganha noutro evento
            }
            if (apostasAcabadas >= resultados.size()) return 0;
            saldo = this.quantia * this.odd();
            this.resultado_final = 1; // ganhou aposta
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
            String estado = "";
            switch (this.resultado_final){
                case -1:
                    estado = "aposta perdida";
                    break;
                case 0:
                    estado = "aposta em aberta";
                    break;
                case 1:
                    estado = "aposta ganha";
                    break;
            }
            System.out.println("\nid = " + this.id + ", odd = " + this.odd() + 
                               ", quantia = " + quantia +
                               " ESScoins, possíveis ganhos = " + this.possiveisGanhos() +
                               " ESScoins, estado = " + estado);
        }
        this.components.forEach(ApostaComponent::show);
    }

    @Override
    public double possiveisGanhos() {
        if (this.resultado_final != 0) return 0; // não há possiveis ganhos se o evento já terminou
        return this.odd() * this.quantia;
    }

    @Override
    public double ganhoOuPerda() {
        if (this.resultado_final == 0) return 0; // evento em aberto 
        if (this.resultado_final == 1) return  this.odd() * this.quantia;
        else return this.quantia * -1 ;
    }
    
}
