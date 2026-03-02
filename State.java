public final class State {
    int row;
    int col;
    State(int r, int c) {
        row = r; col = c;
    }
    public String toString() {
        return "" + col + row;
    }
}