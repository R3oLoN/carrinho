/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carrinho.servlets;

import com.carrinho.model.entities.Listas;
import com.carrinho.model.entities.ListasItens;
import com.carrinho.model.entities.maneger.GenericDao;
import com.carrinho.model.entities.maneger.ParametersQuery;
import com.carrinho.utils.Response;
import com.carrinho.utils.Util;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author R3oLoN
 */
@WebServlet(name = "ComprasServlet", urlPatterns = {"/compras", "/compras/creat"})
public class ComprasServlet extends CadServlet {
    
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.endsWith("/compras")) {
            if (Util.isEmpty(getData(request))) {
                response.sendRedirect("/carrinho/compras.jsp");
            } else {
                request.setAttribute("data", getData(request));
                request.getRequestDispatcher("/compras.jsp")
                        .forward(request, response);
            }
        } else if (uri.endsWith("/compras/creat")) {
            String data = getData(request);
            Integer iLista = Integer.valueOf(data);
            Response<Listas> responseData = null;
            try {
                Listas lista = (Listas) GenericDao.getFirstByNameQuery("Listas.findByILista", new ParametersQuery("iLista", iLista));
                lista.setIUsuario(null);
                if(lista.getListasItensCollection()!=null){
                    for (ListasItens item : lista.getListasItensCollection()) {
                        item.setILista(null);
                    }
                }
                responseData = new Response<Listas>(lista, "success", "Compra iniciada.");
            } catch (Exception ex) {
                responseData = new Response<Listas>(null, "error", "Erro ao iniciar compra: " + ex.getMessage());
            } finally {
                sendResponseJson(response, responseData);
            }
        }
    }
}
