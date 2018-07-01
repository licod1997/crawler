package com.pickyourcpu.repository.spec;

import com.pickyourcpu.dto.SearchCreateriaDTO;
import com.pickyourcpu.entity.Product;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ProductSpecification implements Specification<Product> {

    private SearchCreateriaDTO criteria;

    public ProductSpecification() {
    }

    public SearchCreateriaDTO getCriteria() {
        return criteria;
    }

    public void setCriteria( SearchCreateriaDTO criteria ) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate( Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb ) {
        if ( criteria == null ) return cb.conjunction();

        Predicate preManufacture = cb.conjunction();
        Predicate preSocket = cb.conjunction();
        Predicate preType = cb.conjunction();
        Predicate preNoOfCores = cb.conjunction();

        String manufacture = criteria.getManufacture();
        String socket = criteria.getSocket();
        String type = criteria.getType();
        Integer noOfCores = criteria.getNoOfCores();

        if ( manufacture != null ) preManufacture = cb.like( root.get( "name" ), "%" + manufacture + "%" );
        if ( socket != null ) preSocket = cb.equal( root.get( "socket" ), socket );
        if ( type != null ) preType = cb.like( root.get( "name" ), "%" + type + "%" );
        if ( noOfCores != null ) preNoOfCores = cb.equal( root.get( "noOfCores" ), noOfCores );

        return cb.and( preManufacture, preSocket, preType, preNoOfCores );
    }
}
