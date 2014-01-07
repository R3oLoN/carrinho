/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carrinho.utils;

import com.carrinho.model.entities.Usuarios;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author R3oLoN
 */
public class Util {
    public static boolean isEmpty(String string){
        return string == null || string.length()==0 || "".equals(string.trim()) ;
    }
    public static Usuarios getSessionUser(HttpServletRequest request){
        return (Usuarios) request.getSession().getAttribute("sessionUser");
    }
}
