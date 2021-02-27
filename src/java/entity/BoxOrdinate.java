/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *Classe che implementa la logica business
 * EJB creato dal database per accedere alla tabella boxOrdinate
 * @author pc
 */
@Entity
@Table(name = "box_ordinate")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BoxOrdinate.findAll", query = "SELECT b FROM BoxOrdinate b"),
    @NamedQuery(name = "BoxOrdinate.findByOrdineidOrdine", query = "SELECT b FROM BoxOrdinate b WHERE b.boxOrdinatePK.ordineidOrdine = :ordineidOrdine"),
    @NamedQuery(name = "BoxOrdinate.findByBoxidBox", query = "SELECT b FROM BoxOrdinate b WHERE b.boxOrdinatePK.boxidBox = :boxidBox"),
    @NamedQuery(name = "BoxOrdinate.findByQuantit\u00e0", query = "SELECT b FROM BoxOrdinate b WHERE b.quantit\u00e0 = :quantit\u00e0")})
public class BoxOrdinate implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @EmbeddedId
    protected BoxOrdinatePK boxOrdinatePK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantit\u00e0")
    private int quantità;
    @JoinColumn(name = "Box_idBox", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Box box;
    @JoinColumn(name = "Ordine_idOrdine", referencedColumnName = "idOrdine", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Ordine ordine;

    /**
     *
     */
    public BoxOrdinate() {
    }

    /**
     *Costruttore
     * @param boxOrdinatePK chiave composta da due PK, idOrdine e id della box
     */
    public BoxOrdinate(BoxOrdinatePK boxOrdinatePK) {
        this.boxOrdinatePK = boxOrdinatePK;
    }

    /**
     * Costruttore 
     * @param boxOrdinatePK chiave composta da due PK, idOrdine e id della box
     * @param quantità quantità box ordinate
     */
    public BoxOrdinate(BoxOrdinatePK boxOrdinatePK, int quantità) {
        this.boxOrdinatePK = boxOrdinatePK;
        this.quantità = quantità;
    }

    /**
     *Costruttore
     * @param ordineidOrdine idOridne
     * @param boxidBox idbox ordinate
     */
    public BoxOrdinate(int ordineidOrdine, int boxidBox) {
        this.boxOrdinatePK = new BoxOrdinatePK(ordineidOrdine, boxidBox);
    }

    /**
     *Metodo che ritorna boxOrdinatePK chiave composta da due PK, idOrdine e id della box
     * @return boxOrdinatePK 
     */
    public BoxOrdinatePK getBoxOrdinatePK() {
        return boxOrdinatePK;
    }

    /**
     *Metodo che imposta boxOrdinatePK chiave composta da due PK, idOrdine e id della box
     * @param boxOrdinatePK chiave composta da due PK, idOrdine e id della box
     */
    public void setBoxOrdinatePK(BoxOrdinatePK boxOrdinatePK) {
        this.boxOrdinatePK = boxOrdinatePK;
    }

    /**
     *Metodo che ritorna la quantità di boxOrdinate
     * @return  quantità
     */
    public int getQuantità() {
        return quantità;
    }

    /**
     **Metodo che imposta la quantità di box ordinate
     * @param quantità quantità di box ordinate
     */
    public void setQuantità(int quantità) {
        this.quantità = quantità;
    }

    /**
     *Metodo che ritorna una box ordinata
     * @return box ordinata
     */
    public Box getBox() {
        return box;
    }

    /**
     *Metodo che imposta la box ordinata
     * @param box  box ordinata
     */
    public void setBox(Box box) {
        this.box = box;
    }

    /**
     *Metodo che ritorna un ordine
     * @return ordine
     */
    public Ordine getOrdine() {
        return ordine;
    }

    /**
     *Metodo che imposta collegato alle box ordinate
     * @param ordine l'ordine che va collegato alle box ordinate
     */
    public void setOrdine(Ordine ordine) {
        this.ordine = ordine;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (boxOrdinatePK != null ? boxOrdinatePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BoxOrdinate)) {
            return false;
        }
        BoxOrdinate other = (BoxOrdinate) object;
        if ((this.boxOrdinatePK == null && other.boxOrdinatePK != null) || (this.boxOrdinatePK != null && !this.boxOrdinatePK.equals(other.boxOrdinatePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.BoxOrdinate[ boxOrdinatePK=" + boxOrdinatePK + " ]";
    }
    
}
