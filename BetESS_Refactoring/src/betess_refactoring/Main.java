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
        String[] dados = new String[3];
        
        dados[0] = "antonio@hotmail.com";
        dados[1] = "12345";
        dados[2] = "António Silva";
        model.addApostador(dados, 15.60);
        
        dados[0] = "mafalda@hotmail.com";
        dados[1] = "11111";
        dados[2] = "Mafalda Castro";
        model.addApostador(dados, 5.90);
        
        dados[0] = "carlos@hotmail.com";
        dados[1] =  "22222";
        dados[2] = "Carlos Sampaio";
        model.addApostador(dados, 3.00);
        
        dados[0] = "alberto@hotmail.com";
        dados[1] =  "33333";
        dados[2] = "Alberto Campos";
        model.addApostador(dados, 42.30);
        
        return model;
        

    }
    public static BetESSModel addEventos(BetESSModel model) {
        String equipas[] = new String[2];
        double odds[] = new double[3];
        
        equipas[0] = "CD Leganés";
        equipas[1] = "Atlético Madrid";
        odds[0]= 5.25;
        odds[1]= 2.85;
        odds[2]= 1.57;
        model.addEvento(equipas,odds, true);
        
        equipas[0] ="Real Madrid CF";
        equipas[1] = "Real Valladolid";
        odds[0]=  1.19;
        odds[1]= 6.25;
        odds[2]= 11.00 ;
        model.addEvento(equipas,odds, true);
        
        equipas[0] = "Valência CF";
        equipas[1] = "Girona CF";
        odds[0]=1.47 ;
        odds[1]=  3.90;
        odds[2]= 6.5 ;
        model.addEvento(equipas,odds, true);
        
        equipas[0] = "Rayo Vallecano";
        equipas[1] = "FC Barcelona";
        odds[0]= 9.00;
        odds[1]= 5.75;
        odds[2]= 1.25;
        model.addEvento(equipas,odds, true);
       
        equipas[0] = "SD Eibar";
        equipas[1] = "CD Alavés";
        odds[0]= 2.05;
        odds[1]= 3.10;
        odds[2]= 3.60;
        model.addEvento(equipas,odds, true);
         
        equipas[0] = "CF Villareal";
        equipas[1] = "UD Levante";
        odds[0]= 1.57;
        odds[1]= 4.00;
        odds[2]= 5.00;
        model.addEvento(equipas,odds, true);
        
        equipas[0] = "Real Sociedad";
        equipas[1] = "Sevilha FC";
        odds[0]= 2.65;
        odds[1]= 3.30;
        odds[2]= 2.40;
        model.addEvento(equipas,odds, true);
        
        equipas[0] ="SD Huesca";
        equipas[1] = "Getafe CF";
        odds[0]= 3.50;
        odds[1]= 3.00;
        odds[2]= 2.15;
        model.addEvento(equipas,odds, true);
        
        equipas[0] = "Bétis Sevilha";
        equipas[1] = "Celta de Vigo";
        odds[0]= 1.77;
        odds[1]= 3.60;
        odds[2]= 4.00;
        model.addEvento(equipas,odds, true);    
        
        return model;
    }
}

