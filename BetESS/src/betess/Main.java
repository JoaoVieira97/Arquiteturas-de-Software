package betess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author João Vieira & Simão Barbosa
 */
public class Main {
    
    public static void main(String[] args){
        
        BetESSModel model = new BetESSModel();
        
        //Apostador para teste
        List<Aposta> apostas = new ArrayList<>();
        Evento evento = new Evento(0, "equipa_1","equipa_2", 2, 2, 4, true);
        model.addEvento(evento);
    //    Aposta aposta = new Aposta(0, 0, 0, 100, evento );
     //   apostas.add(aposta);
        Apostador ap = new Apostador("nome",9999,apostas,"email","password");
        Map<String,Utilizador> utilizadores = new HashMap<>();
        utilizadores.put("email", ap);
        model.setUtilizadores(utilizadores);
        
        BetESSView view = new BetESSView();

        BetESSController control = new BetESSController();
        control.setModel(model);
        control.setView(view);

        control.startFlow();
    }
    
}
