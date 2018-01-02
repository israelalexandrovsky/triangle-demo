package com.triangle.test.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class AbstractDao {

        @PersistenceContext
        protected EntityManager em;
        private static final Logger logger = LoggerFactory.getLogger(AbstractDao.class);
        @Transactional(propagation= Propagation.MANDATORY)
        public void setFlushMode(FlushModeType flushMode) {
            em.setFlushMode(flushMode);
        }

        @Transactional(propagation=Propagation.MANDATORY)
        public <T> T insertObject(T object) {
            em.persist(object);
            return object;
        }

        @Transactional(propagation=Propagation.MANDATORY)
        public <T> T insertObject(T object, boolean flush) {
            em.persist(object);
            if (flush) {
                em.flush();
            }
            return object;
        }

        @Transactional(propagation=Propagation.MANDATORY)
        public void flush() {
            em.flush();
        }

        @Transactional(propagation=Propagation.MANDATORY)
        public <T> void refresh(T object) {
            em.refresh(object);
        }

        @Transactional(propagation=Propagation.MANDATORY)
        public <T> T mergeObject(T entity) {
            return em.merge(entity);
        }


        @Transactional(propagation=Propagation.MANDATORY)
        public void deleteObject(Object entity) {
            em.remove(entity);
        }

        @Transactional(propagation=Propagation.MANDATORY)
        public boolean isInContext(Object entity) {
            return em.contains(entity);
        }

        /**
         * Get first result from query results
         * @param <T>
         * @param query
         * @return null if no results
         */
        public <T> T getFirst(TypedQuery<T> query){
            List<T> results = query.getResultList();
            if(results.size() > 0){
                return results.get(0);
            }
            return null;
        }


        protected String getId(String key, String keyPrefix) {
            if(key.contains(keyPrefix)) {
                String id = key.substring(keyPrefix.length());
                return id;
            }
            return null;
        }
    }

