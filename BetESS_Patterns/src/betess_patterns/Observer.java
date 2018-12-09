package betess_patterns;

import java.util.List;

public interface Observer {
    
    public void update(int idEvento, int resultado);
    
    public void update(int idEvento, String equipa_1, String equipa_2, double valor);

    public void update(List<String> notificacoes);
    
}
