/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *Classe che implementa la logica business
 * EJB creato dal database per accedere alla tabella categoria
 * @author pc
 */
@Entity
@Table(name = "categoria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Categoria.findAll", query = "SELECT c FROM Categoria c"),
    @NamedQuery(name = "Categoria.findById", query = "SELECT c FROM Categoria c WHERE c.id = :id"),
    @NamedQuery(name = "Categoria.findByName", query = "SELECT c FROM Categoria c WHERE c.name = :name")})
public class Categoria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoriaId")
    private Collection<Box> boxCollection;

    /**
     */
    public Categoria() {
    }

    /**
     *Costruttore 
     * @param id id della categoria
     */
    public Categoria(Integer id) {
        this.id = id;
    }

    /**
     *Costruttore
     * @param id id della categoria
     * @param name nome della categoria
     */
    public Categoria(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     *Metodo che ritorna l'id della categoria
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     *Metodo che imposta l'id della categoria
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *Metodo che ritorna il nome della categoria
     * @return  nome della categoria
     */
    public String getName() {
        return name;
    }

    /**
     *Metodo che imposta il nome della categoria
     * @param name  nome della categoria
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *Metodo che ritorna una collezione di box appartenenti alla categoria
     * @return  collezione di box 
     */
    @XmlTransient
    public Collection<Box> getBoxCollection() {
        return boxCollection;
    }

    /**
     *Metodo che imposta una collezione di box da collegare alla categoria
     * @param boxCollection collezione delle box 
     */
    public void setBoxCollection(Collection<Box> boxCollection) {
        this.boxCollection = boxCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categoria)) {
            return false;
        }
        Categoria other = (Categoria) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Categoria[ id=" + id + " ]";
    }
    
}
