package game.utils;

import game.engine.actors.Actor;
import game.engine.positions.Exit;
import game.engine.positions.GameMap;
import game.engine.positions.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that finds enemies in surrounding of an actor
 * @author Ziheng Liao
 * Modified By: Harshath Murugannatham
 */
public class EnemyInSurrounding {

    /**
     * Finds enemies in surrounding of an actor
     * @param actor The actor to find enemies around
     * @param map The map to find enemies around
     * @param range The range to find enemies around
     * @return A list of enemies in surrounding of an actor
     */
    public static List<Actor> findEnemiesInSurrounding(Actor actor, GameMap map, int range) {

        List<Actor> enemies = new ArrayList<>();
        Location here = map.locationOf(actor);
        List<Location> vistedLocations = new ArrayList<>();
        vistedLocations.add(here);
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            vistedLocations.add(destination);
        }

        int x = here.x();
        int y = here.y();
        for (int i = x - range; i <= x + range; i++) {
            if (i >= map.getXRange().min() && i <= map.getXRange().max()) {
                for (int j = y - range; j <= y + range; j++) {
                    if (j >= map.getYRange().min() && j <= map.getYRange().max()) {
                        Location location = map.at(i, j);
                        if (!vistedLocations.contains(location)) {
                            if (here.map().at(i, j).containsAnActor()) {
                                enemies.add(here.map().at(i, j).getActor());
                            }
                        }
                    }
                }
            }
        }
        return enemies;
    }

}
