package de.smartcrew.eatforfitserver.constants;

public enum ReceipeUnits {
    G("g"), KG("kg"), TL("TL"), EL("EL"), ML("ml"), L("l");

    private String unit;

    private ReceipeUnits(String unit){
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
    
}
