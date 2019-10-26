package br.com.valhala.academia.testes.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.valhala.academia.clients.ClienteApiCep;
import br.com.valhala.academia.clients.wrapper.EnderecoApiCep;
import br.com.valhala.academia.modelo.Endereco;
import br.com.valhala.academia.modelo.Estado;
import br.com.valhala.academia.modelo.Municipio;
import br.com.valhala.academia.modelo.TipoLogradouro;
import br.com.valhala.academia.modelo.enums.EnumUnidadeFederacao;
import br.com.valhala.academia.servicos.AlunoService;
import br.com.valhala.academia.servicos.EstadoService;
import br.com.valhala.academia.servicos.TipoLogradouroService;
import br.com.valhala.academia.web.controllers.aluno.AlunoController;

@ExtendWith(MockitoExtension.class)
class AlunoControllerTest {

	@Mock
	AlunoService alunoService;

	@Mock
	TipoLogradouroService tipoLogradouroService;

	@Mock
	EstadoService estadoService;

	@Mock
	ClienteApiCep clientApiCep;

	@InjectMocks
	AlunoController controller;

	@Test
	@DisplayName("Deve atualizar o endereco a partir do resultado da api de CEP")
	void deveAtualizarEnderecoAPartirDePesquisaDeCep() {

		TipoLogradouro rua = TipoLogradouro.builder().withAbreviatura("R").withDescricao("Rua").build();
		TipoLogradouro ruaIntegracao = TipoLogradouro.builder().withAbreviatura("R I").withDescricao("Rua Integração")
				.build();

		Municipio abadiaGoias = Municipio.builder().withCodigoIbge(5200050).withNome("ABADIA DE GOIÁS")
				.withUf(EnumUnidadeFederacao.GO).build();
		Municipio catalao = Municipio.builder().withCodigoIbge(5205109).withNome("CATALÃO")
				.withUf(EnumUnidadeFederacao.GO).build();

		Estado goias = Estado.builder().withCodigo((short) 52).withNome("Goiás").withUf(EnumUnidadeFederacao.GO)
				.withMunicipios(List.of(abadiaGoias, catalao)).build();

		EnderecoApiCep enderecoApi = EnderecoApiCep.builder().withBairro("Residencial Portal do Lago I")
				.withCep("75709-773").withCidade("Catalão").withUf("GO").withLogradouro("Rua Integração 29")
				.withIbge("5205109").build();

		when(tipoLogradouroService.listaTiposLogradouros()).thenReturn(List.of(rua, ruaIntegracao));

		when(estadoService.buscaEstadoPorUFComMunicipios(EnumUnidadeFederacao.GO)).thenReturn(goias);

		when(clientApiCep.buscaCep("75709-773")).thenReturn(enderecoApi);

		controller.inicializa();

		Endereco endereco = controller.getEndereco();
		endereco.setCep("75709-773");

		controller.trataMudancaCep();

		verify(tipoLogradouroService, times(1)).listaTiposLogradouros();
		verify(clientApiCep, times(1)).buscaCep("75709-773");
		verify(estadoService, times(1)).buscaEstadoPorUFComMunicipios(EnumUnidadeFederacao.GO);

		assertThat(endereco.getBairro(), equalTo("Residencial Portal do Lago I"));
		assertThat(endereco.getCep(), equalTo("75709-773"));
		assertThat(endereco.getMunicipio().getUf(), equalTo(EnumUnidadeFederacao.GO));
		assertThat(endereco.getMunicipio(), is(notNullValue()));
		assertThat(endereco.getMunicipio().getCodigoIbge(), equalTo(Integer.valueOf(5205109)));
		assertThat(endereco.getEnderecoCompleto(), equalTo("Rua Integração 29"));
	}

	@Test
	@DisplayName("Deve limpar dados quando pesquisa de CEP der erro")
	void deveLimparDadosCasoPesquisaDeCepDeErro() {

		controller.inicializa();

		TipoLogradouro ruaIntegracao = TipoLogradouro.builder().withAbreviatura("R I").withDescricao("Rua Integração")
				.build();
		Municipio catalao = Municipio.builder().withCodigoIbge(5205109).withNome("CATALÃO")
				.withUf(EnumUnidadeFederacao.GO).build();

		Endereco endereco = controller.getEndereco();
		preencheEndereco(ruaIntegracao, catalao, endereco);

		when(clientApiCep.buscaCep("99999-888")).thenThrow(new RuntimeException());

		endereco.setCep("99999-888");

		controller.trataMudancaCep();

		verify(clientApiCep, times(1)).buscaCep("99999-888");

		assertThat(endereco.getLogradouro(), is(nullValue()));
		assertThat(endereco.getTipoLogradouro(), is(notNullValue()));
		assertThat(endereco.getTipoLogradouro().getId(), is(nullValue()));
		assertThat(endereco.getCep(), is(nullValue()));
		assertThat(endereco.getBairro(), is(nullValue()));
		assertThat(endereco.getMunicipio(), is(notNullValue()));
		assertThat(endereco.getMunicipio().getId(), is(nullValue()));

	}

	@Test
	@DisplayName("Deve limpar dados caso pesquisa de CEP não tenha resultados")
	void deveLimparDadosCasoPesquisaDeCepNaoTenhaResultados() {

		controller.inicializa();

		TipoLogradouro ruaIntegracao = TipoLogradouro.builder().withAbreviatura("R I").withDescricao("Rua Integração")
				.build();
		Municipio catalao = Municipio.builder().withCodigoIbge(5205109).withNome("CATALÃO")
				.withUf(EnumUnidadeFederacao.GO).build();

		Endereco endereco = controller.getEndereco();
		preencheEndereco(ruaIntegracao, catalao, endereco);

		when(clientApiCep.buscaCep("99999-888")).thenReturn(null);

		endereco.setCep("99999-888");

		controller.trataMudancaCep();

		verify(clientApiCep, times(1)).buscaCep("99999-888");

		assertThat(endereco.getLogradouro(), is(nullValue()));
		assertThat(endereco.getTipoLogradouro(), is(notNullValue()));
		assertThat(endereco.getTipoLogradouro().getId(), is(nullValue()));
		assertThat(endereco.getCep(), is(nullValue()));
		assertThat(endereco.getBairro(), is(nullValue()));
		assertThat(endereco.getMunicipio(), is(notNullValue()));
		assertThat(endereco.getMunicipio().getId(), is(nullValue()));

	}

	private void preencheEndereco(TipoLogradouro ruaIntegracao, Municipio catalao, Endereco endereco) {
		endereco.setLogradouro("29");
		endereco.setTipoLogradouro(ruaIntegracao);
		endereco.setCep("75709-773");
		endereco.setBairro("Residencial Portal do Lago I");
		endereco.setMunicipio(catalao);
	}

}
