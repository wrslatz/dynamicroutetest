/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slatz.dynamicroutetest.processclasses;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author slattumwr
 */
@XmlRootElement(name="PROCESS_LIST")
public class MyProcList {
    private String dateTime;
    private ArrayList<MyProc> procList;
    
    
    public MyProcList(){
        Date now = new Date();
        SimpleDateFormat myFormat = new SimpleDateFormat("yyy.MM.dd 'at' hh:mm:ss a zzz");
        this.dateTime = myFormat.format(now);
        this.procList = new ArrayList<MyProc>();
    }
    
    @XmlElement(name="DATE_TIME")
    public void setDateTime(String blank){
        Date now = new Date();
        SimpleDateFormat myFormat = new SimpleDateFormat("yyy.MM.dd 'at' hh:mm:ss a zzz");
        this.dateTime = myFormat.format(now);
    }
    public String getDateTime(){
        return this.dateTime;
    }
    @XmlElement(name="PROCESS")
    public void setProcList(ArrayList<MyProc> list){
        this.procList = list;
    }
    public ArrayList<MyProc> getProcList(){
        return this.procList;
    }
    
    public void addProc(MyProc proc){
        this.procList.add(proc);
    }
    public void getProc(String id){
        
    }
}
