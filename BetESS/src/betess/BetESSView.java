/**
 *
 * @author João Vieira & Simão Barbosa
 */
package betess;

import java.util.Arrays;
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
            
        return menus;   
    }

    public Menus getMenus() {
        return menus;
    }

    public void setMenus(Menus menus) {
        this.menus = menus;
    }

    public void menuEquipas(Evento evento, double valor) {
        System.out.println("Opções de aposta disponíveis no evento " + evento.getId() + ":");
        System.out.println("1 - Vitória do/da " + evento.getEquipa_1() + " (" + evento.getOdds()[0] + ")  --> ganhos = " + (evento.getOdds()[0] * valor) + " ESScoins");
        System.out.println("X - Empate (" + evento.getOdds()[1] + ")  --> ganhos = " + (evento.getOdds()[1] * valor) + " ESScoins");
        System.out.println("2 - Vitória do/da " + evento.getEquipa_2() + " (" + evento.getOdds()[2] + ")  --> ganhos = " + (evento.getOdds()[2] * valor) + " ESScoins");
        System.out.println("S >>> Cancelar aposta");
    }
    
    public Menu getMenu(int indice){
        return this.menus.get(indice);
    }
    
    public void println(String str){ 
        System.out.println(str);

    }
    public void print(String str){ 
        System.out.print(str);
    }
    public void printEventos (List<Evento> lista) {
        println("Lista de eventos BetESS:");
        for (Evento e : lista)
            println(e.toString());
    }
    public void printApostas (List<Aposta> apostas){
        if (apostas.isEmpty()) System.out.println("Não tem apostas realizadas");
        else{
            for (Aposta a : apostas)
                println(a.toString());
        }
    }
    
    public Menu menuApostadorNotificacoes(int n){
        Opcao op21, op22, op23, op24, op25, op26, op27;
        op21 = new Opcao("VER NOTIFICAÇÕES(" + n + ") ........ ", "N");
	op22 = new Opcao("Ver eventos ................ ", "E");
	op23 = new Opcao("Ver minhas apostas ......... ", "V");
        op24 = new Opcao("Realizar nova aposta ....... ", "A");
        op25 = new Opcao("Ver saldo da conta ......... ", "C");
        op26 = new Opcao("Importar quantia ........... ", "I");
        op27 = new Opcao("Menu Inicial >>>>>>>>>>>>>>> ", "S");
	List<Opcao> linhas = Arrays.asList(op21, op22, op23, op24, op25, op26, op27);
	Menu menuApostador = new Menu(linhas, "BetESS - Menu do Apostador");        
	return menuApostador;
    }
    
    public void printNotificacoes(List<String> notificacoes){
        for (String n : notificacoes){
            System.out.println(n + '\n');
        }
    }
}
