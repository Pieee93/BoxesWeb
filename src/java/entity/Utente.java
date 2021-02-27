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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
 * EJB creato dal database per accedere alla tabella utente
 * @author pc
 */
@Entity
@Table(name = "utente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Utente.findAll", query = "SELECT u FROM Utente u"),
    @NamedQuery(name = "Utente.findByIdUtente", query = "SELECT u FROM Utente u WHERE u.idUtente = :idUtente"),
    @NamedQuery(name = "Utente.findByNomeUtente", query = "SELECT u FROM Utente u WHERE u.nomeUtente = :nomeUtente"),
    @NamedQuery(name = "Utente.findByCognomeUtente", query = "SELECT u FROM Utente u WHERE u.cognomeUtente = :cognomeUtente"),
    @NamedQuery(name = "Utente.findByEmailUtente", query = "SELECT u FROM Utente u WHERE u.emailUtente = :emailUtente"),
    @NamedQuery(name = "Utente.findByIsAdmin", query = "SELECT u FROM Utente u WHERE u.isAdmin = :isAdmin"),
    @NamedQuery(name = "Utente.findByIndirizzo", query = "SELECT u FROM Utente u WHERE u.indirizzo = :indirizzo"),
    @NamedQuery(name = "Utente.findByCitt\u00e0Provincia", query = "SELECT u FROM Utente u WHERE u.citt\u00e0Provincia = :citt\u00e0Provincia"),
    @NamedQuery(name = "Utente.findByCap", query = "SELECT u FROM Utente u WHERE u.cap = :cap")})
public class Utente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idUtente")
    private Integer idUtente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nomeUtente")
    private String nomeUtente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "cognomeUtente")
    private String cognomeUtente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "emailUtente")
    private String emailUtente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "isAdmin")
    private short isAdmin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "indirizzo")
    private String indirizzo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "citt\u00e0_provincia")
    private String cittàProvincia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "cap")
    private String cap;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "utenteidUtente")
    private Collection<Ordine> ordineCollection;

    /**
     */
    public Utente() {
    }

    /**
     *Costruttore
     * @param idUtente //
     */
    public Utente(Integer idUtente) {
        this.idUtente = idUtente;
    }
     /**
      * Costruttore
      * @param idUtente id utente
     * @param nomeUtente nome utente
     * @param cognomeUtente cognome utente
     * @param emailUtente email utente
     * @param indirizzo indirizzo utente
     * @param cittàProvincia città e provincia dell'utente
     * @param cap cap utente
     * @param isAdmin //
    */
    public Utente(Integer idUtente, String nomeUtente, String cognomeUtente, String emailUtente, short isAdmin, String indirizzo, String cittàProvincia, String cap) {
        this.idUtente = idUtente;
        this.nomeUtente = nomeUtente;
        this.cognomeUtente = cognomeUtente;
        this.emailUtente = emailUtente;
        this.isAdmin = isAdmin;
        this.indirizzo = indirizzo;
        this.cittàProvincia = cittàProvincia;
        this.cap = cap;
    }

    /**
     *Metodo che ritorna l'id utente
     * @return  id utente
     */
    public Integer getIdUtente() {
        return idUtente;
    }

    /**
     *Metodo che imposta l'id utente
     * @param idUtente   id utente
     */
    public void setIdUtente(Integer idUtente) {
        this.idUtente = idUtente;
    }

    /**
     *Metodo che ritorna il nome utente
     * @return nome utente
     */
    public String getNomeUtente() {
        return nomeUtente;
    }

    /**
     *Metodo che imposta il nome utente
     * @param nomeUtente  nome utente
     */
    public void setNomeUtente(String nomeUtente) {
        this.nomeUtente = nomeUtente;
    }

    /**
     *Metodo che ritorna il cognome utente
     * @return  cognome utente
     */
    public String getCognomeUtente() {
        return cognomeUtente;
    }

    /**
     *Metodo che imposta il cognome utente
     * @param cognomeUtente cognome utente
     */
    public void setCognomeUtente(String cognomeUtente) {
        this.cognomeUtente = cognomeUtente;
    }

    /**
     *Metodo che ritorna l'email utente
     * @return email utente
     */
    public String getEmailUtente() {
        return emailUtente;
    }

    /**
     *Metodo che imposta email utente
     * @param emailUtente email utente
     */
    public void setEmailUtente(String emailUtente) {
        this.emailUtente = emailUtente;
    }

    /**
     *Metodo che ritorna se un utente è amministratore
     * @return isAdmin
     */
    public short getIsAdmin() {
        return isAdmin;
    }

    /**
     *Metodo che imposta amministratore
     * @param isAdmin //
     */
    public void setIsAdmin(short isAdmin) {
        this.isAdmin = isAdmin;
    }

    /**
     *Metodo che ritorna l' indirizzo 
     * @return indirizzo 
     */
    public String getIndirizzo() {
        return indirizzo;
    }

    /**
     *Metodo che imposta l'indirizzo 
     * @param indirizzo  indirizzo 
     */
    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    /**
     *Metodo che ritorna  provincia
     * @return  provincia
     */
    public String getCittàProvincia() {
        return cittàProvincia;
    }

    /**
     *Metodo che imposta città e provincia 
     * @param cittàProvincia città e provincia
     */
    public void setCittàProvincia(String cittàProvincia) {
        this.cittàProvincia = cittàProvincia;
    }

    /**
     *Metodo che ritorna il cap
     * @return cap
     */
    public String getCap() {
        return cap;
    }

    /**
     *Metodo che imposta cap
     * @param cap  cap
     */
    public void setCap(String cap) {
        this.cap = cap;
    }

    /**
     *Metodo che ritorna gli ordini dell'utente
     * @return ordini
     */
    @XmlTransient
    public Collection<Ordine> getOrdineCollection() {
        return ordineCollection;
    }

    /**
     *Metodo che imposta la collezione di ordini dell'utente
     * @param ordineCollection collezione di ordini dell'utente
     */
    public void setOrdineCollection(Collection<Ordine> ordineCollection) {
        this.ordineCollection = ordineCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUtente != null ? idUtente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Utente)) {
            return false;
        }
        Utente other = (Utente) object;
        if ((this.idUtente == null && other.idUtente != null) || (this.idUtente != null && !this.idUtente.equals(other.idUtente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Utente[ idUtente=" + idUtente + " ]";
    }
    
}
