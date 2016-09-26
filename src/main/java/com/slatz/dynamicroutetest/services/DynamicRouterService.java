/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slatz.dynamicroutetest.services;

import com.slatz.dynamicroutetest.routes.DynamicRoute;
import java.util.ArrayList;
import java.util.List;
import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author slattumwr
 */
@Service
public class DynamicRouterService {
    
    @Autowired
    CamelContext camelContext;
    
    @Autowired
    ProducerTemplate producerTemplate;
    @Autowired
    ConsumerTemplate consumerTemplate;
    
    private List<DynamicRoute> dynamicRoutes;

    public DynamicRouterService() {
        this.dynamicRoutes = new ArrayList<>();
    }
    
    public DynamicRoute create(String dest, String query){
        DynamicRoute route = new DynamicRoute(dest, query, this.producerTemplate, this.consumerTemplate);
        add(route);
        start(route);
        return route;
    }
            
    public boolean add(DynamicRoute route){
        dynamicRoutes.add(route);
        return true;
    }
    
    public boolean remove(DynamicRoute route){
        for(DynamicRoute checkRoute: this.dynamicRoutes){
            if(checkRoute.getId().equals(route.getId())){
                stop(route);
                return dynamicRoutes.remove(route);
            }
        }
        return false;
    }
            
    public boolean start(DynamicRoute route){
        try{
            camelContext.startRoute(route.getId());
            return true;
        }
        catch(Exception e){    
            return false;
        }
    }        
    public boolean stop(DynamicRoute route){
        try{
            camelContext.stopRoute(route.getId());
            return true;
        }
        catch(Exception e){    
            return false;
        }
    }
            
    public DynamicRoute getDynamicRoute(String id){
        for(DynamicRoute route : this.dynamicRoutes){
            if(route.getId().equals(id)){
                return route;
            }
        }
        return null;
    }
    
    public List<DynamicRoute> getAllDynamicRoutes(){
        return this.dynamicRoutes;
    }
            
    public boolean removeAllDynamicRoutes(){
        //Iterate through all routes and remove
        for(DynamicRoute route : this.dynamicRoutes){
                remove(route);
        }
        //Reset to empty ArrayList
        this.dynamicRoutes = new ArrayList<>();
        return true;
    }
    
}
