package com.triangle.test.service;

import com.triangle.test.domain.TriangleEntity;
import com.triangle.test.domain.enums.TriangleState;
import com.triangle.test.dao.TriangleDao;
import com.triangle.test.model.Triangle;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

public interface ITriangleStorageService {

    List<Triangle> getAllTriangles();

    int getAllTrianglesCount();

    List<Triangle> getTrianglesByState(TriangleState state);

    int getTrianglesCountByState(TriangleState state);

    Triangle getTriangleById(long triangleId);

    Triangle addTriangle(Triangle triangle);

    Triangle updateTriangle(Triangle triangle);

    List<Triangle> updateTriangleList(List<Triangle> triangle);


}
