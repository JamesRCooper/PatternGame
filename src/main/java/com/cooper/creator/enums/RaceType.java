package com.cooper.creator.enums;

public enum RaceType {

    HUMAN(0, 0, 0, 0, 0, 0),
    HALFLING(0, 0, 2, 0, 2, 0),
    ELF(0, 0, 2, 0, 0, 2);

    private Integer strengthMod;
    private Integer constitutionMod;
    private Integer dexterityMod;
    private Integer inteligenceMod;
    private Integer charismaMod;
    private Integer wisdomMod;

    RaceType(
            Integer strengthMod,
            Integer constitutionMod,
            Integer dexterityMod,
            Integer inteligenceMod,
            Integer charismaMod,
            Integer wisdomMod) {

        this.strengthMod = strengthMod;
        this.constitutionMod = constitutionMod;
        this.dexterityMod = dexterityMod;
        this.inteligenceMod = inteligenceMod;
        this.charismaMod = charismaMod;
        this.wisdomMod = wisdomMod;
    }

    public Integer getStrengthMod() {
        return strengthMod;
    }

    public Integer getConstitutionMod() {
        return constitutionMod;
    }

    public Integer getDexterityMod() {
        return dexterityMod;
    }

    public Integer getInteligenceMod() {
        return inteligenceMod;
    }

    public Integer getCharismaMod() {
        return charismaMod;
    }

    public Integer getWisdomMod() {
        return wisdomMod;
    }
}
