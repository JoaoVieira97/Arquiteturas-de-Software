package betess_patterns;

import betess_patterns.model.BetESSModel;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class BetESS_Persistency {
    
    public static void saveData(BetESSModel model, String filename){
        try{
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(model);
            oos.flush();
            oos.close();
            System.out.println("Dados guardados com sucesso");
        }
        catch (FileNotFoundException ex) {System.out.println("Dados da sessão não guardados! Ficheiro não encontrado.");}
        catch (IOException ex) {System.out.println("Dados da sessão não guardados! Erro a aceder a ficheiro!");}
    }
    
    public static BetESSModel getData(String filename){
        try{
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            BetESSModel m = (BetESSModel) ois.readObject();
            ois.close();
            System.out.println("Dados carregados com sucesso!");
            return m;
        }
        catch (FileNotFoundException ex) {System.out.println("Dados não importados! Ficheiro não encontrado.");}
        catch (IOException ex) {System.out.println("Dados não importados! Erro a aceder a ficheiro.");}
        catch (ClassNotFoundException ex) {System.out.println("Dados não importados! Classe não encontrada.");}
        return null;
    }

}
