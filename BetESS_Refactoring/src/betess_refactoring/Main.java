package betess_refactoring;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author João Vieira & Simão Barbosa
 */
public class Main {
    
    public static void main(String[] args){
        
        BetESSModel model = new BetESSModel();
        int carregado = 0;
        
        try{
            model = BetESSModel.carregaEstado("dados.obj");
            System.out.println("Dados carregados com sucesso!");
            carregado = 1;
        }
        catch (FileNotFoundException ex) {System.out.println("Dados não importados! Ficheiro não encontrado.");}
        catch (IOException ex) {System.out.println("Dados não importados! Erro a aceder a ficheiro.");}
        catch (ClassNotFoundException ex) {System.out.println("Dados não importados! Classe não encontrada.");}
        
        if (carregado == 0){
            System.out.println("A criar dados...");
            
            model.addEvento("CD Leganés", "Atlético Madrid", 5.25, 2.85, 1.57, true);
            model.addEvento("Real Madrid CF", "Real Valladolid", 1.19, 6.25, 11.00, true);
            model.addEvento("Valência CF", "Girona CF", 1.47, 3.90, 6.50, true);
            model.addEvento("Rayo Vallecano", "FC Barcelona", 9.00, 5.75, 1.25, true);
            model.addEvento("SD Eibar", "CD Alavés", 2.05, 3.10, 3.60, true);
            model.addEvento("CF Villareal", "UD Levante", 1.57, 4.00, 5.00, true);
            model.addEvento("Real Sociedad", "Sevilha FC", 2.65, 3.30, 2.40, true);
            model.addEvento("SD Huesca", "Getafe CF", 3.50, 3.00, 2.15, true);
            model.addEvento("Bétis Sevilha", "Celta de Vigo", 1.77, 3.60, 4.00, true);

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
        
        UserControllerInterface apostadorController = new Controller_Apostador();
        apostadorController.setModel(model);
        apostadorController.setView(view);
        UserControllerInterface funcionarioController = new Controller_Funcionario();
        funcionarioController.setModel(model);
        funcionarioController.setView(view);
        
        control.setApostadorController(apostadorController);
        control.setFuncionarioController(funcionarioController);

        control.startFlow();
        
        try{
            model.guardaEstado("dados.obj");
            System.out.println("Dados guardados com sucesso");
        }
        catch (FileNotFoundException ex) {System.out.println("Dados da sessão não guardados! Ficheiro não encontrado.");}
        catch (IOException ex) {System.out.println("Dados da sessão não guardados! Erro a aceder a ficheiro!");}
    }
    
}

