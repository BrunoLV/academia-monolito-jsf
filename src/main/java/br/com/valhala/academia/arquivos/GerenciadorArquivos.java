package br.com.valhala.academia.arquivos;

import javax.inject.Named;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

@Named
public class GerenciadorArquivos implements Serializable {

    public void gravaArquivoAPartirDePart(final Part arquivo) throws IOException {
        if (arquivo != null && arquivo.getSize() > 0) {
            InputStream is = arquivo.getInputStream();

        }
    }

}
