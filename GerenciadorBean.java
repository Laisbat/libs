package br.com.bb.direo.bean;

import br.com.bb.direo.dao.ArquivoDAO;
import br.com.bb.direo.dao.ComissaoDAO;
import br.com.bb.direo.dao.ExtensaoArquivoDAO;
import br.com.bb.direo.dao.LogDAO;
import br.com.bb.direo.dao.ReferenciaOrganizacionalDAO;
import br.com.bb.direo.dao.RelComissaoArquivoDAO;
import br.com.bb.direo.dao.RelRoArquivoDAO;
import br.com.bb.direo.dao.TipoConteudoDAO;
import br.com.bb.direo.model.Arquivo;
import br.com.bb.direo.model.Comissao;
import br.com.bb.direo.model.Log;
import br.com.bb.direo.model.ReferenciaOrganizacional;
import br.com.bb.direo.model.RelComissaoArquivo;
import br.com.bb.direo.model.RelComissaoArquivoPK;
import br.com.bb.direo.model.RelRoArquivo;
import br.com.bb.direo.model.RelRoArquivoPK;
import br.com.bb.direo.model.TipoConteudo;
import br.com.bb.sso.api.bean.Usuario;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author t1075825
 */
@ManagedBean
@ViewScoped
public class GerenciadorBean implements Serializable {

    @EJB
    private LogDAO logDao;
    @ManagedProperty(value = "#{usuario}")
    private Usuario usuarioSessao;
    @EJB
    private ArquivoDAO arquivoDAO;
    @EJB
    private TipoConteudoDAO tipoConteudoDAO;
    @EJB
    private ReferenciaOrganizacionalDAO roDAO;
    @EJB
    private ComissaoDAO comissaoDAO;
    @EJB
    private RelComissaoArquivoDAO relcomissaoArquivoDAO;
    @EJB
    private RelRoArquivoDAO relRoArquivoDAO;
    @EJB
    private ExtensaoArquivoDAO extensaoArquivoDAO;

    private String caminho;
    private boolean exibeAlert;
    private boolean exibeAlertTamanhoArquivo;
    private boolean exibeAlertExtensaoInvalida;
    private boolean exibeAlertMinimoRO;
    private boolean exibeAlertTitulo;
    private boolean exibeAlertDescricao;
    private boolean exibeAlertTipoConteudo;

    //Dados tela Upload e Download
    private List<Arquivo> listaArquivo;
    private List<TipoConteudo> listaTipoConteudo;
    private List<ReferenciaOrganizacional> listaRO;
    private TipoConteudo conteudoSelecionado;
    private List<Comissao> listaComissao;

    // Dados do Formulário
    private TipoConteudo tipoConteudo;
    private List<ReferenciaOrganizacional> listaRoArquivo;
    private List<Comissao> listaComissaoArquivo;
    private String titulo;
    private String descricao;
    private Part arquivo;

    //Post -> será executado sempre que esse bean for chamado
    @PostConstruct
    public void inicio() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
        String conteudo = "1";
        exibeAlert = false;
        exibeAlertTamanhoArquivo = false;
        exibeAlertExtensaoInvalida = false;
        exibeAlertMinimoRO = false;
        exibeAlertTitulo = false;
        exibeAlertDescricao = false;
        exibeAlertTipoConteudo = false;
        
        if (request.getParameter("conteudo") != null) {
            conteudo = request.getParameter("conteudo");
        }
        listaArquivo = arquivoDAO.getByTipoConteudoId(Integer.parseInt(conteudo));
        listaTipoConteudo = tipoConteudoDAO.findAll();
        listaRO = roDAO.findAll();
        listaComissao = comissaoDAO.findAll();
        conteudoSelecionado = tipoConteudoDAO.getById(Integer.parseInt(conteudo));
        caminho = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
        if (caminho.contains("C:\\")) {
            caminho = caminho.replace("RepositorioArquivos\\build\\web", "_arqs");
        } else {
            caminho = caminho.replace("repositorioArquivos", "_arqs");
        }

    }

    public void downloadFile(Arquivo arq) {

        Log log = new Log(usuarioSessao.getChave(), arq.getId(), new Date());

        boolean inseriu = logDao.insert(log);

        File file = new File(caminho + arq.getId() + "." + FilenameUtils.getExtension(arq.getNome_arquivo()));
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

        URI uri;
        String url = URLDecoder.decode(arq.getNome_arquivo());
        response.setHeader("Content-Disposition", "attachment;filename=\"" + url + "\"");
        response.setContentLength((int) file.length());
        ServletOutputStream out = null;
        try {
            FileInputStream input = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            out = response.getOutputStream();
            int i = 0;
            while ((i = input.read(buffer)) != -1) {
                out.write(buffer);
            }
            out.flush();
            FacesContext.getCurrentInstance().getResponseComplete();
        } catch (IOException err) {
            err.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException err) {
                err.printStackTrace();
            }
        }

    }

    public void upload() {
        try {
            if (arquivo.getSize() > 15728640) {
                exibeAlertTamanhoArquivo = true;
            }
            if (extensaoArquivoDAO.retornaExtensaoXDescricao(FilenameUtils.getExtension(arquivo.getSubmittedFileName())) == null) {
                exibeAlertExtensaoInvalida = true;
            }
            if (listaRoArquivo.size() < 1){
                exibeAlertMinimoRO = true;
            }
            if (titulo.length() < 10){
                exibeAlertTitulo = true;
            }
            
            if (descricao.length() < 20){
                exibeAlertDescricao = true;
            }
            if (tipoConteudo == null){
                exibeAlertTipoConteudo = true;
            }
            
            if ((exibeAlertTamanhoArquivo == false) && (exibeAlertExtensaoInvalida == false) && (exibeAlertMinimoRO == false) && (exibeAlertTitulo == false) && (exibeAlertDescricao == false) && (exibeAlertTipoConteudo == false)) {
                Arquivo arq = new Arquivo();
                arq.setAtivo(true);
                arq.setDataEnvio(new Date());
                arq.setDescricao(descricao);
                arq.setTitulo(titulo);
                arq.setTipoConteudo(tipoConteudo);
                arq.setIdExtensao(extensaoArquivoDAO.retornaExtensaoXDescricao(FilenameUtils.getExtension(arquivo.getSubmittedFileName())));
                arq.setNome_arquivo(URLEncoder.encode(arquivo.getSubmittedFileName()));
                arquivoDAO.insert(arq);
                int idArquivo = arq.getId();

                listaComissaoArquivo.stream().map((comissao) -> new RelComissaoArquivoPK(idArquivo, comissao.getCodComissao())).forEachOrdered((rel) -> {
                    relcomissaoArquivoDAO.insert(new RelComissaoArquivo(rel));
                });
                listaRoArquivo.stream().map((ro) -> new RelRoArquivoPK(idArquivo, ro.getId())).forEachOrdered((rel) -> {
                    relRoArquivoDAO.insert(new RelRoArquivo(rel));
                });

                Upload upload = Upload.getInstance();
                upload.write(arquivo, idArquivo);

                limpaForm();
                exibeAlert = true;
            }

        } catch (IOException e) {
        }
    }

    private void limpaForm() {
        descricao = "";
        titulo = "";
        tipoConteudo = null;
        listaRoArquivo = null;
        listaComissaoArquivo = null;
    }

    public TipoConteudo getConteudoSelecionado() {
        return conteudoSelecionado;
    }

    public void setConteudoSelecionado(TipoConteudo conteudoSelecionado) {
        this.conteudoSelecionado = conteudoSelecionado;
    }

    public TipoConteudoDAO getTipoConteudoDAO() {
        return tipoConteudoDAO;
    }

    public void setTipoConteudoDAO(TipoConteudoDAO tipoConteudoDAO) {
        this.tipoConteudoDAO = tipoConteudoDAO;
    }

    public List<TipoConteudo> getListaTipoConteudo() {
        return listaTipoConteudo;
    }

    public void setListaTipoConteudo(List<TipoConteudo> listaTipoConteudo) {
        this.listaTipoConteudo = listaTipoConteudo;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public Part getArquivo() {
        return arquivo;
    }

    public void setArquivo(Part arquivo) {
        this.arquivo = arquivo;
    }

    public List<Arquivo> getListaArquivo() {
        return listaArquivo;
    }

    public void setListaArquivo(List<Arquivo> listaArquivo) {
        this.listaArquivo = listaArquivo;
    }

    public void setUsuarioSessao(Usuario usuarioSessao) {
        this.usuarioSessao = usuarioSessao;
    }

    public ArquivoDAO getArquivoDAO() {
        return arquivoDAO;
    }

    public void setArquivoDAO(ArquivoDAO arquivoDAO) {
        this.arquivoDAO = arquivoDAO;
    }

    public ReferenciaOrganizacionalDAO getRoDAO() {
        return roDAO;
    }

    public void setRoDAO(ReferenciaOrganizacionalDAO roDAO) {
        this.roDAO = roDAO;
    }

    public List<ReferenciaOrganizacional> getListaRO() {
        return listaRO;
    }

    public void setListaRO(List<ReferenciaOrganizacional> listaRO) {
        this.listaRO = listaRO;
    }

    public List<Comissao> getListaComissao() {
        return listaComissao;
    }

    public void setListaComissao(List<Comissao> listaComissao) {
        this.listaComissao = listaComissao;
    }

    public TipoConteudo getTipoConteudo() {
        return tipoConteudo;
    }

    public void setTipoConteudo(TipoConteudo tipoConteudo) {
        this.tipoConteudo = tipoConteudo;
    }

    public List<ReferenciaOrganizacional> getListaRoArquivo() {
        return listaRoArquivo;
    }

    public void setListaRoArquivo(List<ReferenciaOrganizacional> listaRoArquivo) {
        this.listaRoArquivo = listaRoArquivo;
    }

    public List<Comissao> getListaComissaoArquivo() {
        return listaComissaoArquivo;
    }

    public void setListaComissaoArquivo(List<Comissao> listaComissaoArquivo) {
        this.listaComissaoArquivo = listaComissaoArquivo;
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

    public boolean isExibeAlert() {
        return exibeAlert;
    }

    public void setExibeAlert(boolean exibeAlert) {
        this.exibeAlert = exibeAlert;
    }

    public boolean isExibeAlertTamanhoArquivo() {
        return exibeAlertTamanhoArquivo;
    }

    public void setExibeAlertTamanhoArquivo(boolean exibeAlertTamanhoArquivo) {
        this.exibeAlertTamanhoArquivo = exibeAlertTamanhoArquivo;
    }

    public boolean isExibeAlertExtensaoInvalida() {
        return exibeAlertExtensaoInvalida;
    }

    public void setExibeAlertExtensaoInvalida(boolean exibeAlertExtensaoInvalida) {
        this.exibeAlertExtensaoInvalida = exibeAlertExtensaoInvalida;
    }

    public boolean isExibeAlertMinimoRO() {
        return exibeAlertMinimoRO;
    }

    public void setExibeAlertMinimoRO(boolean exibeAlertMinimoRO) {
        this.exibeAlertMinimoRO = exibeAlertMinimoRO;
    }

    public boolean isExibeAlertTitulo() {
        return exibeAlertTitulo;
    }

    public void setExibeAlertTitulo(boolean exibeAlertTitulo) {
        this.exibeAlertTitulo = exibeAlertTitulo;
    }

    public boolean isExibeAlertDescricao() {
        return exibeAlertDescricao;
    }

    public void setExibeAlertDescricao(boolean exibeAlertDescricao) {
        this.exibeAlertDescricao = exibeAlertDescricao;
    }

    public boolean isExibeAlertTipoConteudo() {
        return exibeAlertTipoConteudo;
    }

    public void setExibeAlertTipoConteudo(boolean exibeAlertTipoConteudo) {
        this.exibeAlertTipoConteudo = exibeAlertTipoConteudo;
    }
    
    
}
