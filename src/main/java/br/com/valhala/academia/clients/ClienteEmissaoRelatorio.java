package br.com.valhala.academia.clients;

import br.com.valhala.academia.clients.wrapper.Relatorio;
import br.com.valhala.academia.clients.wrapper.RequisicaoRelatorio;

import javax.inject.Named;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

@Named
public class ClienteEmissaoRelatorio implements Serializable {

    private static final long serialVersionUID = 1L;

    private String urlEmissaoRelatorio;

    public ClienteEmissaoRelatorio() {
        InputStream stream = getClass().getClassLoader().getResourceAsStream("client.properties");
        Properties props = new Properties();
        try {
            props.load(stream);
            urlEmissaoRelatorio = props.getProperty("emissor.relatorio.url");
        } catch (IOException e) {
        }
    }

    public Relatorio emitiRelatorio(RequisicaoRelatorio requisicao) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(urlEmissaoRelatorio).path("/");

        Invocation.Builder invokeBuilder = target.request(MediaType.APPLICATION_JSON);

        Response response = invokeBuilder.post(Entity.entity(requisicao, MediaType.APPLICATION_JSON));

        Relatorio relatorio = response.readEntity(Relatorio.class);
        return relatorio;
    }

}
