/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carrinho.servlets;

import com.carrinho.model.entities.Listas;
import com.carrinho.model.entities.ListasItens;
import com.carrinho.model.entities.Usuarios;
import com.carrinho.model.entities.maneger.GenericDao;
import com.carrinho.model.entities.maneger.ParametersQuery;
import com.carrinho.utils.Response;
import com.carrinho.utils.Util;
import java.io.IOException;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author R3oLoN
 */
@WebServlet(name = "CadListaServlet", urlPatterns = {"/cad_lista", "/cad_lista/create",
    "/cad_lista/update", "/cad_lista/first", "/cad_lista/last", "/cad_lista/next", "/cad_lista/previous", "/cad_lista/exclude", "/cad_lista/item/exclude"})
public class CadListaServlet extends CadServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        if (requestURI.endsWith("/cad_lista")) {
            response.sendRedirect("/carrinho/cad_lista.jsp");
        } else if (requestURI.endsWith("/cad_lista/create")) {
            String data = getData(request);
            Response<Listas> responseData = null;
            Listas lista = gson.fromJson(data, Listas.class);
            Usuarios sessionUser = Util.getSessionUser(request);
            lista.setIUsuario(sessionUser);
            try {
                if (lista.getListasItensCollection() != null && lista.getListasItensCollection().size() > 0) {
                    Collection<ListasItens> listasItensCollection = lista.getListasItensCollection();
                    lista.setListasItensCollection(null);
                    for (ListasItens item : listasItensCollection) {
                        item.setILista(lista);
                        lista.addListasItens(item);
                    }
                }
                GenericDao.create(lista);
                lista.setIUsuario(null);
                if (lista.getListasItensCollection() != null && lista.getListasItensCollection().size() > 0) {
                    for (ListasItens item : lista.getListasItensCollection()) {
                        item.setILista(null);
                    }
                }
                responseData = new Response<Listas>(lista, "success", "Lisa criada com sucesso.");
            } catch (Exception ex) {
                responseData = new Response<Listas>(null, "error", "Erro ao criar registro: " + ex.getMessage());
            } finally {
                sendResponseJson(response, responseData);
            }
        } else if (requestURI.endsWith("/cad_lista/update")) {
            String data = getData(request);
            Listas lista = gson.fromJson(data, Listas.class);
            Response<Listas> responseData = null;
            try {
                lista.setIUsuario(Util.getSessionUser(request));
                if (lista.getListasItensCollection() != null && lista.getListasItensCollection().size() > 0) {
                    for (ListasItens item : lista.getListasItensCollection()) {
                        item.setILista(lista);
                    }
                }
                GenericDao.update(lista);
                lista.setIUsuario(null);
                if (lista.getListasItensCollection() != null && lista.getListasItensCollection().size() > 0) {
                    for (ListasItens item : lista.getListasItensCollection()) {
                        item.setILista(null);
                    }
                }
                responseData = new Response<Listas>(lista, "success", "Lisa atualizada com sucesso.");
            } catch (Exception ex) {
                responseData = new Response<Listas>(null, "error", "Erro ao atualizar registro: " + ex.getMessage());
            } finally {
                sendResponseJson(response, responseData);
            }
        } else if (requestURI.endsWith("/cad_lista/exclude")) {
            String data = getData(request);
            Integer iLista = Integer.valueOf(data);
            Response<Listas> responseData = null;
            try {
                GenericDao.remove(iLista, Listas.class);
                responseData = new Response<Listas>(null, "success", "Lisa excluida com sucesso.");
            } catch (Exception ex) {
                responseData = new Response<Listas>(null, "error", "Erro ao excluir registro: " + ex.getMessage());
            } finally {
                sendResponseJson(response, responseData);
            }
        } else if (requestURI.endsWith("/cad_lista/item/exclude")) {
            String data = getData(request);
            Integer iItem = Integer.valueOf(data);
            Response<Object> responseData = null;
            try {
                GenericDao.remove(iItem, ListasItens.class);
                responseData = new Response<Object>(null, "success", "Item excluido com sucesso.");
            } catch (Exception ex) {
                responseData = new Response<Object>(null, "error", "Erro ao excluir registro: " + ex.getMessage());
            } finally {
                sendResponseJson(response, responseData);
            }
        } else if (requestURI.endsWith("/cad_lista/first")) {
            Response<Listas> responseData = null;
            try {
                Listas lista = (Listas) GenericDao.getFirstByNameQuery("Listas.findFirst", new ParametersQuery("iUsuario", Util.getSessionUser(request)));
                if (lista != null) {
                    lista.setIUsuario(null);
                    if (lista.getListasItensCollection() != null && lista.getListasItensCollection().size() > 0) {
                        for (ListasItens item : lista.getListasItensCollection()) {
                            item.setILista(null);
                        }
                    }
                }
                responseData = new Response<Listas>(lista, "success", null);
            } catch (Exception ex) {
                responseData = new Response<Listas>(null, "error", "Erro ao buscar registro: " + ex.getMessage());
            } finally {
                sendResponseJson(response, responseData);
            }
        } else if (requestURI.endsWith("/cad_lista/last")) {
            Response<Listas> responseData = null;
            try {
                Listas lista = (Listas) GenericDao.getFirstByNameQuery("Listas.findLast", new ParametersQuery("iUsuario", Util.getSessionUser(request)));
                if (lista != null) {
                    lista.setIUsuario(null);
                    if (lista.getListasItensCollection() != null && lista.getListasItensCollection().size() > 0) {
                        for (ListasItens item : lista.getListasItensCollection()) {
                            item.setILista(null);
                        }
                    }
                }
                responseData = new Response<Listas>(lista, "success", null);
            } catch (Exception ex) {
                responseData = new Response<Listas>(null, "error", "Erro ao buscar registro: " + ex.getMessage());
            } finally {
                sendResponseJson(response, responseData);
            }
        } else if (requestURI.endsWith("/cad_lista/previous")) {
            String data = getData(request);
            Integer iLInteger = Integer.valueOf(data);
            Response<Listas> responseData = null;
            try {
                Listas lista = (Listas) GenericDao.getFirstByNameQuery("Listas.findPrevious", new ParametersQuery("iLista", iLInteger), new ParametersQuery("iUsuario", Util.getSessionUser(request)));
                if (lista != null) {
                    lista.setIUsuario(null);
                    if (lista.getListasItensCollection() != null && lista.getListasItensCollection().size() > 0) {
                        for (ListasItens item : lista.getListasItensCollection()) {
                            item.setILista(null);
                        }
                    }
                    responseData = new Response<Listas>(lista, "success", null);
                }
            } catch (Exception ex) {
                responseData = new Response<Listas>(null, "error", "Erro ao buscar registro: " + ex.getMessage());
            } finally {
                sendResponseJson(response, responseData);
            }
        } else if (requestURI.endsWith("/cad_lista/next")) {
            String data = getData(request);
            Integer iLInteger = Integer.valueOf(data);
            Response<Listas> responseData = null;
            try {
                Listas lista = (Listas) GenericDao.getFirstByNameQuery("Listas.findNext", new ParametersQuery("iLista", iLInteger), new ParametersQuery("iUsuario", Util.getSessionUser(request)));
                if (lista != null) {
                    lista.setIUsuario(null);
                    if (lista.getListasItensCollection() != null && lista.getListasItensCollection().size() > 0) {
                        for (ListasItens item : lista.getListasItensCollection()) {
                            item.setILista(null);
                        }
                    }
                    responseData = new Response<Listas>(lista, "success", null);
                }
            } catch (Exception ex) {
                responseData = new Response<Listas>(null, "error", "Erro ao buscar registro: " + ex.getMessage());
            } finally {
                sendResponseJson(response, responseData);
            }
        }
    }
}
