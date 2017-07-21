package br.com.bb.direo.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author t1075825
 */
@Embeddable
public class LogPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "chave", nullable = false, length = 8)
    private String chave;
    @Basic(optional = false)
    @Column(name = "id_arquivo", nullable = false)
    private int idArquivo;
    @Basic(optional = false)
    @Column(name = "data_hora_download", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraDownload;

    public LogPK() {
    }

    public LogPK(String chave, int idArquivo, Date dataHoraDownload) {
        this.chave = chave;
        this.idArquivo = idArquivo;
        this.dataHoraDownload = dataHoraDownload;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public int getIdArquivo() {
        return idArquivo;
    }

    public void setIdArquivo(int idArquivo) {
        this.idArquivo = idArquivo;
    }

    public Date getDataHoraDownload() {
        return dataHoraDownload;
    }

    public void setDataHoraDownload(Date dataHoraDownload) {
        this.dataHoraDownload = dataHoraDownload;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (chave != null ? chave.hashCode() : 0);
        hash += (int) idArquivo;
        hash += (dataHoraDownload != null ? dataHoraDownload.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LogPK)) {
            return false;
        }
        LogPK other = (LogPK) object;
        if ((this.chave == null && other.chave != null) || (this.chave != null && !this.chave.equals(other.chave))) {
            return false;
        }
        if (this.idArquivo != other.idArquivo) {
            return false;
        }
        if ((this.dataHoraDownload == null && other.dataHoraDownload != null) || (this.dataHoraDownload != null && !this.dataHoraDownload.equals(other.dataHoraDownload))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.LogPK[ chave=" + chave + ", idArquivo=" + idArquivo + ", dataHoraDownload=" + dataHoraDownload + " ]";
    }
    
}
