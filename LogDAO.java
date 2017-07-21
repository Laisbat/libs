package br.com.bb.direo.dao;

import br.com.bb.direo.model.Log;
import br.com.bb.direo.util.JPAUtil;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
/**
 *
 * @author f3565932
 */
@Stateless
public class LogDAO {
    
    @EJB
    private JPAUtil util;
    
    public boolean insert(Log log) {
        EntityManager em = util.getEntityManagerDEV();
        try {
            em.persist(log);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
}
