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

public class Opcao {
	private String op;
	private String letra;

	public Opcao(String op, String letra) {
		this.op = op;
		this.letra = letra;
	}
	public String getOp() {
		return op;
	}
	public void setOp(String op) {
		this.op = op;
	}
	public String getLetra() {
		return letra;
	}
	public void setLetra(String letra) {
		this.letra = letra;
	}
	
}
