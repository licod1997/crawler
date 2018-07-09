package com.pickyourcpu.repository;

import com.pickyourcpu.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, Long> {

    void deleteAll();
}
