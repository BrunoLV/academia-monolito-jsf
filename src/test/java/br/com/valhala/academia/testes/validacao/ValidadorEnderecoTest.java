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
import br.com.valhala.academia.modelo.Endereco;
import br.com.valhala.academia.validacao.Validador;
import br.com.valhala.academia.validacao.ValidadorEndereco;
import br.com.valhala.academia.validacao.marcadores.ValidaEndereco;

@EnableAutoWeld
@AddPackages(value = {Validador.class})
@DisplayName("Teste do validador de Endereço")
public class ValidadorEnderecoTest {

	@BeforeAll
	public static void setup() {
		FixtureFactoryLoader.loadTemplates("br.com.valhala.academia.testes.data.templates");
	}

	@Inject
	@ValidaEndereco
	private Validador validador;

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
	@DisplayName("Deve validar quando o endereço tiver um municipio informado que não pertença ao estado atrelado a ele")
	public void deveValidarEnderecoComMunicipioPartencenteAoEstado() {
		Endereco endereco = Fixture.from(Endereco.class).gimme("cenario_endereco_municipio_nao_pertencente_estado");
		Set<String> validacoes = validador.validar(endereco);
		assertThat(validacoes, is(not(empty())));
		assertThat(validacoes.size(), equalTo(1));
		assertThat(validacoes, hasItem("O municipio não pertence ao estado informado."));
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
	@DisplayName("Deve validar quando o endereço não possuir bairro informado")
	public void deveValidarEnderecoSemBairro() {
		Endereco endereco = Fixture.from(Endereco.class).gimme("cenario_sem_bairro");
		Set<String> validacoes = validador.validar(endereco);
		assertThat(validacoes, is(not(empty())));
		assertThat(validacoes.size(), equalTo(1));
		assertThat(validacoes, hasItem("Informe o bairro."));
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
	@DisplayName("Deve validar quando o endereço não tiver nenhuma informação obrigatória preenchida")
	public void deveValidarEnderecoSemInformacoesObrigatoriasPreenchidas() {
		Endereco endereco = Fixture.from(Endereco.class).gimme("cenario_sem_informacoes_preenchidas");
		Set<String> validacoes = validador.validar(endereco);
		assertThat(validacoes, is(not(empty())));
		assertThat(validacoes.size(), equalTo(7));
		assertThat(validacoes,
				containsInAnyOrder("Informe o munícipio.", "Informe o tipo do endereço.",
						"Informe o tipo de logradouro do endereço.", "Informe o logradouro do endereço.",
						"Informe o número do endereço.", "Informe o bairro.", "Informe o cep."));
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
	@DisplayName("Deve validar quando o endereço não possuir município informado")
	public void deveValidarEnderecoSemMunicipio() {
		Endereco endereco = Fixture.from(Endereco.class).gimme("cenario_sem_municipio");
		Set<String> validacoes = validador.validar(endereco);
		assertThat(validacoes, is(not(empty())));
		assertThat(validacoes.size(), equalTo(1));
		assertThat(validacoes, hasItem("Informe o munícipio."));
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
	@DisplayName("Deve validar quando o endereço não possuir tipo de logradouro informado")
	public void deveValidarEnderecoSemTipoLogradouro() {
		Endereco endereco = Fixture.from(Endereco.class).gimme("cenario_sem_tipo_logradouro");
		Set<String> validacoes = validador.validar(endereco);
		assertThat(validacoes, is(not(empty())));
		assertThat(validacoes.size(), equalTo(1));
		assertThat(validacoes, hasItem("Informe o tipo de logradouro do endereço."));
	}

}
