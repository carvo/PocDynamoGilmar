package com.poc.com.poc.dynamo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.poc.com.poc.dynamo.entity.SituacaoIdentificadorEntity;

@Service
public class SituacaoIdentificadorRepository {

    @Autowired
    private AmazonDynamoDBClient dbClient;

    private DynamoDBMapper mapper;

    @PostConstruct
    public void init() {
        final DynamoDBMapperConfig.Builder builder = new DynamoDBMapperConfig.Builder();
        mapper = new DynamoDBMapper(dbClient, builder.build());
    }

    public SituacaoIdentificadorEntity save(final SituacaoIdentificadorEntity entity) {
        mapper.save(entity);
        return entity;
    }

    public List<SituacaoIdentificadorEntity> selectAllWithScan() {
        final DynamoDBScanExpression e = new DynamoDBScanExpression();
        return mapper.scan(SituacaoIdentificadorEntity.class, e);
    }

    public List<SituacaoIdentificadorEntity> queryByNuIdentificador(final Long nuIdentificador) {
        final Map<String, AttributeValue> expressionAttributeValues = new HashMap<>();
        expressionAttributeValues.put(":nuIdentificador", new AttributeValue().withN(nuIdentificador.toString()));

        final DynamoDBQueryExpression<SituacaoIdentificadorEntity> e = new DynamoDBQueryExpression<SituacaoIdentificadorEntity>()
                .withIndexName(SituacaoIdentificadorEntity.IDXNAME_NUIDENTIFICADOR)
                .withKeyConditionExpression("nuIdentificador = :nuIdentificador")
                .withExpressionAttributeValues(expressionAttributeValues)
                .withScanIndexForward(false)
                .withConsistentRead(false)
                .withLimit(1);

        return mapper.query(SituacaoIdentificadorEntity.class, e);
    }

}
