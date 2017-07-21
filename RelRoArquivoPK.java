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
public class RelRoArquivoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_arquivo", nullable = false)
    private int idArquivo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_ro", nullable = false)
    private int idRo;

    public RelRoArquivoPK() {
    }

    public RelRoArquivoPK(int idArquivo, int idRo) {
        this.idArquivo = idArquivo;
        this.idRo = idRo;
    }

    public int getIdArquivo() {
        return idArquivo;
    }

    public void setIdArquivo(int idArquivo) {
        this.idArquivo = idArquivo;
    }

    public int getIdRo() {
        return idRo;
    }

    public void setIdRo(int idRo) {
        this.idRo = idRo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idArquivo;
        hash += (int) idRo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RelRoArquivoPK)) {
            return false;
        }
        RelRoArquivoPK other = (RelRoArquivoPK) object;
        if (this.idArquivo != other.idArquivo) {
            return false;
        }
        if (this.idRo != other.idRo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.bb.direo.model.RelRoArquivoPK[ idArquivo=" + idArquivo + ", idRo=" + idRo + " ]";
    }
    
}
