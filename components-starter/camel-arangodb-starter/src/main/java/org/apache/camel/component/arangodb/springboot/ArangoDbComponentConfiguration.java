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
package org.apache.camel.component.arangodb.springboot;

import com.arangodb.ArangoDB;
import org.apache.camel.component.arangodb.ArangoDbConfiguration;
import org.apache.camel.component.arangodb.ArangoDbOperation;
import org.apache.camel.spring.boot.ComponentConfigurationPropertiesCommon;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Perform operations on ArangoDb when used as a Document Database, or as a
 * Graph Database
 * 
 * Generated by camel-package-maven-plugin - do not edit this file!
 */
@ConfigurationProperties(prefix = "camel.component.arangodb")
public class ArangoDbComponentConfiguration
        extends
            ComponentConfigurationPropertiesCommon {

    /**
     * Whether to enable auto configuration of the arangodb component. This is
     * enabled by default.
     */
    private Boolean enabled;
    /**
     * Component configuration. The option is a
     * org.apache.camel.component.arangodb.ArangoDbConfiguration type.
     */
    private ArangoDbConfiguration configuration;
    /**
     * Collection name, when using ArangoDb as a Document Database. Set the
     * documentCollection name when using the CRUD operation on the document
     * database collections (SAVE_DOCUMENT , FIND_DOCUMENT_BY_KEY,
     * UPDATE_DOCUMENT, DELETE_DOCUMENT).
     */
    private String documentCollection;
    /**
     * Collection name of vertices, when using ArangoDb as a Graph Database. Set
     * the edgeCollection name to perform CRUD operation on edges using these
     * operations : SAVE_VERTEX, FIND_VERTEX_BY_KEY, UPDATE_VERTEX,
     * DELETE_VERTEX. The graph attribute is mandatory.
     */
    private String edgeCollection;
    /**
     * Graph name, when using ArangoDb as a Graph Database. Combine this
     * attribute with one of the two attributes vertexCollection and
     * edgeCollection.
     */
    private String graph;
    /**
     * ArangoDB host. If host and port are default, this field is Optional.
     */
    private String host;
    /**
     * Whether the producer should be started lazy (on the first message). By
     * starting lazy you can use this to allow CamelContext and routes to
     * startup in situations where a producer may otherwise fail during starting
     * and cause the route to fail being started. By deferring this startup to
     * be lazy then the startup failure can be handled during routing messages
     * via Camel's routing error handlers. Beware that when the first message is
     * processed then creating and starting the producer may take a little time
     * and prolong the total processing time of the processing.
     */
    private Boolean lazyStartProducer = false;
    /**
     * Operations to perform on ArangoDb. For the operation AQL_QUERY, no need
     * to specify a collection or graph.
     */
    private ArangoDbOperation operation;
    /**
     * ArangoDB exposed port. If host and port are default, this field is
     * Optional.
     */
    private Integer port;
    /**
     * Collection name of vertices, when using ArangoDb as a Graph Database. Set
     * the vertexCollection name to perform CRUD operation on vertices using
     * these operations : SAVE_EDGE, FIND_EDGE_BY_KEY, UPDATE_EDGE, DELETE_EDGE.
     * The graph attribute is mandatory.
     */
    private String vertexCollection;
    /**
     * To use an existing ArangDB client. The option is a com.arangodb.ArangoDB
     * type.
     */
    private ArangoDB arangoDB;
    /**
     * Whether autowiring is enabled. This is used for automatic autowiring
     * options (the option must be marked as autowired) by looking up in the
     * registry to find if there is a single instance of matching type, which
     * then gets configured on the component. This can be used for automatic
     * configuring JDBC data sources, JMS connection factories, AWS Clients,
     * etc.
     */
    private Boolean autowiredEnabled = true;
    /**
     * ArangoDB password. If user and password are default, this field is
     * Optional.
     */
    private String password;
    /**
     * ArangoDB user. If user and password are default, this field is Optional.
     */
    private String user;

    public ArangoDbConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(ArangoDbConfiguration configuration) {
        this.configuration = configuration;
    }

    public String getDocumentCollection() {
        return documentCollection;
    }

    public void setDocumentCollection(String documentCollection) {
        this.documentCollection = documentCollection;
    }

    public String getEdgeCollection() {
        return edgeCollection;
    }

    public void setEdgeCollection(String edgeCollection) {
        this.edgeCollection = edgeCollection;
    }

    public String getGraph() {
        return graph;
    }

    public void setGraph(String graph) {
        this.graph = graph;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Boolean getLazyStartProducer() {
        return lazyStartProducer;
    }

    public void setLazyStartProducer(Boolean lazyStartProducer) {
        this.lazyStartProducer = lazyStartProducer;
    }

    public ArangoDbOperation getOperation() {
        return operation;
    }

    public void setOperation(ArangoDbOperation operation) {
        this.operation = operation;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getVertexCollection() {
        return vertexCollection;
    }

    public void setVertexCollection(String vertexCollection) {
        this.vertexCollection = vertexCollection;
    }

    public ArangoDB getArangoDB() {
        return arangoDB;
    }

    public void setArangoDB(ArangoDB arangoDB) {
        this.arangoDB = arangoDB;
    }

    public Boolean getAutowiredEnabled() {
        return autowiredEnabled;
    }

    public void setAutowiredEnabled(Boolean autowiredEnabled) {
        this.autowiredEnabled = autowiredEnabled;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}