package betess_patterns;

public interface ApostaComponent {
    
    public void terminaEvento();
    
    public void add(ApostaComponent a);
    
    public void remove(ApostaComponent a);
    
    public void getChild(int i);
    
}
