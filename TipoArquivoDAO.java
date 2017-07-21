package br.com.bb.direo.dao;

import br.com.bb.direo.model.TipoArquivo;
import br.com.bb.direo.util.JPAUtil;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

/**
 *
 * @author t1075825
 */
@Stateless
public class TipoArquivoDAO {

    @EJB
    private JPAUtil util;

    public TipoArquivoDAO() {
    }

    public boolean insert(TipoArquivo tipoArquivo) {
        EntityManager em = util.getEntityManagerDEV();
        try {
            em.persist(tipoArquivo);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public TipoArquivo getById(final int tipoArquivo) {
        EntityManager em = util.getEntityManagerDEV();
        return em.find(TipoArquivo.class, tipoArquivo);
    }

    public void remove(TipoArquivo tipoArquivo) {
        EntityManager em = util.getEntityManagerDEV();
        try {
            tipoArquivo = em.find(TipoArquivo.class, tipoArquivo.getId());
            em.remove(tipoArquivo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeById(final int id) {
        EntityManager em = util.getEntityManagerDEV();
        try {
            TipoArquivo tipoArquivo = getById(id);
            remove(tipoArquivo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
