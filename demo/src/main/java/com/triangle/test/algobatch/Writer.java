package com.triangle.test.algobatch;

import com.triangle.test.model.Triangle;
import com.triangle.test.service.TriangleStorageService;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class Writer implements ItemWriter<List<Triangle>>{

    @Autowired
    TriangleStorageService triangleStorageService;

    @Override
    public void write(List<? extends List<Triangle>> items) throws Exception {

        if(items!=null){
            for (List<Triangle> triangleList:items) {
                System.out.println("\nSTART UPDATE TRIANGLES:");
                List<Triangle> updatedList = triangleStorageService.updateTriangleList(triangleList);
                for (Triangle triangle: updatedList){
                  System.out.println(triangle.toString());
                }
            }
        }
        System.out.println("END ALGO SERVICE BATCH AT :"  + (new Date()).toString());
    }
}
