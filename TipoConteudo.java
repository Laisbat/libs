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
@Table(name = "tipo_conteudo", schema = "sis_repositorio_arquivo")
@NamedQueries({
    @NamedQuery(name = "TipoConteudo.findAll", query = "SELECT t FROM TipoConteudo t")
    , @NamedQuery(name = "TipoConteudo.findById", query = "SELECT t FROM TipoConteudo t WHERE t.id = :id")
    , @NamedQuery(name = "TipoConteudo.findByDescricao", query = "SELECT t FROM TipoConteudo t WHERE t.descricao = :descricao")})
public class TipoConteudo implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoConteudo")
    private Collection<Arquivo> arquivoCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "descricao", nullable = false, length = 255)
    private String descricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoConteudo")
    private Collection<AdminConteudo> adminConteudoCollection;

    public TipoConteudo() {
    }

    public TipoConteudo(Integer id) {
        this.id = id;
    }

    public TipoConteudo(Integer id, String descricao) {
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

    public Collection<AdminConteudo> getAdminConteudoCollection() {
        return adminConteudoCollection;
    }

    public void setAdminConteudoCollection(Collection<AdminConteudo> adminConteudoCollection) {
        this.adminConteudoCollection = adminConteudoCollection;
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
        if (!(object instanceof TipoConteudo)) {
            return false;
        }
        TipoConteudo other = (TipoConteudo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TipoConteudo[ id=" + id + " ]";
    }

    public Collection<Arquivo> getArquivoCollection() {
        return arquivoCollection;
    }

    public void setArquivoCollection(Collection<Arquivo> arquivoCollection) {
        this.arquivoCollection = arquivoCollection;
    }
    
}
