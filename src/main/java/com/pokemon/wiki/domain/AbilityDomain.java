package com.pokemon.wiki.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;


@NoArgsConstructor
@Entity
@ToString
public class AbilityDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @OneToOne
            @JoinColumn(name = "Abilty_Fk" )
    AbilitiesDomains abilitiesDto;

    private String name;

    private String url;

    public AbilityDomain(AbilitiesDomains abilitiesDto, String name, String url) {
        this.abilitiesDto = abilitiesDto;
        this.name = name;
        this.url = url;
    }



    public AbilitiesDomains getAbilitiesDto() {
        return abilitiesDto;
    }

    public void setAbilitiesDto(AbilitiesDomains abilitiesDto) {
        this.abilitiesDto = abilitiesDto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
