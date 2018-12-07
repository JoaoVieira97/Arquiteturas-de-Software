package betess_patterns;

import java.io.Serializable;

class FactoryApostas implements Serializable{
    
    private int id_proximaAposta;
    private ApostaComponent todasApostas;
    
    public FactoryApostas(ApostaComponent todasApostas) {
        this.id_proximaAposta = 1;
        this.todasApostas = todasApostas;
    }
    
    public void factoryApSimples(int resultado_apostado ,double quantia, double odd, Evento evento){
        ApostaComponent apostasimples = new ApostaSimples(id_proximaAposta, resultado_apostado , quantia, odd , evento);
        id_proximaAposta++;
        this.todasApostas.add(apostasimples);
        
    }
    
    public void factoryApMultiplas(double quantia, int[] resultados, Evento[] eventos){
        ApostaComponent apostamultipla = new ConjuntoApostas(id_proximaAposta, quantia);
        int length = resultados.length;
        for (int i=0; i<length; i++){
            apostamultipla.add(new ApostaSimples(-1, resultados[i], quantia, eventos[i].getOdds()[resultados[i]], eventos[i]));
        }
        id_proximaAposta++;
        this.todasApostas.add(apostamultipla);
    }
}
