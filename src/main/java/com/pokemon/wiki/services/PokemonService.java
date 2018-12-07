package com.pokemon.wiki.services;


import com.pokemon.wiki.domain.PokemonDomain;
import com.pokemon.wiki.dto.PokemonDto;

public interface PokemonService {
    public PokemonDto getPokemonFromApi(Long id);
    public PokemonDomain convertPokemon (PokemonDto pokemonDto);





}
