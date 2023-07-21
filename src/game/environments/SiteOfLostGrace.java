package game.environments;

import game.engine.actions.ActionList;
import game.engine.actors.Actor;
import game.engine.displays.Display;
import game.engine.positions.Ground;
import game.engine.positions.Location;
import game.actions.siteofgrace.ActivateSiteOfLostGrace;
import game.actions.siteofgrace.IncreaseMaxHitPointsAction;
import game.actions.siteofgrace.RestAction;
import game.actions.travel.FastTravelAction;
import game.characters.Status;
import game.utils.FancyMessage;

/**
 * A class that represents a SiteOfLostGrace -> a type of environment.
 * @author Harshath Muruganantham
 * @see Ground
 */
public class SiteOfLostGrace extends Ground {

    /**
     * The name of the SiteOfLostGrace.
     */
    private String name;

    /**
     * If the SiteOfLostGrace is active.
     */
    private boolean active = false;

    /**
     * The location of the SiteOfLostGrace.
     */
    private Location location;

    /**
     * Constructor.
     * @param name The name of the SiteOfLostGrace.
     * @param location The location of the SiteOfLostGrace.
     */
    public SiteOfLostGrace(String name, Location location) {
        super('U');
        setName(name);
        setLocation(location);
    }

    /**
     * Constructor.
     */
    public SiteOfLostGrace() {
        super('U');
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
        if (!(this.isActive())){
            for (String line : FancyMessage.LOST_SITE_DISCOVERED.split("\n")) {
                new Display().println(line);
                try {
                    Thread.sleep(200);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            actions.add(new ActivateSiteOfLostGrace(this));
            return actions;
        }
        if(actor.hasCapability(Status.RESTING)){
            actions.add(new RestAction(this));
        }
        if(actor.hasCapability(Status.INCREASE_HITPOINTS)){
            actions.add(IncreaseMaxHitPointsAction.getInstance());
        }
        if(actor.hasCapability(Status.FAST_TRAVEL)){
            for (SiteOfLostGrace siteOfLostGrace : ActivatedSiteManager.getInstance().getActivatesSites()){
                if (!siteOfLostGrace.equals(this)){
                    actions.add(new FastTravelAction(siteOfLostGrace, siteOfLostGrace.location));
                }

            }
        }
        return actions;
    }

    /**
     * Returns the name of the SiteOfLostGrace.
     * @return the name of the SiteOfLostGrace.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the SiteOfLostGrace.
     * @param name The name of the SiteOfLostGrace.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the active status of the SiteOfLostGrace.
     * @return the active status of the SiteOfLostGrace.
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets the active status of the SiteOfLostGrace.
     * @param active The active status of the SiteOfLostGrace.
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Returns the location of the SiteOfLostGrace.
     * @return the location of the SiteOfLostGrace.
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Sets the location of the SiteOfLostGrace.
     * @param location The location of the SiteOfLostGrace.
     */
    public void setLocation(Location location) {
        this.location = location;
    }
}
