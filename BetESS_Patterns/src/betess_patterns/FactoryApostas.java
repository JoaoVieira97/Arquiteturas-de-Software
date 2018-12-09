package betess_patterns;

import java.io.Serializable;

class FactoryApostas implements Serializable{
    
    private int id_proximaAposta;
    
    public FactoryApostas() {
        this.id_proximaAposta = 1;
    }
    
    public void factoryApSimples(ApostaComponent apostas, int resultado_apostado ,double quantia, double odd, Evento evento,String email){
        ApostaComponent apostasimples = new ApostaSimples(id_proximaAposta, resultado_apostado , quantia, odd , evento,email);
        id_proximaAposta++;
        apostas.add(apostasimples);
        
    }
    
    public void factoryApMultiplas(ApostaComponent apostas, double quantia, int[] resultados, Evento[] eventos,String email){
        ApostaComponent apostamultipla = new ConjuntoApostas(id_proximaAposta, quantia,email);
        int length = resultados.length;
        for (int i=0; i<length; i++){
            apostamultipla.add(new ApostaSimples(-1, resultados[i], quantia, eventos[i].getOdds()[resultados[i]], eventos[i],email));
        }
        id_proximaAposta++;
        apostas.add(apostamultipla);
    }
    
}
