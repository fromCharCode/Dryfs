package main.scenes;

public enum PaneType {
    MAINMENU, PLAYERMENU, OPTIONSMENU, GAMESCENE;

    private PaneType(){

    }

    public PaneType getInstance() {
        return this;
    }
}
