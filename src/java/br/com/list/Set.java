/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.list;

import br.com.node.Element;
import br.com.node.Pair;
import java.util.ArrayList;
import java.util.List;

/**
 *Classe Conjunto. Conjuntos possuem um nome.
 * Podem possuir uma lista de elementos ou uma lista de pares.
 * @author Vitor Hugo
 */
public class Set {
    private List<Element> elements;
    private List<Pair> pairs;
    private String name;

    public Set() {
        this.elements = new ArrayList();
        this.pairs = new ArrayList();
    }

    public Set(List<Element> elements, List<Pair> pairs, String name) {
        this.elements = elements;
        this.pairs = pairs;
        this.name = name;
    }

    public Set(List<Element> elements) {
        this.elements = elements;
        this.pairs = new ArrayList();
    }
    
    
    /**
    *Retorna a lista de elementos do conjunto.
     * @return 
    */
    public List<Element> getElements() {
        return elements;
    }

    /**
    *Insere um elemento na lista de elementos do conjunto.
     * @param element
    */
    public void setElements(Element element) {
        this.elements.add(element);
    }

    /**
    *Retorna a lista de pares do conjunto.
     * @return 
    */
    public List<Pair> getPairs() {
        return pairs;
    }

    /**
    *Insere um elemento na lista de pares do conjunto.
     * @param pair
    */
    public void setPairs(Pair pair) {
        this.pairs.add(pair);
    }

    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Retorna o ultimo elemento do conjunto
     * @return 
    */
    public Element getLastElement(){
        return this.elements.get((elements.size())-1);
    }
    
    public Set clone(){
        List<Element> elementList = new ArrayList<>();
        for (int i = 0; i < this.elements.size(); i++) {
            elementList.add(this.elements.get(i));
        }
        Set set = new Set(elementList);
        set.setName(this.name);
        return set;
    }

    @Override
    public String toString() {
        if (!pairs.isEmpty()) {
            return  name + " = { "+ pairs + '}';
        }else{
            String tostring = name + " = { "+ elements + " }";
            tostring = tostring.replace("[", "");
            tostring = tostring.replace("]", "");
            return tostring; 
        }
        
    }
  
   
}
