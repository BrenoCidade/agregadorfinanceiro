package com.brenocidade.agregadorfinanceiro.repository;

import com.brenocidade.agregadorfinanceiro.entity.Account;
import com.brenocidade.agregadorfinanceiro.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID>{
}
