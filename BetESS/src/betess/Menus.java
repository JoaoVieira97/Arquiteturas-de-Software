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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Menus {
	
	private Map<Integer,Menu> menus;
	
	public Menus () {
		menus = new HashMap<Integer,Menu>();
	
	}
	
	public Map<Integer, Menu> getMenus() {
		return menus;
	}

	public void setMenus(Map<Integer, Menu> menus) {
		this.menus = menus;
	}

	public void addMenu(int ordem, Menu menu) {
			menus.put(ordem,menu);	
	}
	
}
