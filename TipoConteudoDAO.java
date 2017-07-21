package br.com.bb.direo.dao;

import br.com.bb.direo.model.TipoConteudo;
import br.com.bb.direo.util.JPAUtil;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

/**
 * Classe utilizada para realizar as operações de 
 * banco de dados sobre a entity TipoConteudo.
 * @author t1075825
 */
@Stateless
public class TipoConteudoDAO {

    @EJB
    private JPAUtil util;

    public boolean insert(TipoConteudo tipoConteudo) {
        EntityManager em = util.getEntityManagerDEV();
        try {
            em.persist(tipoConteudo);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public TipoConteudo getById(final int id) {
        EntityManager em = util.getEntityManagerDEV();
        return em.find(TipoConteudo.class, id);
    }

    public List<TipoConteudo> findAll() {
        EntityManager em = util.getEntityManagerDEV();
        return em.createNamedQuery("TipoConteudo.findAll" , TipoConteudo.class).getResultList();
    }

//    public void remove(TipoConteudo tipoConteudo) {
//        EntityManager em = util.getEntityManagerDEV();
//        try {
//            tipoConteudo = entityManager.find(TipoConteudo.class, tipoConteudo.getId());
//            entityManager.remove(tipoConteudo);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void removeById(final int id) {
//        EntityManager em = util.getEntityManagerDEV();
//        try {
//            TipoConteudo tipoConteudo = getById(id);
//            remove(tipoConteudo);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
 