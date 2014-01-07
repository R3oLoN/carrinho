/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carrinho.model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author R3oLoN
 */
@Entity
@Table(name = "listas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Listas.findAll", query = "SELECT l FROM Listas l"),
    @NamedQuery(name = "Listas.findByILista", query = "SELECT l FROM Listas l WHERE l.iLista = :iLista"),
    @NamedQuery(name = "Listas.findByDescricao", query = "SELECT l FROM Listas l WHERE l.descricao = :descricao"),
    @NamedQuery(name = "Listas.findAllByUser", query = "SELECT l FROM Listas l WHERE l.iUsuario = :iUsuario"),
    @NamedQuery(name = "Listas.findFirst", query = "SELECT l FROM Listas l WHERE l.iLista = (SELECT MIN(m.iLista) FROM Listas m WHERE m.iUsuario = :iUsuario)"),
    @NamedQuery(name = "Listas.findLast", query = "SELECT l FROM Listas l WHERE l.iLista = (SELECT MAX(m.iLista) FROM Listas m WHERE m.iUsuario = :iUsuario)"),
    @NamedQuery(name = "Listas.findPrevious", query = "SELECT l FROM Listas l WHERE l.iLista = (SELECT MAX(m.iLista) FROM Listas m WHERE m.iLista < :iLista AND m.iUsuario = :iUsuario)"),
    @NamedQuery(name = "Listas.findNext", query = "SELECT l FROM Listas l WHERE l.iLista = (SELECT MIN(m.iLista) FROM Listas m WHERE m.iLista > :iLista AND m.iUsuario = :iUsuario)")})

public class Listas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "i_lista")
    private Integer iLista;
    @Basic(optional = false)
    @Column(name = "descricao")
    private String descricao;
    @JoinColumn(name = "i_usuario", referencedColumnName = "i_usuario")
    @ManyToOne(optional = false)
    private Usuarios iUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iLista")
    private Collection<ListasItens> listasItensCollection;

    public void addListasItens(ListasItens item) {
        if (this.listasItensCollection == null) {
            listasItensCollection = new ArrayList<ListasItens>();
        }
        item.setILista(this);
        listasItensCollection.add(item);
    }

    public Listas() {
    }

    public Listas(Integer iLista) {
        this.iLista = iLista;
    }

    public Listas(Integer iLista, String descricao) {
        this.iLista = iLista;
        this.descricao = descricao;
    }

    public Integer getILista() {
        return iLista;
    }

    public void setILista(Integer iLista) {
        this.iLista = iLista;
    }
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Usuarios getIUsuario() {
        return iUsuario;
    }

    public void setIUsuario(Usuarios iUsuario) {
        this.iUsuario = iUsuario;
    }

    @XmlTransient
    public Collection<ListasItens> getListasItensCollection() {
        return listasItensCollection;
    }

    public void setListasItensCollection(Collection<ListasItens> listasItensCollection) {
        this.listasItensCollection = listasItensCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iLista != null ? iLista.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Listas)) {
            return false;
        }
        Listas other = (Listas) object;
        if ((this.iLista == null && other.iLista != null) || (this.iLista != null && !this.iLista.equals(other.iLista))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carrinho.model.entities.Listas[ iLista=" + iLista + " ]";
    }

}
