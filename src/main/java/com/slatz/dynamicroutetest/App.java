/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slatz.dynamicroutetest;

import com.slatz.dynamicroutetest.services.DynamicRouterService;
import org.apache.camel.CamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author slattumwr
 */
@SpringBootApplication
public class App {
    public static void main(String args[]){
        
       SpringApplication.run(App.class, args);
       
    }

    //Automatically start dynamic router service on app start
    @Autowired
    DynamicRouterService dynamicRouterService;
}
