/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.node;

/**
 *
 * Classe Elemento. Elementos possuem nome do conjunto na qual pertencem, seu propio nome e seu valor.
 * Elementos que nao pertencem a um conjunto possuem seu nome de conjunto igual a null.
 * Elementos que pertencem a um conjunto possuem seu nome e valor iguais.
 * @author Vitor Hugo
 */
public class Element {
    private String set = null;
    private String name = null;
    private String value = null;

    public Element(String set, String name, String value) {
        this.set = set;
        this.name = name;
        this.value = value;
    }
     
    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        
        if (name == value) {
            return value;
        }else{
            return ""+name+" = { " + value + " }";
        }
        
    }
    
    
}
