
package br.com.bb.direo.bean;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import org.apache.commons.io.FilenameUtils;
/**
 *
 * @author t1075825
 */

public class Upload {
    private static final Upload INSTANCE = new Upload();
    private String caminho;
    private Upload() {}
    
    
    public void write(Part part, int nomeNoRepositorio) throws IOException {
        caminho = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
        if (caminho.contains("C:\\")) {
            caminho = caminho.replace("RepositorioArquivos\\build\\web\\", "_arqs");
        } else {
            caminho = caminho.replace("repositorioArquivos/", "_arqs");
        }
        
        String fileName = extractFileName(part);
        String filePath = caminho;
        
        
        File fileSaveDir = new File(filePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }

        String caminhoTotal = filePath + File.separator + nomeNoRepositorio + "." + FilenameUtils.getExtension(part.getSubmittedFileName());
        
        InputStream entrada = part.getInputStream();
        byte[] buffer = new byte[entrada.available()];
        entrada.read(buffer);

        File novoArquivo = new File(caminhoTotal);
        OutputStream saida = new FileOutputStream(novoArquivo);
        saida.write(buffer);
        saida.close();

    }

    public String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }

    public static Upload getInstance() {
        return INSTANCE;
    }

}