package com.fh.scm.repository.implement;

import com.fh.scm.pojo.Category;
import com.fh.scm.repository.CategoryRepository;
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
public class CategoryRepositoryImplement implements CategoryRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    private Session getCurrentSession() {
        return Objects.requireNonNull(this.factory.getObject()).getCurrentSession();
    }

    @Override
    public Category get(UUID id) {
        Session session = this.getCurrentSession();

        return session.get(Category.class, id);
    }

    @Override
    public void insert(Category category) {
        Session session = this.getCurrentSession();
        session.save(category);
    }

    @Override
    public void update(Category category) {
        Session session = this.getCurrentSession();
        session.update(category);
    }

    @Override
    public void delete(UUID id) {
        Session session = this.getCurrentSession();
        Category category = session.get(Category.class, id);
        session.delete(category);
    }

    @Override
    public void softDelete(UUID id) {
        Session session = this.getCurrentSession();
        Category category = session.get(Category.class, id);
        category.setActive(false);
        session.update(category);
    }

    @Override
    public void insertOrUpdate(Category category) {
        Session session = this.getCurrentSession();
        session.saveOrUpdate(category);
    }

    @Override
    public Long count() {
        Session session = this.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Category> root = criteria.from(Category.class);

        criteria.select(builder.count(root));
        Query<Long> query = session.createQuery(criteria);

        return query.getSingleResult();
    }

    @Override
    public Boolean exists(UUID id) {
        Session session = this.getCurrentSession();
        Category category = session.get(Category.class, id);

        return category != null;
    }

    @Override
    public List<Category> getAll(Map<String, String> params) {
        Session session = this.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Category> criteria = builder.createQuery(Category.class);
        Root<Category> root = criteria.from(Category.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get("isActive"), true));

        if (params != null) {
            String name = params.get("name");
            if (name != null && !name.isEmpty()) {
                predicates.add(builder.like(root.get("name"), String.format("%%%s%%", name)));
            }
        }

        criteria.select(root).where(predicates.toArray(Predicate[]::new));
        Query<Category> query = session.createQuery(criteria);
        Pagination.paginator(query, params);

        return query.getResultList();
    }
}