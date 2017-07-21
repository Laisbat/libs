package br.com.bb.direo.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author t1075825
 */
@Embeddable
public class RelComissaoArquivoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_arquivo", nullable = false)
    private int idArquivo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_comissao", nullable = false)
    private int idComissao;

    public RelComissaoArquivoPK() {
    }

    public RelComissaoArquivoPK(int idArquivo, int idComissao) {
        this.idArquivo = idArquivo;
        this.idComissao = idComissao;
    }

    public int getIdArquivo() {
        return idArquivo;
    }

    public void setIdArquivo(int idArquivo) {
        this.idArquivo = idArquivo;
    }

    public int getIdComissao() {
        return idComissao;
    }

    public void setIdComissao(int idComissao) {
        this.idComissao = idComissao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idArquivo;
        hash += (int) idComissao;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RelComissaoArquivoPK)) {
            return false;
        }
        RelComissaoArquivoPK other = (RelComissaoArquivoPK) object;
        if (this.idArquivo != other.idArquivo) {
            return false;
        }
        if (this.idComissao != other.idComissao) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.bb.direo.model.RelComissaoArquivoPK[ idArquivo=" + idArquivo + ", idComissao=" + idComissao + " ]";
    }
    
}
