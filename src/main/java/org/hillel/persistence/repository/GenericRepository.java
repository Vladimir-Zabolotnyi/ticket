package org.hillel.persistence.repository;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Optional;

public interface GenericRepository<E, ID> {

    E createOrUpdate(E entity);

    Optional<E> findById(ID id);

    void removeById(ID id) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException;

    void remove(E entity);

    Collection<E> findByIds(ID... ids);

    Collection<E> findAll();
}
