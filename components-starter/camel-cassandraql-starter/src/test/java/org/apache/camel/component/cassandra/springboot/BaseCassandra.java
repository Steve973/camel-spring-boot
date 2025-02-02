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
package org.apache.camel.component.cassandra.springboot;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.time.Duration;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.config.DefaultDriverOption;
import com.datastax.oss.driver.api.core.config.DriverConfigLoader;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.test.infra.cassandra.services.CassandraLocalContainerService;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.RegisterExtension;

public class BaseCassandra {
    
    @Autowired
    protected CamelContext context;
    
    @Autowired
    protected ProducerTemplate template;

    @RegisterExtension
    public static CassandraLocalContainerService service;

    public static final String KEYSPACE_NAME = "camel_ks";
    public static final String DATACENTER_NAME = "datacenter1";

    private CqlSession session;

    static {
        service = new CassandraLocalContainerService();

        service.getContainer()
                .withInitScript("initScript.cql")
                .withNetworkAliases("cassandra");
        
    }

    @BeforeEach
    public void beforeEach() throws Exception {
        executeScript("BasicDataSet.cql");      
        
    }

    public void executeScript(String pathToScript) throws IOException {
        String s = new String(IOUtils.toByteArray(getClass().getResourceAsStream("/" + pathToScript)));
        String[] statements = s.split(";");
        for (int i = 0; i < statements.length; i++) {
            if (!statements[i].isEmpty()) {
                executeCql(statements[i]);
            }
        }
    }

    public void executeCql(String cql) {
        getSession().execute(cql);
    }

    @AfterEach
    protected void doPostTearDown() throws Exception {
        
        try {
            if (session != null) {
                session.close();
                session = null;
            }
        } catch (Exception e) {
            // ignored
        }
    }

    public CqlSession getSession() {
        if (session == null) {
            InetSocketAddress endpoint
                    = new InetSocketAddress(service.getCassandraHost(), service.getCQL3Port());
            //create a new session
            session = CqlSession.builder()
                    .withLocalDatacenter(DATACENTER_NAME)
                    .withKeyspace(KEYSPACE_NAME)
                    .withConfigLoader(DriverConfigLoader.programmaticBuilder()
                            .withDuration(DefaultDriverOption.REQUEST_TIMEOUT, Duration.ofSeconds(5)).build())
                    .addContactPoint(endpoint).build();
        }
        return session;
    }

    public String getUrl() {
        return service.getCQL3Endpoint();
    }
}
