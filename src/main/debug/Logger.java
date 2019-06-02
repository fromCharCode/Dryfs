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
