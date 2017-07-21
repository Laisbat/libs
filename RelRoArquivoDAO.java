package br.com.bb.direo.dao;

import br.com.bb.direo.model.RelRoArquivo;
import br.com.bb.direo.util.JPAUtil;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

/**
 *
 * @author t1075825
 */

@Stateless
public class RelRoArquivoDAO {
    @EJB
    private JPAUtil util;
    
    public boolean insert(RelRoArquivo relRoArquivo) {
        EntityManager em = util.getEntityManagerDEV();
        try {
            em.persist(relRoArquivo);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
