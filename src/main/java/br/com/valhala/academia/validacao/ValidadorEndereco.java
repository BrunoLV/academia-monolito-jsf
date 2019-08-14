package br.com.valhala.academia.validacao;

import java.io.Serializable;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@Named
@ValidaEndereco
public class ValidadorEndereco implements Validador, Serializable {

	private static final long serialVersionUID = 1L;

	private Validator validator;

	@PostConstruct
	public void setup() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Override
	public <Endereco> Set<ConstraintViolation<Endereco>> validar(Endereco obj) {
		Set<ConstraintViolation<Endereco>> constraints = validator.validate(obj);
		return constraints;
	}

}
