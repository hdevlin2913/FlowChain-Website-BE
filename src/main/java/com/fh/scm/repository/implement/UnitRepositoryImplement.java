package com.fh.scm.repository.implement;

import com.fh.scm.pojo.Unit;
import com.fh.scm.repository.UnitRepository;
import com.fh.scm.util.Pagination;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

@Repository
@Transactional
public class UnitRepositoryImplement implements UnitRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    private Session getCurrentSession() {
        return Objects.requireNonNull(factory.getObject()).getCurrentSession();
    }

    @Override
    public Unit get(UUID id) {
        Session session = this.getCurrentSession();

        return session.get(Unit.class, id);
    }

    @Override
    public void insert(Unit unit) {
        Session session = this.getCurrentSession();
        session.save(unit);
    }

    @Override
    public void update(Unit unit) {
        Session session = this.getCurrentSession();
        session.update(unit);
    }

    @Override
    public void delete(UUID id) {
        Session session = this.getCurrentSession();
        Unit unit = session.get(Unit.class, id);
        session.delete(unit);
    }

    @Override
    public void softDelete(UUID id) {
        Session session = this.getCurrentSession();
        Unit unit = session.get(Unit.class, id);
        unit.setActive(false);
        session.update(unit);
    }

    @Override
    public void insertOrUpdate(Unit unit) {
        Session session = this.getCurrentSession();
        session.saveOrUpdate(unit);
    }

    @Override
    public Long count() {
        Session session = this.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Unit> root = criteria.from(Unit.class);

        criteria.select(builder.count(root));
        Query<Long> query = session.createQuery(criteria);

        return query.getSingleResult();
    }

    @Override
    public Boolean exists(UUID id) {
        Session session = this.getCurrentSession();
        Unit unit = session.get(Unit.class, id);

        return unit != null;
    }

    @Override
    public List<Unit> getAll(Map<String, String> params) {
        Session session = this.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Unit> criteria = builder.createQuery(Unit.class);
        Root<Unit> root = criteria.from(Unit.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get("isActive"), true));

        if (params != null) {
            String name = params.get("name");
            if (name != null && !name.isEmpty()) {
                predicates.add(builder.like(root.get("name"), String.format("%%%s%%", name)));
            }
        }

        criteria.select(root).where(predicates.toArray(Predicate[]::new));
        Query<Unit> query = session.createQuery(criteria);
        Pagination.paginator(query, params);

        return query.getResultList();
    }
}