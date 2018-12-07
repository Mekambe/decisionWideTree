package com.pokemon.wiki.dto;

public class SpeciesDto {
    private String name;
    private String url;

    @Override
    public String toString() {
        return "SpeciesDomain{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public SpeciesDto() {
        this.name = name;
        this.url = url;
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
