package br.com.valhala.academia.testes.data.templates;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.valhala.academia.db.modelo.Estado;
import br.com.valhala.academia.db.modelo.Municipio;
import br.com.valhala.academia.db.modelo.enums.EnumUnidadeFederacao;

public class MunicipioTemplateLoader implements TemplateLoader {

	@Override
	public void load() {
		Fixture.of(Municipio.class).addTemplate("cenario_sao_jose", new Rule() {
			{
				add("nome", "São José");
				add("codigoIbge", 4216602);
				add("uf", EnumUnidadeFederacao.SC);
				add("estado", one(Estado.class, "cenario_santa_catarina"));
			}
		}).addTemplate("cenario_municipio_nao_pertence_estado", new Rule() {
			{
				add("nome", "São José");
				add("codigoIbge", 4216602);
				add("uf", EnumUnidadeFederacao.SC);
				add("estado", one(Estado.class, "cenario_sao_paulo"));
			}
		});
	}

}
