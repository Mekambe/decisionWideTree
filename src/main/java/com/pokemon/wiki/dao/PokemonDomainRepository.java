package com.pokemon.wiki.dao;



import com.pokemon.wiki.domain.PokemonDomain;
import com.pokemon.wiki.dto.PokemonDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonDomainRepository extends JpaRepository<PokemonDomain, Long> {

    PokemonDomain findByName(String name);



}
