/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.BoxOrdinate;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author pc
 */
@Stateless
public class BoxOrdinateFacade extends AbstractFacade<BoxOrdinate> {

    @PersistenceContext(unitName = "BoxesWebPU")
    private EntityManager em;

    /**
     *
     * @return istanza entity manager
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     *
     */
    public BoxOrdinateFacade() {
        super(BoxOrdinate.class);
    }
    //funzione custom

    /**
     *funzione che cerca le box ordinate in base all'idOrdine
     * @param id l'idOrdine dell'ordine di cui si vuole trovare le box ordinate
     * @return ritorna una list di boxOrdinate
     */
    public List<BoxOrdinate> findByOrderId(Object id) {
        return em.createNamedQuery("BoxOrdinate.findByOrdineidOrdine").setParameter("ordineidOrdine", id).getResultList();
    }
    
}
