package br.com.bb.direo.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author t1075825
 */
@Embeddable
public class AdminConteudoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "chave", nullable = false, length = 8)
    private String chave;
    @Basic(optional = false)
    @Column(name = "id_tipo_conteudo", nullable = false)
    private int idTipoConteudo;

    public AdminConteudoPK() {
    }

    public AdminConteudoPK(String chave, int idTipoConteudo) {
        this.chave = chave;
        this.idTipoConteudo = idTipoConteudo;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public int getIdTipoConteudo() {
        return idTipoConteudo;
    }

    public void setIdTipoConteudo(int idTipoConteudo) {
        this.idTipoConteudo = idTipoConteudo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (chave != null ? chave.hashCode() : 0);
        hash += (int) idTipoConteudo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdminConteudoPK)) {
            return false;
        }
        AdminConteudoPK other = (AdminConteudoPK) object;
        if ((this.chave == null && other.chave != null) || (this.chave != null && !this.chave.equals(other.chave))) {
            return false;
        }
        if (this.idTipoConteudo != other.idTipoConteudo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.AdminConteudoPK[ chave=" + chave + ", idTipoConteudo=" + idTipoConteudo + " ]";
    }
    
}
