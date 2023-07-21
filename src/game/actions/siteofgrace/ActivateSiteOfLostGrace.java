package game.actions.siteofgrace;

import game.engine.actions.Action;
import game.engine.actors.Actor;
import game.engine.positions.GameMap;
import game.environments.ActivatedSiteManager;
import game.environments.SiteOfLostGrace;

/**
 * An Action that is entered when the player touches the Site of Lost Grace to Activate it..
 * @author Harshath Muruganantham
 * @see Action
 * @see game.environments.SiteOfLostGrace
 */
public class ActivateSiteOfLostGrace extends Action {

    /**
     * The Site of Lost Grace that is to be activated.
     */
    private SiteOfLostGrace siteOfLostGrace;

    /**
     * Constructor.
     * @param siteOfLostGrace the Site of Lost Grace that is to be activated.
     */
    public ActivateSiteOfLostGrace(SiteOfLostGrace siteOfLostGrace){
        setSiteOfLostGrace(siteOfLostGrace);
    }

    /**
     * Executes the action of activating the Site of Lost Grace.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A string describing the action.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        getSiteOfLostGrace().setActive(true);
        ActivatedSiteManager.getInstance().addActivatedSite(getSiteOfLostGrace());
        return actor + " has activated " + getSiteOfLostGrace().getName();
    }

    /**
     * Returns a string describing the action.
     * @param actor The actor performing the action.
     * @return A string describing the action.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " touches (activates) " + getSiteOfLostGrace().getName();
    }

    /**
     * Returns the Site of Lost Grace that is to be activated.
     * @return the Site of Lost Grace that is to be activated.
     */
    public SiteOfLostGrace getSiteOfLostGrace() {
        return siteOfLostGrace;
    }

    /**
     * Sets the Site of Lost Grace that is to be activated.
     * @param siteOfLostGrace the Site of Lost Grace that is to be activated.
     */
    public void setSiteOfLostGrace(SiteOfLostGrace siteOfLostGrace) {
        this.siteOfLostGrace = siteOfLostGrace;
    }
}
