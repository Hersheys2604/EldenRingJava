package game.environments;

import game.engine.actions.ActionList;
import game.engine.actors.Actor;
import game.engine.positions.Ground;
import game.engine.positions.Location;
import game.actions.SummonAction;
import game.characters.Status;
import game.characters.players.archetype.*;

import java.util.ArrayList;

/**
 * A class that represents a SummonSign -> a type of environment.
 * A SummonSign is a ground that summons a random ally when activated.
 * The ally is summoned on a random adjacent location.
 * The ally is summoned only if the actor has the capability Status.SUMMONING.
 * @author Ho Sneg
 * Modified By: Ziheng Liao
 * @see Ground
 */
public class SummonSign extends Ground{

    /**
     * The list of Archetypes that can be summoned.
     */
    private ArrayList<Archetype> summons = new ArrayList<>();

    /**
     * Constructor.
     */
    public SummonSign() {
        super('=');
        summons.add(new Wretch());
        summons.add(new Samurai());
        summons.add(new Bandit());
        summons.add(new Astrologer());
    }

    /**
     * Returns the allowable actions for the actor in the location.
     * @param actor The actor to check.
     * @param location The location of the ground.
     * @param direction The direction of the ground from the actor.
     * @return the allowable actions for the actor in the location.
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();
        if(actor.hasCapability(Status.SUMMONING)) {
            actions.add(new SummonAction(this, location));
        }
        return actions;
    }

    /**
     * Returns true if the actor can enter the ground.
     * @param actor The actor to check.
     * @return true if the actor can enter the ground.
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Status.SUMMONING);
    }

/**
     * Returns the list of Archetypes that can be summoned.
     * @return the list of Archetypes that can be summoned.
     */
    public ArrayList<Archetype> getSummons() {
        return summons;
    }

    /**
     * Sets the list of Archetypes that can be summoned.
     * @param summons the list of Archetypes that can be summoned.
     */
    public void setSummons(ArrayList<Archetype> summons) {
        this.summons = summons;
    }
}
