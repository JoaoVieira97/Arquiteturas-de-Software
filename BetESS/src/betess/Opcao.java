package betess;

/**
 *
 * @author João Vieira & Simão Barbosa
 */

public class Opcao {
    
	private String opcao;
	private String letra;

	public Opcao(String opcao, String letra) {
            this.opcao = opcao;
            this.letra = letra;
	}
        
	public String getOpcao() {
            return opcao;
	}
	
        public void setOpcao(String opcao) {
            this.opcao = opcao;
	}
	
        public String getLetra() {
            return letra;
	}
	
        public void setLetra(String letra) {
            this.letra = letra;
	}
	
}
