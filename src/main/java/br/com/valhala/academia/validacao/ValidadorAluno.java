package br.com.valhala.academia.validacao;

import br.com.valhala.academia.validacao.marcadores.ValidaAluno;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Named
@ValidaAluno
public class ValidadorAluno implements Validador, Serializable {

    private static final long serialVersionUID = 1L;

    private Validator validator;

    @PostConstruct
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Override
    public <Aluno> Set<String> validar(Aluno aluno) {
        Set<String> validacoes = new HashSet<>();
        Set<ConstraintViolation<Aluno>> constraints = validator.validate(aluno);
        if (constraints != null) {
            validacoes = constraints.stream().map(c -> c.getMessage()).collect(Collectors.toSet());
        }
        return validacoes;
    }
}
