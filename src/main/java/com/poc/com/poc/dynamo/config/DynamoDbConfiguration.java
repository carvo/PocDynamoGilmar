package com.poc.com.poc.dynamo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

@Configuration
public class DynamoDbConfiguration {

    @Value("${aws.access.key}")
    private String user;

    @Value("${aws.secret.key}")
    private String password;

    @Value("${aws.region}")
    private String awsRegion;

    @Value("${aws.dynamodb.endpoint.use}")
    private boolean useEndpoint;

    @Value("${aws.dynamodb.endpoint.host}")
    private String dynamoEndpointHost;

    @Value("${aws.dynamodb.endpoint.port}")
    private Integer dynamoEndpointPort;

    @Bean
    public AmazonDynamoDBClient amazonDynamoDBClient() {
        final BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(user, password);
        final AmazonDynamoDBClient dbClient = new AmazonDynamoDBClient(basicAWSCredentials);

        if (useEndpoint) {
            final String dynamoEndpoint = "http://".concat(dynamoEndpointHost).concat(":")
                    .concat(dynamoEndpointPort.toString());
            dbClient.withEndpoint(dynamoEndpoint);
        } else {
            dbClient.setRegion(Region.getRegion(Regions.fromName(awsRegion)));
        }

        return dbClient;
    }

}
