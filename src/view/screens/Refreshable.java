package view.screens;

/**
 * This interface allows a screen to refresh it's data
 */
public interface Refreshable {

    /**
     * This method refreshes the content of a GUI component.
     * This allows the content to update when the data is updated.
     */
    void refresh();
}
