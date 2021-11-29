
package Facade;

import classes.Product;
import javax.persistence.EntityManager;
import tools.Singleton;

public class ProductFacade extends AbstractFacade{
    
    private EntityManager em;

    public ProductFacade(Class<Product> entityClass) {
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
