package br.com.valhala.academia.testes.validacao;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

import java.util.Set;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.jboss.weld.junit5.EnableWeld;
import org.jboss.weld.junit5.WeldInitiator;
import org.jboss.weld.junit5.WeldSetup;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import br.com.valhala.academia.db.modelo.Telefone;
import br.com.valhala.academia.validacao.Validador;
import br.com.valhala.academia.validacao.ValidadorTelefone;
import br.com.valhala.academia.validacao.marcadores.ValidaTelefone;

@EnableWeld
@DisplayName("Teste do validador de Telefone")
public class ValidadorTelefoneTest {

	@Inject
	@ValidaTelefone
	private Validador validador;

	@WeldSetup
	public WeldInitiator weld = WeldInitiator.from(ValidadorTelefone.class).activate(RequestScoped.class).build();

	@BeforeAll
	public static void setup() {
		FixtureFactoryLoader.loadTemplates("br.com.valhala.academia.testes.data.templates");
	}

	@Test
	@DisplayName("Deve injetar validador de telefone correto")
	public void deveInjetarValidadorEnderecoCorreto() {
		assertThat(validador, is(notNullValue()));
		assertThat(validador, instanceOf(ValidadorTelefone.class));
	}

	@Test
	@DisplayName("Deve passar sem erro quando telefone estiver completo e com informações válidas")
	public void devePassarSemErroDeValidacaoComTelefoneCompleto() {
		Telefone telefone = Fixture.from(Telefone.class).gimme("cenario_telefone_valido");
		Set<String> validacoes = validador.validar(telefone);
		assertThat(validacoes, is(empty()));
	}

	@Test
	@DisplayName("Deve validar telefone sem DDD informado")
	public void deveValidarTelefoneSemDdd() {
		Telefone telefone = Fixture.from(Telefone.class).gimme("cenario_telefone_sem_ddd");
		Set<String> validacoes = validador.validar(telefone);
		assertThat(validacoes, is(not(empty())));
		assertThat(validacoes.size(), equalTo(1));
		assertThat(validacoes, hasItem("Informe o DDD do telefone."));
	}

	@Test
	@DisplayName("Deve validar telefone com DDD informado com tamanho diferente de 2 caracteres")
	public void deveValidarTelefoneComDddComTamanhoDiferenteDoisCaracteres() {
		Telefone telefone = Fixture.from(Telefone.class)
				.gimme("cenario_telefone_ddd_tamanho_diferente_dois_caracteres");
		Set<String> validacoes = validador.validar(telefone);
		assertThat(validacoes, is(not(empty())));
		assertThat(validacoes.size(), equalTo(1));
		assertThat(validacoes, hasItem("O tamanho do DDD informado é inválido, deve possuir 2 caracteres."));
	}

	@Test
	@DisplayName("Deve validar telefone sem Número informado")
	public void deveValidarTelefoneSemNumero() {
		Telefone telefone = Fixture.from(Telefone.class).gimme("cenario_telefone_sem_numero");
		Set<String> validacoes = validador.validar(telefone);
		assertThat(validacoes, is(not(empty())));
		assertThat(validacoes.size(), equalTo(1));
		assertThat(validacoes, hasItem("Informe o número do telefone."));
	}

	@Test
	@DisplayName("Deve validar telefone sem Tipo informado")
	public void deveValidarTelefoneSemTipo() {
		Telefone telefone = Fixture.from(Telefone.class).gimme("cenario_telefone_sem_tipo");
		Set<String> validacoes = validador.validar(telefone);
		assertThat(validacoes, is(not(empty())));
		assertThat(validacoes.size(), equalTo(1));
		assertThat(validacoes, hasItem("Informe do tipo do telefone."));
	}

	@Test
	@DisplayName("Deve validar quando o telefone não tiver nenhuma informação obrigatória preenchida")
	public void deveValidarTelefoneSemInformacoesObrigatoriasPreenchidas() {
		Telefone telefone = Fixture.from(Telefone.class).gimme("cenario_telefone_com_nada_preenchido");
		Set<String> validacoes = validador.validar(telefone);
		assertThat(validacoes, is(not(empty())));
		assertThat(validacoes.size(), equalTo(3));
		assertThat(validacoes, containsInAnyOrder("Informe o DDD do telefone.", "Informe o número do telefone.",
				"Informe do tipo do telefone."));
	}

}
