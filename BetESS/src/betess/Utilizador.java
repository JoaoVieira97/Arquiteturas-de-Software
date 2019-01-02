/**
 *
 * @author João Vieira & Simão Barbosa
 */
package betess;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Utilizador implements Serializable{
    
    private String email;
    private String password;
    private String nome;
    
    public Utilizador(String email, String password, String nome){
        this.email = email;
        this.password = password;
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }
    
    public String getNome() {
        return this.nome;
    }
}
