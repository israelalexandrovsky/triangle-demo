package com.triangle.test.listener;


import com.triangle.test.domain.TriangleEntity;
import com.triangle.test.domain.enums.TriangleState;
import com.triangle.test.model.Triangle;
import com.triangle.test.service.TriangleStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Date;

@Component
public class Consumer {

    @Autowired
    TriangleStorageService triangleStorageService;

    @Transactional
    @JmsListener(destination = "TRIANGLE_QUEUE")
    public void listener(Triangle triangle){
        System.out.println("\nSTART CONSUMING NEW TRIANGLE AT :"  + (new Date()).toString());
        triangle = triangleStorageService.addTriangle(triangle);
        System.out.println("ADDING NEW TRIANGLE TO DB :"  + triangle.toString());
        System.out.println("END CONSUMING NEW TRIANGLE AT :"  + (new Date()).toString());
    }
}
