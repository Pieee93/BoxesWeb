/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Box;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author pc
 */
@Stateless
public class BoxFacade extends AbstractFacade<Box> {

    @PersistenceContext(unitName = "BoxesWebPU")
    private EntityManager em;

    /**
     *
     * @return istanza di entity manager
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     *
     */
    public BoxFacade() {
        super(Box.class);
    }
    
}
