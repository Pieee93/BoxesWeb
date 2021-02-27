/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

/**
 *Pattern Facade creato per gestire le sessioni di accesso alle entity e al databse
 * AbstractFacade è implementata dalle sottoclassi del package session, session beans per ogni entity
 * @author pc
 * @param <T> rappresenta un'entità generica
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    /**
     *Assegna un'entità al suo session bean Facade
     * @param entityClass entity 
     */
    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     *Metodo che ritorna
     * @return interfaccia che ritorna un'istanza dell'EntityManager in riferimento alla classe entity corrispondente
     */
    protected abstract EntityManager getEntityManager();

    /**
     *Metodo che crea un'entità
     * @param entity rappresenta le entitò che possono essere create attraverso AbstractFacade 
     */
    public void create(T entity) {        
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    javax.validation.Validator validator = factory.getValidator();
    Set<ConstraintViolation<T>> constraintViolations = validator.validate(entity);
    if (constraintViolations.size() > 0 ) { 
       System.out.println("Constraint Violations occurred.."); 
       for (ConstraintViolation<T> contraints : constraintViolations) {
            System.out.println(contraints.getRootBeanClass().getSimpleName()+
            "." + contraints.getPropertyPath() + " " + contraints.getMessage());
        }        
        getEntityManager().persist(entity);              
    }
}

    /**
     *Metodo che modifica un'entità
     * @param entity  entità
     */
    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    /**
     *Metodo che rimuove un'entità
     * @param entity  entità 
     */
    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    /**
     *Metodo che trova un oggetto per id
     * @param id id oggetto
     * @return ritonra l'oggetto cercato
     */
    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    /**
     *Metodo che trova tutte le entità di un certo tipo 
     * @return ritorna una list di entità
     */
    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    /**
     *Metodo che ritorna una lista di entità in un determinato range 
     * @param range range 
     * @return lista entità
     */
    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    /**
     *Metodo che conta il numero di entità del tipo che chiama l'operazione
     * @return ritorna un valore numerico 
     */
    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
}
