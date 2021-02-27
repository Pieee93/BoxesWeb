/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Box;
import entity.Categoria;
import entity.Prodotto;
import entity.Utente;
import java.math.BigDecimal;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *Session bean che implementa le funzioni dell'amministratore per transazioni db sicure
 * @author pc
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class AdminM {
@PersistenceContext(unitName = "BoxesWebPU")
    private EntityManager em;
    @Resource
    private SessionContext context;
    @EJB
    private BoxFacade boxFacade;
    @EJB
    private OrdineFacade ordineFacade;
    @EJB
    private BoxOrdinateFacade boxOrdinateFacade;
    @EJB
    private CategoriaFacade categoriaFacade;

    /**
     *costruttore
     */
    public AdminM() {
    }

    /**
     *Metodo che crea un nuovo utente e lo aggiunge al database
     * @param nome nome utente
     * @param cognome cognome utente
     * @param email email utente
     * @param indirizzo indirizzo utente
     * @param cittprovincia città e provincia dell'utente
     * @param cap cap utente
     * @return ritorna un utente 
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Utente addUtente(String nome, String cognome, String email, String indirizzo, String cittprovincia, String cap) {
        
        Short isNotAdmin = 0;

        Utente utente = new Utente();
        utente.setNomeUtente(nome);
        utente.setCognomeUtente(cognome);
        utente.setEmailUtente(email);
        utente.setIsAdmin(isNotAdmin);
        utente.setIndirizzo(indirizzo);
        utente.setCittàProvincia(cittprovincia);
        utente.setCap(cap);

        em.persist(utente);
        em.flush();

        return utente;
    }

    /**
     *Metodo che crea una nuova box e lo aggiunge al database
     * @param id id Box
     * @param nomeBox nome Box
     * @param tipoDiBox tipologia di Box
     * @param numeroProdotti numero prodotti che contiene
     * @param punteggioBox punteggio assegnato 
     * @param descrizioneBox descrizione
     * @param prezzoBox prezzo 
     * @param categoria_id FK della categoria a cui appartiene, nel metodo viene 
     * cercata una categoria per poter essere passata al metodo setCategoriaId(); 
     * @return box
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Box addBox(String id,String nomeBox, String tipoDiBox, String numeroProdotti, String punteggioBox, String descrizioneBox, String prezzoBox, String categoria_id) {
       
        Categoria cat = categoriaFacade.find(Integer.parseInt(categoria_id)); 
        Box box = new Box();
        box.setId(Integer.parseInt(id));
        box.setNomeBox(nomeBox);
        box.setTipoDiBox(tipoDiBox);
        box.setNumeroProdotti(Integer.parseInt(numeroProdotti));
        box.setPunteggioBox(new BigDecimal(punteggioBox));
        box.setDescrizioneBox(descrizioneBox);
        box.setPrezzoBox(new BigDecimal(prezzoBox));
        box.setCategoriaId(cat);
        
        em.persist(box);
        em.flush();

        return box;
    }

    /**
     *Metodo che crea un prodotto e lo aggiunge al database
     * @param nomeProdotto nome del prodotto 
     * @param punteggio punteggio assegnato 
     * @param prezzo prezzo  
     * @param descrizioneProdotto descrizione 
     * @param Box_idBox FK della box a cui appartiene. Nel metodo viene utilizzato per
     * cercare la box corrispondente e passarla al metodo setBoxidBox();
     * @return prodotto 
     */
    public Prodotto addProd(String nomeProdotto, String punteggio, String prezzo, String descrizioneProdotto, String Box_idBox) {
        Box bx = boxFacade.find(Integer.parseInt(Box_idBox)); 
        Prodotto prod = new Prodotto();
        
        prod.setNomeProdotto(nomeProdotto);
        prod.setPunteggio(new BigDecimal(punteggio));
        prod.setPrezzo(new BigDecimal(prezzo));
        prod.setDescrizioneProdotto(descrizioneProdotto);
        prod.setBoxidBox(bx);
        
        
        em.persist(prod);
        em.flush();

        return prod;
    }
   
}
