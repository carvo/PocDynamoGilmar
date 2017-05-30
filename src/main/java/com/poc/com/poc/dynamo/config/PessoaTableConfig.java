package com.poc.com.poc.dynamo.config;

import java.util.ArrayList;
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
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.Projection;
import com.amazonaws.services.dynamodbv2.model.ProjectionType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.poc.com.poc.dynamo.entity.PessoaEntity;

@Component
public class PessoaTableConfig {

    private static final Logger log = LoggerFactory.getLogger(PessoaTableConfig.class);

    private static final Long READ_WRITE_CAPACITY_UNIT = 5L;

    private static final String tableName = "Pessoa";

    @Autowired
    private AmazonDynamoDBClient dbClient;

    @Autowired
    private DynamoHelper dynamoHelper;

    public void configureTables() {
        try {
            if (dynamoHelper.existeTabela(dbClient, tableName)) {
                log.info("=========== Excluindo Tabela {}", tableName);
                dbClient.deleteTable(tableName);
            }

            log.info("=========== Criando Tabela {}{} e {}", tableName);
            createTable(dbClient, tableName);

        } catch (final InterruptedException e) {
            log.error("Erro: ", e);
        }
    }

    private void createTable(final AmazonDynamoDBClient dbClient, final String tableName) throws InterruptedException {

        final DynamoDBMapper dbMapper = new DynamoDBMapper(dbClient);

        final CreateTableRequest createTableRequest = dbMapper.generateCreateTableRequest(PessoaEntity.class);
        createTableRequest.setTableName(tableName);
        createTableRequest.setProvisionedThroughput(new ProvisionedThroughput(READ_WRITE_CAPACITY_UNIT,
                READ_WRITE_CAPACITY_UNIT));

        final List<GlobalSecondaryIndex> globalSecondaryIndices = new ArrayList<>();

        final ArrayList<KeySchemaElement> indexKeySchema = new ArrayList<>();

        indexKeySchema.add(new KeySchemaElement()
                .withAttributeName("idade")
                .withKeyType(KeyType.HASH)); // Partition key
        indexKeySchema.add(new KeySchemaElement()
                .withAttributeName("dataDeInclusao")
                .withKeyType(KeyType.RANGE)); // Sort key

        final GlobalSecondaryIndex idadeIndex = new GlobalSecondaryIndex()
                .withIndexName("idadeIndex")
                .withProvisionedThroughput(new ProvisionedThroughput()
                        .withReadCapacityUnits((long) 10)
                        .withWriteCapacityUnits((long) 1))
                .withKeySchema(indexKeySchema)
                .withProjection(new Projection().withProjectionType(ProjectionType.ALL));
        globalSecondaryIndices.add(idadeIndex);

        createTableRequest.setGlobalSecondaryIndexes(globalSecondaryIndices);

        final DynamoDB dynamoDB = new DynamoDB(dbClient);
        dynamoDB.createTable(createTableRequest).waitForActive();

        log.info("=========== Tabelas criadas com sucesso ===========");
    }

    public String getTableName() {
        return tableName;
    }
}
