package br.com.bb.direo.dao;

import br.com.bb.direo.model.Comissao;
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
public class ComissaoDAO {
    @EJB
    private JPAUtil util;

    public boolean insert(Comissao comissao) {
        EntityManager em = util.getEntityManagerDEV();
        try {
            em.persist(comissao);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public List<Comissao> findAll() {
        EntityManager em = util.getEntityManagerDEV();
        return em.createNamedQuery("Comissao.findAll" , Comissao.class).getResultList();
    }


//    public Comissao getById(final int id) {
//        EntityManager em = util.getEntityManagerDEV();
//        return em.find(Comissao.class, id);
//    }

//    public void remove(Comissao comissao) {
//        EntityManager em = util.getEntityManagerDEV();
//        try {
//            comissao = em.find(Comissao.class, Comissao.getId());
//            em.remove(comissao);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void removeById(final int id) {
//        EntityManager em = util.getEntityManagerDEV();
//        try {
//            Comissao comissao = getById(id);
//            remove(comissao);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
