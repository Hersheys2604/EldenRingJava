package game.environments;

import java.util.ArrayList;
import java.util.List;

/**
 * A ActivatedSiteManager class that manages a list of all activated Site of Lost Graces.
 * @author Harshath Murugannatham
 * @see SiteOfLostGrace
 */
public class ActivatedSiteManager {

    /**
     * A list of all activated Site of Lost Graces.
     */
    private List<SiteOfLostGrace> activatesSites = new ArrayList<>();

    /**
     * The instance of the ActivatedSiteManager.
     */
    private static ActivatedSiteManager instance;

    /**
     * Constructor.
     */
    private ActivatedSiteManager() {
    }

    /**
     * Returns the instance of the ActivatedSiteManager.
     * @return The instance of the ActivatedSiteManager.
     */
    public static ActivatedSiteManager getInstance() {
        if (instance == null) {
            instance = new ActivatedSiteManager();
        }
        return instance;
    }

    /**
     * Adds an activated Site of Lost Grace to the list of activated Site of Lost Graces.
     * @param siteOfLostGrace The activated Site of Lost Grace to add.
     */
    public void addActivatedSite(SiteOfLostGrace siteOfLostGrace){
        if (!getActivatesSites().contains(siteOfLostGrace)){
            getActivatesSites().add(siteOfLostGrace);
        }
    }

    /**
     * Returns the list of all activated Site of Lost Graces.
     * @return The list of all activated Site of Lost Graces.
     */
    public List<SiteOfLostGrace> getActivatesSites() {
        return activatesSites;
    }
}
