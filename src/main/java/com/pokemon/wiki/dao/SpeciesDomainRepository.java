package com.pokemon.wiki.dao;

import com.pokemon.wiki.domain.SpeciesDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpeciesDomainRepository extends JpaRepository <SpeciesDomain, Long> {
}
