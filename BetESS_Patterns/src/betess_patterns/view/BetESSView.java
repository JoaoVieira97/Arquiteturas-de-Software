package betess_patterns.view;

import betess_patterns.model.Evento;
import java.util.Arrays;
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
	op11 = new Opcao("Login ............................ ", "L");
	op12 = new Opcao("Efetuar registo .................. ", "R");  
        op13 = new Opcao("Sair da Aplicação >>>>>>>>>>>>>>>> ", "S");
	List<Opcao> linhas1 = Arrays.asList(op11, op12, op13);
	Menu menuInicial = new Menu(linhas1, "BetESS - Menu Inicial");        
	menus.addMenu(1, menuInicial);
            
        //Menu Apostador - número 2
        Opcao op21, op22, op23, op24, op25, op26;
	op21 = new Opcao("Ver eventos ...................... ", "E");
	op22 = new Opcao("Ver minhas apostas ............... ", "V");
        op23 = new Opcao("Realizar nova aposta ............. ", "A");
        op24 = new Opcao("Ver saldo da conta ............... ", "C");
        op25 = new Opcao("Importar quantia ................. ", "I");
        op26 = new Opcao("Menu Inicial >>>>>>>>>>>>>>>>>>>>> ", "S");
	List<Opcao> linhas2 = Arrays.asList(op21, op22, op23, op24, op25, op26);
	Menu menuApostador = new Menu(linhas2, "BetESS - Menu do Apostador");        
	menus.addMenu(2, menuApostador);
            
        //Menu Funcionário - número 3
        Opcao op31, op32, op33, op34, op35, op36, op37;
        op31 = new Opcao("Ver eventos ...................... ", "E");
        op32 = new Opcao("Adicionar evento ................. ", "A");
        op33 = new Opcao("Modificar eventos ................ ", "M");
        op34 = new Opcao("Observar evento .................. ", "O");
        op37 = new Opcao("Mostrar/Observar Apostas ......... ", "B");
        op35 = new Opcao("Terminar evento .................. ", "T");
        op36 = new Opcao("Menu Inicial >>>>>>>>>>>>>>>>>>>>> ", "S");
        List<Opcao> linhas3 = Arrays.asList(op31, op32, op33, op34,op37 ,op35, op36);
        Menu menuFuncionario = new Menu(linhas3, "BetESS - Menu do Funcionário");
        menus.addMenu(3, menuFuncionario);
            
        //Menu Modificar Evento - número 4
        Opcao op41, op42, op43, op44,op45;
        op41 = new Opcao("Modificar disponibilidade ........ ", "D");
        op42 = new Opcao("Modificar odd's .................. ", "O");
        op43 = new Opcao("Modificar equipas ................ ", "E");
        op44 = new Opcao("Modificar competição ............. ", "C");
        op45 = new Opcao("Menu do Funcionário >>>>>>>>>>>>>> ", "S");
        List<Opcao> linhas4 = Arrays.asList(op41, op42, op43, op44,op45);
        Menu menuEditarEvento = new Menu(linhas4, "BetESS - Modificar evento");
        menus.addMenu(4, menuEditarEvento);
        
        //Menu Eventos - número 5
        Opcao op51, op52, op53, op54, op55;
	op51 = new Opcao("Ver todos os eventos ............. ", "T");
	op52 = new Opcao("Ver eventos de competição ........ ", "C");
        op53 = new Opcao("Ver eventos de equipa ............ ", "E");
        op54 = new Opcao("Ver eventos mais apostados ....... ", "M");
        op55 = new Opcao("Retroceder >>>>>>>>>>>>>>>>>>>>>>> ", "R");
	List<Opcao> linhas5 = Arrays.asList(op51, op52, op53, op54, op55);
	Menu menuEventos = new Menu(linhas5, "BetESS - Visualizar Eventos");        
	menus.addMenu(5, menuEventos);
        
        //Menu Fazer Aposta - número 6
        Opcao op61, op62, op63;
	op61 = new Opcao("Fazer aposta simples ............. ", "S");
	op62 = new Opcao("Fazer aposta múltipla ............ ", "M");
        op63 = new Opcao("Cancelar >>>>>>>>>>>>>>>>>>>>>>>>> ", "C");
	List<Opcao> linhas6 = Arrays.asList(op61, op62, op63);
	Menu menuNovaAposta = new Menu(linhas6, "BetESS - Tipos de Apostas");        
	menus.addMenu(6, menuNovaAposta);
        
        //Menu Ver Apostas do Apostador - número 7
        Opcao op71, op72, op73, op74, op75;
	op71 = new Opcao("Ver sem critério ................. ", "S");
	op72 = new Opcao("Ordenadas por ganhos/perdas ...... ", "G");
        op73 = new Opcao("Ordenados por possíveis ganhos ... ", "P");
        op74 = new Opcao("Ordenados por valor apostado ..... ", "A");
        op75 = new Opcao("Voltar >>>>>>>>>>>>>>>>>>>>>>>>>>> ", "V");
	List<Opcao> linhas7 = Arrays.asList(op71, op72, op73, op74, op75);
	Menu menuVerApostas = new Menu(linhas7, "BetESS - Ver Apostas");        
	menus.addMenu(7, menuVerApostas);
        
        //Menu ver/observar Apostas do funcionário - número 8
        Opcao op81, op82, op83;
	op81 = new Opcao("Mostrar Apostas .................. ", "M");
	op82 = new Opcao("Observar Aposta .................. ", "O");
        op83 = new Opcao("Voltar >>>>>>>>>>>>>>>>>>>>>>>>>>> ", "V");
	List<Opcao> linhas8 = Arrays.asList(op81, op82, op83);
	Menu menuVerObservarApostas = new Menu(linhas8, "BetESS - Mostrar/Observar Apostas");        
	menus.addMenu(8, menuVerObservarApostas);
            
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
    
    public void printNotificacoes(List<String> notificacoes){
        for (String n : notificacoes){
            System.out.println(n + '\n');
        }
    }
    
    public void menuEquipas(Evento evento, double valor) {
        System.out.println("Opções de aposta disponíveis no evento " + evento.getId() + ":");
        System.out.println("1 - Vitória do/da " + evento.getEquipa_1() + " (" + evento.getOdds()[0] + ")  --> ganhos = " + (evento.getOdds()[0] * valor) + " BetESSCoins");
        System.out.println("X - Empate (" + evento.getOdds()[1] + ")  --> ganhos = " + (evento.getOdds()[1] * valor) + " BetESSCoins");
        System.out.println("2 - Vitória do/da " + evento.getEquipa_2() + " (" + evento.getOdds()[2] + ")  --> ganhos = " + (evento.getOdds()[2] * valor) + " BetESSCoins");
        System.out.println("C >>> Cancelar aposta");
    }
    
    public void menuEquipasMultipla(Evento evento){
        System.out.println("Opções de aposta disponíveis no evento " + evento.getId() + ":");
        System.out.println("1 - Vitória do/da " + evento.getEquipa_1() + " (" + evento.getOdds()[0] + ")");
        System.out.println("X - Empate (" + evento.getOdds()[1] + ")");
        System.out.println("2 - Vitória do/da " + evento.getEquipa_2() + " (" + evento.getOdds()[2] + ")");
    }
    
    public Menu menuApostadorNotificacoes(int notificacoes){
        Opcao op21, op22, op23, op24, op25, op26, op27;
        op21 = new Opcao("VER NOTIFICAÇÕES (" + notificacoes + ") ............. ", "N");
	op22 = new Opcao("Ver eventos ...................... ", "E");
	op23 = new Opcao("Ver minhas apostas ............... ", "V");
        op24 = new Opcao("Realizar nova aposta ............. ", "A");
        op25 = new Opcao("Ver saldo da conta ............... ", "C");
        op26 = new Opcao("Importar quantia ................. ", "I");
        op27 = new Opcao("Menu Inicial >>>>>>>>>>>>>>>>>>>>> ", "S");
	List<Opcao> linhas = Arrays.asList(op21, op22, op23, op24, op25, op26, op27);
	Menu menuApostador = new Menu(linhas, "BetESS - Menu do Apostador");        
	return menuApostador;
    }
    
    public Menu menuFuncionarioNotificacoes(int notificacoes){
        Opcao op31, op32, op33, op34, op35, op36, op37;
        op31 = new Opcao("VER NOTIFICAÇÕES (" + notificacoes + ") ............. ", "N");
        op32 = new Opcao("Ver eventos ...................... ", "E");
        op33 = new Opcao("Adicionar evento ................. ", "A");
        op34 = new Opcao("Modificar eventos ................ ", "M");
        op35 = new Opcao("Observar evento .................. ", "O");
        op36 = new Opcao("Terminar evento .................. ", "T");
        op37 = new Opcao("Menu Inicial >>>>>>>>>>>>>>>>>>>>> ", "S");
        List<Opcao> linhas3 = Arrays.asList(op31, op32, op33, op34, op35, op36, op37);
        Menu menuFuncionario = new Menu(linhas3, "BetESS - Menu do Funcionário");
        return menuFuncionario;
    }
    
}
