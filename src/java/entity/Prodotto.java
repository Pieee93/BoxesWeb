/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *Classe che implementa la logica business
 * EJB creato dal database per accedere alla tabella prodotto
 * @author pc
 */
@Entity
@Table(name = "prodotto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prodotto.findAll", query = "SELECT p FROM Prodotto p"),
    @NamedQuery(name = "Prodotto.findByIdProdotto", query = "SELECT p FROM Prodotto p WHERE p.idProdotto = :idProdotto"),
    @NamedQuery(name = "Prodotto.findByNomeProdotto", query = "SELECT p FROM Prodotto p WHERE p.nomeProdotto = :nomeProdotto"),
    @NamedQuery(name = "Prodotto.findByPunteggio", query = "SELECT p FROM Prodotto p WHERE p.punteggio = :punteggio"),
    @NamedQuery(name = "Prodotto.findByPrezzo", query = "SELECT p FROM Prodotto p WHERE p.prezzo = :prezzo")})
public class Prodotto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idProdotto")
    private Integer idProdotto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nomeProdotto")
    private String nomeProdotto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "punteggio")
    private BigDecimal punteggio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prezzo")
    private BigDecimal prezzo;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 16777215)
    @Column(name = "descrizioneProdotto")
    private String descrizioneProdotto;
    @JoinColumn(name = "Box_idBox", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Box boxidBox;

    /**
     *costruttore di default
     */
    public Prodotto() {
    }

    /**
     *Costruttore
     * @param idProdotto //
     */
    public Prodotto(Integer idProdotto) {
        this.idProdotto = idProdotto;
    }

    /**
     *Costruttore
     * @param idProdotto //
     * @param nomeProdotto // 
     * @param punteggio //
     * @param prezzo // 
     * @param descrizioneProdotto //
     */
    public Prodotto(Integer idProdotto, String nomeProdotto, BigDecimal punteggio, BigDecimal prezzo, String descrizioneProdotto) {
        this.idProdotto = idProdotto;
        this.nomeProdotto = nomeProdotto;
        this.punteggio = punteggio;
        this.prezzo = prezzo;
        this.descrizioneProdotto = descrizioneProdotto;
    }

    /**
     *Metodo che ritorna id del prodotto
     * @return  id del prodotto
     */
    public Integer getIdProdotto() {
        return idProdotto;
    }

    /**
     *Metodo che imposta id del prodotto
     * @param idProdotto id del prodotto
     */
    public void setIdProdotto(Integer idProdotto) {
        this.idProdotto = idProdotto;
    }

    /**
     *Metodo che ritorna il nome del prodotto
     * @return  nome del prodotto
     */
    public String getNomeProdotto() {
        return nomeProdotto;
    }

    /**
     *Metodo che imposta il nome del prodotto
     * @param nomeProdotto nome del prodotto
     */
    public void setNomeProdotto(String nomeProdotto) {
        this.nomeProdotto = nomeProdotto;
    }

    /**
     *Metodo che ritorna il punteggio del prodotto
     * @return punteggio del prodotto
     */ 
    public BigDecimal getPunteggio() {
        return punteggio;
    }

    /**
     *Metodo che imposta il punteggio
     * @param punteggio imposta il punteggio
     */
    public void setPunteggio(BigDecimal punteggio) {
        this.punteggio = punteggio;
    }

    /**
     *Metodo che ritorna il prezzo del prodotto
     * @return prezzo del prodotto
     */
    public BigDecimal getPrezzo() {
        return prezzo;
    }

    /**
     *Metodo che imposta il prezzo del prodotto
     * @param prezzo prezzo del rodotto
     */
    public void setPrezzo(BigDecimal prezzo) {
        this.prezzo = prezzo;
    }

    /**
     *Metodo che ritorna descrizione del prodotto
     * @return  descrizione del prodotto
     */
    public String getDescrizioneProdotto() {
        return descrizioneProdotto;
    }

    /**
     *Metodo che imposta descrizione prodotto
     * @param descrizioneProdotto descrizione prodotto
     */
    public void setDescrizioneProdotto(String descrizioneProdotto) {
        this.descrizioneProdotto = descrizioneProdotto;
    }

    /**
     *Metodo che ritorna la box a cui appartiene il prodotto
     * @return box
     */
    public Box getBoxidBox() {
        return boxidBox;
    }

    /**
     *Metodo che imposta la box a cui appartiene il prodotto
     * @param boxidBox box
     */
    public void setBoxidBox(Box boxidBox) {
        this.boxidBox = boxidBox;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProdotto != null ? idProdotto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prodotto)) {
            return false;
        }
        Prodotto other = (Prodotto) object;
        if ((this.idProdotto == null && other.idProdotto != null) || (this.idProdotto != null && !this.idProdotto.equals(other.idProdotto))) {
            return false;
        }
        return true;
    }
    

    @Override
    public String toString() {
        return "entity.Prodotto[ idProdotto=" + idProdotto + " ]";
    }
    
}
