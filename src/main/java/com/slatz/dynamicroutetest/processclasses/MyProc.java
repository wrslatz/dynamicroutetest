/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slatz.dynamicroutetest.processclasses;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author slattumwr
 */
@XmlRootElement( name = "PROCESS" )
@XmlType(propOrder = {"procID", "userID", "procInfo"})
public class MyProc {
    
    private String procID;
    private String userID;
    private String procInfo;
    
    public MyProc(){    
        procID = "";
        userID = "";
        procInfo = "";
    }
    public MyProc(String input) {
        String noSpace = input.trim();
        String extra[] = noSpace.split(" ", 2);
        userID = extra[0];
        
        String splitExtra[] = extra[1].trim().split(" ", 2);
        procID = splitExtra[0];
        procInfo = splitExtra[1];
    }
    
    public String getProcID(){
        return this.procID;
    }
    public String getUserID(){
        return this.userID;
    }
    public String getProcInfo(){
        return this.procInfo;
    }
    
    @XmlElement(name = "PROCESS_PID")
    public void setProcID(String id){
        this.procID = id;
    }
    @XmlElement(name = "PROCESS_UID")
    public void setUserID(String uid){
        this.userID = uid;
    }
    @XmlElement(name = "PROCESS_INFO")
   public void setProcInfo(String info){
        this.procInfo = info;
    }
}
