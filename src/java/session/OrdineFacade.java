/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Ordine;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author pc
 */
@Stateless
public class OrdineFacade extends AbstractFacade<Ordine> {

    @PersistenceContext(unitName = "BoxesWebPU")
    private EntityManager em;

    /**
     *
     * @return //
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     *
     */
    @PreDestroy
    public void destruct() {
        em.close();
    }

    /**
     *
     */
    public OrdineFacade() {
        super(Ordine.class);
    }
     // overridden - refresh method called to retrieve order id from database

    /**
     *
     * @param id id dell'ordine
     * @return ordine riorna ordine dal suo id
     */
    public Ordine find(Object id) {
        Ordine order = em.find(Ordine.class, id);
        em.refresh(order);
        return order;
    }
    

    /**
     *funzione custom che ritorna l'ordine effettuato per utente
     * @param utente che ha effettuato l'ordine
     * @return ordine effettuato dall'utente
     */
    public Ordine findByUtente(Object utente) {
        return (Ordine) em.createNamedQuery("Ordine.findByUtente").setParameter("utenteidUtente", utente).getSingleResult();
    }

}
