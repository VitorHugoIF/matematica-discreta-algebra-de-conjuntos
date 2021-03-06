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
     * metodos elementares - pertinence - retorno Boleano.
     *
     * @param set1
     * @param set2
     * @return
     */
    public boolean contained(Set set1, Set set2) {
        if (set1.getElements().get(0).getValue().equals("vazio")) {
            return true;
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
     * utiliza de metodos elementares - contained - retorno Boleano.
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
     * paramentro. Utiliza de metodos elementares - pertinence - retorno Boleano.
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
     * como paramentro. retorno Boleano.
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
     * elementares - pertinence - retorno Boleano.
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
   
    /**
     * Retorna uma lista de objetos 
     * posicao 0 = Conjunto representando o Produto cartesiano entre dois conjuntos
     * posicao 1 = Conjunto representando a operacao inversa ao produto cartesiano
     * @param set1
     * @param set2
     * @return 
     */
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
    
    /**
     * Retorna uma lista de objetos 
     * posicao 0 = Conjunto representando o conjunto das partes de um conjunto
     * posicao 1 = Conjunto representando a operacao inversa ao conjunto das partes
     * @param set
     * @return 
     */
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
        //conjunto inicial com uma lista de cunjuntos unitarios de cada elemento
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
    
    //retorna uma lista de conjuntos resultado dos subconjuntos das listas em parametro
    /**
     * uma lista de conjuntos resultado dos subconjuntos das listas em parametro
     * a segunda lista no parametro deve ser uma lista de conjuntos unitarios
     *
     * @param list
     * @param list1
     * @param q
     * @return 
     */
    private List<Set> subs(List<Set> list, List<Set> list1, int q) {
        
        //se a cardinalidade e 0, retorna o conjunto vazio
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
                    //Verifica se o ultimo elemento do par e menor que o elemento cadidato a entrar no conjunto
                    if (Integer.parseInt(set.getLastElement().getValue()) < Integer.parseInt(el1.get(j).getValue())) {
                        List<Element> aux = new ArrayList<>();
                        aux.addAll(el);//adiciona a lista de elementos do primeiro conjunto
                        aux.addAll(el1);//adiciona a lista de elementos do segundo conjunto
                        Set s = new Set(aux);
                        s.setName("CONJUNTO DAS PARTES");
                        finalListSet.add(s);
                    }
                }

            }
        }
        return finalListSet;
    }
    
    //retorna uma lista que representa o operacao inversa ao produto cartesiano
    /**
     * Retorna uma lista de conjuntos resultado dos subconjuntos das listas em parametro
     * elementares - pertenceNaoPertence retorno Boleano.
     *
     * @param list
     * @param list1
     * @param q
     * @return 
     */
    private List<Set> reverseCartesianProduct(Set set1){
        Set setA = new Set();
        Set setB = new Set();
        List<Set> list = new ArrayList<>();
        
        String nameA = set1.getPairs().get(0).getFirstElementPair().getSet();
        String nameB = set1.getPairs().get(0).getSecondElementPair().getSet();
        
        //para cada elemento do par verifica se ja ele pertence ao conjunto resultado,
        // caso nao, adiciona-o
        for (int i = 0; i < set1.getPairs().size(); i++) {
            
            //pertence ao primeiro conjunto origem
            if(set1.getPairs().get(i).getFirstElementPair().getSet().equals(nameA)){
                
                if (!this.pertinence(setA, set1.getPairs().get(i).getFirstElementPair())) {
                    setA.setElements(set1.getPairs().get(i).getFirstElementPair());
                }
                setA.setName(nameA);
                
            }
            //pertence ao segundo conjunto origem
            if(set1.getPairs().get(i).getSecondElementPair().getSet().equals(nameB)){
                
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
    //retorna uma lista que representa o operacao inversa ao conjunto das partes
    /**
     * Retorna uma lista que representa o operacao inversa ao conjunto das partes
     *
     * @param sets
     * @return 
     */
    private Set reverseSetOfParties(List<Set> sets){
        //caso o conjunto da lista de subconjuntos seja unitario
        //adiciona o elemento ao conjunto origem
        Set set = new Set();
        for (int i = 0; i < sets.size(); i++) {
            if (sets.get(i).getElements().size() == 1) {
                set.setElements(sets.get(i).getElements().get(0));
            }
        }
        set.setName(sets.get(0).getElements().get(0).getSet());
        return set;
    }
    
    /*Os times 4, 8, 12 e 16 do estado de Pernambuco devem jogar com os times 3, 7, 11 e 15 do estado do Para.
    Cada time de Pernambuco deve jogar uma vez com cada time do Para. Exiba os jogos que devem ser realizados.*/
    public List<Object> solveExercisesOne(List<Set> list){
        Set set1 = list.get(0);
        Set set2 = list.get(1);
        return this.cartesianProduct(set1, set2);
    }
    
    /*Suponha que voce tem o codigo (numerico) dos alunos que obtiveram media na disciplina A, o codigo dos alunos
    com media na disciplina B e o codigo dos alunos com media na disciplina C. O criterio de aprovacao na escola exige
    media em todas as disciplinas para aprovcao. Exiba a lista dos alunos aprovados. Exiba a lista dos alunos com
    media em pelo menos uma disciplina.*/
    public List<Object> solveExercisesTwo(List<Set> list){
        List<Object> result = new ArrayList<>();        
        result.add(this.intersection(list));
        result.add(this.union(list));
       
        return result;
    }
    
    /*Uma empresa tem a seguinte poltica de premiacao de fim de ano: ganha premio o funcionario que tiver batido sua
    meta no primeiro ou no segundo semestre ou em ambos e que tenha sido o melhor vendedor em pelo menos um mes.
    De posse do codigo do funcionario, da relacao de funcionarios cumpridores da meta em cada semestre e da relacao
    de melhores vendedores do mes, informe se este funcionario recebera ou nao premio de fim de ano.*/
    public List<Object> solveExercisesThree(List<Element> elements, List<Set> sets){
        Element element = elements.get(0);
        Set sem1 = sets.get(0);
        Set sem2 = sets.get(1);
        Set mes = sets.get(2);
        
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
    
    public List<Object> Relationships(List<Set> list, String relation, String StringPairs){
        List<Object> result = new ArrayList<>();
        Set pairs = new Set();pairs.setName("PARES");
        Set domain = new Set();domain.setName("DOMINIO");
        Set image = new Set();image.setName("IMAGEM");
        Set set1 = list.get(0);
        Set set2 = list.get(1);
        int matrix[][];

        if (relation.equals("arbitraryRelationship")) {
           
            result = this.generateArbitraryRelationPairs(StringPairs);            
            matrix = this.generateMatrix(set1, set2, (Set) result.get(0));
            result.add(this.getRelationshipClassification(matrix));
            result.add(matrix);
            if (!checkPairPertinence((Set) result.get(0), set1, set2)) {
                result.add("PARES INCORRETOS");
            }
           
           return result;
        } else {
            
            for (int i = 0; i < set1.getElements().size(); i++) {
                for (int j = 0; j < set2.getElements().size(); j++) {
                    this.setRelationship(pairs, domain, image, set1.getElements().get(i), set2.getElements().get(j),
                            relation.equals("=") ? "=" : relation.equals(">") ? ">" : relation.equals("<") ? "<"
                            : relation.equals("^2") ? "^2" : "sqrt");
                }
            }

            matrix = this.generateMatrix(set1, set2, pairs);

            result.add(pairs);
            result.add(domain);
            result.add(image);
            result.add(this.getRelationshipClassification(matrix));
            result.add(matrix);
            return result;
        }
    }
    
    private void setRelationship(Set pairs, Set domain, Set image, Element a, Element b, String relation){
        Pair pair = new Pair();
        switch (relation) {
            case ">":
                if (a.getParseValue() > b.getParseValue()) {
                    pair.setFirstElementPair(a);
                    pair.setSecondElementPair(b);
                    this.setDomain(domain, a);
                    this.setImage(image, b);
                    pairs.setPairs(pair);
                }   break;
            case "<":
                if (a.getParseValue() < b.getParseValue()) {
                    pair.setFirstElementPair(a);
                    pair.setSecondElementPair(b);
                    this.setDomain(domain, a);
                    this.setImage(image, b);
                    pairs.setPairs(pair);
                }   break;
            case "=":
                if (a.getParseValue() == b.getParseValue()) {
                    pair.setFirstElementPair(a);
                    pair.setSecondElementPair(b);
                    this.setDomain(domain, a);
                    this.setImage(image, b);
                    pairs.setPairs(pair);
                }   break;
                case "^2":
                if (a.getParseValue() == Math.pow(b.getParseValue(),2)) {
                    pair.setFirstElementPair(a);
                    pair.setSecondElementPair(b);
                    this.setDomain(domain, a);
                    this.setImage(image, b);
                    pairs.setPairs(pair);
                }break;
            case "sqrt":
                if (a.getParseValue() == Math.sqrt(b.getParseValue())) {
                    pair.setFirstElementPair(a);
                    pair.setSecondElementPair(b);
                    this.setDomain(domain, a);
                    this.setImage(image, b);
                    pairs.setPairs(pair);
                }break;
                 
            default:
                break;
        }
    }
    
    private void setDomain(Set domain, Element element){
        if (!this.pertinence(domain, element)) {
            domain.setElements(element);
        }
    }
    private void setImage(Set image, Element element){
        if (!this.pertinence(image, element)) {
            image.setElements(element);
        }
    }
    
    public List<Object> generateArbitraryRelationPairs(String pairs) {
        Set listPairs = new Set();listPairs.setName("PARES");
        Set domain = new Set();domain.setName("DOMINIO");
        Set image = new Set();image.setName("IMAGEM");
        List<Object> list = new ArrayList<>();
        
        if (pairs.equals("")) {
            list.add(listPairs);
            list.add(domain);
            list.add(image);
            return list;
        } else {
            
            String ArrayOfPairs[] = pairs.split(";");
            
            for (String ArrayOfPair : ArrayOfPairs) {
                String[] ArrayOfElements = ArrayOfPair.split(",");
                

                Pair pair = new Pair();
                Element e1 = new Element("Arbitrary", "domain", ArrayOfElements[0]);
                Element e2 = new Element("Arbitrary", "image", ArrayOfElements[1]);
                pair.setFirstElementPair(e1);
                pair.setSecondElementPair(e2);
                this.setDomain(domain, e1);
                this.setImage(image, e2);
                listPairs.setPairs(pair);
            }
            list.add(listPairs);
            list.add(domain);
            list.add(image);
            return list;
        }

    }
    
    public int [][] generateMatrix(Set set1, Set set2, Set pairs){
        
        int matrix [][] = new int[set1.getElements().size()][set2.getElements().size()];
        
        for (int i = 0; i < set1.getElements().size(); i++) {
            for (int j = 0; j < set2.getElements().size(); j++) {
                
                if (pairs.checkPairPertinence(set1.getElements().get(i), set2.getElements().get(j))) {
                    matrix[i][j] = 1;
                }else{
                    matrix[i][j] = 0;
                }
                
            }
        }
        return matrix;
    }
    
    public List getRelationshipClassification(int[][] matrix){
        
        int row[] = new int[matrix.length];
        int column[] = new int[matrix[matrix.length - 1].length];
        
        for (int i = 0; i < matrix.length; i++) {
            row[i]=0;
        }
        for (int j = 0; j < matrix[matrix.length - 1].length; j++) {
            column[j]=0;
        }

        List<Object> listFinal = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            
            for (int j = 0; j < matrix[i].length; j++) {

                if (matrix[i][j] == 1) {
                    row[i] = row[i] + 1;
                    column[j] = column[j] + 1;
                }

            }
        }


        if (checkFunctional(row)) {
            listFinal.add("Funcional");
        }
        if (checkInjector(column)) {
            listFinal.add("Injetora");
        }
        if (checkTotal(row)) {
            listFinal.add("Total");
        }
        if (checkOverjet(column)) {
            listFinal.add("Sobrejetora");
        }
        if (checkMonomorphism(row, column)) {
            listFinal.add("Monomorfismo");
        }
        if (checkEpimorphism(row, column)) {
            listFinal.add("Epimorfismo");
        }
        if (checkIsomorphism(row, column)) {
            listFinal.add("Isomorfismo");
        }
        //listFinal.add(row);
        //listFinal.add(column);
        return listFinal;
    }
    private boolean checkFunctional(int row[]){
        for (int i : row) {
            if (i > 1) {
                return false;
            }
        }
        return true;
    }
    private boolean checkInjector(int column[]){
        for (int i : column) {
            if (i > 1) {
                return false;
            }
        }
        return true;
    }
    private boolean checkTotal(int row[]){
        for (int i : row) {
            if (i < 1) {
                return false;
            }
        }
        return true;
    }
    private boolean checkOverjet(int column[]){
        for (int i : column) {
            if (i < 1) {
                return false;
            }
        }
        return true;
    }
    private boolean checkMonomorphism(int row[], int column[]){
        return (this.checkTotal(row) && this.checkInjector(column));
    }
    private boolean checkEpimorphism(int row[], int column[]){
        return (this.checkFunctional(row) && this.checkOverjet(column));
    }
    private boolean checkIsomorphism(int row[], int column[]){
        return (this.checkMonomorphism(row, column) && this.checkEpimorphism(row, column));
    }
    
    private boolean checkPairPertinence(Set pairs, Set set1, Set set2){
        Set domain = new Set();
        Set image = new Set();
        for (Pair pair : pairs.getPairs()) {
            this.setDomain(domain, pair.getFirstElementPair());
            this.setImage(image, pair.getSecondElementPair());
        }
        
        for (Element element : domain.getElements()) {
            if (!this.pertinence(set1, element)) {
                return false;
            }
        }
        for (Element element : image.getElements()) {
            if (!this.pertinence(set2, element)) {
                return false;
            }
        }
        return true;
    }
    
    public List<Object> Compositions (List<Set> list, String relation, String StringPairsR, String StringPairsS){
        List<Set> listR = new ArrayList<>();
        List<Set> listS = new ArrayList<>();
        List<Object> result = new ArrayList<>();
        
        listR.add(list.get(0));
        listR.add(list.get(1));
        listS.add(list.get(1));
        listS.add(list.get(2));
        
        List<Object> compositionR;
        List<Object> compositionS;
        int matrix[][];
        
        if (relation.equals("arbitraryCompositionRelationship")) {
            compositionR = this.Relationships(listR, "arbitraryRelationship", StringPairsR);
            compositionS = this.Relationships(listS, "arbitraryRelationship", StringPairsS);
            
        }else{
            compositionR = this.Relationships(listR, relation, StringPairsR);
            compositionS = this.Relationships(listS, relation, StringPairsS);
        }
        
        
        Set pairsR = (Set) compositionR.get(0);
        Set pairsS = (Set) compositionS.get(0);
        int[][] matrixR =  (int[][]) compositionR.get(4);
        int[][] matrixS =  (int[][]) compositionS.get(4);
        
        Set listPairs = new Set();listPairs.setName("PARES");
        Set domain = new Set();domain.setName("DOMINIO");
        Set image = new Set();image.setName("IMAGEM");
        System.out.println( pairsR.getPairs());
        System.out.println( "------------------------");
        System.out.println( pairsS.getPairs());
        
        for (Pair pairR : pairsR.getPairs()) {
           
            for (Pair pairS : pairsS.getPairs()) {
                System.out.println("----------");
                System.out.println(pairR.getSecondElementPair()+" = "+pairS.getFirstElementPair());
                if (pairR.getSecondElementPair().getParseValue() == pairS.getFirstElementPair().getParseValue()) {
                    System.out.println("igual");
                    Pair p = new Pair();
                    p.setFirstElementPair(pairR.getFirstElementPair());
                    p.setSecondElementPair(pairS.getSecondElementPair());
                    listPairs.setPairs(p);
                    
                    this.setDomain(domain, pairR.getFirstElementPair());
                    this.setImage(image, pairS.getSecondElementPair());
                }
            }
        }
        matrix = this.matrixProduct(matrixR, matrixS);
        result.add(listPairs);
        result.add(domain);
        result.add(image);
        result.add(this.getRelationshipClassification(matrix));
        result.add(matrix);
        
        if (compositionR.size() > 5 || compositionS.size() > 5) {
            result.add("PARES INCORRETOS");
        }
            
        
        return result;
    }
    
    private int[][] matrixProduct(int[][] matrixR, int[][] matrixS) {
        int[][] matrixComposition = new int[matrixR.length][matrixS[matrixS.length - 1].length];

        for (int i = 0; i < matrixR.length; i++) {
            for (int j = 0; j < matrixS[matrixS.length - 1].length; j++) {
                int sum = 0;
                for (int k = 0; k < matrixR.length; k++) {
                    sum += (matrixR[i][k] * matrixS[k][j]);
                }
                if (sum > 0) {
                    matrixComposition[i][j] = 1;
                } else {
                    matrixComposition[i][j] = 0;
                }
            }
        }
        return matrixComposition;
    }
}
