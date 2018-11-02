package betess;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author João Vieira & Simão Barbosa
 */
public class BetESSView {
    Menus menus;
    public BetESSView() {
        this.menus= new Menus();
    }
    public Menus initView() {
            Opcao op1, op2, op3, op4, op5, op6, op7, op8;
	    op1 = new Opcao("Login .......... ", "L");
	    op2 = new Opcao("Registar ....... ", "R");      
	    List<Opcao> linhas = Arrays.asList(op1, op2);
	    Menu menuInicial = new Menu(linhas, "BetESS");        
	    menus.addMenu(1, menuInicial);
            
            
	    op1 = new Opcao("Ver Eventos .............. ", "L");
	    op2 = new Opcao("Ver minhas apostas ....... ", "R");
            op3 = new Opcao("Realizar nova aposta ......", "A");
	    List<Opcao> linhasApost = Arrays.asList(op1, op2,op3);
	    Menu menuApostador = new Menu(linhasApost, "Gestão Apostador");        
	    menus.addMenu(2, menuApostador);
            
            
            return menus;
            
    }

    public Menus getMenus() {
        return menus;
    }

    public void setMenus(Menus menus) {
        this.menus = menus;
    }

    public Menu menuEquipas(String equipa1, String equipa2) {
            Opcao op1, op2, op3;
	    op1 = new Opcao(equipa1, "0");
	    op2 = new Opcao(equipa2, "1");  
            op3 = new Opcao("Empate", "2");
	    List<Opcao> linhas = Arrays.asList(op1, op2,op3);
	    Menu menu = new Menu(linhas, "Aposta na equipa:");        
	    return menu;
            
    }
    
    
}
