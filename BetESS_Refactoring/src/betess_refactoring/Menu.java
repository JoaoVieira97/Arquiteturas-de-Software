/**
 *
 * @author João Vieira & Simão Barbosa
 */
package betess_refactoring;

import java.util.List;

public class Menu {
	
	private List<Opcao> linhas;
	private String titulo;
	
	public Menu(List<Opcao> linhas, String titulo) {
            //super();
            this.linhas = linhas;
            this.titulo = titulo;
	}
        
	public List<Opcao> getLinhas() {
            return linhas;
	}
        
	public void setLinhas(List<Opcao> linhas) {
            this.linhas = linhas;
	}
        
	public String getTitulo() {
            return titulo;
	}
        
	public void setTitulo(String titulo) {
            this.titulo = titulo;
	}
        
	public void show() {
            System.out.println("--------------------------------------------");
            System.out.println("          " + this.titulo);
            System.out.println("--------------------------------------------");
            for (Opcao o : this.linhas)
               System.out.println(o.getOpcao() + " " + o.getLetra());
            System.out.println("          Introduza a sua opção:");
	}
}