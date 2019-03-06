/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.AlgebraOfSets;

import br.com.list.Set;
import br.com.node.Element;
import br.com.node.Pair;
import java.util.ArrayList;
import java.util.List;

/**
 *Classe Algebra de Conjuntos. 
 * Metodos, PertenceNaoPertence, ContidoNaoContido, ContidoPropiamenteNaoContidoPropiamente, Uniao, Intersecao
 * @author Vitor Hugo
 */
public class AlgebraOfSets {
    
    /**
    *Verifica se um dado elemento pertence ou nao a um conjunto.
    * retorno Boleano.
     * @param set
     * @param element
     * @return 
    */
    public boolean pertinence(Set set, Element element){
        List<Element> elementList = set.getElements();
        for (int i = 0; i < elementList.size(); i++) {
            Element elementAux = elementList.get(i);
            if (elementAux.getValue().equals(element.getValue())) {
                return true;
            }
        }
        return false;
    }
    
    /**
    *Verifica se um dado conjunto esta contido ou é igual a outro.
    * utiliza de metodos elementares - pertenceNaoPertence 
    * retorno Boleano.
     * @param set1
     * @param set2
     * @return 
    */
    public boolean contained(Set set1, Set set2){
        List<Element> elementList = set1.getElements();
        for (int i = 0; i < elementList.size(); i++) {
            Element element = elementList.get(i);
            if (! pertinence(set2, element)) {
                return false;
            }
        }
        return true;
    }
    
    
    /**
    *Verifica se um dado conjunto esta contido propiamente ou nao em outro.
    * utiliza de metodos elementares - contidoNaoContido 
    * retorno Boleano.
     * @param set1
     * @param set2
     * @return 
    */
    public boolean containedProperly(Set set1, Set set2){
        List<Element> elements1 = set1.getElements();
        List<Element> elements2 = set2.getElements();
        return elements2.size() > elements1.size() && contained(set1, set2);
    }
    
    /**
    *Retorna o conjunto uniao dos conjuntos presentes na lista passada como paramentro..
    * utiliza de metodos elementares - pertenceNaoPertence 
    * retorno Boleano.
     * @param sets
     * @return 
    */
    public Set union(List<Set> sets){
        Set setResult =  sets.get(0);
        
        for (int i = 1; i < sets.size(); i++) {
            List<Element> elementList = sets.get(i).getElements();
            for (int j = 0; j < elementList.size(); j++) {
                Element element = elementList.get(j);
                if (! pertinence(setResult, element)) {
                    setResult.setElements(element);
                }        
            }
        }
        setResult.setName("UNIAO");
        return setResult;
    }
    
    /**
    *Retorna o conjunto intersecao dos conjuntos presentes na lista passada como paramentro..
    * retorno Boleano.
     * @param sets
     * @return 
    */
    public Set intersection(List<Set> sets){
        Set setResult = intersectionBetweenTwoSets(sets.get(0), sets.get(1));//conjunto intersecao inicial
        
        for (int i = 2; i < sets.size(); i++) {
            Set set = sets.get(i);
            setResult = intersectionBetweenTwoSets(setResult, set);
        }
        
        setResult.setName("INTERSECAO");
        return setResult;
    }
    
    /**
    *Retorna o conjunto intersecao de dois conjuntos.
    * utiliza de metodos elementares - pertenceNaoPertence 
    * retorno Boleano.
     * @param sets
     * @return 
    */
    private Set intersectionBetweenTwoSets(Set set1, Set set2){
        Set setResult = new Set();
        List<Element> elementList = set1.getElements();
        for (int i = 0; i < elementList.size(); i++) {
            Element element = set1.getElements().get(i);
            if (pertinence(set2, element)) {
                setResult.setElements(element);
            }
        }
        
        return setResult;
    }
    
    public Set cartesianProduct (Set set1, Set set2){
        Set setResult = new Set();
        
        List<Element> elementList1 = set1.getElements();
        for (int i = 0; i < elementList1.size(); i++) {
            List<Element> elementList2 = set2.getElements();
            for (int j = 0; j < elementList2.size(); j++) {
                Pair pair = new Pair();
                Element element1 = elementList1.get(i);
                Element element2 = elementList2.get(j);
                pair.setFirstElementPair(element1);
                pair.setSecondElementPair(element2);
                setResult.setPairs(pair);
            }
        }
        
        setResult.setName("PRODUTO CARTESIANO");
        return setResult;
    }
    
    public List<Set> setOfParties(Set set){
        List<Set> setList = new ArrayList<>();
         //todos conjuntos unitarios dos elementos
        for (int i = 0; i < set.getElements().size(); i++) {
            
            Set setAux = new Set();
            setAux.setName("CONJUNTO DAS PARTES");
            setAux.setElements(set.getElements().get(i));
            setList.add(setAux);
        }
        
        for (int i = 0; i < set.getElements().size()-; i++) {
            //Set setRemove = set;
            setList.addAll(subSets(set, i));
        }
 
        return setList;
    }
    
    private List<Set> subSets(Set set, int amount){
        List<Set> setList = new ArrayList<>();
        
        for (int i = 0; i < amount; i++) {
            Set setAux = new Set();
            List<Element> listElement;
            listElement = set.getElements().subList(i, amount-1);
            for (int j = 0; j < 10; j++) {
                setAux.setElements(listElement.get(j));
            }
            setAux.setName("CONJUNTO DAS PARTES");
            setList.add(setAux);
        }
        return setList;
    }
    /*private List<Set> subSets(Set set, int amount){
        List<Set> setList = new ArrayList<>();
        //todos os conjuntos de duplas
        for (int i = 0; i < amount-1; i++) {
            for (int j = i+1; j < amount; j++) {
               Set setAux = new Set();
               setAux.setName("CONJUNTO DAS PARTES");
               setAux.setElements(set.getElements().get(i));
               setAux.setElements(set.getElements().get(j));
               setList.add(setAux);
               
            }
        }
        
        for (int i = amount; i > 0 ; i--) {
            Set setAux = new Set();
            setAux.setName("CONJUNTO DAS PARTES");
            for (int j = 0; j < i; j++) {
               setAux.setElements(set.getElements().get(j));
            }
            setList.add(setAux);
        }
        
        return setList;
             
    }*/
}