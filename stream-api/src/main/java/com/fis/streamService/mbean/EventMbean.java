package com.fis.streamService.mbean;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

@ManagedResource(
        objectName="PD:category=MBeans,name=testBean",
        description="Managed Bean")
@Component("testMbean")
public class EventMbean {

    private String message = "Simple Message";

    public EventMbean(){
        System.out.println("......TestMbean........");
    }

    @ManagedAttribute
    public void resetMessageViaMBean(){
        this.message = "Message RESET";
    }

    public void show(){
        System.out.println(message);
    }

}