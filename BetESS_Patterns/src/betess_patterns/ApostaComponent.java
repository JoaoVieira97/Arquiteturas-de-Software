package betess_patterns;

public interface ApostaComponent {
    
    public void show();
    
    public double terminaEvento(int idEvento, int resultado);
    
    public void add(ApostaComponent a);
    
    public void remove(ApostaComponent a);
    
    public void getChild(int i);
    
    public double getQuantia();
    
    public double possiveisGanhos();
    
    public double ganhoOuPerda();
    
}
