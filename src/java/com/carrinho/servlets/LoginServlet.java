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
import com.carrinho.utils.Util;
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
@WebServlet(name = "LoginServlet", urlPatterns = {"/login", "/login/access", "/login/logoff"})
public class LoginServlet extends CadServlet {
 
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getRequestURI().endsWith("/login")) {
            response.sendRedirect("/carrinho/login.jsp");
        } else if (request.getRequestURI().endsWith("/login/access")) {
            Response<Login> responseData = null;
            if (!Util.isEmpty(request.getParameter("data"))) {
                String data = getData(request);
                Login login = gson.fromJson(data, Login.class);
                Usuarios usuario = null;
                try {
                    usuario = (Usuarios) GenericDao.getFirstByNameQuery("Usuarios.login",
                            new ParametersQuery("login", login.getUser()),
                            new ParametersQuery("senha", login.getPassword()));
                    if (usuario != null) {
                        usuario.setListasCollection(null);
                        request.getSession().setAttribute("sessionUser", usuario);
                        responseData = new Response<Login>(login, "success", "Login efetuado com sucesso.");
                    } else {
                        responseData = new Response<Login>(login, "error", "Usuário ou senha incorreto!");
                    }
                } catch (Exception ex) {
                    responseData = new Response<Login>(login, "error", "Erro tentar logar: " + ex.getMessage());
                } finally {
                    sendResponseJson(response, responseData);
                }
            }else{
                responseData = new Response<Login>(null, "error", "Usuário e senha devem ser informados!");
                response.getWriter().print(gson.toJson(responseData));
            }
        } else if (request.getRequestURI().endsWith("/login/logoff")) {
            request.getSession().removeAttribute("sessionUser");
            response.sendRedirect("/carrinho/login");
        } 
    }

    public class Login {

        private String user;
        private String password;

        public Login() {
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

    }
}
