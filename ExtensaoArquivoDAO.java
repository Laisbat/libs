package br.com.bb.direo.dao;

import br.com.bb.direo.model.ExtensaoArquivo;
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
public class ExtensaoArquivoDAO {

    @EJB
    private JPAUtil util;

    public List<ExtensaoArquivo> findAll() {
        EntityManager em = util.getEntityManagerDEV();
        return em.createNamedQuery("ExtensaoArquivo.findAll", ExtensaoArquivo.class).getResultList();
    }
    
    public ExtensaoArquivo retornaExtensaoXDescricao(String extensao){
        EntityManager em = util.getEntityManagerDEV();
        TypedQuery<ExtensaoArquivo> query = em.createNamedQuery("ExtensaoArquivo.findByDescricao", ExtensaoArquivo.class);
        query.setParameter("descricao", extensao);
        return query.getSingleResult();
        
    }
    
//    public List<ExtensaoArquivo> findAllByType(int id_tipo_arquivo){
//        return entityManager.createQuery("FROM " + ExtensaoArquivo.class.getName()+ " WHERE idTipoArquivo = " + id_tipo_arquivo);
//    }
}
