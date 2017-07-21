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
@Table(name = "rel_ro_arquivo", schema = "sis_repositorio_arquivo")
@NamedQueries({
    @NamedQuery(name = "RelRoArquivo.findAll", query = "SELECT r FROM RelRoArquivo r")
    , @NamedQuery(name = "RelRoArquivo.findByIdArquivo", query = "SELECT r FROM RelRoArquivo r WHERE r.relRoArquivoPK.idArquivo = :idArquivo")
    , @NamedQuery(name = "RelRoArquivo.findByIdRo", query = "SELECT r FROM RelRoArquivo r WHERE r.relRoArquivoPK.idRo = :idRo")})
public class RelRoArquivo implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RelRoArquivoPK relRoArquivoPK;
    @JoinColumn(name = "id_arquivo", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Arquivo arquivo;

    public RelRoArquivo() {
    }

    public RelRoArquivo(RelRoArquivoPK relRoArquivoPK) {
        this.relRoArquivoPK = relRoArquivoPK;
    }

    public RelRoArquivo(int idArquivo, int idRo) {
        this.relRoArquivoPK = new RelRoArquivoPK(idArquivo, idRo);
    }

    public RelRoArquivoPK getRelRoArquivoPK() {
        return relRoArquivoPK;
    }

    public void setRelRoArquivoPK(RelRoArquivoPK relRoArquivoPK) {
        this.relRoArquivoPK = relRoArquivoPK;
    }

    public Arquivo getArquivo() {
        return arquivo;
    }

    public void setArquivo(Arquivo arquivo) {
        this.arquivo = arquivo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.relRoArquivoPK);
        hash = 17 * hash + Objects.hashCode(this.arquivo);
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
        final RelRoArquivo other = (RelRoArquivo) obj;
        if (!Objects.equals(this.relRoArquivoPK, other.relRoArquivoPK)) {
            return false;
        }
        return Objects.equals(this.arquivo, other.arquivo);
    }


    @Override
    public String toString() {
        return "RelRoArquivo[ relRoArquivoPK=" + relRoArquivoPK + " ]";
    }
    
}
