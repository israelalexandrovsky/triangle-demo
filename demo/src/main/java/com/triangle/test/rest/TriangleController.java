package com.triangle.test.rest;

import com.triangle.test.model.Triangle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.jms.core.JmsTemplate;
import javax.jms.Queue;

import javax.validation.Valid;
import javax.xml.ws.RequestWrapper;
import java.util.Date;

@Controller("triangleController")
@RequestMapping("/demo")
public class TriangleController {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Queue queue;

    @PostMapping("/triangle")
    @ResponseStatus
    public String getTriangle(@RequestBody Triangle triangle){
      System.out.println("\nSTART PRODUCING NEW TRIANGLE AT :"  + (new Date()).toString());
      jmsTemplate.convertAndSend(queue,triangle);
      System.out.println("END PRODUCING NEW TRIANGLE AT :"  + (new Date()).toString());
      return "OK";
    }

}
