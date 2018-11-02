package betess;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author João Vieira & Simão Barbosa
 */
public abstract class Utilizador {
    
    private String email;
    private String password;
    
    public Utilizador(String email, String password){
        this.email = email;
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    
    public List<Aposta> getApostas() {
        return new ArrayList<>();
    }
}
