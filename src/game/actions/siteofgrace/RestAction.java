package game.actions.siteofgrace;

import game.engine.actions.Action;
import game.engine.actors.Actor;
import game.engine.positions.GameMap;
import game.characters.Status;
import game.environments.SiteOfLostGrace;
import game.reset.ResetManager;

/**
 * Action for resting at the First Step Lost Site of Grace.
 * @author Harshath Muruganantham
 * @see Action
 */
public class RestAction extends Action {

    /**
     * The Site of Lost Grace where the actor is resting.
     */
    private SiteOfLostGrace siteOfLostGrace;

    /**
     * Constructor.
     * @param siteOfLostGrace The Site of Lost Grace where the actor is resting.
     */
    public RestAction(SiteOfLostGrace siteOfLostGrace) {
        setSiteOfLostGrace(siteOfLostGrace);
    }

    /**
     * Perform the Action of resting.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.addCapability(Status.IS_RESTING);
        ResetManager.getInstance().run(map);
        return actor + " rests at " + getSiteOfLostGrace().getName();
    }

    /**
     * Returns a description of the action suitable for displaying in the menu.
     *
     * @param actor The actor performing the action.
     * @return a String, e.g. "Player rests at the First Step Lost Site of Grace"
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " rests at " + getSiteOfLostGrace().getName();
    }

/**
     * Returns the amount of turns required to perform the action.
     * @return the amount of turns required to perform the action
     */
    public SiteOfLostGrace getSiteOfLostGrace() {
        return siteOfLostGrace;
    }

    /**
     * Sets the amount of turns required to perform the action.
     * @param siteOfLostGrace the amount of turns required to perform the action
     */
    public void setSiteOfLostGrace(SiteOfLostGrace siteOfLostGrace) {
        this.siteOfLostGrace = siteOfLostGrace;
    }
}
