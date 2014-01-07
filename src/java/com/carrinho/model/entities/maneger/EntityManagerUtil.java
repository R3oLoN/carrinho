/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carrinho.model.entities.maneger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author R3oLoN
 */
public class EntityManagerUtil {

    protected EntityManagerFactory managerFactory;
    protected EntityManager manager;

    protected void close() {
        manager.close();
        managerFactory.close();
    }

    protected EntityManagerUtil() {
        managerFactory = Persistence.createEntityManagerFactory("CarrinhoPU");
        manager = managerFactory.createEntityManager();
    }

    protected EntityManager getManager() {
        return manager;
    }
}
