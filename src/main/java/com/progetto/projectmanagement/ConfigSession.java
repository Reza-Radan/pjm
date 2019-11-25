package com.progetto.projectmanagement;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.QueryOptions;
import com.datastax.driver.core.Session;
import com.progetto.projectmanagement.domain.RequirementsProperties;
import org.springframework.beans.factory.annotation.Autowired;


public class ConfigSession {

    @Autowired
    RequirementsProperties requirementsProperties;

    Cluster cluster;
    Session session;

    public Session getSession(){

        cluster = Cluster.builder().addContactPoint(requirementsProperties.getContactPoint()).withPort(Integer.valueOf(requirementsProperties.getPort())).build();
        session = cluster.connect(requirementsProperties.getKeyspace());
        return session;
    }
    public Cluster getCluster(){
        cluster = Cluster.builder().addContactPoint(requirementsProperties.getContactPoint())
                .withPort(Integer.valueOf(requirementsProperties.getPort())).build();
        return cluster;
    }
}
