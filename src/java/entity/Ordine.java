/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *Classe che implementa la logica business
 * EJB creato dal database per accedere alla tabella ordine
 * @author pc
 */
@Entity
@Table(name = "ordine")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ordine.findAll", query = "SELECT o FROM Ordine o"),
    @NamedQuery(name = "Ordine.findByIdOrdine", query = "SELECT o FROM Ordine o WHERE o.idOrdine = :idOrdine"),
    @NamedQuery(name = "Ordine.findByAmmonatre", query = "SELECT o FROM Ordine o WHERE o.ammonatre = :ammonatre"),
    @NamedQuery(name = "Ordine.findByDataCreazione", query = "SELECT o FROM Ordine o WHERE o.dataCreazione = :dataCreazione"),
    @NamedQuery(name = "Ordine.findByUtente", query = "SELECT o FROM Ordine o WHERE o.utenteidUtente = :utenteidUtente"), // manually created    

    @NamedQuery(name = "Ordine.findByNumeroConfermaOridne", query = "SELECT o FROM Ordine o WHERE o.numeroConfermaOridne = :numeroConfermaOridne")})
public class Ordine implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idOrdine")
    private Integer idOrdine;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "ammonatre")
    private BigDecimal ammonatre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dataCreazione")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCreazione;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numeroConfermaOridne")
    private int numeroConfermaOridne;
    @JoinColumn(name = "Utente_idUtente", referencedColumnName = "idUtente")
    @ManyToOne(optional = false)
    private Utente utenteidUtente;

    /**
     */
    public Ordine() {
    }

    /**
     *Costruttore
     * @param idOrdine id dell'ordine
     */
    public Ordine(Integer idOrdine) {
        this.idOrdine = idOrdine;
    }

    /**
     *Costruttore
     * @param idOrdine //
     * @param ammonatre //
     * @param dataCreazione //
     * @param numeroConfermaOridne //
     */
    public Ordine(Integer idOrdine, BigDecimal ammonatre, Date dataCreazione, int numeroConfermaOridne) {
        this.idOrdine = idOrdine;
        this.ammonatre = ammonatre;
        this.dataCreazione = dataCreazione;
        this.numeroConfermaOridne = numeroConfermaOridne;
    }

    /**
     *Metodo che ritorna l'id dell'ordine
     * @return id dell'ordine
     */
    public Integer getIdOrdine() {
        return idOrdine;
    }

    /**
     *Metodo che imposta l'id ordine
     * @param idOrdine  id ordine
     */
    public void setIdOrdine(Integer idOrdine) {
        this.idOrdine = idOrdine;
    }

    /**
     *Metodo che ritorna l'ammonatare dell'ordine
     * @return ammonatare dell'ordine
     */
    public BigDecimal getAmmonatre() {
        return ammonatre;
    }

    /**
     *Metodo che imposta l'ammonatare dell'ordine
     * @param ammonatre ammonatare dell'ordine
     */
    public void setAmmonatre(BigDecimal ammonatre) {
        this.ammonatre = ammonatre;
    }

    /**
     *Metodo che ritorna la data di creazione dell'ordine
     * @return data creazioen ordine
     */
    public Date getDataCreazione() {
        return dataCreazione;
    }

    /**
     *Metodo che imposta la data di creazione dell'ordine
     * @param dataCreazione data creazione ordine
     */
    public void setDataCreazione(Date dataCreazione) {
        this.dataCreazione = dataCreazione;
    }

    /**
     *Metodo che ritorna il numero di conferma dell'ordine
     * @return ritorna il numero di conferma dell'ordine
     */
    public int getNumeroConfermaOridne() {
        return numeroConfermaOridne;
    }

    /**
     *Metodo che imposta il numero di conferma dell'ordine
     * @param numeroConfermaOridne  numero di conferma dell'ordine
     */
    public void setNumeroConfermaOridne(int numeroConfermaOridne) {
        this.numeroConfermaOridne = numeroConfermaOridne;
    }

    /**
     *Metodo che ritorna l'utente a cui si riferisce l'ordine
     * @return utenet
     */
    public Utente getUtenteidUtente() {
        return utenteidUtente;
    }

    /**
     *Metodo che imposta l'utente a cui si riferisce l'ordine
     * @param utenteidUtente  l'utente a cui si riferisce l'ordine
     */
    public void setUtenteidUtente(Utente utenteidUtente) {
        this.utenteidUtente = utenteidUtente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOrdine != null ? idOrdine.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ordine)) {
            return false;
        }
        Ordine other = (Ordine) object;
        if ((this.idOrdine == null && other.idOrdine != null) || (this.idOrdine != null && !this.idOrdine.equals(other.idOrdine))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Ordine[ idOrdine=" + idOrdine + " ]";
    }
    
}
