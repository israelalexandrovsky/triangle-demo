package com.triangle.test.service;

import com.triangle.test.dao.TriangleDao;
import com.triangle.test.domain.TriangleEntity;
import com.triangle.test.domain.enums.TriangleState;
import com.triangle.test.model.Triangle;
import com.triangle.test.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class TriangleStorageService implements ITriangleStorageService{

    @Autowired
    private TriangleDao triangleDao;


    @Override
    @Transactional
    public List<Triangle> getAllTriangles() {
        return Utils.fromDomainList(triangleDao.getAllTriangles());
    }

    @Override
    @Transactional
    public int getAllTrianglesCount() {
        return triangleDao.getAllTrianglesCount();
    }

    @Override
    @Transactional
    public List<Triangle> getTrianglesByState(TriangleState state) {
        return Utils.fromDomainList(triangleDao.getTrianglesByState(state));
    }

    @Override
    @Transactional
    public int getTrianglesCountByState(TriangleState state) {
        return triangleDao.getTrianglesCountByState(state);
    }

    @Override
    @Transactional
    public Triangle getTriangleById(long triangleId) {
        return Utils.fromDomain(triangleDao.getTriangleById(triangleId));
    }

    @Override
    @Transactional
    public Triangle addTriangle(Triangle triangle) {
        return Utils.fromDomain(triangleDao.addTriangle(Utils.toDomain(triangle)));
    }

    @Override
    @Transactional
    public Triangle updateTriangle(Triangle triangle) {
        return Utils.fromDomain(triangleDao.updateTriangle(Utils.toDomain(triangle)));
    }

    @Override
    @Transactional
    public List<Triangle> updateTriangleList(List<Triangle> triangleList) {
        List<Triangle> updatedList = null;
        if(triangleList!=null){
            updatedList = new ArrayList<Triangle>();

            for (Triangle triangle:triangleList) {
                Triangle updatedTriangle = updateTriangle(triangle);
                updatedList.add(updatedTriangle);
            }
        }
        return  updatedList;
    }



}
