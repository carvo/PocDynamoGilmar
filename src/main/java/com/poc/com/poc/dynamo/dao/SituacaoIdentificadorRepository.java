package com.poc.com.poc.dynamo.dao;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
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

}
