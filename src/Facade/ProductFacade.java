
package Facade;

import Classes.Product;
import javax.persistence.EntityManager;
import Tools.Singleton;

public class ProductFacade extends AbstractFacade<Product>{
    
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
