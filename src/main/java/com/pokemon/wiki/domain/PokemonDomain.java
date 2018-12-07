package com.pokemon.wiki.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@ToString
public class PokemonDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String name;

    private int order1;


    @OneToMany (mappedBy = "pokemonDto", cascade = CascadeType.ALL)
   private  List <AbilitiesDomains> abilities = new ArrayList<>();

    @JsonManagedReference
    @OneToOne (mappedBy = "pokemonDomain", cascade = CascadeType.ALL)
   private SpeciesDomain species;

   @JsonProperty(value = "base_experience")
    private int baseExperience;

    public PokemonDomain(String name, int order1, List<AbilitiesDomains> abilities, SpeciesDomain species, int baseExperience) {
        this.name = name;
        this.order1 = order1;
        this.abilities = abilities;
        this.species = species;
        this.baseExperience = baseExperience;
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

    public int getOrder1() {
        return order1;
    }

    public void setOrder1(int order1) {
        this.order1 = order1;
    }

    public List<AbilitiesDomains> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<AbilitiesDomains> abilities) {
        this.abilities = abilities;
    }

    public SpeciesDomain getSpecies() {
        return species;
    }

    public void setSpecies(SpeciesDomain species) {
        this.species = species;
    }

    public int getBaseExperience() {
        return baseExperience;
    }

    public void setBaseExperience(int baseExperience) {
        this.baseExperience = baseExperience;
    }
}
//