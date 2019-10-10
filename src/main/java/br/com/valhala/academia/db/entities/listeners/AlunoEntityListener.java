package br.com.valhala.academia.db.entities.listeners;

import java.util.UUID;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import br.com.valhala.academia.modelo.Aluno;

public class AlunoEntityListener {

    @PrePersist
    @PreUpdate
    public void completaDadosAutomaticos(Aluno aluno) {
        if (StringUtils.isBlank(aluno.getUuid())) {
            UUID uuid = UUID.randomUUID();
            aluno.setUuid(uuid.toString());
        }
        if (CollectionUtils.isNotEmpty(aluno.getTelefones())) {
            aluno.getTelefones().forEach(t -> t.setAluno(aluno));
        }
    }

}
