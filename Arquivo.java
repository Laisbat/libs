package br.com.bb.direo.model;

import java.io.Serializable;
import java.util.List;
import java.util.Date;
import java.util.Objects;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author t1075825
 */
@Entity
@Table(name = "arquivo", schema = "sis_repositorio_arquivo")
@NamedQueries({
    @NamedQuery(name = "Arquivo.findAll", query = "SELECT a FROM Arquivo a")
    , @NamedQuery(name = "Arquivo.findById", query = "SELECT a FROM Arquivo a WHERE a.id = :id")
    , @NamedQuery(name = "Arquivo.findByTitulo", query = "SELECT a FROM Arquivo a WHERE a.titulo = :titulo")
    , @NamedQuery(name = "Arquivo.findByDescricao", query = "SELECT a FROM Arquivo a WHERE a.descricao = :descricao")
    , @NamedQuery(name = "Arquivo.findByDataEnvio", query = "SELECT a FROM Arquivo a WHERE a.dataEnvio = :dataEnvio ORDER BY a.dataEnvio DESC")
    , @NamedQuery(name = "Arquivo.findByAtivo", query = "SELECT a FROM Arquivo a WHERE a.ativo = :ativo")
    , @NamedQuery(name = "Arquivo.findByTipoConteudoId", query = "SELECT a FROM Arquivo a WHERE a.tipoConteudo.id = :tipo")})
public class Arquivo implements Serializable {

    @OneToMany(mappedBy = "arquivo")
    private List<RelComissaoArquivo> relComissaoArquivoList;
    @OneToMany(mappedBy = "arquivo")
    private List<RelRoArquivo> relRoArquivoList;
    @JoinColumn(name = "tipo_conteudo", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private TipoConteudo tipoConteudo;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "titulo", nullable = false, length = 100)
    private String titulo;
    @Basic(optional = false)
    @Column(name = "descricao", nullable = false, length = 255)
    private String descricao;
    @Basic(optional = false)
    @Column(name = "data_envio", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataEnvio;
    @Basic(optional = false)
    @Column(name = "ativo", nullable = false)
    private boolean ativo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "arquivo")
    private List<Log> logList;
    @JoinColumn(name = "id_extensao", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private ExtensaoArquivo idExtensao;
    @Basic(optional = false)
    @Column(name = "nome_arquivo", nullable = false, length = 255)
    private String nome_arquivo;
    
    public Arquivo() {
    }

    public Arquivo(Integer id) {
        this.id = id;
    }

    public Arquivo(Integer id, String titulo, String descricao, Date dataEnvio, boolean ativo, TipoConteudo tipoConteudo, String nome_arquivo) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataEnvio = dataEnvio;
        this.ativo = ativo;
        this.tipoConteudo = tipoConteudo;
        this.nome_arquivo = nome_arquivo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(Date dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public List<Log> getLogList() {
        return logList;
    }

    public void setLogList(List<Log> logList) {
        this.logList = logList;
    }

    public ExtensaoArquivo getIdExtensao() {
        return idExtensao;
    }

    public void setIdExtensao(ExtensaoArquivo idExtensao) {
        this.idExtensao = idExtensao;
    }

    public String getNome_arquivo() {
        return nome_arquivo;
    }

    public void setNome_arquivo(String nome_arquivo) {
        this.nome_arquivo = nome_arquivo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.relComissaoArquivoList);
        hash = 97 * hash + Objects.hashCode(this.relRoArquivoList);
        hash = 97 * hash + Objects.hashCode(this.tipoConteudo);
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.titulo);
        hash = 97 * hash + Objects.hashCode(this.descricao);
        hash = 97 * hash + Objects.hashCode(this.dataEnvio);
        hash = 97 * hash + (this.ativo ? 1 : 0);
        hash = 97 * hash + Objects.hashCode(this.logList);
        hash = 97 * hash + Objects.hashCode(this.idExtensao);
        hash = 97 * hash + Objects.hashCode(this.nome_arquivo);
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
        final Arquivo other = (Arquivo) obj;
        if (this.ativo != other.ativo) {
            return false;
        }
        if (!Objects.equals(this.titulo, other.titulo)) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.nome_arquivo, other.nome_arquivo)) {
            return false;
        }
        if (!Objects.equals(this.relComissaoArquivoList, other.relComissaoArquivoList)) {
            return false;
        }
        if (!Objects.equals(this.relRoArquivoList, other.relRoArquivoList)) {
            return false;
        }
        if (!Objects.equals(this.tipoConteudo, other.tipoConteudo)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.dataEnvio, other.dataEnvio)) {
            return false;
        }
        if (!Objects.equals(this.logList, other.logList)) {
            return false;
        }
        if (!Objects.equals(this.idExtensao, other.idExtensao)) {
            return false;
        }
        return true;
    }
    

    @Override
    public String toString() {
        return "model.Arquivo[ id=" + id + " ]";
    }

    public List<RelRoArquivo> getRelRoArquivoList() {
        return relRoArquivoList;
    }

    public void setRelRoArquivoList(List<RelRoArquivo> relRoArquivoList) {
        this.relRoArquivoList = relRoArquivoList;
    }

    public TipoConteudo getTipoConteudo() {
        return tipoConteudo;
    }

    public void setTipoConteudo(TipoConteudo tipoConteudo) {
        this.tipoConteudo = tipoConteudo;
    }

    public List<RelComissaoArquivo> getRelComissaoArquivoList() {
        return relComissaoArquivoList;
    }

    public void setRelComissaoArquivoList(List<RelComissaoArquivo> relComissaoArquivoList) {
        this.relComissaoArquivoList = relComissaoArquivoList;
    }
    
}
