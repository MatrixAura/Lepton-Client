package org.newdawn.slick.loading;

import org.newdawn.slick.opengl.InternalTextureLoader;
import org.newdawn.slick.util.Log;

import java.util.ArrayList;

/**
 * A central list where all deferred loading resoures end up when deferred loading is in use. Each
 * texture and sound loaded will be put in this list and can be loaded in one by one
 *
 * @author kevin
 */
public class LoadingList {
    /**
     * The single instance of this list
     */
    private static final LoadingList single = new LoadingList();

    /**
     * Get the single global loading list
     *
     * @return The single global loading list
     */
    public static LoadingList get() {
        return single;
    }

	/**
     * The list of deferred resources to load
     */
    private final ArrayList deferred = new ArrayList();
    /**
     * The total number of elements that have been added - does not go down as elements are removed
     */
    private int total;

    /**
     * Create a new list
     */
    private LoadingList() {
    }

    /**
     * Add a resource to be loaded at some later date
     *
     * @param resource The resource to be added
     */
    public void add(DeferredResource resource) {
        total++;
        deferred.add(resource);
    }

    /**
     * Remove a resource from the list that has been loaded for
     * other reasons.
     *
     * @param resource The resource to remove
     */
    public void remove(DeferredResource resource) {
        Log.info("Early loading of deferred resource due to req: " + resource.getDescription());
        total--;
        deferred.remove(resource);
    }

}
