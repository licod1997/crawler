package com.pickyourcpu.repository.spec;

import com.pickyourcpu.dto.SearchCreateriaDTO;
import com.pickyourcpu.entity.Product;
import com.pickyourcpu.entity.Shop;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

        List<Predicate> preManufacture = new ArrayList<>();
        List<Predicate> preSocket = new ArrayList<>();
        List<Predicate> preType = new ArrayList<>();
        List<Predicate> preNoOfCores = new ArrayList<>();
        List<Predicate> prePrice = new ArrayList<>();
        Predicate preBuyable = cb.conjunction();

        for ( String manufacture : criteria.getManufacture() ) {
            if ( manufacture.equals( "Others" ) ) {
                preManufacture = new ArrayList<>();
                preManufacture.add( cb.conjunction() );
                break;
            }
            preManufacture.add( cb.like( root.get( "name" ), "%" + manufacture + "%" ) );
        }
        for ( String socket : criteria.getSocket() ) {
            preSocket.add( cb.equal( root.get( "socket" ), socket ) );
        }
        for ( String type : criteria.getType() ) {
            preType.add( cb.like( root.get( "name" ), "%" + type + "%" ) );
        }
        for ( int noOfCores : criteria.getNoOfCores() ) {
            preNoOfCores.add( cb.equal( root.get( "noOfCores" ), noOfCores ) );
        }

        Join<Shop, Product> join = root.join( "shops", JoinType.LEFT );

        if ( criteria.getBuyable() ) {
            preBuyable = cb.isNotNull( join.get( "product" ) );
        }

        if ( criteria.getSort().equals( "DESC" ) ) {
            if ( criteria.getField().equals( "benchmark" ) ) {
                query.orderBy( cb.desc( root.get( "benchmark" ) ) );
            } else {
                query.orderBy( cb.desc( join.get( "price" ) ) );
            }
        } else {
            if ( criteria.getField().equals( "benchmark" ) ) {
                query.orderBy( cb.asc( root.get( "benchmark" ) ) );
            } else {
                query.orderBy( cb.asc( join.get( "price" ) ) );
            }
        }

        for ( String price : criteria.getPrice() ) {
            String[] tmp = price.split( "-" );
            prePrice.add(
                    cb.and(
                            cb.ge( join.get( "price" ), new BigDecimal( tmp[0] ) ),
                            cb.le( join.get( "price" ), new BigDecimal( tmp[1] ) )
                    )
            );

        }

        if ( preManufacture.size() == 0 ) preManufacture.add( cb.conjunction() );
        if ( preSocket.size() == 0 ) preSocket.add( cb.conjunction() );
        if ( preType.size() == 0 ) preType.add( cb.conjunction() );
        if ( preNoOfCores.size() == 0 ) preNoOfCores.add( cb.conjunction() );
        if ( prePrice.size() == 0 ) prePrice.add( cb.conjunction() );

        return cb.and(
                cb.or( preManufacture.toArray( new Predicate[preManufacture.size()] ) ),
                cb.or( preSocket.toArray( new Predicate[preSocket.size()] ) ),
                cb.or( preType.toArray( new Predicate[preType.size()] ) ),
                cb.or( preNoOfCores.toArray( new Predicate[preNoOfCores.size()] ) ),
                cb.or( prePrice.toArray( new Predicate[prePrice.size()] ) ),
                preBuyable,
                preBuyable
        );
    }
}
