package com.brenocidade.agregadorfinanceiro.repository;

import com.brenocidade.agregadorfinanceiro.entity.BillingAddress;
import com.brenocidade.agregadorfinanceiro.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BillingAddressRepository extends JpaRepository<BillingAddress, UUID>{
}
