/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.servlet;

import br.com.AlgebraOfSets.AlgebraOfSets;
import br.com.list.SaveTxtList;
import br.com.readFiles.ReadTxtFile;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 55329
 */
@WebServlet("/composition")
public class Composition extends HttpServlet {

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
        
        Gson gson = new Gson();
        String path = this.getServletContext().getRealPath("");
        path = path.replace("build\\web\\", "");
        path+=request.getParameter("fileName");
        
        ReadTxtFile read = new ReadTxtFile(path);
        SaveTxtList save = new SaveTxtList();
        read.read(save);
        AlgebraOfSets algebra = new AlgebraOfSets();
        String operation = request.getParameter("radioCompositionRelations");
        String pairsR = request.getParameter("pairsR");
        String pairsS = request.getParameter("pairsS");
        
        switch(operation){
            case "bigger":
                response.getWriter().write(gson.toJson(algebra.Compositions(save.getListSets(), ">", "","")));
            break;
            case "smaller":
                response.getWriter().write(gson.toJson(algebra.Compositions(save.getListSets(), "<", "","")));
            break;
            case "equal":
                response.getWriter().write(gson.toJson(algebra.Compositions(save.getListSets(), "=", "","")));
            break;
            case "raisedTo2":
                response.getWriter().write(gson.toJson(algebra.Compositions(save.getListSets(), "^2", "","")));
            break;
            case "squareRoot":
                response.getWriter().write(gson.toJson(algebra.Compositions(save.getListSets(), "sqrt", "","")));
            break;
            case "arbitraryCompositionRelationship":
                response.getWriter().write(gson.toJson(algebra.
                        Compositions(save.getListSets(), "arbitraryCompositionRelationship", pairsR, pairsS)));
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

}
