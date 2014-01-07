/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carrinho.servlets;

import com.carrinho.model.entities.Usuarios;
import com.carrinho.model.entities.maneger.GenericDao;
import com.carrinho.model.entities.maneger.ParametersQuery;
import com.carrinho.utils.Response;
import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author R3oLoN
 */
@WebServlet(name = "CadUsuarioServlet", urlPatterns = {"/cad_usuario", "/cad_usuario/create"})
public class CadUsuarioServlet extends CadServlet {

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getRequestURI().endsWith("/cad_usuario")) {
            response.sendRedirect("/carrinho/cad_usuario.jsp");
        } else if (request.getRequestURI().endsWith("/cad_usuario/create")) {
            String data = getData(request);
            Response<Usuarios> responseData = null;
            Usuarios usuario = gson.fromJson(data, Usuarios.class);
            try {
                boolean existByNameQuery = GenericDao.getExistByNameQuery("Usuarios.findByLogin", new ParametersQuery("login", usuario.getLogin()));
                if (!existByNameQuery) {
                    GenericDao.create(usuario);
                    responseData = new Response<Usuarios>(usuario, "success", "Usuário criado com sucesso.");
                } else {
                    responseData = new Response<Usuarios>(usuario, "error", "O Usuário informado já está em uso.");
                }
            } catch (Exception ex) {
                responseData = new Response<Usuarios>(usuario, "error", "Erro ao criar registro: " + ex.getMessage());
            } finally {
                sendResponseJson(response, responseData);
            }
        }
    }
}
