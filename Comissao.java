package br.com.bb.direo.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author t1075825
 */
@Entity
@Table(name = "comissao", schema = "util_arh")
@NamedQueries({
    @NamedQuery(name = "Comissao.findAll", query = "SELECT c FROM Comissao c")
    , @NamedQuery(name = "Comissao.findByCodComissao", query = "SELECT c FROM Comissao c WHERE c.codComissao = :codComissao")
    , @NamedQuery(name = "Comissao.findByNomeComissao", query = "SELECT c FROM Comissao c WHERE c.nomeComissao = :nomeComissao")
    , @NamedQuery(name = "Comissao.findByJornada", query = "SELECT c FROM Comissao c WHERE c.jornada = :jornada")
    , @NamedQuery(name = "Comissao.findByRo", query = "SELECT c FROM Comissao c WHERE c.ro = :ro")
    , @NamedQuery(name = "Comissao.findByRf", query = "SELECT c FROM Comissao c WHERE c.rf = :rf")
    , @NamedQuery(name = "Comissao.findByLocais", query = "SELECT c FROM Comissao c WHERE c.locais = :locais")})
public class Comissao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_comissao", nullable = false)
    private Integer codComissao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nome_comissao", nullable = false, length = 100)
    private String nomeComissao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "jornada", nullable = false)
    private int jornada;
    @Size(max = 4)
    @Column(name = "ro", length = 4)
    private String ro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rf", nullable = false)
    private int rf;
    @Size(max = 100)
    @Column(name = "locais", length = 100)
    private String locais;

    public Comissao() {
    }

    public Comissao(Integer codComissao) {
        this.codComissao = codComissao;
    }

    public Comissao(Integer codComissao, String nomeComissao, int jornada, int rf) {
        this.codComissao = codComissao;
        this.nomeComissao = nomeComissao;
        this.jornada = jornada;
        this.rf = rf;
    }

    public Integer getCodComissao() {
        return codComissao;
    }

    public void setCodComissao(Integer codComissao) {
        this.codComissao = codComissao;
    }

    public String getNomeComissao() {
        return nomeComissao;
    }

    public void setNomeComissao(String nomeComissao) {
        this.nomeComissao = nomeComissao;
    }

    public int getJornada() {
        return jornada;
    }

    public void setJornada(int jornada) {
        this.jornada = jornada;
    }

    public String getRo() {
        return ro;
    }

    public void setRo(String ro) {
        this.ro = ro;
    }

    public int getRf() {
        return rf;
    }

    public void setRf(int rf) {
        this.rf = rf;
    }

    public String getLocais() {
        return locais;
    }

    public void setLocais(String locais) {
        this.locais = locais;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.codComissao);
        hash = 59 * hash + Objects.hashCode(this.nomeComissao);
        hash = 59 * hash + this.jornada;
        hash = 59 * hash + Objects.hashCode(this.ro);
        hash = 59 * hash + this.rf;
        hash = 59 * hash + Objects.hashCode(this.locais);
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
        final Comissao other = (Comissao) obj;
        if (this.jornada != other.jornada) {
            return false;
        }
        if (this.rf != other.rf) {
            return false;
        }
        if (!Objects.equals(this.nomeComissao, other.nomeComissao)) {
            return false;
        }
        if (!Objects.equals(this.ro, other.ro)) {
            return false;
        }
        if (!Objects.equals(this.locais, other.locais)) {
            return false;
        }
        if (!Objects.equals(this.codComissao, other.codComissao)) {
            return false;
        }
        return true;
    }



    @Override
    public String toString() {
        return "br.com.bb.direo.model.Comissao[ codComissao=" + codComissao + " ]";
    }
    
}
