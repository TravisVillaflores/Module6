package PokemonCollection;

public class Pokemon {
    private String name;
    private double weight;
    private double height;
    private double attack;
    private double defense;
    private double stamina;
    private String type;

    public Pokemon(String name, double weight, double height, double attack, double defense, double stamina, String type) {
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.attack = attack;
        this.defense = defense;
        this.stamina = stamina;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public double getAttack() {
        return attack;
    }

    public double getDefense() {
        return defense;
    }

    public double getStamina() {
        return stamina;
    }

    public String getType() {
        return type;
    }

    public boolean isMultiType() {
        return type.contains("/");
    }
}
