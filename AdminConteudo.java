package br.com.bb.direo.model;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author t1075825
 */
@Entity
@Table(name = "admin_conteudo", schema = "sis_repositorio_arquivo")
@NamedQueries({
    @NamedQuery(name = "AdminConteudo.findAll", query = "SELECT a FROM AdminConteudo a")
    , @NamedQuery(name = "AdminConteudo.findByChave", query = "SELECT a FROM AdminConteudo a WHERE a.adminConteudoPK.chave = :chave")
    , @NamedQuery(name = "AdminConteudo.findByIdTipoConteudo", query = "SELECT a FROM AdminConteudo a WHERE a.adminConteudoPK.idTipoConteudo = :idTipoConteudo")})
public class AdminConteudo implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AdminConteudoPK adminConteudoPK;
    @JoinColumn(name = "id_tipo_conteudo", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipoConteudo tipoConteudo;

    public AdminConteudo() {
    }

    public AdminConteudo(AdminConteudoPK adminConteudoPK) {
        this.adminConteudoPK = adminConteudoPK;
    }

    public AdminConteudo(String chave, int idTipoConteudo) {
        this.adminConteudoPK = new AdminConteudoPK(chave, idTipoConteudo);
    }

    public AdminConteudoPK getAdminConteudoPK() {
        return adminConteudoPK;
    }

    public void setAdminConteudoPK(AdminConteudoPK adminConteudoPK) {
        this.adminConteudoPK = adminConteudoPK;
    }

    public TipoConteudo getTipoConteudo() {
        return tipoConteudo;
    }

    public void setTipoConteudo(TipoConteudo tipoConteudo) {
        this.tipoConteudo = tipoConteudo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (adminConteudoPK != null ? adminConteudoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdminConteudo)) {
            return false;
        }
        AdminConteudo other = (AdminConteudo) object;
        if ((this.adminConteudoPK == null && other.adminConteudoPK != null) || (this.adminConteudoPK != null && !this.adminConteudoPK.equals(other.adminConteudoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.AdminConteudo[ adminConteudoPK=" + adminConteudoPK + " ]";
    }
    
}
