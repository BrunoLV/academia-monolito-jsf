package br.com.valhala.academia.web.controllers;

import java.util.Collection;

import javax.faces.application.FacesMessage;

import org.omnifaces.util.Messages;
import org.primefaces.PrimeFaces;

public abstract class BaseController {

    public void adicionaMensagensNoContexto(FacesMessage.Severity severity, Collection<String> mensagens) {
        mensagens.forEach(m -> Messages.addFlash(severity, null, m));
    }

    public void executaScript(String funcao) {
        PrimeFaces.current().executeScript(funcao);
    }

}
