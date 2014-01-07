/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carrinho.model.entities;

import java.io.Serializable;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author R3oLoN
 */
@Entity
@Table(name = "listas_itens")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ListasItens.findAll", query = "SELECT l FROM ListasItens l"),
    @NamedQuery(name = "ListasItens.findByIListaItens", query = "SELECT l FROM ListasItens l WHERE l.iListaItens = :iListaItens"),
    @NamedQuery(name = "ListasItens.findByDescricao", query = "SELECT l FROM ListasItens l WHERE l.descricao = :descricao")})
public class ListasItens implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "i_lista_itens")
    private Integer iListaItens;
    @Basic(optional = false)
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "valor")
    private Double valor;
    @Column(name = "unidade")
    private String unidade;
    @JoinColumn(name = "i_lista", referencedColumnName = "i_lista")
    @ManyToOne(optional = false)
    private Listas iLista;

    public ListasItens() {
    }

    public ListasItens(Integer iListaItens) {
        this.iListaItens = iListaItens;
    }

    public ListasItens(Integer iListaItens, String descricao) {
        this.iListaItens = iListaItens;
        this.descricao = descricao;
    }

    public Integer getIListaItens() {
        return iListaItens;
    }

    public void setIListaItens(Integer iListaItens) {
        this.iListaItens = iListaItens;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

     public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }
 
    public Listas getILista() {
        return iLista;
    }

    public void setILista(Listas iLista) {
        this.iLista = iLista;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iListaItens != null ? iListaItens.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ListasItens)) {
            return false;
        }
        ListasItens other = (ListasItens) object;
        if ((this.iListaItens == null && other.iListaItens != null) || (this.iListaItens != null && !this.iListaItens.equals(other.iListaItens))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carrinho.model.entities.ListasItens[ iListaItens=" + iListaItens + " ]";
    }

}
