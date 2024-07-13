package PokemonCollection;

import acm.graphics.*;
import java.awt.*;

public class PokemonCanvas extends GCanvas {

    public void showSingleBackgroundAndSprite(Pokemon pokemon) {
        removeAll();
        String type = pokemon.getType();
        GImage background = new GImage("assets/" + type + ".png");
        background.setSize(getWidth(), getHeight());
        add(background);

        // Adjust the location of the sprite
        GImage sprite = new GImage("assets/" + pokemon.getName().toLowerCase() + ".png");
        sprite.setLocation(200, 25); // Adjust Y coordinate to be above the stats
        add(sprite);

        // Add the stats text
        GLabel nameLabel = new GLabel("Name: " + pokemon.getName());
        nameLabel.setFont("SansSerif-18");
        nameLabel.setColor(Color.BLACK);
        nameLabel.setLocation(10, 20);
        add(nameLabel);

        GLabel weightLabel = new GLabel("Weight: " + pokemon.getWeight() + " kg");
        weightLabel.setFont("SansSerif-18");
        weightLabel.setColor(Color.BLACK);
        weightLabel.setLocation(10, 50);
        add(weightLabel);

        GLabel heightLabel = new GLabel("Height: " + pokemon.getHeight() + " m");
        heightLabel.setFont("SansSerif-18");
        heightLabel.setColor(Color.BLACK);
        heightLabel.setLocation(10, 80);
        add(heightLabel);

        GLabel typeLabel = new GLabel("Type: " + pokemon.getType());
        typeLabel.setFont("SansSerif-18");
        typeLabel.setColor(Color.BLACK);
        typeLabel.setLocation(10, 110);
        add(typeLabel);

        // Add the stat bars
        GRect hpBar = new GRect(10, 140, 100, 10);
        hpBar.setFilled(true);
        hpBar.setFillColor(Color.RED);
        add(hpBar);

        GRect attackBar = new GRect(10, 160, 100, 10);
        attackBar.setFilled(true);
        attackBar.setFillColor(Color.BLUE);
        add(attackBar);

        GRect defenseBar = new GRect(10, 180, 100, 10);
        defenseBar.setFilled(true);
        defenseBar.setFillColor(Color.GREEN);
        add(defenseBar);
    }

    public void showPokemon(Pokemon pokemon) {
        showSingleBackgroundAndSprite(pokemon);
    }
}
