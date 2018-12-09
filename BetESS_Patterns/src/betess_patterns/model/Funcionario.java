package betess_patterns.model;

import betess_patterns.model.observer.Observer;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Funcionario extends Utilizador implements Observer, Serializable{
    
    private List<String> notificacoes;
    
    public Funcionario(String email, String password, String nome){
        super(email, password, nome);
        this.notificacoes = new ArrayList<String>();
    }
    
    public void update(int idEvento, int resultado){
        throw new UnsupportedOperationException("Not supported.");
    }
    
    public void update(int idEvento, String equipa_1, String equipa_2, double valor){
       notificacoes.add("O evento com o id " + idEvento + ", entre " + equipa_1 + " e "
                        + equipa_2 + ", gerou um balanço de " + valor + " ESScoins");
    }
    
    public List<String> getNotificacoes(){
        return this.notificacoes;
    }
    
    public void cleanNotificacoes(){
        this.notificacoes.clear();
    }

    public void update(List<String> notificacoes) {
        notificacoes.forEach(n->this.notificacoes.add(n));
    }
    
}
