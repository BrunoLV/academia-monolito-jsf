package br.com.valhala.academia.testes.data.templates;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.valhala.academia.db.modelo.TipoLogradouro;

public class TipoLogradouroTemplateLoader implements TemplateLoader {

	@Override
	public void load() {

		Fixture.of(TipoLogradouro.class).addTemplate("cenario_rua", new Rule() {
			{
				add("abreviatura", "R");
				add("descricao", "Rua");
			}
		});

	}

}
