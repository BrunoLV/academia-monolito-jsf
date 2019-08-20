package br.com.valhala.academia.web.controllers.aluno;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.PrimeFaces;

import br.com.valhala.academia.db.modelo.Aluno;
import br.com.valhala.academia.db.modelo.Endereco;
import br.com.valhala.academia.db.modelo.Estado;
import br.com.valhala.academia.db.modelo.Municipio;
import br.com.valhala.academia.db.modelo.Telefone;
import br.com.valhala.academia.db.modelo.TipoLogradouro;
import br.com.valhala.academia.db.modelo.enums.EnumSexoAluno;
import br.com.valhala.academia.db.modelo.enums.EnumTipoEndereco;
import br.com.valhala.academia.db.modelo.enums.EnumTipoTelefone;
import br.com.valhala.academia.db.modelo.enums.EnumUnidadeFederacao;
import br.com.valhala.academia.servicos.ServicoAluno;
import br.com.valhala.academia.servicos.ServicoEstado;
import br.com.valhala.academia.servicos.ServicoTipoLogradouro;
import br.com.valhala.academia.validacao.ValidaEndereco;
import br.com.valhala.academia.validacao.Validador;

@Named
@ViewScoped
public class AlunoController implements Serializable {

	private static final long serialVersionUID = 1L;

	private Collection<Estado> estados;

	private Collection<Municipio> municipios;

	private Collection<TipoLogradouro> tiposLogradouros;

	private Collection<EnumUnidadeFederacao> ufs;

	private Collection<EnumTipoEndereco> tiposEnderecos;

	private Collection<EnumTipoTelefone> tiposTelefones;

	@Inject
	private ServicoAluno servicoAluno;

	@Inject
	private ServicoTipoLogradouro servicoTipoLogradouro;

	@Inject
	private ServicoEstado servicoEstado;

	@Inject
	@ValidaEndereco
	private Validador validadorEndereco;

	private Long id;

	private Aluno aluno;

	private Endereco endereco;

	private Telefone telefone;

	private EnumUnidadeFederacao ufSelecionado;
	private Estado estado;

	public AlunoController() {
		super();
	}

	public void adicionaEndereco() {

		Set<String> constraints = validadorEndereco.validar(endereco);

		if (CollectionUtils.isNotEmpty(constraints)) {
			constraints.stream().forEach(mensagem -> FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, mensagem, null)));
			return;
		}

		aluno.adicionaEndereco(endereco);
		endereco = new Endereco();
		ufSelecionado = null;
		estado = null;
		PrimeFaces.current().executeScript("aluno.controller.resetaMascarasDadosEndereco();");
	}

	public void adicionaTelefone() {

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		Set<ConstraintViolation<Telefone>> constraints = validator.validate(telefone);

		if (CollectionUtils.isNotEmpty(constraints)) {
			constraints.stream().forEach(v -> FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, v.getMessage(), null)));
			return;
		}

		aluno.adicionaTelefone(telefone);
		telefone = new Telefone();
		PrimeFaces.current().executeScript("aluno.controller.resetaMascarasDadosTelefone();");
	}

	public void carregaAluno() {
		if (id != null) {
			aluno = servicoAluno.buscaPorId(id);
		}
	}

	public void editaEndereco(Endereco endereco) {
		this.endereco = endereco;
		aluno.removeEndereco(endereco);
		ufSelecionado = endereco.getMunicipio().getUf();
		estado = servicoEstado.buscaEstadoPorUFComMunicipios(ufSelecionado);
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

		tiposLogradouros = servicoTipoLogradouro.listaTiposLogradouros();

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
			estado = servicoEstado.buscaEstadoPorUFComMunicipios(ufSelecionado);
			endereco.setMunicipio(null);
		}
	}

	public void removeEndereco(Endereco endereco) {
		aluno.removeEndereco(endereco);
	}

	public void removeTelefone(Telefone telefone) {
		aluno.removeTelefone(telefone);
	}

	public String salva() {

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		Set<ConstraintViolation<Aluno>> constraints = validator.validate(aluno);

		if (CollectionUtils.isNotEmpty(constraints)) {
			constraints.stream().forEach(v -> FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, v.getMessage(), null)));
			return null;
		}

		servicoAluno.salva(aluno);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Aluno salvo com sucesso!", null));
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		return "alunos?faces-redirect=true";
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
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

}
