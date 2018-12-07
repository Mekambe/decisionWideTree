package com.pokemon.wiki.dao;

import com.pokemon.wiki.domain.AbilitiesDomains;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbilitiesDomainRepository extends JpaRepository<AbilitiesDomains, Long> {
}
