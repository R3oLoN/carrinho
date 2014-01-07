/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carrinho.servlets;

import com.carrinho.model.entities.Listas;
import com.carrinho.model.entities.maneger.GenericDao;
import com.carrinho.model.entities.maneger.ParametersQuery;
import com.carrinho.utils.Response;
import com.carrinho.utils.Util;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author R3oLoN
 */
@WebServlet(name = "MainServlet", urlPatterns = {"/main", "/main/list", "/main/item"})
public class MainServlet extends CadServlet {

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getRequestURI().endsWith("/main")) {
            response.sendRedirect("/carrinho/main.jsp");
        } else if (request.getRequestURI().endsWith("/main/list")) {
            Response<List<Listas>> responseData = null;
            try {
                List<Listas> list = (List<Listas>) GenericDao.getListByNameQuery("Listas.findAllByUser", new ParametersQuery("iUsuario", Util.getSessionUser(request)));
                for (Listas item : list) {
                    item.setIUsuario(null);
                    item.setListasItensCollection(null);
                }
                responseData = new Response<List<Listas>>(list, "success", null);
            } catch (Exception ex) {
                responseData = new Response<List<Listas>>(null, "error", "Erro ao executar consulta: " + ex.getMessage());
            }
            sendResponseJson(response, responseData);
        }
    }
}
