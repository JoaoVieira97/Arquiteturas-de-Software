package betess_refactoring;

/**
 *
 * @author João Vieira & Simão Barbosa
 */
public class Main {
    
    public static void main(String[] args){
        
        BetESSModel model;
            
        model = BetESSPersistency.carregaEstado("dados.obj");
        
        model = createData(model);
        
        BetESSView view = new BetESSView();

        BetESSController control = new BetESSController();
        control.setModel(model);
        control.setView(view);
        
        UserControllerInterface apostadorController = new Controller_Apostador();
        apostadorController.setModel(model);
        apostadorController.setView(view);
        UserControllerInterface funcionarioController = new Controller_Funcionario();
        funcionarioController.setModel(model);
        funcionarioController.setView(view);
        
        control.setApostadorController(apostadorController);
        control.setFuncionarioController(funcionarioController);

        control.startFlow();
        
        BetESSPersistency.guardaEstado(model, "dados.obj");
    }
    
    public static BetESSModel createData(BetESSModel model){
        if (model == null){
            model = new BetESSModel();
            System.out.println("A criar dados...");
            model = addEventos(model);
            model = addApostadores(model);
            model = addFuncionarios(model);
        }
        return model;
    }
    public static BetESSModel addFuncionarios(BetESSModel model) {
        model.addFuncionario("func1@gmail.com", "111", "Renato Silva");
        model.addFuncionario("func2@gmail.com", "222", "Catarina Coelho");
        return model;
    }
    
    public static BetESSModel addApostadores(BetESSModel model) {
        model.addApostador(new String[]{"antonio@hotmail.com","12345","António Silva"}, 15.60);
        model.addApostador(new String[]{"mafalda@hotmail.com","11111","Mafalda Castro"}, 5.90);
        model.addApostador(new String[]{"carlos@hotmail.com","22222","Carlos Sampaio"}, 3.00);
        model.addApostador(new String[]{"alberto@hotmail.com","33333","Alberto Campos"}, 42.30);
        return model;
        

    }
    public static BetESSModel addEventos(BetESSModel model) {
        model.addEvento(new String[]{"CD Leganés","Atlético Madrid"},new double[]{5.25,2.85,1.57}, true);
        model.addEvento(new String[]{"Real Madrid CF","Real Valladolid"},new double[]{1.19,6.25,11.00}, true);
        model.addEvento(new String[]{"Valência CF","Girona CF"},new double[]{1.47,3.90,6.5}, true);
        model.addEvento(new String[]{"Rayo Vallecano","FC Barcelona"},new double[]{9.00,5.75,1.25}, true);
        model.addEvento(new String[]{"SD Eibar","CD Alavés"},new double[]{2.05,3.10,3.60}, true);
        model.addEvento(new String[]{"CF Villareal","UD Levante"},new double[]{1.57,4.00,5.00}, true);
        model.addEvento(new String[]{"Real Sociedad","Sevilha FC"},new double[]{2.65,3.30,2.40}, true);
        model.addEvento(new String[]{"SD Huesca","Getafe CF"},new double[]{3.50,3.00,2.15}, true);
        model.addEvento(new String[]{"Bétis Sevilha","Celta de Vigo"},new double[]{1.77,3.60,4.00}, true);
        
        return model;
    }
}

