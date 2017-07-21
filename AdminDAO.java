package br.com.bb.direo.dao;

import br.com.bb.direo.model.Admin;
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
public class AdminDAO {
    @EJB
    private JPAUtil util;
    
    public boolean insert(Admin admin) {
        EntityManager em = util.getEntityManagerDEV();
        try {
            em.persist(admin);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public Admin getById(final String chave){
        EntityManager em = util.getEntityManagerDEV();
        return em.find(Admin.class, chave);
    }
    
    public List<Admin> findAll() {
        EntityManager em = util.getEntityManagerDEV();
        return em.createNamedQuery("Admin.findAll" , Admin.class).getResultList();
    }

    public void remove(Admin admin){
        EntityManager em = util.getEntityManagerDEV();
        try {
            admin = em.find(Admin.class, admin.getChave());
            em.remove(admin);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void removeById(final String chave){
        EntityManager em = util.getEntityManagerDEV();
        try{
            Admin admin = getById(chave);
            remove(admin);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
