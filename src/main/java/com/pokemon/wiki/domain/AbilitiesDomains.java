package com.pokemon.wiki.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.ToString;

import javax.persistence.*;


@Entity
@ToString
public class AbilitiesDomains {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
            @JoinColumn (name = "pokemon_id")
    PokemonDomain pokemonDto;

    private int slot;

    private boolean isHidden;
    @OneToOne (mappedBy = "abilitiesDto", cascade = CascadeType.ALL)
    private AbilityDomain ability;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PokemonDomain getPokemonDto() {
        return pokemonDto;
    }

    public void setPokemonDto(PokemonDomain pokemonDto) {
        this.pokemonDto = pokemonDto;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }

    public AbilityDomain getAbility() {
        return ability;
    }

    public void setAbility(AbilityDomain ability) {
        this.ability = ability;
    }
}
