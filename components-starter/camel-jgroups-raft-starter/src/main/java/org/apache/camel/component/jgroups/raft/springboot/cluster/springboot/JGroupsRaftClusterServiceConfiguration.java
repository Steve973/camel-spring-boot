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
package org.apache.camel.component.jgroups.raft.springboot.cluster.springboot;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "camel.cluster.jgroups-raft")
public class JGroupsRaftClusterServiceConfiguration {

    /**
     * Sets if the jgroups raft cluster service should be enabled or not, default is false.
     */
    private boolean enabled;

    /**
     * Cluster Service ID
     */
    private String id;

    /**
     * JGroups-raft ID
     */
    private String raftId;

    /**
     * JGrups-raft configuration File name
     */
    private String jgroupsRaftConfig;

    /**
     * JGroups Cluster name
     */
    private String jgroupsRaftClusterName;

    public String getRaftId() {
        return raftId;
    }

    public void setRaftId(String raftId) {
        this.raftId = raftId;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJgroupsRaftConfig() {
        return jgroupsRaftConfig;
    }

    public void setJgroupsRaftConfig(String jgroupsRaftConfig) {
        this.jgroupsRaftConfig = jgroupsRaftConfig;
    }

    public String getJgroupsRaftClusterName() {
        return jgroupsRaftClusterName;
    }

    public void setJgroupsRaftClusterName(String jgroupsRaftClusterName) {
        this.jgroupsRaftClusterName = jgroupsRaftClusterName;
    }
}
