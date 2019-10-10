package br.com.valhala.academia.validacao;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.valhala.academia.modelo.Municipio;
import br.com.valhala.academia.validacao.marcadores.ValidaMunicipioNaoPertencenteEstado;

public class ValidadorMunicipioNaoPertencentEstado
        implements ConstraintValidator<ValidaMunicipioNaoPertencenteEstado, Municipio> {

    @Override
    public boolean isValid(Municipio municipio, ConstraintValidatorContext context) {
        if (municipio == null || municipio.getEstado() == null) {
            return true;
        }
        boolean valido = municipio.getUf() == municipio.getEstado().getUf();
        return valido;
    }

}
