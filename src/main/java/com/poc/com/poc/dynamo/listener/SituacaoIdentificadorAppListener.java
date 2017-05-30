package com.poc.com.poc.dynamo.listener;

import java.util.Date;
import java.util.Random;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.poc.com.poc.dynamo.config.SituacaoIdentificadorTableConfig;
import com.poc.com.poc.dynamo.dao.SituacaoIdentificadorRepository;
import com.poc.com.poc.dynamo.entity.SituacaoIdentificadorEntity;

@Component
public class SituacaoIdentificadorAppListener implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger log = LoggerFactory.getLogger(SituacaoIdentificadorAppListener.class);

    @Autowired
    private SituacaoIdentificadorTableConfig tableConfig;

    @Autowired
    private SituacaoIdentificadorRepository repo;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        try {

            tableConfig.dropCreateTable();

            IntStream.rangeClosed(1, 4)
                    .forEach(i -> repo.save(createSupervisionedEntity(i)));

            IntStream.rangeClosed(1, 3)
                    .forEach(i -> repo.save(createSupervisionedEntity(i, 2)));

            IntStream.rangeClosed(1, 4)
                    .forEach(i -> repo.save(createSupervisionedEntity(i, 3)));

            IntStream.rangeClosed(1, 10)
                    .forEach(i -> repo.save(createEntityWithRandomInfo()));

            // repo.selectAllWithScan().forEach(e -> log.info(e.toString()));
            repo.queryByNuIdentificador(1L).forEach(e -> log.info(e.toString()));

        } catch (final InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    private SituacaoIdentificadorEntity createSupervisionedEntity(final int concessionariaId) {
        return createSupervisionedEntity(concessionariaId, 1);
    }

    private SituacaoIdentificadorEntity createSupervisionedEntity(final int concessionariaId, final int timeMultiplier) {
        final SituacaoIdentificadorEntity e = new SituacaoIdentificadorEntity();
        e.setConcessionariaId(concessionariaId);
        e.setDataProcessada(new Date().getTime() * timeMultiplier);
        e.setGrupo(1);
        e.setIdCliente(1);
        e.setIdConta(1);
        e.setNuIdentificador(1L);
        e.setPlaca("Multiplicador " + timeMultiplier);
        e.setTemPassagem(Boolean.TRUE);
        e.setTipoProduto(1);

        return e;
    }

    private SituacaoIdentificadorEntity createEntityWithRandomInfo() {
        final SituacaoIdentificadorEntity e = new SituacaoIdentificadorEntity();
        e.setConcessionariaId(new Random().nextInt(10) + 100);
        e.setDataProcessada(new Date().getTime());
        e.setGrupo(1);
        e.setIdCliente(1);
        e.setIdConta(1);
        e.setNuIdentificador(new Random().nextLong() + 100);
        e.setPlaca("Gerado Random");
        e.setTemPassagem(Boolean.TRUE);
        e.setTipoProduto(1);

        return e;
    }

}
