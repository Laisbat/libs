package br.com.bb.direo.dao;

import br.com.bb.direo.model.Arquivo;
import br.com.bb.direo.util.JPAUtil;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author t1075825
 */
@Stateless
public class ArquivoDAO {

    @EJB
    private JPAUtil util;

    public boolean insert(Arquivo arquivo) {
        EntityManager em = util.getEntityManagerDEV();
        try {
            em.persist(arquivo);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Arquivo getById(final int id) {
        EntityManager em = util.getEntityManagerDEV();
        return em.find(Arquivo.class, id);
    }

    public List<Arquivo> getByTipoConteudoId(int id_tipo_conteudo) {
        EntityManager em = util.getEntityManagerDEV();
        TypedQuery<Arquivo> query = em.createNamedQuery("Arquivo.findByTipoConteudoId", Arquivo.class);
        query.setParameter("tipo", id_tipo_conteudo);
        return query.getResultList();
    }

    public List<Arquivo> findAll() {
        EntityManager em = util.getEntityManagerDEV();
        return em.createNamedQuery("Arquivo.findAll", Arquivo.class).getResultList();
    }

//    public void merge(Arquivo arquivo) {
//        EntityManager em = util.getEntityManagerDEV();
//        try {
//            em.merge(arquivo);
//        } catch (Exception e) {
//            e.printStackTrace();
//            em.getTransaction().rollback();
//        }
//    }
}
