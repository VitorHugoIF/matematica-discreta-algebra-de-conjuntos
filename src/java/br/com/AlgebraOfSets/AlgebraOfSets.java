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
        
        if (sets.size() > 2) {
            Set setResult = intersectionBetweenTwoSets(sets.get(0), sets.get(1));//conjunto intersecao inicial
            for (int i = 2; i < sets.size(); i++) {
                Set set = sets.get(i);
                setResult = intersectionBetweenTwoSets(setResult, set);
            }
            setResult.setName("INTERSECAO");
            return setResult;
        }else if (sets.size() == 2){
            Set setResult = intersectionBetweenTwoSets(sets.get(0), sets.get(1));
            setResult.setName("INTERSECAO");
            return setResult;
        }
        Set setResult = new Set();
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
        List<Set> finalListSet = new ArrayList<>();
                
       /* for (int i = 0; i < ((set.getElements().size())-1); i++) {
                
            for (int j = i+1; j < set.getElements().size(); j++) {
                
                Set s = new Set();
                s.setElements(set.getElements().get(i));
                s.setElements(set.getElements().get(j));
                finalListSet.add(s);
            }
        }*/
        for (Element element : set.getElements()) {
            Set s = new Set();
            s.setElements(element);
            finalListSet.add(s);
        }
        
        finalListSet.addAll(subs(finalListSet, finalListSet));
        return finalListSet;
    }
    public List<Set> subs (List<Set> list, List<Set> list1){
        List<Set> finalListSet = new ArrayList<>();
        
        for (Set set : list) {
            for (Set set1 : list1) {
                
                List<Element> el = set.getElements();
                List<Element>el1 = set1.getElements();
                
                for (int i = 0; i < el.size(); i++) {
                    
                    for (int j = 0; j < el1.size(); j++) {
                        
                        if (Integer.parseInt(set.getLastElement().getValue()) < Integer.parseInt(el1.get(j).getValue())) {
                            
                            List<Element> aux = new ArrayList<>();
                            aux.addAll(el);
                            aux.addAll(el1);
                            Set s = new Set(aux);
                            finalListSet.add(s);
                        }
                    }
                    
                }
            }
        }
        
        /*List<Set> finalListSet = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Set setAux = list.get(i);
            
            for (int j = 0; j < (set.getElements().size()); j++) {
                
                List<Element> le = setAux.getElements();
                le.add(set.getElements().get(j));
                Set s = new Set(le);
                finalListSet.add(s);
            }     
        }*/
       
        return finalListSet;
    }
    /*public List<List<Set>> setOfParties(Set set){
        List<List<Set>> listRes = new ArrayList<>();
        for (int i = set.getElements().size(); i >0 ; i--) {
            listRes.add(subconjuntos(set, i));
            
        }
        return listRes;
    }
    private static List<Set> subconjuntos(Set set, int quantidade) {  
  
        if (quantidade == 0) {
            List<Set> list = new ArrayList<>();
            return list;
        }

        List<Set> resultado = new ArrayList();
        List<Element> abertos = new ArrayList(set.getElements());

        while (!abertos.isEmpty()) {
            Element elementAux = abertos.remove(0);
            Set setRemoved = new Set(abertos);

            List<Set> listE = subconjuntos(setRemoved, quantidade - 1);
            for (Set set1 : listE) {
               set1.setElements(elementAux);  
                resultado.add(set1);   
            }
        }
            return resultado  ;

    } */ 
      
}  

    
    
