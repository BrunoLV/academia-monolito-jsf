package br.com.valhala.academia.db.entities.listeners;

import br.com.valhala.academia.modelo.Aluno;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.UUID;

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
