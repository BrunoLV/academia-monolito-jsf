package br.com.valhala.academia.testes.validacao;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import br.com.valhala.academia.db.modelo.Endereco;
import br.com.valhala.academia.validacao.ValidadorEndereco;
import junit.framework.Assert;

public class ValidadorEnderecoTest {
	
	
	
	@BeforeAll
	public static void setup() {
		FixtureFactoryLoader.loadTemplates("br.com.valhala.academia.testes.data.templates");
	}
	
	@Test
	public void devePassarSemErroDeValidacaoComEnderecoCompleto() {
		ValidadorEndereco validador = new ValidadorEndereco();
		Endereco endereco = Fixture.from(Endereco.class).gimme("cenario_endereco_valido");
		Set<ConstraintViolation<Endereco>> validar = validador.validar(endereco);
		Assert.assertTrue(validar.isEmpty());
	}

}
