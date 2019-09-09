package br.com.valhala.academia.testes.data.templates;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.valhala.academia.modelo.Estado;
import br.com.valhala.academia.modelo.enums.EnumUnidadeFederacao;

public class EstadoTemplateLoader implements TemplateLoader {

    @Override
    public void load() {
        Fixture.of(Estado.class).addTemplate("cenario_santa_catarina", new Rule() {
            {
                add("codigo", (short) 42);
                add("nome", "Santa Catarina");
                add("uf", EnumUnidadeFederacao.SC);
            }
        }).addTemplate("cenario_sao_paulo", new Rule() {
            {
                add("codigo", (short) 35);
                add("nome", "São Paulo");
                add("uf", EnumUnidadeFederacao.SP);
            }
        }).addTemplate("cenario_para", new Rule() {
            {
                add("codigo", (short) 15);
                add("nome", "Pará");
                add("uf", EnumUnidadeFederacao.PA);
            }
        });
    }

}
