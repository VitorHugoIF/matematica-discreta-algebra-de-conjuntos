/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.readFiles;

import br.com.list.SaveTxtList;
import br.com.list.Set;
import br.com.node.Element;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *Lê um arquivo .txt e salva em uma classe de armazenamento (SaveTxtList) de listas de elementos e conjuntos.
 * @author Vitor Hugo
 */
public class ReadTxtFile {
    private BufferedReader bufer;
    private FileReader file;

    public ReadTxtFile(String fileName) {
        try {
            this.file = new FileReader(fileName);
            this.bufer = new BufferedReader(this.file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReadTxtFile.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    /**
    * Salva na classe que se encontra no parametro da funcao o conteudo lido.
     * @param save
    */
    public void read(SaveTxtList save){
        
        try {
            while(bufer.ready()){
                
                String line = bufer.readLine();
                line = line.trim();//retira todos os espaços dos extremos da string
                line = line.replaceAll(" ", "");//retira todos os espaços internos da string
                String name = line.substring(0,1);//guarda o conteudo da primeira posica da linha lida
                
                //verifica se o name lido é maiusculo ou minusculo para decidir entre conjunto ou elemento
                if (Character.isUpperCase(name.charAt(0))) {
                    //caso conjunto
                    String elements = line.substring(3, line.length()-1);//pega o conteudo entre as chaves
                    String[] vector = elements.split(",");//transforma a string em um array de elementos
                    Set set = new Set();
                    set.setName(name);
                    
                    //se o elements é vazio, cria um elemento vazio
                    if (elements.equals("")) {
                        //Element e = new Element(name, null, null); 
                        Element e = new Element(name, "vazio", "vazio");
                        set.setElements(e);
                    }else{
                        //para cada conteudo no array cria-se um elemento
                        for (int i = 0; i < vector.length; i++) {  
                            Element e = new Element(name, vector[i], vector[i]); 
                            set.setElements(e);
                        }
                    }
                    
                    save.setListSets(set);
                }else{
                    //caso elemento
                    String element = line.substring(2);//guarda o elemento lido da linha
                    //se element é vazio
                    if (element.equals("")) {
                        //cria um elemento vazio
                        //Element e = new Element(null, name, null);
                        Element e = new Element(null, name, "vazio");
                        save.setListElements(e);
                    }else{
                        //cria um elemento preenchido
                        Element e = new Element(null, name, element);
                        save.setListElements(e);
                    }                
                }
            }
            bufer.close();
        } catch (IOException ex) {
            Logger.getLogger(ReadTxtFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
