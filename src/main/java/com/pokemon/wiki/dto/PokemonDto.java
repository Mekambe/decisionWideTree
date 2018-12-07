package com.pokemon.wiki.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
//@Entity
public class PokemonDto {

    @Id
    @GeneratedValue
    private Long id;



    private String name;
    private int order;
   private  List <AbilitiesDto> abilities;
   private  SpeciesDto species;
   @JsonProperty(value = "base_experience")
    private int baseExperience;

    @Override
    public String toString() {
        return "PokemonDomain{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", order=" + order +
                ", abilities=" + abilities +
                ", species=" + species +
                ", baseExperience=" + baseExperience +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public List<AbilitiesDto> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<AbilitiesDto> abilities) {
        this.abilities = abilities;
    }

    public SpeciesDto getSpecies() {
        return species;
    }

    public void setSpecies(SpeciesDto species) {
        this.species = species;
    }

    public int getBaseExperience() {
        return baseExperience;
    }

    public void setBaseExperience(int baseExperience) {
        this.baseExperience = baseExperience;
    }

    public PokemonDto() {
        this.name = name;
        this.order = order;
        this.abilities = abilities;
        this.species = species;
        this.baseExperience = baseExperience;
    }
}
//