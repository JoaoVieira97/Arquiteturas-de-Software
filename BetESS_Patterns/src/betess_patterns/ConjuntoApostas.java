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
    
    /*
    public double terminaEvento(int idEvento, int resultado, List<String> notificacoes){

        double saldo=0;
        int apostasAcabadas=0;
        List<Double> resultados = new ArrayList<>();
        for (ApostaComponent ap : this.components){
                resultados.add(ap.terminaEvento(idEvento, resultado, notificacoes));
        }
        if (this.id == -1){
            for (Double res : resultados) if (res > 0) saldo += res;
            return saldo;
        }
        else {
            for (Double res : resultados){
                if(res == -1){ //perdeu aposta
                    this.resultado_final = -1;
                    notificacoes.add("Perdeu a aposta múltipla com o id " + this.id
                                     + ", dado o terminar do evento " + idEvento
                                     + ", na qual apostou " + this.quantia + " ESScoins");
                    return -1 ;
                } 
                if(res == 0){//aposta aberta
                    boolean temId = false;
                    for (ApostaComponent ap : this.components){
                        if (((ApostaSimples) ap).getId() == idEvento) temId = true;
                    }
                    if (temId) notificacoes.add("Com o fim do evento " + idEvento + ", a aposta múltipla com o id "
                                                + this.id + " continua em aberto");
                    return 0 ;
                }
                if(res == -2) apostasAcabadas++; // apostaSimples ganha noutro evento
            }
            if (apostasAcabadas >= resultados.size()) return 0;
            saldo = this.quantia * this.odd();
            this.resultado_final = 1; // ganhou aposta
            notificacoes.add("Ganhou a aposta múltipla com o id " + this.id
                             + ", com o terminar do evento " + idEvento
                             + ", o seu saldo foi incrementado em " + saldo + " ESScoins");
            return saldo;
        }
    }
*/
    
    public double terminaEvento(int idEvento, int resultado, List<String> notificacoes){
        double inc = 0;
        List<Double> resultados = new ArrayList<>();
        for (ApostaComponent ap : this.components){
                resultados.add(ap.terminaEvento(idEvento, resultado, notificacoes));
        }
        if (this.id != -1){ // aposta múltipla
            int certo = 0; int errado = 0;
            for (double r : resultados){
                if (r == 0) certo++;
                if (r == -4) errado++;
            }
            if (certo == resultados.size()){ //ganhou multipla
                inc = this.quantia * this.odd();
                notificacoes.add("Ganhou a aposta múltipla com o id " + this.id
                                 + ", com o terminar do evento " + idEvento
                                 + ", o seu saldo foi incrementado em " + inc + " ESScoins");
                return inc;
            }
            if (errado > 0){ //perdeu multipla
                notificacoes.add("Perdeu a aposta múltipla com o id " + this.id
                                 + ", dado o terminar do evento " + idEvento
                                 + ", na qual apostou " + this.quantia + " ESScoins");
                return 0;
            }
            if (certo > 0){ //continua em aberto a multipla mas acertou no evento
                boolean temId = false;
                for (ApostaComponent ap : this.components){
                    if (((ApostaSimples) ap).getId() == idEvento) temId = true;
                }
                notificacoes.add("Com o fim do evento " + idEvento + ", a aposta múltipla com o id "
                                 + this.id + " continua em aberto");
                return 0;
            }
            else return 0;
        }
        else{ // conjunto de apostas
            for (double r : resultados){
                if (r > 0) inc += r;
            }
            return inc;
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
