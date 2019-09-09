package br.com.valhala.academia.web.controllers;

import java.util.Collection;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.PrimeFaces;

public abstract class BaseController {

	@Inject
	FacesContext facesContext;

	@Inject
	ExternalContext externalContext;

	public void adicionaMensagensAlerta(Collection<String> mensagens) {
		adicionaMensagensNoContexto(FacesMessage.SEVERITY_WARN, mensagens);
	}

	public void adicionaMensagensErro(Collection<String> mensagens) {
		adicionaMensagensNoContexto(FacesMessage.SEVERITY_ERROR, mensagens);
	}

	public void adicionaMensagensErroFatal(Collection<String> mensagens) {
		adicionaMensagensNoContexto(FacesMessage.SEVERITY_FATAL, mensagens);
	}

	public void adicionaMensagensInformativas(Collection<String> mensagens) {
		adicionaMensagensNoContexto(FacesMessage.SEVERITY_INFO, mensagens);
	}

	private void adicionaMensagensNoContexto(FacesMessage.Severity severity, Collection<String> mensagens) {
		mensagens.forEach(m -> facesContext.addMessage(null, new FacesMessage(severity, m, null)));
		externalContext.getFlash().setKeepMessages(true);
	}

	public void executaScript(String funcao) {
		PrimeFaces.current().executeScript(funcao);
	}

}
