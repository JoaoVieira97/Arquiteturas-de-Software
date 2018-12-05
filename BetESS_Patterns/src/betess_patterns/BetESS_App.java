package betess_patterns;


public class BetESS_App {
    
    public static void main(String[] args) {
        
        BetESSModel model = new BetESSModel();
        
        model.addEvento("CD Leganés", "Atlético Madrid", "Liga Espanhola", 5.25, 2.85, 1.57, true);
        model.addEvento("Real Madrid CF", "Real Valladolid", "Liga Espanhola", 1.19, 6.25, 11.00, true);
        model.addEvento("Valência CF", "Girona CF", "Liga Espanhola", 1.47, 3.90, 6.50, true);
        model.addEvento("Rayo Vallecano", "FC Barcelona", "Liga Espanhola", 9.00, 5.75, 1.25, true);
        model.addEvento("SD Eibar", "CD Alavés", "Liga Espanhola", 2.05, 3.10, 3.60, true);
        model.addEvento("CF Villareal", "UD Levante", "Liga Espanhola", 1.57, 4.00, 5.00, true);
        model.addEvento("Real Sociedad", "Sevilha FC", "Liga Espanhola", 2.65, 3.30, 2.40, true);
        model.addEvento("SD Huesca", "Getafe CF", "Liga Espanhola", 3.50, 3.00, 2.15, true);
        model.addEvento("Bétis Sevilha", "Celta de Vigo", "Liga Espanhola", 1.77, 3.60, 4.00, true);
    
        model.addApostador("antonio@hotmail.com", "12345", "António Silva", 15.60);
        model.addApostador("mafalda@hotmail.com", "11111", "Mafalda Castro", 5.90);
        model.addApostador("carlos@hotmail.com", "22222", "Carlos Sampaio", 3.00);
        model.addApostador("alberto@hotmail.com", "33333", "Alberto Campos", 42.30);
            
        model.addFuncionario("func1@gmail.com", "111", "Renato Silva");
        model.addFuncionario("func2@gmail.com", "222", "Catarina Coelho");
        
        BetESSView view = new BetESSView();

        BetESSController control = new BetESSController();
        control.setModel(model);
        control.setView(view);

        control.startFlow();
    }
    
}
