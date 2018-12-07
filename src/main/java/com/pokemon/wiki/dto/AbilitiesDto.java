package com.pokemon.wiki.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AbilitiesDto {
    private int slot;
    @JsonProperty(value = "is_Hidden")
    private boolean isHidden;
    private AbilityDto ability;

   

    public AbilityDto getAbility() {
        return ability;
    }

    public void setAbility(AbilityDto ability) {
        this.ability = ability;
    }

    @Override
    public String toString() {
        return "AbilitiesDomains{" +
                "slot=" + slot +
                ", isHidden=" + isHidden +
                '}';
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

    public AbilitiesDto() {
        this.slot = slot;
        this.isHidden = isHidden;
    }
}
