package br.com.valhala.academia.validacao;

import java.util.Set;

public interface Validador {

    <T> Set<String> validar(T obj);

}
