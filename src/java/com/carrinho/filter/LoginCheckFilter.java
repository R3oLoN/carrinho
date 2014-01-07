/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carrinho.filter;

import com.carrinho.model.entities.Usuarios;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author R3oLoN
 */
public class LoginCheckFilter implements Filter {

    private static final boolean debug = false;
    private FilterConfig filterConfig = null;

    public LoginCheckFilter() {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("LoginFilter:DoBeforeProcessing");
        }
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("LoginFilter:DoAfterProcessing");
        }
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        if (debug) {
            log("LoginFilter:doFilter()");
        }
        doBeforeProcessing(request, response);
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        if (filter(servletRequest)) {
            HttpServletResponse servletResponse = (HttpServletResponse) response;
            HttpSession session = servletRequest.getSession();
            Object user = session.getAttribute("sessionUser");
            if (user == null || !(user instanceof Usuarios)) {
                if (redirectForLogin(servletRequest)) {
                    servletResponse.sendRedirect("/carrinho/login");
                    return;
                }
                servletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Sua sessão expirou.");
                return;
            }
        }
        Throwable problem = null;
        try {
            chain.doFilter(request, response);
        } catch (Throwable t) {
            problem = t;
            t.printStackTrace();
        }
        doAfterProcessing(request, response);
        if (problem != null) {
            if (problem instanceof ServletException) {
                throw (ServletException) problem;
            }
            if (problem instanceof IOException) {
                throw (IOException) problem;
            }
            sendProcessingError(problem, response);
        }
    }

    private boolean redirectForLogin(HttpServletRequest servletRequest) {
        String uri = servletRequest.getRequestURI();
        return uri.endsWith(".jsp") || uri.endsWith("/carrinho") || uri.endsWith("/carrinho/") || uri.endsWith("/carrinho/main")
                 || uri.endsWith("/carrinho/cad_lista")|| uri.endsWith("/carrinho/compras");
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("LoginFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("LoginFilter()");
        }
        StringBuffer sb = new StringBuffer("LoginFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                HttpServletResponse servletResponse = (HttpServletResponse) response;
                servletResponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Houve um erro no servidor.");
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

    private boolean filter(HttpServletRequest req) {
        boolean filter = true;
        String requestURI = req.getRequestURI();
        if (requestURI.contains("/carrinho/bootstrap/")
                || requestURI.contains("/carrinho/css/")
                || requestURI.contains("/carrinho/js/")
                || requestURI.contains("/carrinho/login")
                || requestURI.contains("/carrinho/cad_usuario")
                || requestURI.contains("/carrinho/images")) {
            filter = false;
        }
        return filter;
    }

}
