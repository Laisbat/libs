package br.com.bb.direo.model;

import java.io.Serializable;
import java.util.Objects;
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
@Table(name = "rel_comissao_arquivo", schema = "sis_repositorio_arquivo")
@NamedQueries({
    @NamedQuery(name = "RelComissaoArquivo.findAll", query = "SELECT r FROM RelComissaoArquivo r")
    , @NamedQuery(name = "RelComissaoArquivo.findByIdArquivo", query = "SELECT r FROM RelComissaoArquivo r WHERE r.relComissaoArquivoPK.idArquivo = :idArquivo")
    , @NamedQuery(name = "RelComissaoArquivo.findByIdComissao", query = "SELECT r FROM RelComissaoArquivo r WHERE r.relComissaoArquivoPK.idComissao = :idComissao")})
public class RelComissaoArquivo implements Serializable {

    @JoinColumn(name = "id_arquivo", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Arquivo arquivo;

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RelComissaoArquivoPK relComissaoArquivoPK;

    public RelComissaoArquivo() {
    }

    public RelComissaoArquivo(RelComissaoArquivoPK relComissaoArquivoPK) {
        this.relComissaoArquivoPK = relComissaoArquivoPK;
    }

    public RelComissaoArquivo(int idArquivo, int idComissao) {
        this.relComissaoArquivoPK = new RelComissaoArquivoPK(idArquivo, idComissao);
    }

    public RelComissaoArquivoPK getRelComissaoArquivoPK() {
        return relComissaoArquivoPK;
    }

    public void setRelComissaoArquivoPK(RelComissaoArquivoPK relComissaoArquivoPK) {
        this.relComissaoArquivoPK = relComissaoArquivoPK;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.arquivo);
        hash = 29 * hash + Objects.hashCode(this.relComissaoArquivoPK);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RelComissaoArquivo other = (RelComissaoArquivo) obj;
        if (!Objects.equals(this.arquivo, other.arquivo)) {
            return false;
        }
        return Objects.equals(this.relComissaoArquivoPK, other.relComissaoArquivoPK);
    }


    @Override
    public String toString() {
        return "RelComissaoArquivo[ relComissaoArquivoPK=" + relComissaoArquivoPK + " ]";
    }

    public Arquivo getArquivo() {
        return arquivo;
    }

    public void setArquivo(Arquivo arquivo) {
        this.arquivo = arquivo;
    }
    
}
