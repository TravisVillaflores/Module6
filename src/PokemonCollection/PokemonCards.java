package PokemonCollection;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class PokemonCards {
    private ArrayList<Pokemon> pokemons = new ArrayList<>();

    public PokemonCards() {
        loadStats("assets/Pokemon.txt");
    }

    private void loadStats(String filename) {
        try {
            Scanner text = new Scanner(new File(filename));
            while (text.hasNextLine()) {
                String line = text.nextLine();
                String[] stats = line.split(",");
                String name = stats[0];
                double weight = Double.parseDouble(stats[1].replace("kg", ""));
                double height = Double.parseDouble(stats[2].replace("m", ""));
                double attack = Double.parseDouble(stats[3]);
                double defense = Double.parseDouble(stats[4]);
                double stamina = Double.parseDouble(stats[5]);
                String type = stats[6];
                pokemons.add(new Pokemon(name, weight, height, attack, defense, stamina, type));
            }
            text.close();
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist");
        }
    }

    public Pokemon getPokemon(int index) {
        return pokemons.get(index);
    }

    public int getSize() {
        return pokemons.size();
    }

    public void remove(int index) {
        pokemons.remove(index);
    }

    public int searchPokemon(String name) {
        for (int i = 0; i < pokemons.size(); i++) {
            if (pokemons.get(i).getName().equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }
}
