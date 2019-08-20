package br.com.valhala.academia.testes.validacao;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.instanceOf;

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
import br.com.valhala.academia.db.modelo.Endereco;
import br.com.valhala.academia.validacao.ValidaEndereco;
import br.com.valhala.academia.validacao.Validador;
import br.com.valhala.academia.validacao.ValidadorEndereco;

@EnableWeld
@DisplayName("Teste do validador de Endereço")
public class ValidadorEnderecoTest {

	@Inject
	@ValidaEndereco
	private Validador validador;

	@WeldSetup
	public WeldInitiator weld = WeldInitiator.from(ValidadorEndereco.class).activate(RequestScoped.class).build();

	@BeforeAll
	public static void setup() {
		FixtureFactoryLoader.loadTemplates("br.com.valhala.academia.testes.data.templates");
	}

	@Test
	@DisplayName("Deve injetar validador de endereço correto")
	public void deveInjetarValidadorEnderecoCorreto() {
		assertThat(validador, is(notNullValue()));
		assertThat(validador, instanceOf(ValidadorEndereco.class));
	}

	@Test
	@DisplayName("Deve passar sem erro quando endereço estiver completo e com informações válidas")
	public void devePassarSemErroDeValidacaoComEnderecoCompleto() {
		Endereco endereco = Fixture.from(Endereco.class).gimme("cenario_endereco_valido");
		Set<String> validacoes = validador.validar(endereco);
		assertThat(validacoes, is(empty()));
	}

	@Test
	@DisplayName("Deve validar quando o endereço não possuir município informado")
	public void deveValidarEnderecoSemMunicipio() {
		Endereco endereco = Fixture.from(Endereco.class).gimme("cenario_sem_municipio");
		Set<String> validacoes = validador.validar(endereco);
		assertThat(validacoes, is(not(empty())));
		assertThat(validacoes.size(), equalTo(1));
		assertThat(validacoes, hasItem("Informe o munícipio."));
	}

	@Test
	@DisplayName("Deve validar quando o endereço não possuir tipo de logradouro informado")
	public void deveValidarEnderecoSemTipoLogradouro() {
		Endereco endereco = Fixture.from(Endereco.class).gimme("cenario_sem_tipo_logradouro");
		Set<String> validacoes = validador.validar(endereco);
		assertThat(validacoes, is(not(empty())));
		assertThat(validacoes.size(), equalTo(1));
		assertThat(validacoes, hasItem("Informe o tipo de logradouro do endereço."));
	}

	@Test
	@DisplayName("Deve validar quando o endereço não possuir logradouro informado")
	public void deveValidarEnderecoSemLogradouro() {
		Endereco endereco = Fixture.from(Endereco.class).gimme("cenario_sem_logradouro");
		Set<String> validacoes = validador.validar(endereco);
		assertThat(validacoes, is(not(empty())));
		assertThat(validacoes.size(), equalTo(1));
		assertThat(validacoes, hasItem("Informe o logradouro do endereço."));
	}

	@Test
	@DisplayName("Deve validar quando o endereço não possuir bairro informado")
	public void deveValidarEnderecoSemBairro() {
		Endereco endereco = Fixture.from(Endereco.class).gimme("cenario_sem_bairro");
		Set<String> validacoes = validador.validar(endereco);
		assertThat(validacoes, is(not(empty())));
		assertThat(validacoes.size(), equalTo(1));
		assertThat(validacoes, hasItem("Informe o bairro."));
	}

	@Test
	@DisplayName("Deve validar quando o endereço não possuir número informado")
	public void deveValidarEnderecoSemNumero() {
		Endereco endereco = Fixture.from(Endereco.class).gimme("cenario_sem_numero");
		Set<String> validacoes = validador.validar(endereco);
		assertThat(validacoes, is(not(empty())));
		assertThat(validacoes.size(), equalTo(1));
		assertThat(validacoes, hasItem("Informe o número do endereço."));
	}

	@Test
	@DisplayName("Deve validar quando o endereço não possuir o tipo informado")
	public void deveValidarEnderecoSemTipo() {
		Endereco endereco = Fixture.from(Endereco.class).gimme("cenario_sem_tipo_endereco");
		Set<String> validacoes = validador.validar(endereco);
		assertThat(validacoes, is(not(empty())));
		assertThat(validacoes.size(), equalTo(1));
		assertThat(validacoes, hasItem("Informe o tipo do endereço."));
	}

	@Test
	@DisplayName("Deve validar quando o endereço não possuir o cep informado")
	public void deveValidarEnderecoSemCep() {
		Endereco endereco = Fixture.from(Endereco.class).gimme("cenario_sem_cep");
		Set<String> validacoes = validador.validar(endereco);
		assertThat(validacoes, is(not(empty())));
		assertThat(validacoes.size(), equalTo(1));
		assertThat(validacoes, hasItem("Informe o cep."));
	}

	@Test
	@DisplayName("Deve validar quando o endereço tiver complemento informado com tamanho menor que o permitido")
	public void deveValidarEnderecoComTamanhoComplementoMenorPermitido() {
		Endereco endereco = Fixture.from(Endereco.class).gimme("cenario_complemento_invalido_tamanho_abaixo_permitido");
		Set<String> validacoes = validador.validar(endereco);
		assertThat(validacoes, is(not(empty())));
		assertThat(validacoes.size(), equalTo(1));
		assertThat(validacoes,
				hasItem("Complemento informado com tamanho abaixo do mínimo permitido de 2 caracteres."));
	}

	@Test
	@DisplayName("Deve validar quando o endereço tiver complemento informado com tamanho acima que o permitido")
	public void deveValidarEnderecoComTamanhoComplementoAcimaPermitido() {
		Endereco endereco = Fixture.from(Endereco.class).gimme("cenario_complemento_invalido_tamanho_acima_permitido");
		Set<String> validacoes = validador.validar(endereco);
		assertThat(validacoes, is(not(empty())));
		assertThat(validacoes.size(), equalTo(1));
		assertThat(validacoes,
				hasItem("Complemento informado com tamanho abaixo do máximo permitido de 20 caracteres."));
	}

	@Test
	@DisplayName("Deve validar quando o endereço não tiver nenhuma informação obrigatória preenchida")
	public void deveValidarEnderecoSemInformacoesObrigatoriasPreenchidas() {
		Endereco endereco = Fixture.from(Endereco.class).gimme("cenario_sem_informacoes_preenchidas");
		Set<String> validacoes = validador.validar(endereco);
		assertThat(validacoes, is(not(empty())));
		assertThat(validacoes.size(), equalTo(7));
		assertThat(validacoes,
				containsInAnyOrder("Informe o munícipio.", 
						"Informe o tipo do endereço.",
						"Informe o tipo de logradouro do endereço.", 
						"Informe o logradouro do endereço.",
						"Informe o número do endereço.", 
						"Informe o bairro.", 
						"Informe o cep."));
	}

}
