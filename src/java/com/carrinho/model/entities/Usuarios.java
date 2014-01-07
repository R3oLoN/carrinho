/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carrinho.model.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuarios.findAll", query = "SELECT u FROM Usuarios u"),
    @NamedQuery(name = "Usuarios.findByIUsuario", query = "SELECT u FROM Usuarios u WHERE u.iUsuario = :iUsuario"),
    @NamedQuery(name = "Usuarios.findByLogin", query = "SELECT u FROM Usuarios u WHERE u.login = :login"),
    @NamedQuery(name = "Usuarios.findBySenha", query = "SELECT u FROM Usuarios u WHERE u.senha = :senha"),
    @NamedQuery(name = "Usuarios.findByNome", query = "SELECT u FROM Usuarios u WHERE u.nome = :nome"),
    @NamedQuery(name = "Usuarios.login", query = "SELECT u FROM Usuarios u WHERE u.login = :login AND u.senha = :senha")})
public class Usuarios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "i_usuario")
    private Integer iUsuario;
    @Basic(optional = false)
    @Column(name = "login")
    private String login;
    @Basic(optional = false)
    @Column(name = "senha")
    private String senha;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iUsuario")
    private Collection<Listas> listasCollection;
    
    public Usuarios() {
    }

    public Usuarios(Integer iUsuario) {
        this.iUsuario = iUsuario;
    }

    public Usuarios(Integer iUsuario, String login, String senha, String nome) {
        this.iUsuario = iUsuario;
        this.login = login;
        this.senha = senha;
        this.nome = nome;
    }

    public Integer getIUsuario() {
        return iUsuario;
    }

    public void setIUsuario(Integer iUsuario) {
        this.iUsuario = iUsuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @XmlTransient
    public Collection<Listas> getListasCollection() {
        return listasCollection;
    }

    public void setListasCollection(Collection<Listas> listasCollection) {
        this.listasCollection = listasCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iUsuario != null ? iUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.iUsuario == null && other.iUsuario != null) || (this.iUsuario != null && !this.iUsuario.equals(other.iUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.carrinho.model.entities.Usuarios[ iUsuario=" + iUsuario + " ]";
    }
    
}
