package br.com.bb.direo.dao;

import br.com.bb.direo.model.RelComissaoArquivo;
import br.com.bb.direo.util.JPAUtil;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

/**
 *  Classe para relacionar o Arquivo com a Comissao
 * @author t1075825
 */

@Stateless
public class RelComissaoArquivoDAO {
    
    @EJB
    private JPAUtil util;
    
    public boolean insert(RelComissaoArquivo relComissaoArquivo) {
        EntityManager em = util.getEntityManagerDEV();
        try {
            em.persist(relComissaoArquivo);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
