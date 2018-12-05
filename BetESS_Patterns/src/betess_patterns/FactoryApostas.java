package betess_patterns;

import java.util.List;

class FactoryApostas {
    int idApostas;
    int idComposites;
    ApostaComponent todasApostas;
    public FactoryApostas(ApostaComponent todasApostas) {
        this.idApostas=0;
        this.idComposites=0;
        this.todasApostas = todasApostas;
    }
    
    public void factoryApSimples(int resultado_apostado ,double quantia, double odd, Evento evento){
        ApostaComponent apostasimples = new ApostaSimples(idApostas,-1 , resultado_apostado , quantia, odd , evento);
        idApostas++;
        this.todasApostas.add(apostasimples);
        
    }
    public void factoryApMultiplas(double quantia,List<ApostaSimples> apostas){
        
        ApostaComponent apostamultipla = new ConjuntoApostas(idApostas,idComposites,quantia);
        for( ApostaSimples aposta : apostas){
            aposta.setIdComposite(idComposites);
            aposta.setQuantia(quantia); // provávelmente não é necessário
            apostamultipla.add(aposta);
        }
        idApostas++;
        idComposites++;
        this.todasApostas.add(apostamultipla);
    }
}
