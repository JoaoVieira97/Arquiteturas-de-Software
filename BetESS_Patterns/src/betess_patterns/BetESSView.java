package betess_patterns;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class BetESSView {
    
    private Menus menus;
    
    public BetESSView() {
        this.menus = initView();
    }
    
    public Menus initView() {
        Menus menus = new Menus();
        
        //Menu Inicial - número 1
        Opcao op11, op12, op13;
	op11 = new Opcao("Login ...................... ", "L");
	op12 = new Opcao("Efetuar registo ............ ", "R");  
        op13 = new Opcao("Sair da Aplicação >>>>>>>>>> ", "S");
	List<Opcao> linhas1 = Arrays.asList(op11, op12, op13);
	Menu menuInicial = new Menu(linhas1, "BetESS - Menu Inicial");        
	menus.addMenu(1, menuInicial);
            
        //Menu Apostador - número 2
        Opcao op21, op22, op23, op24, op25, op26;
	op21 = new Opcao("Ver eventos ................ ", "E");
	op22 = new Opcao("Ver minhas apostas ......... ", "V");
        op23 = new Opcao("Realizar nova aposta ....... ", "A");
        op24 = new Opcao("Ver saldo da conta ......... ", "C");
        op25 = new Opcao("Importar quantia ........... ", "I");
        op26 = new Opcao("Menu Inicial >>>>>>>>>>>>>>> ", "S");
	List<Opcao> linhas2 = Arrays.asList(op21, op22, op23, op24, op25, op26);
	Menu menuApostador = new Menu(linhas2, "BetESS - Menu do Apostador");        
	menus.addMenu(2, menuApostador);
            
        //Menu Funcionário - número 3
        Opcao op31, op32, op33, op34, op35;
        op31 = new Opcao("Ver eventos ................ ", "E");
        op32 = new Opcao("Adicionar evento ........... ", "A");
        op33 = new Opcao("Modificar eventos .......... ", "M");
        op34 = new Opcao("Terminar evento ............ ", "T");
        op35 = new Opcao("Menu Inicial >>>>>>>>>>>>>>> ", "S");
        List<Opcao> linhas3 = Arrays.asList(op31, op32, op33, op34, op35);
        Menu menuFuncionario = new Menu(linhas3, "BetESS - Menu do Funcionário");
        menus.addMenu(3, menuFuncionario);
            
        //Menu Modificar Evento - número 4
        Opcao op41, op42, op43, op44;
        op41 = new Opcao("Modificar disponibilidade .. ", "D");
        op42 = new Opcao("Modificar odd's ............ ", "O");
        op43 = new Opcao("Modificar equipas .......... ", "E");
        op44 = new Opcao("Menu do Funcionário >>>>>>>> ", "S");
        List<Opcao> linhas4 = Arrays.asList(op41, op42, op43, op44);
        Menu menuEditarEvento = new Menu(linhas4, "BetESS - Modificar evento");
        menus.addMenu(4, menuEditarEvento);
        
        //Menu Eventos - número 5
        Opcao op51, op52, op53, op54, op55;
	op51 = new Opcao("Ver todos os eventos ....... ", "T");
	op52 = new Opcao("Ver eventos de competição .. ", "C");
        op53 = new Opcao("Ver eventos de equipa ...... ", "E");
        op54 = new Opcao("Ver eventos mais apostados . ", "M");
        op55 = new Opcao("Retroceder >>>>>>>>>>>>>>>>> ", "R");
	List<Opcao> linhas5 = Arrays.asList(op51, op52, op53, op54, op55);
	Menu menuEventos = new Menu(linhas5, "BetESS - Visualizar Eventos");        
	menus.addMenu(5, menuEventos);
            
        return menus;   
    }

    public Menus getMenus() {
        return menus;
    }

    public void setMenus(Menus menus) {
        this.menus = menus;
    }
    
    public Menu getMenu(int indice){
        return this.menus.get(indice);
    }
    
    public void printCollectionEventos(String header, List<Evento> eventos){
        System.out.println(header);
        for(Evento e : eventos){
            System.out.println(e.toString());
        }
    }
    
    public void printIteratorEventos(String header, Iterator<Evento> eventos){
        System.out.println(header);
        while (eventos.hasNext()){
            System.out.println(eventos.next().toString());
        }
    }
    
}
