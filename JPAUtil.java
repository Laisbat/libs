package br.com.bb.direo.util;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author t1075825
 */
@Stateless
public class JPAUtil {

    
    private final String PU_DEV = "PU_DEV";
    
    @PersistenceContext(unitName = PU_DEV)
    private EntityManager EM_DEV;
    
    public EntityManager getEntityManagerDEV(){
        return EM_DEV;
    }
}

