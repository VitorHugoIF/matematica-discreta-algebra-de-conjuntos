/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.node;

/**
 *Classe Par. Vetor com 2 elementos do tipo Element
 * @author Vitor Hugo
 */
public class Pair {
    private Element pair [];

    public Pair() {
        this.pair = new Element[2];
    }
    
    /**
    *Retorna o par de elementos
     * @return 
    */
    public Element[] getPair() {
        return pair;
    }
    
    /**
    *Seta o primeiro elemento do par
     * @param element
    */
    public void setFirstElementPair(Element element) {
        this.pair[0] = element;
    }
    
    /**
    *Seta o segundo elemento do par
     * @param element
    */
    public void setSecondElementPair(Element element) {
        this.pair[1] = element;
    }
    
    /**
    *Retorna o primeiro elemento do par
     * @return 
    */
    public Element getFirstElementPair() {
        return pair[0];
    }
    
    /**
    *Retorna o segundo elemento do par
     * @return 
    */
    public Element getSecondElementPair() {
        return pair[1];
    }

    @Override
    public String toString() {
        return "Pair=<" + pair[0] +", "+ pair[1] + ">" +'}';
    }
    
    
}
