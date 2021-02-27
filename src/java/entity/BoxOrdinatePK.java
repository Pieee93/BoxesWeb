/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *Classe che implementa la logica business
 * EJB creato dal database per accedere alla tabella rappresentante la PK delle boxOrdinates
 * @author pc
 */
@Embeddable
public class BoxOrdinatePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "Ordine_idOrdine")
    private int ordineidOrdine;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Box_idBox")
    private int boxidBox;

    /**
     */
    public BoxOrdinatePK() {
    }

    /**
     *Costruttore
     * @param ordineidOrdine l'id dell'ordine effettuato
     * @param boxidBox l'id della box ordinata
     */
    public BoxOrdinatePK(int ordineidOrdine, int boxidBox) {
        this.ordineidOrdine = ordineidOrdine;
        this.boxidBox = boxidBox;
    }

    /**
     *Metodo che ritorna l'ordine
     * @return ordine
     */
    public int getOrdineidOrdine() {
        return ordineidOrdine;
    }

    /**
     *Metodo che imposta l'ordine il cui id è collegato alle box ordinate
     * @param ordineidOrdine id ordine
     */
    public void setOrdineidOrdine(int ordineidOrdine) {
        this.ordineidOrdine = ordineidOrdine;
    }

    /**
     *Metodo che ritorna l'id delle box ordinate
     * @return idBoxOrdinate
     */
    public int getBoxidBox() {
        return boxidBox;
    }

    /**
     *Metodo che impostta l'id delle box ordinate
     * @param boxidBox idBoxOrdinate
     */
    public void setBoxidBox(int boxidBox) {
        this.boxidBox = boxidBox;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) ordineidOrdine;
        hash += (int) boxidBox;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BoxOrdinatePK)) {
            return false;
        }
        BoxOrdinatePK other = (BoxOrdinatePK) object;
        if (this.ordineidOrdine != other.ordineidOrdine) {
            return false;
        }
        if (this.boxidBox != other.boxidBox) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.BoxOrdinatePK[ ordineidOrdine=" + ordineidOrdine + ", boxidBox=" + boxidBox + " ]";
    }
    
}
