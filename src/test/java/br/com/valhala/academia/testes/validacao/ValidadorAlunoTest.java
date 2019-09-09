package br.com.valhala.academia.testes.validacao;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

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
import br.com.valhala.academia.modelo.Aluno;
import br.com.valhala.academia.validacao.Validador;
import br.com.valhala.academia.validacao.ValidadorAluno;
import br.com.valhala.academia.validacao.marcadores.ValidaAluno;

@EnableWeld
@DisplayName("Teste do validador de Aluno")
public class ValidadorAlunoTest {
	
    @WeldSetup
    public WeldInitiator weld = WeldInitiator.from(ValidadorAluno.class).activate(RequestScoped.class).build();
    
    @Inject
    @ValidaAluno
    private Validador validador;

    @BeforeAll
    public static void setup() {
        FixtureFactoryLoader.loadTemplates("br.com.valhala.academia.testes.data.templates");
    }
    
    @Test
    @DisplayName("Deve injetar validador de aluno correto")
    public void deveInjetarValidadorEnderecoCorreto() {
        assertThat(validador, is(notNullValue()));
        assertThat(validador, instanceOf(ValidadorAluno.class));
    }
    
    @Test
    @DisplayName("Deve passar sem erro quando aluno validos, porém sem relacionamentos")
    public void devePassarSemErroAlunoValidoSemRelacionamentos() {
    	Aluno aluno = Fixture.from(Aluno.class).gimme("cenario_valido_sem_relacionamentos");
        Set<String> validacoes = validador.validar(aluno);
        assertThat(validacoes, is(empty()));
    }
    
    @Test
    @DisplayName("Deve passar sem erro quando aluno valido, com endereços válidos")
    public void devePassarSemErroAlunoValidoComEnderecosValidos() {
    	Aluno aluno = Fixture.from(Aluno.class).gimme("cenario_valido_com_enderecos_validos");
        Set<String> validacoes = validador.validar(aluno);
        assertThat(validacoes, is(empty()));
    }

}
