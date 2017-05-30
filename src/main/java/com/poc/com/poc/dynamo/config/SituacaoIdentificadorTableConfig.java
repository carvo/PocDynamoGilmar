package com.poc.com.poc.dynamo.config;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.GlobalSecondaryIndex;
import com.amazonaws.services.dynamodbv2.model.Projection;
import com.amazonaws.services.dynamodbv2.model.ProjectionType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.poc.com.poc.dynamo.entity.SituacaoIdentificadorEntity;

@Component
public class SituacaoIdentificadorTableConfig {

    private static final Logger log = LoggerFactory.getLogger(SituacaoIdentificadorTableConfig.class);

    private static final Long READ_WRITE_CAPACITY_UNIT = 150L;

    private static final Class<SituacaoIdentificadorEntity> ENTITY_CLASS = SituacaoIdentificadorEntity.class;

    private static final String tableName = "SituacaoIdentificador";

    @Autowired
    private AmazonDynamoDBClient dbClient;

    @Autowired
    private DynamoHelper dynamoHelper;

    public void dropCreateTable() throws InterruptedException {
        if (dynamoHelper.existeTabela(dbClient, tableName)) {
            dropTable();
        }

        createTable();
    }

    private void dropTable() {
        dbClient.deleteTable(tableName);
        log.info("=========== Tabela removida: {} ===========", tableName);
    }

    private void createTable() throws InterruptedException {
        final DynamoDBMapper dbMapper = new DynamoDBMapper(dbClient);

        final CreateTableRequest createTableRequest = dbMapper.generateCreateTableRequest(ENTITY_CLASS);
        createTableRequest.setTableName(tableName);
        createTableRequest.setProvisionedThroughput(new ProvisionedThroughput(READ_WRITE_CAPACITY_UNIT, READ_WRITE_CAPACITY_UNIT));

        final List<GlobalSecondaryIndex> listaIndex = createTableRequest.getGlobalSecondaryIndexes();
        for (final GlobalSecondaryIndex index : listaIndex) {
            index.setProvisionedThroughput(
                    new ProvisionedThroughput(Long.valueOf(READ_WRITE_CAPACITY_UNIT), Long.valueOf(READ_WRITE_CAPACITY_UNIT)));
            index.setProjection(new Projection().withProjectionType(ProjectionType.ALL));
        }

        final DynamoDB dynamoDB = new DynamoDB(dbClient);
        dynamoDB.createTable(createTableRequest).waitForActive();

        log.info("=========== Tabela criada: {} ===========", tableName);
    }

}
