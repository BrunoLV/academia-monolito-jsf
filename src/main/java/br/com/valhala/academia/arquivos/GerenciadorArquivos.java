package br.com.valhala.academia.arquivos;

import javax.inject.Named;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Named
public class GerenciadorArquivos implements Serializable {

    public static final String CAMINHO_BASE = "/home/bruno/Files";

    public void gravaArquivoAPartirDePart(final Part arquivo, final String path) throws IOException {

        Path diretorio = Paths.get(CAMINHO_BASE, path);
        if (!Files.exists(diretorio)) {
            Files.createDirectories(diretorio);
        }

        if (arquivo != null && arquivo.getSize() > 0) {
            try (InputStream is = arquivo.getInputStream()) {
                Files.copy(is, Path.of(diretorio.toString(), arquivo.getSubmittedFileName()));
            }
        }

    }

}
