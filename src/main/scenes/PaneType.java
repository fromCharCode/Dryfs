/*
 * Author:              fromCharCode - David Schneider
 * Contribution:        none
 * Date of creation:    01.06.2019
 * Deadline:            -
 * Information:         defining the scenes for which we will create panes
 *
 * Notes:               todo: clean up: create just one main menu scene, use this, delete output
 *
 */

package main.scenes;

public enum PaneType {
    MAINMENU, PLAYERMENU, OPTIONSMENU, GAMESCENE;

    PaneType(){

    }

    public PaneType getInstance() {
        return this;
    }
}
