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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author t1075825
 */
@Entity
@Table(name = "extensao_arquivo", schema = "sis_repositorio_arquivo")
@NamedQueries({
    @NamedQuery(name = "ExtensaoArquivo.findAll", query = "SELECT e FROM ExtensaoArquivo e")
    , @NamedQuery(name = "ExtensaoArquivo.findById", query = "SELECT e FROM ExtensaoArquivo e WHERE e.id = :id")
    , @NamedQuery(name = "ExtensaoArquivo.findByDescricao", query = "SELECT e FROM ExtensaoArquivo e WHERE e.descricao = :descricao")})
public class ExtensaoArquivo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "descricao", nullable = false, length = 7)
    private String descricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idExtensao")
    private Collection<Arquivo> arquivoCollection;
    @JoinColumn(name = "id_tipo_arquivo", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private TipoArquivo idTipoArquivo;

    public ExtensaoArquivo() {
    }

    public ExtensaoArquivo(Integer id) {
        this.id = id;
    }

    public ExtensaoArquivo(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
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

    public Collection<Arquivo> getArquivoCollection() {
        return arquivoCollection;
    }

    public void setArquivoCollection(Collection<Arquivo> arquivoCollection) {
        this.arquivoCollection = arquivoCollection;
    }

    public TipoArquivo getIdTipoArquivo() {
        return idTipoArquivo;
    }

    public void setIdTipoArquivo(TipoArquivo idTipoArquivo) {
        this.idTipoArquivo = idTipoArquivo;
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
        if (!(object instanceof ExtensaoArquivo)) {
            return false;
        }
        ExtensaoArquivo other = (ExtensaoArquivo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.ExtensaoArquivo[ id=" + id + " ]";
    }
    
}
