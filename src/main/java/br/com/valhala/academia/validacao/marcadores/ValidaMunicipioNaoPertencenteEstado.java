package br.com.valhala.academia.validacao.marcadores;

import br.com.valhala.academia.validacao.ValidadorMunicipioNaoPertencentEstado;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Constraint(validatedBy = ValidadorMunicipioNaoPertencentEstado.class)
public @interface ValidaMunicipioNaoPertencenteEstado {

    String message() default "{endereco.municipio.nao.pertence.estado}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
