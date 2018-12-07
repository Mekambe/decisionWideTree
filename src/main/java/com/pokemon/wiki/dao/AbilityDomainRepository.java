package com.pokemon.wiki.dao;

import com.pokemon.wiki.domain.AbilityDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbilityDomainRepository extends JpaRepository<AbilityDomain, Long> {
}
