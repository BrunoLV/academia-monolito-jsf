package br.com.valhala.academia.validacao.marcadores;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.valhala.academia.validacao.ValidadorMunicipioNaoPertencentEstado;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.TYPE })
@Constraint(validatedBy = ValidadorMunicipioNaoPertencentEstado.class)
public @interface ValidaMunicipioNaoPertencenteEstado {

	String message() default "{endereco.municipio.nao.pertence.estado}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
