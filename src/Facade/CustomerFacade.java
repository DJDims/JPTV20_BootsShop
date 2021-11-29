
package Facade;

import classes.Customer;
import javax.persistence.EntityManager;
import tools.Singleton;

public class CustomerFacade extends AbstractFacade<Customer>{
    
    private EntityManager em;

    public CustomerFacade(Class<Customer> entityClass) {
        super(entityClass);
        init();
    }
    
    private void init(){
        Singleton singleton = Singleton.getInstance();
        em = singleton.getEntityManager();
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
