package br.com.bb.direo.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author t1075825
 */
@Entity
@Table(name = "tipo_arquivo", schema = "sis_repositorio_arquivo")
@NamedQueries({
    @NamedQuery(name = "TipoArquivo.findAll", query = "SELECT t FROM TipoArquivo t")
    , @NamedQuery(name = "TipoArquivo.findById", query = "SELECT t FROM TipoArquivo t WHERE t.id = :id")
    , @NamedQuery(name = "TipoArquivo.findByDescricao", query = "SELECT t FROM TipoArquivo t WHERE t.descricao = :descricao")
    , @NamedQuery(name = "TipoArquivo.findByIcone", query = "SELECT t FROM TipoArquivo t WHERE t.icone = :icone")})
public class TipoArquivo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "descricao", nullable = false, length = 20)
    private String descricao;
    @Basic(optional = false)
    @Column(name = "icone", nullable = false, length = 20)
    private String icone;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoArquivo")
    private Collection<ExtensaoArquivo> extensaoArquivoCollection;

    public TipoArquivo() {
    }

    public TipoArquivo(Integer id) {
        this.id = id;
    }

    public TipoArquivo(Integer id, String descricao, String icone) {
        this.id = id;
        this.descricao = descricao;
        this.icone = icone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }

    public Collection<ExtensaoArquivo> getExtensaoArquivoCollection() {
        return extensaoArquivoCollection;
    }

    public void setExtensaoArquivoCollection(Collection<ExtensaoArquivo> extensaoArquivoCollection) {
        this.extensaoArquivoCollection = extensaoArquivoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoArquivo)) {
            return false;
        }
        TipoArquivo other = (TipoArquivo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TipoArquivo[ id=" + id + " ]";
    }
    
}
