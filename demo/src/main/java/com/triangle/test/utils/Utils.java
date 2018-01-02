package com.triangle.test.utils;

import com.triangle.test.domain.TriangleEntity;
import com.triangle.test.model.Triangle;

import java.util.ArrayList;
import java.util.List;


public class Utils{

    public static List<Triangle> fromDomainList(List<TriangleEntity> triangleEntityList){
        List<Triangle> triangleList = null;
        if(triangleEntityList !=null){
            triangleList = new ArrayList<Triangle>();
            for (TriangleEntity triangleEntity:triangleEntityList) {
               triangleList.add(fromDomain(triangleEntity));
            }
        }
        return triangleList;
    }

    public static Triangle fromDomain(TriangleEntity triangleEntity){
        Triangle triangle = null;
        if(triangleEntity !=null){
            triangle = new Triangle(triangleEntity.getEdgeOne(),triangleEntity.getEdgeTwo(),triangleEntity.getHypotenuse(),triangleEntity.getState());
        }
        return triangle;
    }


    public static List<TriangleEntity> toDomainList(List<Triangle> triangleList){
        List<TriangleEntity> triangleEntityList = null;
        if(triangleList !=null){
            triangleEntityList = new ArrayList<TriangleEntity>();
            for (Triangle triangle:triangleList) {
                triangleEntityList.add(toDomain(triangle));
            }
        }
        return triangleEntityList;
    }

    public static TriangleEntity toDomain(Triangle triangle){
        TriangleEntity triangleEntity = null;
        if(triangle !=null){
            triangleEntity = new TriangleEntity(triangle.getEdgeOne(),triangle.getEdgeTwo(),triangle.getHypotenuse(),triangle.getState());
        }
        return triangleEntity;
    }

    public static double roundAvoid(double value, int places) {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }

}
