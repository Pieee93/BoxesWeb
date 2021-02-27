/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Utente;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author pc
 */
@Stateless
public class UtenteFacade extends AbstractFacade<Utente> {

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
    public UtenteFacade() {
        super(Utente.class);
    }
    
}
