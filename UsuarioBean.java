package br.com.bb.direo.bean;

import br.com.bb.direo.dao.LogDAO;
import javax.faces.bean.ManagedBean;
import br.com.bb.sso.api.bean.Usuario;
import java.io.Serializable;
import java.util.Arrays;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author t1075825
 *
 */
@ManagedBean
@SessionScoped
public class UsuarioBean implements Serializable {

    @ManagedProperty(value = "#{usuario}")
    private Usuario usuarioSessao;
    private String caminho;
    private boolean temAcesso;

    @EJB
    private LogDAO log;

    @PostConstruct
    private void inicio() {
        temAcesso = this.temAcesso();

    }

    public boolean temAcesso() {
        boolean retorno = false;
        String[] ros = {"1GUT", "2GUT", "1GUN", "1GUE", "2GUE", "3GUE", "4GUE", "1AUE", "2AUE", "3AUE"};
        int[] comissoes = {3001, 3002, 3003, 7130, 7131, 7132};
        String[] desenvolvedores = {"T1075825"};

        if (Arrays.asList(ros).contains(usuarioSessao.getCodigoRO()) || Arrays.asList(comissoes).contains(usuarioSessao.getCodigoComissao()) || Arrays.asList(desenvolvedores).contains(usuarioSessao.getChave())) {
            retorno = true;
        }
        return retorno;
    }

    public void setUsuarioSessao(Usuario usuarioSessao) {
        this.usuarioSessao = usuarioSessao;
    }

    public boolean isTemAcesso() {
        return temAcesso;
    }
}
