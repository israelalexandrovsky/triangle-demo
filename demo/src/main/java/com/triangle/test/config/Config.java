package com.triangle.test.config;

import com.triangle.test.algobatch.Processor;
import com.triangle.test.algobatch.Reader;
import com.triangle.test.algobatch.Writer;
import com.triangle.test.dao.TriangleDao;
import com.triangle.test.model.Triangle;
import com.triangle.test.service.TriangleStorageService;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.web.client.RestTemplate;

import javax.jms.Queue;
import java.util.List;

@EnableJms
@Configuration
@EnableBatchProcessing
public class Config{

    @Bean
    public Queue queue() {
        return new ActiveMQQueue("TRIANGLE_QUEUE");
    }

    @Bean
    public TriangleDao triangleDao() {
        return new TriangleDao();
    }

    @Bean
    public TriangleStorageService triangleStorageService(){
        return new TriangleStorageService();
    }

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job(){
        return jobBuilderFactory.get("job").incrementer(new RunIdIncrementer()).flow(algoFlow()).end().build();
    }

    @Bean
    public Reader reader(){
        return new Reader();
    }

    @Bean
    public Processor processor(){
        return new Processor();
    }

    @Bean
    public Writer writer(){
        return new Writer();
    }


    @Bean
    public Step algoFlow(){
        return stepBuilderFactory.get("algoFlow").<List<Triangle>,List<Triangle>>chunk(1).reader(reader()).processor(processor()).writer(writer()).build();
    }

    @Bean
    RestTemplate restTemplate(){
       return new RestTemplate();
    }

}

