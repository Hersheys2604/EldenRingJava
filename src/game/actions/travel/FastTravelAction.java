package game.actions.travel;

import game.engine.actions.Action;
import game.engine.actors.Actor;
import game.engine.positions.GameMap;
import game.engine.positions.Location;
import game.environments.SiteOfLostGrace;

/**
 * Action for fast travelling to a site of lost Grace from another Site of Lost Grace
 * @author Harshath Muruganantham
 * @see Action
 * @see game.environments.SiteOfLostGrace
 * @see Location
 */
public class FastTravelAction extends Action {

    /**
     * The Site of Lost Grace that is to be fast travelled to.
     */
    private SiteOfLostGrace siteOfLostGrace;

    /**
     * The location of the Site of Lost Grace that is to be fast travelled to.
     */
    private Location targetLocation;

    /**
     * Constructor.
     * @param siteOfLostGrace the Site of Lost Grace that is to be fast travelled to.
     * @param targetLocation the location of the Site of Lost Grace that is to be fast travelled to.
     */
    public FastTravelAction(SiteOfLostGrace siteOfLostGrace, Location targetLocation){
        setTargetLocation(targetLocation);
        setSiteOfLostGrace(siteOfLostGrace);
    }

    /**
     * Executes the action of fast travelling to the Site of Lost Grace.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A string describing the action.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        map.addActor(actor, getTargetLocation());
        return actor + " fast travels to " + getSiteOfLostGrace().getName();
    }

    /**
     * Returns a string describing the action.
     * @param actor The actor performing the action.
     * @return A string describing the action.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " fast travels to " + getSiteOfLostGrace().getName();
    }

    /**
     * Returns the Site of Lost Grace that is to be fast travelled to.
     * @return the Site of Lost Grace that is to be fast travelled to.
     */
    public SiteOfLostGrace getSiteOfLostGrace() {
        return siteOfLostGrace;
    }

    /**
     * Sets the Site of Lost Grace that is to be fast travelled to.
     * @param siteOfLostGrace the Site of Lost Grace that is to be fast travelled to.
     */
    public void setSiteOfLostGrace(SiteOfLostGrace siteOfLostGrace) {
        this.siteOfLostGrace = siteOfLostGrace;
    }

    /**
     * Returns the location of the Site of Lost Grace that is to be fast travelled to.
     * @return the location of the Site of Lost Grace that is to be fast travelled to.
     */
    public Location getTargetLocation() {
        return targetLocation;
    }

    /**
     * Sets the location of the Site of Lost Grace that is to be fast travelled to.
     * @param targetLocation the location of the Site of Lost Grace that is to be fast travelled to.
     */
    public void setTargetLocation(Location targetLocation) {
        this.targetLocation = targetLocation;
    }
}
