package br.com.bb.direo.model;

import java.io.Serializable;
import java.util.Date;
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
@Table(name = "log", schema = "sis_repositorio_arquivo")
@NamedQueries({
    @NamedQuery(name = "Log.findAll", query = "SELECT l FROM Log l")
    , @NamedQuery(name = "Log.findByChave", query = "SELECT l FROM Log l WHERE l.logPK.chave = :chave")
    , @NamedQuery(name = "Log.findByIdArquivo", query = "SELECT l FROM Log l WHERE l.logPK.idArquivo = :idArquivo")
    , @NamedQuery(name = "Log.findByDataHoraDownload", query = "SELECT l FROM Log l WHERE l.logPK.dataHoraDownload = :dataHoraDownload")})
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LogPK logPK;
    @JoinColumn(name = "chave", referencedColumnName = "chave", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Admin admin;
    @JoinColumn(name = "id_arquivo", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Arquivo arquivo;

    public Log() {
    }

    public Log(LogPK logPK) {
        this.logPK = logPK;
    }

    public Log(String chave, int idArquivo, Date dataHoraDownload) {
        this.logPK = new LogPK(chave, idArquivo, dataHoraDownload);
    }

    public LogPK getLogPK() {
        return logPK;
    }

    public void setLogPK(LogPK logPK) {
        this.logPK = logPK;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Arquivo getArquivo() {
        return arquivo;
    }

    public void setArquivo(Arquivo arquivo) {
        this.arquivo = arquivo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (logPK != null ? logPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Log)) {
            return false;
        }
        Log other = (Log) object;
        if ((this.logPK == null && other.logPK != null) || (this.logPK != null && !this.logPK.equals(other.logPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Log[ logPK=" + logPK + " ]";
    }
    
}
