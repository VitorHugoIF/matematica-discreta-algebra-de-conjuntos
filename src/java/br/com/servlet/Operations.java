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
        //response.getWriter().write("true");
        
        String path = "C:/Users/vitor/Documents/NetBeansProjects/matematica-discreta-algebra-de-conjuntos/";
        path+=request.getParameter("fileName");
        ReadTxtFile read = new ReadTxtFile(path);
        
        SaveTxtList save = new SaveTxtList();
        read.read(save);
        AlgebraOfSets algebra = new AlgebraOfSets();
        System.out.println(save.getListSets());
        String operation = request.getParameter("radioOperation");
        
        String param1;
        String param2;
        
        Element element;
        Set set1;
        Set set2;
        Set set3;
        Gson gson = new Gson();
        
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
                set1 = findSet(save.getListSets(), "A");
                set2 = findSet(save.getListSets(), "B");

                response.getWriter().write(gson.toJson(algebra.solveExercisesOne(set1, set2)));
                break;
            
            case "solveExerciseTwo":
                response.getWriter().write(gson.toJson(algebra.solveExercisesTwo(save.getListSets())));
                break;
            
            case "solveExerciseThree":
                set1 = findSet(save.getListSets(), "A");
                set2 = findSet(save.getListSets(), "B");
                set3 = findSet(save.getListSets(), "C");
                response.getWriter().write(gson.toJson(algebra.solveExercisesThree(save.getListElements().get(0), set1, set2, set3)));
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
