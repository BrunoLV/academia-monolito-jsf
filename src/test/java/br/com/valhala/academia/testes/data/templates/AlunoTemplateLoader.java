package br.com.valhala.academia.testes.data.templates;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.valhala.academia.modelo.Aluno;
import br.com.valhala.academia.modelo.Endereco;
import br.com.valhala.academia.modelo.enums.EnumSexoAluno;
import br.com.valhala.academia.modelo.enums.EnumSituacaoAluno;

public class AlunoTemplateLoader implements TemplateLoader {

	@Override
	public void load() {

		Fixture.of(Aluno.class).addTemplate("cenario_valido_sem_relacionamentos", new Rule() {
			{
				add("nome", "Elaine Rosângela Hadassa Aragão");
				add("cpf", "771.306.389-74");
				add("email", "elainerosangelahadassaaragao__elainerosangelahadassaaragao@amaralmonteiro.com.br");
				add("dataNascimento", Date.from(LocalDate.of(1994, 07, 22).atStartOfDay().toInstant(ZoneOffset.UTC)));
				add("situacao", EnumSituacaoAluno.ATIVO);
				add("sexo", EnumSexoAluno.FEMININO);
			}
		}).addTemplate("cenario_valido_com_enderecos_validos").inherits("cenario_valido_sem_relacionamentos",
				new Rule() {
					{
						add("enderecos", has(1).of(Endereco.class, "cenario_parceiro_valido"));
					}
				});

	}

}
