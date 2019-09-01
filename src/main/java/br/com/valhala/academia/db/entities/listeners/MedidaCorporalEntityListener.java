package br.com.valhala.academia.db.entities.listeners;

import br.com.valhala.academia.modelo.MedidaCorporal;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Calendar;
import java.util.UUID;

public class MedidaCorporalEntityListener {

    @PrePersist
    @PreUpdate
    public void completaDadosAutomaticos(MedidaCorporal medidaCorporal) {

        if (StringUtils.isBlank(medidaCorporal.getUuid())) {
            UUID uuid = UUID.randomUUID();
            medidaCorporal.setUuid(uuid.toString());
        }

        if (medidaCorporal.getDataMedicao() == null) {
            medidaCorporal.setDataMedicao(Calendar.getInstance().getTime());
        }

    }

}
