package br.com.valhala.academia.testes.validacao;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

import java.util.Set;

import javax.inject.Inject;

import org.jboss.weld.junit5.auto.AddPackages;
import org.jboss.weld.junit5.auto.EnableAutoWeld;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import br.com.valhala.academia.modelo.Telefone;
import br.com.valhala.academia.validacao.Validador;
import br.com.valhala.academia.validacao.ValidadorTelefone;
import br.com.valhala.academia.validacao.marcadores.ValidaTelefone;

@EnableAutoWeld
@AddPackages(value = { Validador.class })
@DisplayName("Teste do validador de Telefone")
class ValidadorTelefoneTest {

	@BeforeAll
	static void setup() {
		FixtureFactoryLoader.loadTemplates("br.com.valhala.academia.testes.data.templates");
	}

	@Inject
	@ValidaTelefone
	Validador validador;

	@Test
	@DisplayName("Deve injetar validador de telefone correto")
	void deveInjetarValidadorEnderecoCorreto() {
		assertThat(validador, is(notNullValue()));
		assertThat(validador, instanceOf(ValidadorTelefone.class));
	}

	@Test
	@DisplayName("Deve passar sem erro quando telefone estiver completo e com informações válidas")
	void devePassarSemErroDeValidacaoComTelefoneCompleto() {
		Telefone telefone = Fixture.from(Telefone.class).gimme("cenario_telefone_valido");
		Set<String> validacoes = validador.validar(telefone);
		assertThat(validacoes, is(empty()));
	}

	@Test
	@DisplayName("Deve validar telefone com DDD informado com tamanho diferente de 2 caracteres")
	void deveValidarTelefoneComDddComTamanhoDiferenteDoisCaracteres() {
		Telefone telefone = Fixture.from(Telefone.class)
				.gimme("cenario_telefone_ddd_tamanho_diferente_dois_caracteres");
		Set<String> validacoes = validador.validar(telefone);
		assertThat(validacoes, is(not(empty())));
		assertThat(validacoes.size(), equalTo(1));
		assertThat(validacoes, hasItem("O tamanho do DDD informado é inválido, deve possuir 2 caracteres."));
	}

	@Test
	@DisplayName("Deve validar telefone sem DDD informado")
	void deveValidarTelefoneSemDdd() {
		Telefone telefone = Fixture.from(Telefone.class).gimme("cenario_telefone_sem_ddd");
		Set<String> validacoes = validador.validar(telefone);
		assertThat(validacoes, is(not(empty())));
		assertThat(validacoes.size(), equalTo(1));
		assertThat(validacoes, hasItem("Informe o DDD do telefone."));
	}

	@Test
	@DisplayName("Deve validar quando o telefone não tiver nenhuma informação obrigatória preenchida")
	void deveValidarTelefoneSemInformacoesObrigatoriasPreenchidas() {
		Telefone telefone = Fixture.from(Telefone.class).gimme("cenario_telefone_com_nada_preenchido");
		Set<String> validacoes = validador.validar(telefone);
		assertThat(validacoes, is(not(empty())));
		assertThat(validacoes.size(), equalTo(3));
		assertThat(validacoes, containsInAnyOrder("Informe o DDD do telefone.", "Informe o número do telefone.",
				"Informe do tipo do telefone."));
	}

	@Test
	@DisplayName("Deve validar telefone sem Número informado")
	void deveValidarTelefoneSemNumero() {
		Telefone telefone = Fixture.from(Telefone.class).gimme("cenario_telefone_sem_numero");
		Set<String> validacoes = validador.validar(telefone);
		assertThat(validacoes, is(not(empty())));
		assertThat(validacoes.size(), equalTo(1));
		assertThat(validacoes, hasItem("Informe o número do telefone."));
	}

	@Test
	@DisplayName("Deve validar telefone sem Tipo informado")
	void deveValidarTelefoneSemTipo() {
		Telefone telefone = Fixture.from(Telefone.class).gimme("cenario_telefone_sem_tipo");
		Set<String> validacoes = validador.validar(telefone);
		assertThat(validacoes, is(not(empty())));
		assertThat(validacoes.size(), equalTo(1));
		assertThat(validacoes, hasItem("Informe do tipo do telefone."));
	}

}
