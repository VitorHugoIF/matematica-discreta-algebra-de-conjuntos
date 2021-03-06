/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.servlet;

import br.com.AlgebraOfSets.AlgebraOfSets;
import br.com.list.SaveTxtList;
import br.com.list.Set;
import br.com.node.Element;
import br.com.readFiles.ReadTxtFile;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 55329
 */
@WebServlet("/operations")
public class Operations extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        
        //Adiciona o path absoluto ao arquivo
        //String path = "C:/Users/vitor/Documents/NetBeansProjects/matematica-discreta-algebra-de-conjuntos/";
        String path = this.getServletContext().getRealPath("");//"C:/Users/55329/Documents/NetBeansProjects/matematica-discreta-algebra-de-conjuntos/";
        path = path.replace("build\\web\\", "");
        path+=request.getParameter("fileName");
        
        ReadTxtFile read = new ReadTxtFile(path);
        
        //le o arquivo e pega a o peracao a ser realizada
        SaveTxtList save = new SaveTxtList();
        read.read(save);
        AlgebraOfSets algebra = new AlgebraOfSets();
        String operation = request.getParameter("radioOperation");
        
        //Variaveis ultilizadas no processo
        String param1;//Nome do conjunto recebido da combobox 1
        String param2;//Nome do conjunto recebido da combobox 2
        Element element;//Elemento que representa o elemento buscado na funcao findElement
        Set set1;//Conjunto que representa o conjunto buscado na funcao findSet
        Set set2;//Conjunto que representa o conjunto buscado na funcao findSet
        Set set3;//Conjunto que representa o conjunto buscado na funcao findSet
        Gson gson = new Gson();//classe de manipulacao JSON
        
        switch (operation) {
            
            case "pertinence":
                param1 = request.getParameter("objects1");
                param2 = request.getParameter("objects2");
                element = findElement(save.getListElements(), param1);
                set1 = findSet(save.getListSets(), param2);
                
                if (algebra.pertinence(set1, element)) {
                    response.getWriter().write("true");
                }else{
                    response.getWriter().write("false");
                }
                
                break;
            case "contained":
                param1 = request.getParameter("objects1");
                param2 = request.getParameter("objects2");
                set1 = findSet(save.getListSets(), param1);
                set2 = findSet(save.getListSets(), param2);
                
                if (algebra.contained(set1, set2)) {
                    response.getWriter().write("true");
                }else{
                    response.getWriter().write("false");
                }
                
                break;
            case "containedProperly":
                param1 = request.getParameter("objects1");
                param2 = request.getParameter("objects2");
                set1 = findSet(save.getListSets(), param1);
                set2 = findSet(save.getListSets(), param2);
                
                if (algebra.containedProperly(set1, set2)) {
                    response.getWriter().write("true");
                }else{
                    response.getWriter().write("false");
                }
                
                break;
            case "union":
                response.getWriter().write(gson.toJson(algebra.union(save.getListSets())));
                break;
            case "intersection":
                response.getWriter().write(gson.toJson(algebra.intersection(save.getListSets())));
                break;
            case "cartesianProduct":
                param1 = request.getParameter("objects1");
                param2 = request.getParameter("objects2");
                set1 = findSet(save.getListSets(), param1);
                set2 = findSet(save.getListSets(), param2);
                response.getWriter().write(gson.toJson(algebra.cartesianProduct(set1, set2)));
                break;
            case "setOfParties":
                param1 = request.getParameter("objects1");
                set1 = findSet(save.getListSets(), param1);
                response.getWriter().write(gson.toJson(algebra.setOfParties(set1)));
                break; 
            case "solveExerciseOne":
                response.getWriter().write(gson.toJson(algebra.solveExercisesOne(save.getListSets())));
                break;
            
            case "solveExerciseTwo":
                response.getWriter().write(gson.toJson(algebra.solveExercisesTwo(save.getListSets())));
                break;
            
            case "solveExerciseThree":
                response.getWriter().write(gson.toJson(algebra.solveExercisesThree(save.getListElements(), save.getListSets())));
                break;
            
            default:
                break;
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    private Set findSet(List<Set> list, String name){
        for (Set set : list) {
            if (set.getName().equals(name)) {
               return set; 
            }
        }
        return null;
    }
    private Element findElement(List<Element> list, String name){
        for (Element element : list) {
            if (element.getName().equals(name)) {
               return element; 
            }
        }
        return null;
    }
}
