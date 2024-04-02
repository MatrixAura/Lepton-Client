package org.newdawn.slick.loading;

import java.io.IOException;

/**
 * A description of any class providing a resource handle that be loaded
 * at a later date (i.e. deferrred)
 *
 * @author kevin
 */
public interface DeferredResource {

    /**
     * Get a description of the resource to be loaded
     *
     * @return The description of the resource to be loaded
     */
    String getDescription();
}
