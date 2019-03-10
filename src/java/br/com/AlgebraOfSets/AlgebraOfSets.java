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
 * Classe Algebra de Conjuntos. Metodos, PertenceNaoPertence, ContidoNaoContido,
 * ContidoPropiamenteNaoContidoPropiamente, Uniao, Intersecao
 *
 * @author Vitor Hugo
 */
public class AlgebraOfSets {

    /**
     * Verifica se um dado elemento pertence ou nao a um conjunto. retorno
     * Boleano.
     *
     * @param set
     * @param element
     * @return
     */
    public boolean pertinence(Set set, Element element) {
        if (element.getValue().equals("vazio")) {
            return true;
        }
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
     * Verifica se um dado conjunto esta contido ou é igual a outro. utiliza de
     * metodos elementares - pertenceNaoPertence retorno Boleano.
     *
     * @param set1
     * @param set2
     * @return
     */
    public boolean contained(Set set1, Set set2) {
        if (set1.getElements().get(0).getValue().equals("vazio")) {
            return false;
        }
        List<Element> elementList = set1.getElements();
        for (int i = 0; i < elementList.size(); i++) {
            Element element = elementList.get(i);
            if (!pertinence(set2, element)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Verifica se um dado conjunto esta contido propiamente ou nao em outro.
     * utiliza de metodos elementares - contidoNaoContido retorno Boleano.
     *
     * @param set1
     * @param set2
     * @return
     */
    public boolean containedProperly(Set set1, Set set2) {
        List<Element> elements1 = set1.getElements();
        List<Element> elements2 = set2.getElements();
        return elements2.size() > elements1.size() && contained(set1, set2);
    }

    /**
     * Retorna o conjunto uniao dos conjuntos presentes na lista passada como
     * paramentro.. utiliza de metodos elementares - pertenceNaoPertence retorno
     * Boleano.
     *
     * @param sets
     * @return
     */
    public Set union(List<Set> sets) {
        Set setResult = sets.get(0);

        for (int i = 1; i < sets.size(); i++) {
            List<Element> elementList = sets.get(i).getElements();
            for (int j = 0; j < elementList.size(); j++) {
                Element element = elementList.get(j);
                if (!pertinence(setResult, element)) {
                    setResult.setElements(element);
                }
            }
        }
        setResult.setName("UNIAO");
        return setResult;
    }

    /**
     * Retorna o conjunto intersecao dos conjuntos presentes na lista passada
     * como paramentro.. retorno Boleano.
     *
     * @param sets
     * @return
     */
    public Set intersection(List<Set> sets) {

        if (sets.size() > 2) {
            Set setResult = intersectionBetweenTwoSets(sets.get(0), sets.get(1));//conjunto intersecao inicial
            for (int i = 2; i < sets.size(); i++) {
                Set set = sets.get(i);
                setResult = intersectionBetweenTwoSets(setResult, set);
            }
            setResult.setName("INTERSECAO");
            return setResult;
        } else if (sets.size() == 2) {
            Set setResult = intersectionBetweenTwoSets(sets.get(0), sets.get(1));
            setResult.setName("INTERSECAO");
            return setResult;
        }
        Set setResult = new Set();
        setResult.setName("INTERSECAO");
        return setResult;
    }

    /**
     * Retorna o conjunto intersecao de dois conjuntos. utiliza de metodos
     * elementares - pertenceNaoPertence retorno Boleano.
     *
     * @param sets
     * @return
     */
    private Set intersectionBetweenTwoSets(Set set1, Set set2) {
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
    
    
    /*public Set cartesianProduct(Set set1, Set set2) {
        Set setResult = new Set();

        if (set1.getElements().get(0).getValue().equals("vazio") || set2.getElements().get(0).getValue().equals("vazio")) {
            Pair pair = new Pair();
            Element element1 = new Element("PRODUTO CARTESIANO", "vazio", "vazio");
            Element element2 = new Element("PRODUTO CARTESIANO", "vazio", "vazio");
            pair.setFirstElementPair(element1);
            pair.setSecondElementPair(element2);
            setResult.setName("PRODUTO CARTESIANO");
            setResult.setPairs(pair);
            return setResult;
        }

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
    }*/
    
    public List<Object> cartesianProduct(Set set1, Set set2) {
        Set setResult = new Set();
        List<Object> s = new ArrayList<>();

        if (set1.getElements().get(0).getValue().equals("vazio") || set2.getElements().get(0).getValue().equals("vazio")) {
            Pair pair = new Pair();
            Element element1 = new Element("PRODUTO CARTESIANO", "vazio", "vazio");
            Element element2 = new Element("PRODUTO CARTESIANO", "vazio", "vazio");
            pair.setFirstElementPair(element1);
            pair.setSecondElementPair(element2);
            setResult.setName("PRODUTO CARTESIANO");
            setResult.setPairs(pair);
            s.add(setResult);
            return s;
        }

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
        List<Set> lsAux = this.reverseCartesianProduct(setResult);
        s.add(setResult);
         s.add(lsAux);
        return s;
    }

    /*public List<Set> setOfParties(Set set) {
        List<Set> finalListSet = new ArrayList<>();
        List<Set> ListSet = new ArrayList<>();
        if (set.getElements().get(0).getValue().equals("vazio")) {
            set.setName("CONJUNTO DAS PARTES");
            finalListSet.add(set);
            return finalListSet;
        }

        for (Element element : set.getElements()) {
            Set s = new Set();
            s.setName("CONJUNTO DAS PARTES");
            s.setElements(element);
            ListSet.add(s);
        }
        finalListSet.addAll(ListSet);

        List<Set> aux = ListSet;
        int i = (int) (Math.pow(2, ListSet.size()) - ListSet.size());//total de item no subconjunto final (2^n) - a quantidade da lista unitaria
        while (i >= 0) {
            aux = subs(aux, ListSet, i);
            finalListSet.addAll(aux);
            i--;
        }

        return finalListSet;
    }*/
    
    public List<Object> setOfParties(Set set) {
        List<Set> finalListSet = new ArrayList<>();
        List<Set> ListSet = new ArrayList<>();
        List<Object> returList = new ArrayList<>();
        
        if (set.getElements().get(0).getValue().equals("vazio")) {
            set.setName("CONJUNTO DAS PARTES");
            finalListSet.add(set);
            Set setEmpty = new Set();
            setEmpty.setElements(new Element("vazio", "vazio", "vazio"));
            returList.add(finalListSet);
            returList.add(setEmpty);
                    
            return returList;
        }

        for (Element element : set.getElements()) {
            Set s = new Set();
            s.setName("CONJUNTO DAS PARTES");
            s.setElements(element);
            ListSet.add(s);
        }
        finalListSet.addAll(ListSet);

        List<Set> aux = ListSet;
        int i = (int) (Math.pow(2, ListSet.size()) - ListSet.size());//total de item no subconjunto final (2^n) - a quantidade da lista unitaria
        while (i >= 0) {
            aux = subs(aux, ListSet, i);
            finalListSet.addAll(aux);
            i--;
        }

        Set setAux = this.reverseSetOfParties(finalListSet);
        returList.add(finalListSet);
        returList.add(setAux);
        return returList;
    }
    
    private List<Set> subs(List<Set> list, List<Set> list1, int q) {

        if (q == 0) {
            List<Set> l = new ArrayList<>();
            Set s = new Set();
            l.add(s);
            return l;
        }
        List<Set> finalListSet = new ArrayList<>();

        for (Set set : list) {
            for (Set set1 : list1) {
                List<Element> el = set.getElements();
                List<Element> el1 = set1.getElements();

                for (int j = 0; j < el1.size(); j++) {

                    if (Integer.parseInt(set.getLastElement().getValue()) < Integer.parseInt(el1.get(j).getValue())) {
                        List<Element> aux = new ArrayList<>();
                        aux.addAll(el);
                        aux.addAll(el1);
                        Set s = new Set(aux);
                        s.setName("CONJUNTO DAS PARTES");
                        finalListSet.add(s);
                    }
                }

            }
        }
        return finalListSet;
    }
    
    public List<Set> reverseCartesianProduct(Set set1){
        Set setA = new Set();
        Set setB = new Set();
        List<Set> list = new ArrayList<>();
        
        String nameA = set1.getPairs().get(0).getFirstElementPair().getSet();
        String nameB = set1.getPairs().get(0).getSecondElementPair().getSet();
        System.out.println("A = "+ nameA+"  B = "+nameB);
        
        for (int i = 0; i < set1.getPairs().size(); i++) {
            
            if(set1.getPairs().get(i).getFirstElementPair().getSet().equals(nameA)){// || set1.getPairs().get(i).getSecondElementPair().getSet().equals(nameA)){
                
                if (!this.pertinence(setA, set1.getPairs().get(i).getFirstElementPair())) {
                    setA.setElements(set1.getPairs().get(i).getFirstElementPair());
                }
                setA.setName(nameA);
                
            }
            if(set1.getPairs().get(i).getSecondElementPair().getSet().equals(nameB)){// || set1.getPairs().get(i).getFirstElementPair().getSet().equals(nameB)){
                
                if (!this.pertinence(setB, set1.getPairs().get(i).getSecondElementPair())) {
                    setB.setElements(set1.getPairs().get(i).getSecondElementPair());
                }
                setB.setName(nameB);
                
            }  
        }
        list.add(setA);
        list.add(setB);
        
        return list;
    }
    public Set reverseSetOfParties(List<Set> sets){
        Set set = new Set();
        for (int i = 0; i < sets.size(); i++) {
            if (sets.get(i).getElements().size() == 1) {
                set.setElements(sets.get(i).getElements().get(0));
            }
        }
        set.setName(sets.get(0).getElements().get(0).getSet());
        return set;
    }
    
    public List<Object> solveExercisesOne(Set set1, Set set2){
        return this.cartesianProduct(set1, set2);
    }
    
    public List<Object> solveExercisesTwo(List<Set> list){
        List<Object> result = new ArrayList<>();
        List<Element> average = new ArrayList<>();
        
        result.add(this.intersection(list));
        result.add(this.union(list));
       
        return result;
    }
    
    public List<Object> solveExercisesThree(Element element, Set sem1, Set sem2, Set mes){
        List<Object> result = new ArrayList<>();
        
        List<Set> list = new ArrayList<>();
        list.add(sem1);
        list.add(sem2);
        Set union = this.union(list);
        list.clear();
        
        list.add(union);
        list.add(mes);
        Set intersection = this.intersection(list);
        
        boolean pertinence = this.pertinence(union, element);
        
        result.add(pertinence);
        result.add(intersection);

        return result;
    }
}
