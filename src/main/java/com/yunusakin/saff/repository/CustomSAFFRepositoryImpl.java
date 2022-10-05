package com.yunusakin.saff.repository;

import com.yunusakin.saff.model.SaffFile;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
public class CustomSAFFRepositoryImpl implements CustomSAFFRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public SaffFile getByFileCode(String fileCode) {
        try {
            return entityManager.createNamedQuery("SaffFile.findByFileCode", SaffFile.class).
                    setParameter("fileCode", fileCode).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
