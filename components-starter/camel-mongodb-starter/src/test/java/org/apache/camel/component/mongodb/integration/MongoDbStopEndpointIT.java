/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.component.mongodb.integration;

import org.apache.camel.EndpointInject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mongodb.MongoDbEndpoint;
import org.apache.camel.spring.boot.CamelAutoConfiguration;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;

import org.bson.Document;
import org.junit.jupiter.api.condition.DisabledIfSystemProperty;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.DirtiesContext;

import org.junit.jupiter.api.Test;

import static org.apache.camel.component.mongodb.MongoDbConstants.MONGO_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@CamelSpringBootTest
@SpringBootTest(
        classes = {
                CamelAutoConfiguration.class,
                MongoDbStopEndpointIT.class,
                MongoDbStopEndpointIT.TestConfiguration.class,
                AbstractMongoDbITSupport.MongoConfiguration.class
        },
        properties = { "mongodb.testDb=test", "mongodb.testCollection=camelTest" }
)
@DisabledIfSystemProperty(named = "ci.env.name", matches = "github.com", disabledReason = "Disabled on GH Action due to Docker limit")
public class MongoDbStopEndpointIT extends AbstractMongoDbITSupport {

    private static final String MY_ID = "myId";

    String endpoint = "mongodb:myDb?database={{mongodb.testDb}}&collection={{mongodb.testCollection}}&operation=insert";

    @Configuration
    public class TestConfiguration {

        @Bean
        public RouteBuilder routeBuilder() {
            return new RouteBuilder() {
                @Override
                public void configure() {
                    from("direct:insertJsonString").routeId("insert").to(endpoint);
                    from("direct:findById").routeId("find").to(
                            "mongodb:myDb?database={{mongodb.testDb}}&collection={{mongodb.testCollection}}&operation=findById&dynamicity=true");
                }
            };
        }
    }

    @Test
    public void testStopEndpoint() {
        assertEquals(0, testCollection.countDocuments());

        template.requestBody("direct:insertJsonString", "{\"scientist\": \"Newton\", \"_id\": \"" + MY_ID + "\"}");

        context.getEndpoint(endpoint).stop();

        Document result = template.requestBody("direct:findById", MY_ID, Document.class);

        assertEquals(MY_ID, result.get(MONGO_ID));
        assertEquals("Newton", result.get("scientist"));
    }
}
