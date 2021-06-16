package com.example.pet.response;

public class PetGroomResult {

    private final int hairTrimmed;
    private final int fliesRemoved;
    private final int nailsCut;
    private final String groomerName;

    public PetGroomResult(String groomerName,int hairTrimmed, int fliesRemoved, int nailsCut) {
        this.hairTrimmed = hairTrimmed;
        this.fliesRemoved = fliesRemoved;
        this.nailsCut = nailsCut;
        this.groomerName=groomerName;
    }


    public int getHairTrimmed() {
        return hairTrimmed;
    }

    public int getFliesRemoved() {
        return fliesRemoved;
    }

    public int getNailsCut() {
        return nailsCut;
    }

    public String getGroomerName() {
        return groomerName;
    }
}
