/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carrinho.model.entities.maneger;

import java.util.List;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author R3oLoN
 */
public class GenericDao {

    public static void create(Object entity) throws Exception {
        EntityManagerUtil emu = new EntityManagerUtil();
        try {
            EntityTransaction transaction = emu.getManager().getTransaction();
            transaction.begin();
            emu.getManager().persist(entity);
            transaction.commit();
        } finally {
            emu.close();
        }
    }

    public static void update(Object entity) throws Exception {
        EntityManagerUtil emu = new EntityManagerUtil();
        try {
            EntityTransaction transaction = emu.getManager().getTransaction();
            transaction.begin();
            emu.getManager().merge(entity);
            transaction.commit();
        } finally {
            emu.close();
        }
    }

    public static void remove(Integer id, Class entityClass) throws Exception {
        EntityManagerUtil emu = new EntityManagerUtil();
        try {
            EntityTransaction transaction = emu.getManager().getTransaction();
            transaction.begin();
            emu.getManager().remove(emu.getManager().find(entityClass, id));
            transaction.commit();
        } finally {
            emu.close();
        }
    }

    public static Object getFirstByNameQuery(String nameQuery, ParametersQuery... params) throws Exception {
        EntityManagerUtil emu = new EntityManagerUtil();
        try {
            Query query = emu.getManager().createNamedQuery(nameQuery);
            if (params != null) {
                for (ParametersQuery param : params) {
                    query.setParameter(param.getName(), param.getValue());
                }
            } else {
                return null;
            }
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            emu.close();
        }
    }

    public static List getListByNameQuery(String nameQuery, ParametersQuery... params) throws Exception {
        EntityManagerUtil emu = new EntityManagerUtil();
        try {
            Query query = emu.getManager().createNamedQuery(nameQuery);
            if (params != null) {
                for (ParametersQuery param : params) {
                    query.setParameter(param.getName(), param.getValue());
                }
            } else {
                return null;
            }
            return query.getResultList();
        } finally {
            emu.close();
        }
    }

    public static boolean getExistByNameQuery(String nameQuery, ParametersQuery... params) throws Exception {
        EntityManagerUtil emu = new EntityManagerUtil();
        try {
            Query query = emu.getManager().createNamedQuery(nameQuery);
            if (params != null) {
                for (ParametersQuery param : params) {
                    query.setParameter(param.getName(), param.getValue());
                }
            } else {
                return false;
            }
            List resultList = query.getResultList();
            if (resultList != null && resultList.size() > 0) {
                return true;
            }
        } finally {
            emu.close();
        }
        return false;
    }
}
