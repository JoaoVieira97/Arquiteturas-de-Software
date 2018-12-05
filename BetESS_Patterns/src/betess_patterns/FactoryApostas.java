package betess_patterns;

import java.util.List;

class FactoryApostas {
    int idApostas;
    int idComposites;

    public FactoryApostas() {
        this.idApostas=0;
        this.idComposites=0;
    }
    
    public ApostaComponent factoryApSimples(int resultado_apostado ,double quantia, double odd, Evento evento){
        ApostaComponent apostasimples = new ApostaSimples(idApostas,-1 , resultado_apostado , quantia, odd , evento);
        idApostas++;
        return apostasimples;
    }
    public ApostaComponent factoryApMultiplas(double quantia,List<ApostaSimples> apostas){
        
        ApostaComponent apostamultipla = new ConjuntoApostas(idApostas,idComposites,quantia);
        for( ApostaSimples aposta : apostas){
            aposta.setIdComposite(idComposites);
            aposta.setQuantia(quantia); // provávelmente não é necessário
            apostamultipla.add(aposta);
        }
        idApostas++;
        idComposites++;
        return apostamultipla;
    }
}
