package org.newdawn.slick.util;

/**
 * A simple central logging system
 *
 * @author kevin
 */
public final class Log {
    /**
     * true if activated by the system property "org.newdawn.slick.forceVerboseLog"
     */
    private static boolean forcedVerbose = false;

    /**
     * The debug property which can be set via JNLP or startup parameter to switch
     * logging mode to verbose for games that were released without verbose logging
     * value must be "true"
     */
    private static final String forceVerboseProperty = "org.newdawn.slick.forceVerboseLog";

    /**
     * the verbose property must be set to "true" to switch on verbose logging
     */
    private static final String forceVerbosePropertyOnValue = "true";


    /**
     * The log is a simple static utility, no construction
     */
    private Log() {

    }


    /**
     * Indicate that we want verbose logging, even if switched off in game code.
     * Only be called when system property "org.newdawn.slick.forceVerboseLog" is set to true.
     * You must not call this method directly.
     */
    public static void setForcedVerboseOn() {
        forcedVerbose = true;
    }

    /**
     * Log an error
     *
     * @param message The message describing the error
     */
    public static void error(String message) {

    }

    /**
     * Log a warning
     *
     * @param message The message describing the warning
     * @param e       The issue causing the warning
     */
    public static void warn(String message, Throwable e) {

    }

    /**
     * Log an information message
     *
     * @param message The message describing the infomation
     */
    public static void info(String message) {
    }

}
