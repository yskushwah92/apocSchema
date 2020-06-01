package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.CurrencyExchange;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CurrencyExchange entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {
}
