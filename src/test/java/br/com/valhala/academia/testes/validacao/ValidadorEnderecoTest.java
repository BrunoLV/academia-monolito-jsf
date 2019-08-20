package br.com.valhala.academia.testes.validacao;

import java.util.Set;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;

import org.jboss.weld.junit5.EnableWeld;
import org.jboss.weld.junit5.WeldInitiator;
import org.jboss.weld.junit5.WeldSetup;
import org.junit.jupiter.api.Assertions;
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
@DisplayName("Teste do validador de Endereco")
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
	@DisplayName("deve passar sem erro quando endereco estiver completo e com informacoes validas")
	public void devePassarSemErroDeValidacaoComEnderecoCompleto() {
		Endereco endereco = Fixture.from(Endereco.class).gimme("cenario_endereco_valido");
		Set<ConstraintViolation<Endereco>> validar = validador.validar(endereco);
		Assertions.assertTrue(validar.isEmpty());
	}
	
	@Test
	@DisplayName("deve validar quando o endereco nao possuir municipio informado")
	public void deveValidarEnderecoSemMunicipio() {
		Endereco endereco = Fixture.from(Endereco.class).gimme("cenario_sem_municipio");
		Set<ConstraintViolation<Endereco>> validacoes = validador.validar(endereco);
		Assertions.assertFalse(validacoes.isEmpty());
		Assertions.assertEquals(1, validacoes.size());
		Assertions.assertEquals("Informe o munícipio.", validacoes.stream().findFirst().get().getMessage());
	}
        
        @Test
        @DisplayName("deve validar quando o endereco nao possuir tipo de logradouro informado") 
        public void deveValidarEnderecoSemTipoLogradouro() {
            Endereco endereco = Fixture.from(Endereco.class).gimme("cenario_sem_tipo_logradouro");
            Set<ConstraintViolation<Endereco>> validacoes = validador.validar(endereco);
            Assertions.assertFalse(validacoes.isEmpty());
            Assertions.assertEquals(1, validacoes.size());
            Assertions.assertEquals("Informe o tipo de logradouro do endereço.", validacoes.stream().findFirst().get().getMessage());
        }

}
