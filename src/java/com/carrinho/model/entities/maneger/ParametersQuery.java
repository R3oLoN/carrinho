/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carrinho.model.entities.maneger;

/**
 *
 * @author R3oLoN
 */
public class ParametersQuery {
    private String name;
    private Object value;

    public ParametersQuery() {
    }

    public ParametersQuery(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
    
}
