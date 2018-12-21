/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package betess_refactoring;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author João Vieira & Simão Barbosa
 */
public class BetESSPersistency {
    
    public static void guardaEstado(BetESSModel model, String nomeFicheiro){
        try{
            FileOutputStream fos = new FileOutputStream(nomeFicheiro);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(model);
            oos.flush();
            oos.close();
            System.out.println("Dados guardados com sucesso");
        }
        catch (FileNotFoundException ex) {System.out.println("Dados da sessão não guardados! Ficheiro não encontrado.");}
        catch (IOException ex) {System.out.println("Dados da sessão não guardados! Erro a aceder a ficheiro!");}
    }
  
    public static BetESSModel carregaEstado(String nomeFicheiro){
        try{
            FileInputStream fis = new FileInputStream(nomeFicheiro);
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