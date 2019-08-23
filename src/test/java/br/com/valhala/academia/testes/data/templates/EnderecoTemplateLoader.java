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
		}).addTemplate("cenario_endereco_municipio_nao_pertencente_estado", new Rule() {
			{
				add("logradouro", "Romeu José Vieira");
				add("numero", "90");
				add("complemento", "5º andar Bloco B");
				add("bairro", "Nossa Senhora do Rosário");
				add("cep", "88110-914");
				add("tipoEndereco", EnumTipoEndereco.RESIDENCIAL);
				add("tipoLogradouro", one(TipoLogradouro.class, "cenario_rua"));
				add("municipio", one(Municipio.class, "cenario_municipio_nao_pertence_estado"));
			}
		}).addTemplate("cenario_sem_municipio", new Rule() {
			{
				add("logradouro", "Romeu José Vieira");
				add("numero", "90");
				add("complemento", "5º andar Bloco B");
				add("bairro", "Nossa Senhora do Rosário");
				add("cep", "88110-914");
				add("tipoEndereco", EnumTipoEndereco.RESIDENCIAL);
				add("tipoLogradouro", one(TipoLogradouro.class, "cenario_rua"));
			}
		}).addTemplate("cenario_sem_tipo_logradouro", new Rule() {
			{
				add("logradouro", "Romeu José Vieira");
				add("numero", "90");
				add("complemento", "5º andar Bloco B");
				add("bairro", "Nossa Senhora do Rosário");
				add("cep", "88110-914");
				add("tipoEndereco", EnumTipoEndereco.RESIDENCIAL);
				add("municipio", one(Municipio.class, "cenario_sao_jose"));
			}
		}).addTemplate("cenario_sem_logradouro", new Rule() {
			{
				add("numero", "90");
				add("complemento", "5º andar Bloco B");
				add("bairro", "Nossa Senhora do Rosário");
				add("cep", "88110-914");
				add("tipoEndereco", EnumTipoEndereco.RESIDENCIAL);
				add("tipoLogradouro", one(TipoLogradouro.class, "cenario_rua"));
				add("municipio", one(Municipio.class, "cenario_sao_jose"));
			}
		}).addTemplate("cenario_sem_bairro", new Rule() {
			{
				add("logradouro", "Romeu José Vieira");
				add("numero", "90");
				add("complemento", "5º andar Bloco B");
				add("cep", "88110-914");
				add("tipoEndereco", EnumTipoEndereco.RESIDENCIAL);
				add("tipoLogradouro", one(TipoLogradouro.class, "cenario_rua"));
				add("municipio", one(Municipio.class, "cenario_sao_jose"));
			}
		}).addTemplate("cenario_sem_numero", new Rule() {
			{
				add("logradouro", "Romeu José Vieira");
				add("complemento", "5º andar Bloco B");
				add("bairro", "Nossa Senhora do Rosário");
				add("cep", "88110-914");
				add("tipoEndereco", EnumTipoEndereco.RESIDENCIAL);
				add("tipoLogradouro", one(TipoLogradouro.class, "cenario_rua"));
				add("municipio", one(Municipio.class, "cenario_sao_jose"));
			}
		}).addTemplate("cenario_sem_tipo_endereco", new Rule() {
			{
				add("logradouro", "Romeu José Vieira");
				add("numero", "90");
				add("complemento", "5º andar Bloco B");
				add("bairro", "Nossa Senhora do Rosário");
				add("cep", "88110-914");
				add("tipoLogradouro", one(TipoLogradouro.class, "cenario_rua"));
				add("municipio", one(Municipio.class, "cenario_sao_jose"));
			}
		}).addTemplate("cenario_sem_cep", new Rule() {
			{
				add("logradouro", "Romeu José Vieira");
				add("numero", "90");
				add("complemento", "5º andar Bloco B");
				add("bairro", "Nossa Senhora do Rosário");
				add("tipoEndereco", EnumTipoEndereco.RESIDENCIAL);
				add("tipoLogradouro", one(TipoLogradouro.class, "cenario_rua"));
				add("municipio", one(Municipio.class, "cenario_sao_jose"));
			}
		}).addTemplate("cenario_complemento_invalido_tamanho_abaixo_permitido", new Rule() {
			{
				add("logradouro", "Romeu José Vieira");
				add("numero", "90");
				add("complemento", "R");
				add("bairro", "Nossa Senhora do Rosário");
				add("cep", "88110-914");
				add("tipoEndereco", EnumTipoEndereco.RESIDENCIAL);
				add("tipoLogradouro", one(TipoLogradouro.class, "cenario_rua"));
				add("municipio", one(Municipio.class, "cenario_sao_jose"));
			}
		}).addTemplate("cenario_complemento_invalido_tamanho_acima_permitido", new Rule() {
			{
				add("logradouro", "Romeu José Vieira");
				add("numero", "90");
				add("complemento", "Teste de tamanho de complemento acima do permitido pelo sistema");
				add("bairro", "Nossa Senhora do Rosário");
				add("cep", "88110-914");
				add("tipoEndereco", EnumTipoEndereco.RESIDENCIAL);
				add("tipoLogradouro", one(TipoLogradouro.class, "cenario_rua"));
				add("municipio", one(Municipio.class, "cenario_sao_jose"));
			}
		}).addTemplate("cenario_sem_informacoes_preenchidas", new Rule() {
			{
				add("complemento", "5º andar Bloco B");
			}
		});
	}

}
