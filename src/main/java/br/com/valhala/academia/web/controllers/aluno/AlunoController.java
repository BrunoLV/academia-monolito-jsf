package br.com.valhala.academia.web.controllers.aluno;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import br.com.valhala.academia.arquivos.GerenciadorArquivos;
import br.com.valhala.academia.clients.ClienteApiCep;
import br.com.valhala.academia.clients.wrapper.EnderecoApiCep;
import br.com.valhala.academia.modelo.Aluno;
import br.com.valhala.academia.modelo.Endereco;
import br.com.valhala.academia.modelo.Estado;
import br.com.valhala.academia.modelo.Municipio;
import br.com.valhala.academia.modelo.Telefone;
import br.com.valhala.academia.modelo.TipoLogradouro;
import br.com.valhala.academia.modelo.enums.EnumSexoAluno;
import br.com.valhala.academia.modelo.enums.EnumTipoEndereco;
import br.com.valhala.academia.modelo.enums.EnumTipoTelefone;
import br.com.valhala.academia.modelo.enums.EnumUnidadeFederacao;
import br.com.valhala.academia.servicos.AlunoService;
import br.com.valhala.academia.servicos.EstadoService;
import br.com.valhala.academia.servicos.TipoLogradouroService;
import br.com.valhala.academia.validacao.Validador;
import br.com.valhala.academia.validacao.marcadores.ValidaAluno;
import br.com.valhala.academia.validacao.marcadores.ValidaEndereco;
import br.com.valhala.academia.validacao.marcadores.ValidaTelefone;
import br.com.valhala.academia.web.controllers.BaseController;

@Named
@ViewScoped
public class AlunoController extends BaseController implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private Aluno aluno;

	private Endereco endereco;

	private Telefone telefone;

	private EnumUnidadeFederacao ufSelecionado;

	private Estado estado;

	private Collection<Estado> estados;

	private Collection<Municipio> municipios;

	private Collection<TipoLogradouro> tiposLogradouros;

	private Collection<EnumUnidadeFederacao> ufs;

	private Collection<EnumTipoEndereco> tiposEnderecos;

	private Collection<EnumTipoTelefone> tiposTelefones;

	private Part foto;

	@Inject
	private AlunoService alunoService;

	@Inject
	private TipoLogradouroService tipoLogradouroService;

	@Inject
	private EstadoService estadoService;

	@Inject
	private GerenciadorArquivos gerenciadorArquivos;

	@Inject
	@ValidaEndereco
	private Validador validadorEndereco;

	@Inject
	@ValidaTelefone
	private Validador validadorTelefone;

	@Inject
	@ValidaAluno
	private Validador validadorAluno;

	@Inject
	private ClienteApiCep apiCep;

	public AlunoController() {
		super();
	}

	public void adicionaEndereco() {

		Set<String> validacoes = validadorEndereco.validar(endereco);

		if (CollectionUtils.isNotEmpty(validacoes)) {
			adicionaMensagensNoContexto(FacesMessage.SEVERITY_WARN, validacoes);
			executaScript("aluno.controller.resetaMascarasDadosEndereco()");
			return;
		}

		aluno.adicionaEndereco(endereco);
		endereco = new Endereco();
		ufSelecionado = null;
		estado = null;

		executaScript("aluno.controller.resetaMascarasDadosEndereco()");
	}

	public void adicionaTelefone() {

		Set<String> validacoes = validadorTelefone.validar(telefone);

		if (CollectionUtils.isNotEmpty(validacoes)) {
			adicionaMensagensNoContexto(FacesMessage.SEVERITY_WARN, validacoes);
			executaScript("aluno.controller.resetaMascarasDadosEndereco()");
			return;
		}

		aluno.adicionaTelefone(telefone);
		telefone = new Telefone();

		executaScript("aluno.controller.resetaMascarasDadosTelefone()");
	}

	public void carregaAluno() {
		if (id != null) {
			aluno = alunoService.buscaPorId(id);
		}
	}

	public void editaEndereco(Endereco endereco) {
		this.endereco = endereco;
		aluno.removeEndereco(endereco);
		ufSelecionado = endereco.getMunicipio().getUf();
		estado = estadoService.buscaEstadoPorUFComMunicipios(ufSelecionado);
	}

	public void editaTelefone(Telefone telefone) {
		this.telefone = telefone;
		aluno.removeTelefone(telefone);
	}

	public Aluno getAluno() {
		return aluno;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public Estado getEstado() {
		return estado;
	}

	public Collection<Estado> getEstados() {
		return estados;
	}

	public Part getFoto() {
		return foto;
	}

	public Long getId() {
		return id;
	}

	public Collection<Municipio> getMunicipios() {
		return municipios;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public Collection<EnumTipoEndereco> getTiposEnderecos() {
		return tiposEnderecos;
	}

	public Collection<TipoLogradouro> getTiposLogradouros() {
		return tiposLogradouros;
	}

	public Collection<EnumTipoTelefone> getTiposTelefones() {
		return tiposTelefones;
	}

	public Collection<EnumUnidadeFederacao> getUfs() {
		return ufs;
	}

	public EnumUnidadeFederacao getUfSelecionado() {
		return ufSelecionado;
	}

	@PostConstruct
	public void inicializa() {

		ufs = Arrays.asList(EnumUnidadeFederacao.values());
		tiposEnderecos = Arrays.asList(EnumTipoEndereco.values());
		tiposTelefones = Arrays.asList(EnumTipoTelefone.values());

		tiposLogradouros = tipoLogradouroService.listaTiposLogradouros();

		aluno = new Aluno();
		endereco = new Endereco();
		endereco.setTipoLogradouro(new TipoLogradouro());
		endereco.setMunicipio(new Municipio());

		telefone = new Telefone();

	}

	public EnumSexoAluno[] listaSexo() {
		return EnumSexoAluno.values();
	}

	public void onChangeUf(AjaxBehaviorEvent event) {
		if (ufSelecionado == null) {
			estado = null;
			endereco.setMunicipio(new Municipio());
		} else {
			estado = estadoService.buscaEstadoPorUFComMunicipios(ufSelecionado);
			endereco.setMunicipio(null);
		}
	}

	private void preencheEnderecoAPartirPesquisaCep(EnderecoApiCep enderecoApi) {
		for (TipoLogradouro tipoLogradouro : tiposLogradouros) {
			if (enderecoApi.getLogradouro().toLowerCase().contains(tipoLogradouro.getDescricao().toLowerCase())) {
				endereco.setTipoLogradouro(tipoLogradouro);
				endereco.setLogradouro(enderecoApi.getLogradouro().replace(tipoLogradouro.getDescricao(), "").trim());
				break;
			}
		}
		endereco.setBairro(enderecoApi.getBairro());
		endereco.setComplemento(enderecoApi.getComplemento());
		ufSelecionado = EnumUnidadeFederacao.valueOf(enderecoApi.getUf());
		if (ufSelecionado == null) {
			estado = null;
			endereco.setMunicipio(new Municipio());
		} else {
			estado = estadoService.buscaEstadoPorUFComMunicipios(ufSelecionado);
			for (Municipio municipio : estado.getMunicipios()) {
				if (municipio.getCodigoIbge().equals(Integer.valueOf(enderecoApi.getIbge()))) {
					endereco.setMunicipio(municipio);
					break;
				}
			}
		}
	}

	public void removeEndereco(Endereco endereco) {
		aluno.removeEndereco(endereco);
	}

	public void removeTelefone(Telefone telefone) {
		aluno.removeTelefone(telefone);
	}

	private void resetaEndereco() {
		endereco.setBairro(null);
		endereco.setCep(null);
		endereco.setComplemento(null);
		endereco.setLogradouro(null);
		endereco.setMunicipio(new Municipio());
		endereco.setNumero(null);
		endereco.setTipoLogradouro(new TipoLogradouro());
	}

	public String salva() {

		Set<String> validacoes = validadorAluno.validar(aluno);

		if (CollectionUtils.isNotEmpty(validacoes)) {
			adicionaMensagensNoContexto(FacesMessage.SEVERITY_WARN, validacoes);
			return null;
		}

		try {
			if (foto != null) {
				gerenciadorArquivos.gravaArquivoAPartirDePart(foto, "Fotos");
				aluno.setPathFoto("/Fotos/" + foto.getSubmittedFileName());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		alunoService.salva(aluno);

		adicionaMensagensNoContexto(FacesMessage.SEVERITY_INFO, Arrays.asList("Aluno salvo com sucesso!"));

		return "alunos?faces-redirect=true";
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public void setFoto(Part foto) {
		this.foto = foto;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public void setUfSelecionado(EnumUnidadeFederacao ufSelecionado) {
		this.ufSelecionado = ufSelecionado;
	}

	public void trataMudancaCep() {
		if (StringUtils.isNotBlank(endereco.getCep())) {
			try {
				EnderecoApiCep enderecoApi = apiCep.buscaCep(this.endereco.getCep());
				if (enderecoApi != null) {
					preencheEnderecoAPartirPesquisaCep(enderecoApi);
				} else {
					resetaEndereco();
				}
			} catch (Exception e) {
				resetaEndereco();
			}
		}
	}

}
