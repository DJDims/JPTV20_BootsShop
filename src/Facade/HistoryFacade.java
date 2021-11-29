
package Facade;

import classes.History;
import javax.persistence.EntityManager;
import tools.Singleton;

public class HistoryFacade extends AbstractFacade<History>{
    
    private EntityManager em;

    public HistoryFacade(Class<History> entityClass) {
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