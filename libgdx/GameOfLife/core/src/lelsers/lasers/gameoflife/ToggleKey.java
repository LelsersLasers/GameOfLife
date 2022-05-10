package lelsers.lasers.gameoflife;

public class ToggleKey {
    private boolean wasDown = false;

    public boolean down(boolean state) {
        if (!wasDown && state) {
            wasDown = true;
            return true;
        }
        else if (!state) wasDown = false;
        return false;
    }
}
