package betess_patterns;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class ConjuntoApostas implements ApostaComponent,Subject, Serializable{
    
    // id = -1 -> conjunto inicial de apostas
    private int id;
    private double quantia;
    private List<ApostaComponent> components;
    // -1 -> perdida , 0-> em aberto , 1 -> ganha
    private int resultado_final;
    private String emailApostador;
    private List<Observer> observers;
    
    public ConjuntoApostas(){
        this.id = -1;
        this.components = new ArrayList<>();
        this.resultado_final = 0;
        this.observers= new ArrayList<>();
    }
    
    public ConjuntoApostas(int id,double quantia,String email){
        this.id = id;
        this.quantia = quantia;
        this.components = new ArrayList<>();
        this.resultado_final = 0;
        this.emailApostador=email;
        this.observers= new ArrayList<>();

    }

    public List<ApostaComponent> getComponents() {
        return components;
    }
    
    public int getId(){
        return this.id;
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
    
 public double terminaEvento(int idEvento, int resultado, List<String> notificacoes){
        boolean apostaAberta= false;
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
                if(res == -1 || res ==-3){ //perdeu aposta
                    this.resultado_final = -1;
                    if (res==-1) {
                        notificacoes.add("Perdeu a aposta múltipla com o id " + this.id
                                     + ", dado o terminar do evento " + idEvento
                                     + ", na qual apostou " + this.quantia + " ESScoins");
                        this.notifyObservers(notificacoes);
                    }
                    return -1 ;
                } 
                if(res == -4){//aposta aberta
                    apostaAberta= true;
                }
                if(res == -2) apostasAcabadas++; // apostaSimples ganha noutro evento
            }
            if (apostasAcabadas >= resultados.size()) return 0;
            if (apostaAberta==true){
                  boolean temId = false;
                   for (ApostaComponent ap : this.components){
                        if (((ApostaSimples) ap).getEvento().getId() == idEvento   )temId = true;
                   }
                   if (temId){ 
                       notificacoes.add("Com o fim do evento " + idEvento + ", a aposta múltipla com o id "
                                                + this.id + " continua em aberto");
                       this.notifyObservers(notificacoes);
                   }
                   return 0;
            }
            saldo = this.quantia * this.odd();
            this.resultado_final = 1; // ganhou aposta
            notificacoes.add("Ganhou a aposta múltipla com o id " + this.id
                             + ", com o terminar do evento " + idEvento
                             + ", o seu saldo foi incrementado em " + saldo + " ESScoins");
            this.notifyObservers(notificacoes);
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

    @Override
    public String emailApostador() {
        return this.emailApostador;
    }

    public void registerObserver(Observer o){
        if(!this.observers.contains(o))
            this.observers.add(o);
    }
    
    
    public void removeObserver(Observer o){
        this.observers.remove(o);
    }
    
    public void notifyObservers(List<String> notificacoes){
        List<String> n = new ArrayList<>();
        for (String noti : notificacoes)
            n.add(this.emailApostador + ": " + noti);
        for (Observer o : this.observers)
            if (o instanceof Funcionario) o.update(n);
    }

    @Override
    public void notifyObservers(int idEvento, int resultado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
