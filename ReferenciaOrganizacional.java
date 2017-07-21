package br.com.bb.direo.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author t1075825
 */
@Entity
@Table(name = "referencia_organizacional", schema = "util_arh")
@NamedQueries({
    @NamedQuery(name = "ReferenciaOrganizacional.findAll", query = "SELECT r FROM ReferenciaOrganizacional r ORDER BY r.sigla")
    , @NamedQuery(name = "ReferenciaOrganizacional.findById", query = "SELECT r FROM ReferenciaOrganizacional r WHERE r.id = :id")
    , @NamedQuery(name = "ReferenciaOrganizacional.findBySigla", query = "SELECT r FROM ReferenciaOrganizacional r WHERE r.sigla = :sigla")
    , @NamedQuery(name = "ReferenciaOrganizacional.findByDescricao", query = "SELECT r FROM ReferenciaOrganizacional r WHERE r.descricao = :descricao")})
public class ReferenciaOrganizacional implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "sigla", nullable = false, length = 5)
    private String sigla;
    @Basic(optional = false)
    @Column(name = "descricao", nullable = false, length = 100)
    private String descricao;

    public ReferenciaOrganizacional() {
    }

    public ReferenciaOrganizacional(Integer id) {
        this.id = id;
    }

    public ReferenciaOrganizacional(Integer id, String sigla, String descricao) {
        this.id = id;
        this.sigla = sigla;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
        if (!(object instanceof ReferenciaOrganizacional)) {
            return false;
        }
        ReferenciaOrganizacional other = (ReferenciaOrganizacional) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.ReferenciaOrganizacional[ id=" + id + " ]";
    }
    
}
