package org.hillel.persistence.repository;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Optional;

public interface GenericRepository<E, ID> {

    E createOrUpdate(E entity);

    Optional<E> findById(ID id);

    Collection<E> findAllByName(String name);

    void removeById(ID id) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException;

    void remove(E entity);

    Collection<E> findByIds(ID... ids);

    Collection<E> findAll();

    Collection<E> findAllAsNative();

    Collection<E> findAllAsCriteria();

    Collection<E> findAllAsNamed();

    Collection<E> findAllAsStoredProcedure();
}
