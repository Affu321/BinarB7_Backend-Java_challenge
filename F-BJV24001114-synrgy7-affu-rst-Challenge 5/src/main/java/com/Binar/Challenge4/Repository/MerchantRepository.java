package com.Binar.Challenge4.Repository;

import com.Binar.Challenge4.Entity.Merchant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface MerchantRepository extends JpaRepository<Merchant, UUID>, JpaSpecificationExecutor<Merchant> {

    @Query(value = "select m from Order m")
    public List<Merchant> getAllPage();
    @Query(value = "SELECT m FROM Merchant m WHERE m.id = :id")
    public Merchant getByIdMerchant(@Param("id") UUID id);
    @Query("FROM Order u")
    public Page<Merchant> getAllDataPage(Pageable pageable);
}





