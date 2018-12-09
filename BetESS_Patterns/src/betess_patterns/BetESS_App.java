package betess_patterns;

import betess_patterns.model.BetESSModel;
import betess_patterns.controller.BetESSController;
import betess_patterns.view.BetESSView;


public class BetESS_App {
    
    public static void main(String[] args) {
        
        BetESSModel model;
        
        model = BetESS_Persistency.getData("dados.obj");
        
        if (model == null){
            model = new BetESSModel();
            System.out.println("A criar dados...");
        
            model.addEvento("CD Leganés", "Atlético Madrid", "Liga Espanhola", 5.25, 2.85, 1.57, true, 20);
            model.addEvento("Real Madrid CF", "Real Valladolid", "Liga Espanhola", 1.19, 6.25, 11.00, true, 5);
            model.addEvento("Valência CF", "Girona CF", "Liga Espanhola", 1.47, 3.90, 6.50, true, 3);
            model.addEvento("Rayo Vallecano", "FC Barcelona", "Liga Espanhola", 9.00, 5.75, 1.25, true, 35);
            model.addEvento("SD Eibar", "CD Alavés", "Liga Espanhola", 2.05, 3.10, 3.60, true, 16);
            model.addEvento("CF Villareal", "UD Levante", "Liga Espanhola", 1.57, 4.00, 5.00, true, 12);
            model.addEvento("Real Sociedad", "Sevilha FC", "Liga Espanhola", 2.65, 3.30, 2.40, true, 35);
            model.addEvento("SD Huesca", "Getafe CF", "Liga Espanhola", 3.50, 3.00, 2.15, true, 32);
            model.addEvento("Bétis Sevilha", "Celta de Vigo", "Liga Espanhola", 1.77, 3.60, 4.00, true, 11);
            model.addEvento("CF Belenenses", "GD Chaves", "Liga Portuguesa", 2.10, 3.20, 3.40, true, 7);
            model.addEvento("CD Nacional", "Boavista FC", "Liga Portuguesa", 2.40, 3.15, 2.80, true, 6);
            model.addEvento("Moreirense FC", "CD Santa Clara", "Liga Portuguesa", 1.95, 3.25, 3.75, true, 8);
            model.addEvento("Vitória SC", "Rio Ave FC", "Liga Portuguesa", 2.00, 3.40, 3.50, true, 21);
            model.addEvento("US Sassuolo", "Catalia", "Taça de Itália", 2.25, 1.90, 5.00, true, 24);
            model.addEvento("FC Nantes", "Olympique de Marseille", "Liga Francesa", 2.95, 2.65, 2.15, true, 54);
            model.addEvento("Olympique de Lyon", "Stade Rennais", "Liga Francesa", 1.45, 3.50, 5.25, true, 64);

            model.addApostador("antonio@hotmail.com", "12345", "António Silva", 15.60);
            model.addApostador("mafalda@hotmail.com", "11111", "Mafalda Castro", 5.90);
            model.addApostador("carlos@hotmail.com", "22222", "Carlos Sampaio", 3.00);
            model.addApostador("alberto@hotmail.com", "33333", "Alberto Campos", 42.30);

            model.addFuncionario("func1@gmail.com", "111", "Renato Silva");
            model.addFuncionario("func2@gmail.com", "222", "Catarina Coelho");
        }
        
        BetESSView view = new BetESSView();

        BetESSController control = new BetESSController();
        control.setModel(model);
        control.setView(view);

        control.startFlow();
        
        BetESS_Persistency.saveData(model, "dados.obj");
    }
    
}
