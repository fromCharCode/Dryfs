/*
 * Author:              fromCharCode - David Schneider
 * Contribution:        none
 * Date of creation:    02.06.2019
 * Deadline:            -
 * Information:         If you want to print some debug information, please use this class - feel free to enhance
 *
 * Notes:               maybe add something more for debugging or use some official debugger
 *
 */

package main.debug;

import main.scenes.Scene;

public class Logger {

    static boolean printLog = true;

    // maybe logToFile
    // later use for exceptions

    // == public methods ==
    public static void log(Class sceneClass, String content){
        if (!printLog) return;
        System.out.println("LOG: " + sceneClass.getPackageName() + ": " + sceneClass.getName() + ": " + content);
    }
}
