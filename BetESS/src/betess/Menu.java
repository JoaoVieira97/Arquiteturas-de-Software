/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package betess;

/**
 *
 * @author Utilizdor
 */
import java.util.List;

public class Menu {
	
	private List<Opcao> linhas;
	private String titulo;
	
	public Menu(List<Opcao> linhas, String titulo) {
		super();
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
		System.out.println(titulo);
		for(Opcao a: linhas) {
			System.out.println(a.getOp()+" "+a.getLetra());
		}
		System.out.println("     Introduza a sua opção:");
	}
}
