package com.triangle.test.utils;

import com.triangle.test.domain.enums.TriangleState;
import com.triangle.test.model.Triangle;
import com.triangle.test.utils.Utils;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.Random;

@Component
@EnableScheduling
public class Scheduler {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    @Autowired
    private RestTemplate restTemplate;

    @Scheduled(cron="*/25 * * * * *")
    public void myScheduler(){
        JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters();
        try {
            JobExecution jobExecution = jobLauncher.run(job, jobParameters);
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
                | JobParametersInvalidException e) {
            e.printStackTrace();
        }
    }


    @Scheduled(cron="*/5 * * * * *")
    public void postNewTriangle(){
        double rangeMin = 1;
        double rangeMax = 500;
        Random r = new Random();
        double edgeOne = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
        double edgeTwo = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
        Triangle triangle = new Triangle(Utils.roundAvoid(edgeOne,2),Utils.roundAvoid(edgeTwo,2),0, TriangleState.PRE_CALC);
        System.out.println("\nPOST STARTED AT :" + (new Date()).toString());
        System.out.println("POSTING NEW TRIANGLE : " + triangle.toString());
        HttpEntity<Triangle> request = new HttpEntity<>(triangle);
        try {
            Triangle foo = restTemplate.postForObject("http://localhost:8082/demo/triangle", request, Triangle.class);
        }catch (Exception ex){

        }
        System.out.println("POST COMPLETED AT :"  + (new Date()).toString());
    }
}