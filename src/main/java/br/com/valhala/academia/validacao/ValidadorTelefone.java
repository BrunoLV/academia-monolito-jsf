package br.com.valhala.academia.validacao;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import br.com.valhala.academia.validacao.marcadores.ValidaTelefone;

@Named
@ValidaTelefone
public class ValidadorTelefone implements Validador, Serializable {

	private static final long serialVersionUID = 1L;

	private Validator validator;

	@PostConstruct
	public void setup() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Override
	public <Telefone> Set<String> validar(Telefone telefone) {
		Set<String> validacoes = new HashSet<>();
		Set<ConstraintViolation<Telefone>> constraints = validator.validate(telefone);
		if (constraints != null) {
			validacoes = constraints.stream().map(c -> c.getMessage()).collect(Collectors.toSet());
		}
		return validacoes;
	}

}
