/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carrinho.utils;

/**
 *
 * @author R3oLoN
 */
public class Response<V> {
    private V data;
    private String status;
    private String message;

    public Response() {
    }

    public Response(V data, String status, String message) {
        this.data = data;
        this.status = status;
        this.message = message;
    }

    public V getData() {
        return data;
    }

    public void setData(V data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
