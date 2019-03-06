/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.list;

import br.com.node.Element;
import java.util.ArrayList;
import java.util.List;

/**
 *Salva o txt lido em listas de conjuntos e elementos para o uso no sistema.
 * @author Vitor Hugo
 */
public class SaveTxtList {
    private List<Element> listElements;
    private List<Set> listSets;

    public SaveTxtList() {
        this.listElements = new ArrayList<>();
        this.listSets = new ArrayList<>();
    }
    
    
    /**
    * Retorna lista de elementos lida do txt.
     * @return 
    */
    public List<Element> getListElements() {
        return listElements;
    }

    /**
    * Insere um elemento na lista de elementos.
     * @param element
    */
    public void setListElements(Element element) {
        this.listElements.add(element);
    }

    /**
    * Retorna lista de conjuntos lida do txt.
     * @return 
    */
    public List<Set> getListSets() {
        return listSets;
    }

    /**
    * Insere um conjunto na lista de conjuntos.
     * @param set
    */
    public void setListSets(Set set) {
        this.listSets.add(set);
    }
    
}
