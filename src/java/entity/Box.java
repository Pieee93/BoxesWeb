/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
 * EJB creato dal database per accedere alla tabella box
 * @author pc
 */
@Entity
@Table(name = "box")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Box.findAll", query = "SELECT b FROM Box b"),
    @NamedQuery(name = "Box.findById", query = "SELECT b FROM Box b WHERE b.id = :id"),
    @NamedQuery(name = "Box.findByTipoDiBox", query = "SELECT b FROM Box b WHERE b.tipoDiBox = :tipoDiBox"),
    @NamedQuery(name = "Box.findByNumeroProdotti", query = "SELECT b FROM Box b WHERE b.numeroProdotti = :numeroProdotti"),
    @NamedQuery(name = "Box.findByNomeBox", query = "SELECT b FROM Box b WHERE b.nomeBox = :nomeBox"),
    @NamedQuery(name = "Box.findByPunteggioBox", query = "SELECT b FROM Box b WHERE b.punteggioBox = :punteggioBox"),
    @NamedQuery(name = "Box.findByPrezzoBox", query = "SELECT b FROM Box b WHERE b.prezzoBox = :prezzoBox")})
public class Box implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tipoDiBox")
    private String tipoDiBox;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numeroProdotti")
    private int numeroProdotti;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nomeBox")
    private String nomeBox;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "punteggioBox")
    private BigDecimal punteggioBox;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 16777215)
    @Column(name = "descrizioneBox")
    private String descrizioneBox;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prezzoBox")
    private BigDecimal prezzoBox;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "box")
    private Collection<BoxOrdinate> boxOrdinateCollection;
    @JoinColumn(name = "categoria_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Categoria categoriaId;

    /**
     *
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "boxidBox")
    public Collection<Prodotto> prodottoCollection;

    /**
     *Costruttore di default
     */
    public Box() {
    }

    /**
     *Crea Bo passando un id
     * @param id id box
     */
    public Box(Integer id) {
        this.id = id;
    }

    /**
     *Costruttore che prende in ingresso tutti i parametri
     * @param id id box
     * @param tipoDiBox tipologia di box
     * @param numeroProdotti numero prodotti contenuti 
     * @param nomeBox nome box
     * @param punteggioBox punteggio
     * @param descrizioneBox descrizione
     * @param prezzoBox prezzo
     */
    public Box(Integer id, String tipoDiBox, int numeroProdotti, String nomeBox, BigDecimal punteggioBox, String descrizioneBox, BigDecimal prezzoBox) {
        this.id = id;
        this.tipoDiBox = tipoDiBox;
        this.numeroProdotti = numeroProdotti;
        this.nomeBox = nomeBox;
        this.punteggioBox = punteggioBox;
        this.descrizioneBox = descrizioneBox;
        this.prezzoBox = prezzoBox;
    }

    /**
     *Metodo che ritorna l'id della box
     * @return ritorna l'id della box
     */
    public Integer getId() {
        return id;
    }

    /**
     *Metodo che imposta l'id box
     * @param id id box
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *Metodo che ritorna il tipo di box
     * @return tipo di box
     */
    public String getTipoDiBox() {
        return tipoDiBox;
    }

    /**
     *Metodo che imposta tipo di box
     * @param tipoDiBox  tipo di box
     */
    public void setTipoDiBox(String tipoDiBox) {
        this.tipoDiBox = tipoDiBox;
    }

    /**
     *Metodo che ritorna il numero prodotti
     * @return  numero prodotti
     */
    public int getNumeroProdotti() {
        return numeroProdotti;
    }

    /**
     *Metodo che imposta il numero prodotti
     * @param numeroProdotti  numero prodotti
     */
    public void setNumeroProdotti(int numeroProdotti) {
        this.numeroProdotti = numeroProdotti;
    }

    /**
     *Metodo che ritorna nome box
     * @return  nome box
     */
    public String getNomeBox() {
        return nomeBox;
    }

    /**
     *Metodo che imposta nome box
     * @param nomeBox  nome box
     */
    public void setNomeBox(String nomeBox) {
        this.nomeBox = nomeBox;
    }

    /**
     *Metodo che ritorna il punteggio della box
     * @return punteggio della box
     */
    public BigDecimal getPunteggioBox() {
        return punteggioBox;
    }

    /**
     *Metodo che imposta il punteggio della box
     * @param punteggioBox  punteggio della box
     */
    public void setPunteggioBox(BigDecimal punteggioBox) {
        this.punteggioBox = punteggioBox;
    }

    /**
     *Metodo che ritorna la descrizione della box
     * @return  descrizione della box
     */
    public String getDescrizioneBox() {
        return descrizioneBox;
    }

    /**
     *Metodo che imposta a descrizione della box
     * @param descrizioneBox  descrizione della box
     */
    public void setDescrizioneBox(String descrizioneBox) {
        this.descrizioneBox = descrizioneBox;
    }

    /**
     *Metodo che ritorna il prezzo della box
     * @return  prezzo della box
     */
    public BigDecimal getPrezzoBox() {
        return prezzoBox;
    }

    /**
     *Metodo che imposta il prezzo della box
     * @param prezzoBox  prezzo della box
     */
    public void setPrezzoBox(BigDecimal prezzoBox) {
        this.prezzoBox = prezzoBox;
    }

    /**
     *Metodo che ritorna una collezione di box ordinate
     * @return collezione di box ordinate
     */
    @XmlTransient
    public Collection<BoxOrdinate> getBoxOrdinateCollection() {
        return boxOrdinateCollection;
    }

    /**
     *Metodo che imposta una collezione di boxOrdinate
     * @param boxOrdinateCollection  collezione di boxOrdinate
     */
    public void setBoxOrdinateCollection(Collection<BoxOrdinate> boxOrdinateCollection) {
        this.boxOrdinateCollection = boxOrdinateCollection;
    }

    /**
     *Metodo che ritorna la categoria di appartenenza della box
     * @return categoria di appartenenza della box
     */
    public Categoria getCategoriaId() {
        return categoriaId;
    }

    /**
     *Metodo che imposta la categoria a cui appartiene la box
     * @param categoriaId categoria a cui appartiene la box
     */
    public void setCategoriaId(Categoria categoriaId) {
        this.categoriaId = categoriaId;
    }

    /**
     *Metodo che ritorna i prodotti che sono collegati alla box
     * @return prodotti collegati alla box
     */
    @XmlTransient
    public Collection<Prodotto> getProdottoCollection() {
        return prodottoCollection;
    }

    /**
     *Metodo che imposta i prodotti collegati alla box
     * @param prodottoCollection prodotti collegati alla box
     */
    public void setProdottoCollection(Collection<Prodotto> prodottoCollection) {
        this.prodottoCollection = prodottoCollection;
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
        if (!(object instanceof Box)) {
            return false;
        }
        Box other = (Box) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Box[ id=" + id + " ]";
    }
    
}
