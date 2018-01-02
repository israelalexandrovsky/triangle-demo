package com.triangle.test.algobatch;

import com.triangle.test.domain.enums.TriangleState;
import com.triangle.test.model.Triangle;
import com.triangle.test.service.TriangleStorageService;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class Reader implements ItemReader<List<Triangle>>{

    @Autowired
    TriangleStorageService triangleStorageService;

    @Override
    public List<Triangle> read(){
        System.out.println("\nSTART ALGO SERVICE BATCH AT :"  + (new Date()).toString());
        List<Triangle> triangleList = triangleStorageService.getTrianglesByState(TriangleState.PRE_CALC);
        System.out.println("READING LIST FROM DB :"  + triangleList.toString());
        if(triangleList.size()==0){
            return null;
        }
         return triangleList;
    }

}
