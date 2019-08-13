package br.com.valhala.academia.validacao;

import java.util.Set;

import javax.inject.Named;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import br.com.valhala.academia.db.modelo.Endereco;

@Named
public class ValidadorEndereco implements Validador<Endereco> {

	@Override
	public Set<ConstraintViolation<Endereco>> validar(Endereco obj) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<Endereco>> constraints = validator.validate(obj);
		return constraints;
	}

}
