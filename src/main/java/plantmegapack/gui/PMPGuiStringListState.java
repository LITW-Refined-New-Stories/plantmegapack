package plantmegapack.gui;

public class PMPGuiStringListState {

    public float scrollDistance;
    public float scrollFactor;
    public int selectedIndex;

    public PMPGuiStringListState() {
        this.resetState();
    }

    public PMPGuiStringListState(PMPGuiStringListState sourceState) {
        this.saveState(sourceState);
    }

    public void resetState() {
        this.scrollDistance = 0.0F;
        this.scrollFactor = 0.0F;
        this.selectedIndex = 0;
    }

    public void saveState(PMPGuiStringListState sourceState) {
        this.scrollDistance = sourceState.scrollDistance;
        this.scrollFactor = sourceState.scrollFactor;
        this.selectedIndex = sourceState.selectedIndex;
    }
}
