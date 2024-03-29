package org.hillel.persistence.repository;


import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hillel.persistence.entity.AbstractModifyEntity;
import org.springframework.util.Assert;

public abstract class CommonRepository<E extends AbstractModifyEntity<ID>, ID extends Serializable> implements GenericRepository<E, ID> {

    private final Class<E> entityClass;
    @PersistenceContext
    protected EntityManager entityManager;

    protected CommonRepository(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public E createOrUpdate(E entity) {
        Assert.notNull(entity, "entity must be set");
        if (Objects.isNull(entity.getId())) {
            entityManager.persist(entity);
        } else {
            return entityManager.merge(entity);
        }
        return entity;
    }

    @Override
    public Optional<E> findById(ID id) {
        if (Objects.isNull(id)) return Optional.empty();
        return Optional.ofNullable(entityManager.find(entityClass, id));
    }

    @Override
    public Collection<E> findAllByName(String name) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<E> query = criteriaBuilder.createQuery(entityClass);
        final Root<E> from = query.from(entityClass);
        final Join<Object, Object> journeys = from.join("journeys", JoinType.LEFT);
        final Predicate byName = criteriaBuilder.equal(from.get("name"), criteriaBuilder.literal(name));
        final Predicate active = criteriaBuilder.equal(from.get("active"), criteriaBuilder.literal(true));
        final Predicate byJourneyStationFrom = criteriaBuilder.equal(journeys.get("stationFrom"), criteriaBuilder.literal("Kiev"));
        return entityManager.createQuery(query.select(from).
                        where(byName, active, byJourneyStationFrom)).
                getResultList();
    }

    @Override
    public void removeById(ID id) {
        entityManager.remove(entityManager.getReference(entityClass, id));
    }

    @Override
    public void remove(E entity) {
        if (entityManager.contains(entity)) {
            entityManager.remove(entity);
        } else {
            removeById(entity.getId());
        }
    }


    @Override
    public Collection<E> findByIds(ID... ids) {
        return entityManager.unwrap(Session.class).byMultipleIds(entityClass).multiLoad(ids);
    }

    @Override
    public Collection<E> findAll() {
        return entityManager.createQuery("from " + entityClass.getSimpleName()).getResultList();
    }

    @Override
    public Collection<E> findAllAsNative() {
        return entityManager.createNativeQuery("select  * from " + entityClass.getAnnotation(Table.class).name(), entityClass).getResultList();
    }

    @Override
    public Collection<E> findAllAsCriteria() {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<E> query = criteriaBuilder.createQuery(entityClass);
        final Root<E> from = query.from(entityClass);
        return entityManager.createQuery(query.select(from)).getResultList();
    }

    @Override
    public Collection<E> findAllAsNamed() {
        return entityManager.createNamedQuery("findAllAsNamed" + entityClass.getSimpleName(), entityClass).getResultList();
    }

    @Override
    public Collection<E> findAllAsStoredProcedure() {
        return entityManager.createStoredProcedureQuery("find_all", entityClass).
                registerStoredProcedureParameter(1, Class.class, ParameterMode.REF_CURSOR).
                registerStoredProcedureParameter(2, String.class, ParameterMode.IN).
                setParameter(2, entityClass.getAnnotation(Table.class).name()).
                getResultList();
    }
}
