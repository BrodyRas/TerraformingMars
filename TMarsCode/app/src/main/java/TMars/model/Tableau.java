package TMars.model;

public class Tableau {

    Corporation corp;
    int terraformerRating;
    int credits;
    int moneyProduction;
    int steel;
    int steelProduction;
    int titanium;
    int titaniumProduction;
    int plants;
    int plantProduction;
    int power;
    int powerProduction;
    int heat;
    int heatProduction;

    public Tableau(Corporation corp)
    {
        this.corp = corp;
        terraformerRating = 20;
        credits = 0;
        moneyProduction = 0;
        steel = 0;
        steelProduction = 0;
        titanium = 0;
        titaniumProduction = 0;
        plants = 0;
        plantProduction = 0;
        power = 0;
        powerProduction = 0;
        heat = 0;
        heatProduction = 0;
    }

    public Corporation getCorp() { return corp; }

    public void setCorp(Corporation corp) { this.corp = corp; }

    public int getTerraformerRating() { return terraformerRating; }

    public void setTerraformerRating(int terraformerRating) { this.terraformerRating = terraformerRating; }

    public int getCredits() { return credits; }

    public void setCredits(int credits) { this.credits = credits; }

    public int getMoneyProduction() { return moneyProduction; }

    public void setMoneyProduction(int moneyProduction) { this.moneyProduction = moneyProduction; }

    public int getSteel() { return steel; }

    public void setSteel(int steel) { this.steel = steel; }

    public int getSteelProduction() { return steelProduction; }

    public void setSteelProduction(int steelProduction) { this.steelProduction = steelProduction; }

    public int getTitanium() { return titanium; }

    public void setTitanium(int titanium) { this.titanium = titanium; }

    public int getTitaniumProduction() { return titaniumProduction; }

    public void setTitaniumProduction(int titaniumProduction) { this.titaniumProduction = titaniumProduction; }

    public int getPlants() { return plants; }

    public void setPlants(int plants) { this.plants = plants; }

    public int getPlantProduction() { return plantProduction; }

    public void setPlantProduction(int plantProduction) { this.plantProduction = plantProduction; }

    public int getPower() { return power; }

    public void setPower(int power) { this.power = power; }

    public int getPowerProduction() { return powerProduction; }

    public void setPowerProduction(int powerProduction) { this.powerProduction = powerProduction; }

    public int getHeat() { return heat; }

    public void setHeat(int heat) { this.heat = heat; }

    public int getHeatProduction() { return heatProduction; }

    public void setHeatProduction(int heatProduction) { this.heatProduction = heatProduction; }
}
