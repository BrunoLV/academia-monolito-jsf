package br.com.valhala.academia.validacao;

import java.util.Set;

import javax.validation.ConstraintViolation;

public interface Validador<T> {

	Set<ConstraintViolation<T>> validar(T obj);

}
