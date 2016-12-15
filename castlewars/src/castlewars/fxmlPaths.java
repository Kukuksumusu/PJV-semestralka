/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package castlewars;

/**
 * Enum for paths to fxml files
 * @author Kukuksumusu
 */
public enum fxmlPaths {
    PROFILE("profileSelectScene.fxml");
    
    private final String name;
    private final String PREFIX = "scenes/";
    
    private fxmlPaths(String name) {
        this.name = name;
    }

    public String getPath() {
        return PREFIX + name;
    }
    
}
