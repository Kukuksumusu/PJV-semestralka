package castlewars;

/**
 * Enum for paths to fxml files
 * @author Kukuksumusu
 */
public enum fxmlPaths {
    PROFILE("profileSelectScene.fxml", null),
    GAME("gameScene.fxml", "gameScene.css"),
    DECK_BUILDER("deckBuilderScene.fxml", "deckBuilderScene.css");
    
    private final String name;
    private final String css;
    private final String PREFIX = "scenes/";
    private final String CSSPREFIX = "castlewars/scenes/css/";
    
    private fxmlPaths(String name, String css) {
        this.name = name;
        this.css = css;
    }

    public String getPath() {
        return PREFIX + name;
    }
    
    public String getCSS() {
        if (css != null) {
            return CSSPREFIX + css;
        }
        return null;
    }
}
