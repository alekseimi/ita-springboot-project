package ita.springboot.application.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.function.Consumer;

@Component
public class DataAccessHelper {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void doInTransaction(final Consumer<EntityManager> c) {
        c.accept(entityManager);
    }
}
