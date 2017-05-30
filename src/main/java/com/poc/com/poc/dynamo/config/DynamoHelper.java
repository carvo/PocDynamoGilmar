package com.poc.com.poc.dynamo.config;

import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.TableCollection;
import com.amazonaws.services.dynamodbv2.model.DescribeTableResult;
import com.amazonaws.services.dynamodbv2.model.ListTablesResult;

@Component
public class DynamoHelper {

    private static final Logger log = LoggerFactory.getLogger(DynamoHelper.class);
    
    public boolean existeTabela(final AmazonDynamoDBClient dbClient, String tableName) {
        try {
            DynamoDB dynamoDB = new DynamoDB(dbClient);

            TableCollection<ListTablesResult> tables = dynamoDB.listTables();
            Iterator<Table> iterator = tables.iterator();

            while (iterator.hasNext()) {
                Table table = iterator.next();
                
                if (tableName.equalsIgnoreCase(table.getTableName())) {
                    DescribeTableResult describeTable = dbClient.describeTable(tableName);
                    String tableStatus = describeTable.getTable().getTableStatus();
                    return "ACTIVE".equals(tableStatus);
                }
            }
        } catch (Exception e) {
            log.error("Erro: ", e);
            return false;
        }

        log.info("=========== Tabela {} não encontrada =========", tableName);
        return false;
    }
}
