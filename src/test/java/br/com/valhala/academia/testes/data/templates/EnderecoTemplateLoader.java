package br.com.valhala.academia.testes.data.templates;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.valhala.academia.db.modelo.Endereco;
import br.com.valhala.academia.db.modelo.Municipio;
import br.com.valhala.academia.db.modelo.TipoLogradouro;
import br.com.valhala.academia.db.modelo.enums.EnumTipoEndereco;

public class EnderecoTemplateLoader implements TemplateLoader {

	@Override
	public void load() {
		Fixture.of(Endereco.class).addTemplate("cenario_endereco_valido", new Rule() {
			{
				add("logradouro", "Romeu José Vieira");
				add("numero", "90");
				add("complemento", "5º andar Bloco B");
				add("bairro", "Nossa Senhora do Rosário");
				add("cep", "88110-914");
				add("tipoEndereco", EnumTipoEndereco.RESIDENCIAL);
				add("tipoLogradouro", one(TipoLogradouro.class, "cenario_rua"));
				add("municipio", one(Municipio.class, "cenario_sao_jose"));
			}
		});
	}

}
