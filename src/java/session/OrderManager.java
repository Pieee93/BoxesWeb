/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import cart.ShoppingCart;
import cart.ShoppingCartItem;
import entity.BoxOrdinate;
import entity.BoxOrdinatePK;
import entity.Box;
import entity.Ordine;
import entity.Utente;
import java.math.BigDecimal;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;

/**
 *Implementa la funzione di gestione degli ordini
 * Si occupa della transazione sicura dell'ordine nel database
 * Si occupa di creare un nuovo utente al momento del checkout
 * @author pc
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)

public class OrderManager {
    Date date = new Date();
    
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

    /**
     */
    public OrderManager() {
        
    }

    /**
     *Metodo che piazza un ordine e lo aggiunge al database
     * @param nome //
     * @param cognome // 
     * @param email //  
     * @param indirizzo // 
     * @param cittprovincia //
     * @param cap //
     * @param cart carrello  
     * @return id ordine creato
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)

    public int placeOrder(String nome, String cognome, String email, String indirizzo, String cittprovincia, String cap, ShoppingCart cart) {
        try {
            Utente utente = addUtente(nome, cognome, email, indirizzo, cittprovincia, cap);
            Ordine ordine = addOrdine(utente, cart);
            addBoxOrdinate(ordine, cart);
            return ordine.getIdOrdine();
        } catch (Exception e) {
            context.setRollbackOnly();
            return 0;
        }
    }

    private Utente addUtente(String nome, String cognome, String email, String indirizzo, String cittprovincia, String cap) {

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

    private Ordine addOrdine(Utente utente, ShoppingCart cart) {
                java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());

        //creo ordine cliente
        Ordine ordine = new Ordine();
        ordine.setUtenteidUtente(utente);
        ordine.setAmmonatre(BigDecimal.valueOf(cart.getTotal()));

        //creo numero di conferma
        Random random = new Random();
        int i = random.nextInt(999999);
        ordine.setNumeroConfermaOridne(i);
        
        ordine.setDataCreazione(date);
        
        
        em.persist(ordine);
        em.flush();

        return ordine;

    }

    private void addBoxOrdinate(Ordine ordine, ShoppingCart cart) {
        
        List<ShoppingCartItem> items = cart.getItems();

        // itera pre creare le box ordinate
        for (ShoppingCartItem scItem : items) {

            int boxId = scItem.getBox().getId();
             
            // set up primary key object
            BoxOrdinatePK boxOrd = new BoxOrdinatePK();
            boxOrd.setOrdineidOrdine(ordine.getIdOrdine());
            
            boxOrd.setBoxidBox(boxId);

            // create ordered item using PK object
            BoxOrdinate boxOrdinate = new BoxOrdinate(boxOrd);

            // set quantity
            boxOrdinate.setQuantità(scItem.getQuantity());

            em.persist(boxOrdinate);
            em.flush();
        }
    }
    
    /**
     *Metodo che ritorna ritorna i dettagli dell'ordine
     * @param idOrdine id dell'ordine
     * @return dettagli ordine
     * 
     */
    public Map getOrderDetails(int idOrdine) {

        Map orderMap = new HashMap();

        // get order
        Ordine order = ordineFacade.find(idOrdine);

        // get customer
        Utente customer = order.getUtenteidUtente();

        // get all ordered products
        List<BoxOrdinate> boxOrdinate = boxOrdinateFacade.findByOrderId(idOrdine);

        // get product details for ordered items
        List<Box> products = new ArrayList<Box>();

        for (BoxOrdinate op : boxOrdinate) {

            Box b = (Box) boxFacade.find(op.getBoxOrdinatePK().getBoxidBox());
            products.add(b);
        }

        // add each item to orderMap
        orderMap.put("orderRecord", order);
        orderMap.put("customer", customer);
        orderMap.put("orderedProducts", boxOrdinate);
        orderMap.put("products", products);

        return orderMap;
    }


}
