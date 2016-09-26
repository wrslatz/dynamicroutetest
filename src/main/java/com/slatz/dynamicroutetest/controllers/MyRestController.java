/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slatz.dynamicroutetest.controllers;

import com.slatz.dynamicroutetest.processclasses.MyProcList;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author slattumwr
 */
@RestController
public class MyRestController {
    @CrossOrigin
    @ApiOperation(httpMethod = "POST", value = "")
    @RequestMapping(value = "/api/slattum/test", produces = MediaType.APPLICATION_XML_VALUE, method = RequestMethod.GET)
    public MyProcList getProcessList(@RequestParam(value = "test") String test) throws Exception{
        
        MyProcList ProcList = ProcListGenerator.getInstance().getProcessList();
        
        return ProcList;
    }
    
    
}
