package com.triangle.test.dao;

import com.triangle.test.domain.TriangleEntity;
import com.triangle.test.domain.enums.TriangleState;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

public class TriangleDao extends AbstractDao{

    @Transactional(readOnly=true)
    public List<TriangleEntity> getAllTriangles() {
        TypedQuery<TriangleEntity> query = em.createQuery("select a from TriangleEntity a", TriangleEntity.class);
        return query.getResultList();
    }

    @Transactional(readOnly=true)
    public int getAllTrianglesCount() {
        TypedQuery<TriangleEntity> query = em.createQuery("select a from TriangleEntity a", TriangleEntity.class);
        return query.getResultList().size();
    }


    @Transactional(readOnly=true)
    public List<TriangleEntity> getTrianglesByState(TriangleState state) {
        TypedQuery<TriangleEntity> query = em.createQuery("select a from TriangleEntity a where a.state= :state", TriangleEntity.class);
        query.setParameter("state",state);
        return query.getResultList();
    }

    @Transactional(readOnly=true)
    public int getTrianglesCountByState(TriangleState state) {
        TypedQuery<TriangleEntity> query = em.createQuery("select a from TriangleEntity a where a.state= :state", TriangleEntity.class);
        query.setParameter("state",state);
        return query.getResultList().size();
    }

    @Transactional(readOnly=true)
    public TriangleEntity getTriangleById(long triangleId) {
        TriangleEntity triangle = em.find(TriangleEntity.class, triangleId);
        return triangle;
    }

    @Transactional(propagation= Propagation.MANDATORY)
    public TriangleEntity addTriangle(TriangleEntity triangle) {
        TypedQuery<TriangleEntity> query = em.createQuery("select a from TriangleEntity a where a.edgeOne= :edgeOne and a.edgeTwo= :edgeTwo", TriangleEntity.class);
        query.setParameter("edgeOne",triangle.getEdgeOne());
        query.setParameter("edgeTwo",triangle.getEdgeTwo());
        List<TriangleEntity> resultList = query.getResultList();
        TriangleEntity existEntity = null;
        if(resultList.size()==0) {
            return insertObject(triangle,true);
        }else {
            existEntity = resultList.get(0);
        }
        return existEntity;
    }

    @Transactional(propagation= Propagation.MANDATORY)
    public TriangleEntity updateTriangle(TriangleEntity triangle) {
        TypedQuery<TriangleEntity> query = em.createQuery("select a from TriangleEntity a where a.edgeOne= :edgeOne and a.edgeTwo= :edgeTwo", TriangleEntity.class);
        query.setParameter("edgeOne",triangle.getEdgeOne());
        query.setParameter("edgeTwo",triangle.getEdgeTwo());
        List<TriangleEntity> resultList = query.getResultList();
        TriangleEntity updatedEntity = null;
        if(resultList.size()>0){
            updatedEntity = resultList.get(0);
            updatedEntity.setHypotenuse(triangle.getHypotenuse());
            updatedEntity.setState(triangle.getState());
            updatedEntity = mergeObject(updatedEntity);
        }
        return updatedEntity;
    }


    public TriangleDao() {
    }
}
