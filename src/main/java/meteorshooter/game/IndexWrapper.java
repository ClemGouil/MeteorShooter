package meteorshooter.game;

public class IndexWrapper {
    private int index;

    public IndexWrapper(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void increment() {
        index++;
    }
}
