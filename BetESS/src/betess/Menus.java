/**
 *
 * @author João Vieira & Simão Barbosa
 */
package betess;

import java.util.Map;
import java.util.HashMap;

public class Menus {
	
	private Map<Integer,Menu> menus;
	
	public Menus () {
            this.menus = new HashMap<Integer,Menu>();
	}
	
	public Map<Integer, Menu> getMenus() {
            return menus;
	}

	public void setMenus(Map<Integer,Menu> menus) {
            this.menus = menus;
	}

	public void addMenu(int ordem, Menu menu) {
            this.menus.put(ordem,menu);	
	}
        
        public Menu get(int indice){
            return this.menus.get(indice);
        }
	
}
