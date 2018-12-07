package com.pokemon.wiki.services;

import com.pokemon.wiki.domain.AbilitiesDomains;
import com.pokemon.wiki.domain.PokemonDomain;
import com.pokemon.wiki.domain.SpeciesDomain;
import com.pokemon.wiki.dto.AbilitiesDto;
import com.pokemon.wiki.dto.AbilityDto;
import com.pokemon.wiki.dto.PokemonDto;
import com.pokemon.wiki.dto.SpeciesDto;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class PokemonServiceImp implements PokemonService {
    @Override
    public PokemonDto getPokemonFromApi(Long id) {
        CloseableHttpClient httpClient
                = HttpClients.custom()
                .setSSLHostnameVerifier(new NoopHostnameVerifier())
                .build();
        HttpComponentsClientHttpRequestFactory requestFactory
                = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient); // request factory ignoruje htpps
        PokemonDto pokemonDto = new RestTemplate(requestFactory).getForObject("http://pokeapi.co/api/v2/pokemon/"+id, PokemonDto.class);
        return pokemonDto;
    }

    @Override
    public PokemonDomain convertPokemon(PokemonDto pokemonDto) {
        PokemonDomain pokemonParsedFromDto = new PokemonDomain();
        SpeciesDomain speciesParsedFromDto = new SpeciesDomain();
        SpeciesDto speciesDto = pokemonDto.getSpecies();
        speciesParsedFromDto.setName(speciesDto.getName());
        speciesParsedFromDto.setUrl(speciesDto.getUrl());

      List<AbilitiesDomains> abilitiesDomainsList = new ArrayList<>();
      List <AbilitiesDto> abilitiesDtosList = pokemonDto.getAbilities();
      for (AbilitiesDto abilityDto:abilitiesDtosList){
          AbilitiesDomains abilitiesDomains = new AbilitiesDomains();


      }





        pokemonParsedFromDto.setName(pokemonDto.getName());
        pokemonParsedFromDto.setSpecies(speciesParsedFromDto);
        speciesParsedFromDto.setPokemonDomain(pokemonParsedFromDto);
        return pokemonParsedFromDto;
    }
}
