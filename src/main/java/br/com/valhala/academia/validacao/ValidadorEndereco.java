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

import br.com.valhala.academia.validacao.marcadores.ValidaEndereco;

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
    public <Endereco> Set<String> validar(Endereco obj) {
        Set<String> validacoes = new HashSet<>();
        Set<ConstraintViolation<Endereco>> constraints = validator.validate(obj);
        if (constraints != null) {
            validacoes = constraints.stream().map(c -> c.getMessage()).collect(Collectors.toSet());
        }
        return validacoes;
    }

}
