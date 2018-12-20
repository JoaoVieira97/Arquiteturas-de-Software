package betess_refactoring;

import java.io.Serializable;

/**
 *
 * @author João Vieira & Simão Barbosa
 */
public class Funcionario extends Utilizador implements Serializable{
    
    public Funcionario(String email, String password, String nome){
        super(email, password, nome);
    }
    
}