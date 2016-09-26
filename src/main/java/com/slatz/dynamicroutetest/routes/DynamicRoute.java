/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slatz.dynamicroutetest.routes;

import com.slatz.dynamicroutetest.processclasses.MyProc;
import com.slatz.dynamicroutetest.processclasses.MyProcList;
import java.util.ArrayList;
import java.util.UUID;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;

/**
 *
 * @author slattumwr
 */
public class DynamicRoute extends RouteBuilder {
    
    private String id;
    private String dest;
    private String query;
    
    private ProducerTemplate producerTemplate;
    private ConsumerTemplate consumerTemplate;
    private JAXBContext jaxbContext;
    

    public DynamicRoute() {
    }

    public DynamicRoute(String dest, String query, ProducerTemplate producerTemplate, ConsumerTemplate consumerTemplate) {
        UUID uuid = UUID.randomUUID();
        this.id = uuid.toString();
        this.dest = dest;
        this.query = query;
        this.producerTemplate = producerTemplate;
        this.consumerTemplate = consumerTemplate;
    }
    
    
    
    @Override
    public void configure() throws Exception{
        jaxbContext = JAXBContext.newInstance(MyProcList.class);
        JaxbDataFormat jaxbDataFormat = new JaxbDataFormat(jaxbContext);
        
        from("activemq:queue:ProcessList")
        .routeId(this.id)
        .wireTap("seda:ProcessList")
        .unmarshal(jaxbDataFormat)
        .bean(this, "onReceive")
        .marshal(jaxbDataFormat)
        .to("activemq:topic:" + dest);
    }
    
    public MyProcList onReceive(MyProcList procList){
        MyProcList output = new MyProcList();
        
        //For each process on the list, grab it and put into a new processlist
        for(MyProc proc : procList.getProcList()){
            if(proc.getProcInfo().contains(query)){
                output.addProc(proc);
            }
        }
        //Return new processList
        return output;
 
    }
    
    //Generated getters
    public String getId() {
        return id;
    }

    public String getDest() {
        return dest;
    }

    public String getQuery() {
        return query;
    }

    //Generated setters
    public void setId(String id) {
        this.id = id;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public void setQuery(String query) {
        this.query = query;
    }
    
    
}
