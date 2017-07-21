package br.com.bb.direo.dao;

import br.com.bb.direo.model.ReferenciaOrganizacional;
import br.com.bb.direo.util.JPAUtil;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

/**
 *
 * @author t1075825
 */
@Stateless
public class ReferenciaOrganizacionalDAO {

    @EJB
    private JPAUtil util;

    public boolean insert(ReferenciaOrganizacional referenciaOrganizacional) {
        EntityManager em = util.getEntityManagerDEV();
        try {
            em.persist(referenciaOrganizacional);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public List<ReferenciaOrganizacional> findAll() {
        EntityManager em = util.getEntityManagerDEV();
        return em.createNamedQuery("ReferenciaOrganizacional.findAll" , ReferenciaOrganizacional.class).getResultList();
    }


//    public ReferenciaOrganizacional getById(final int id) {
//        EntityManager em = util.getEntityManagerDEV();
//        return em.find(ReferenciaOrganizacional.class, id);
//    }

//    public void remove(ReferenciaOrganizacional referenciaOrganizacional) {
//        EntityManager em = util.getEntityManagerDEV();
//        try {
//            referenciaOrganizacional = em.find(ReferenciaOrganizacional.class, referenciaOrganizacional.getId());
//            em.remove(referenciaOrganizacional);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void removeById(final int id) {
//        EntityManager em = util.getEntityManagerDEV();
//        try {
//            ReferenciaOrganizacional referenciaOrganizacional = getById(id);
//            remove(referenciaOrganizacional);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
