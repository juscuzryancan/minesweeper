public class Cell {
    private boolean isMine;
    private boolean isFlagged;

    private boolean isChecked;

    public Cell() {
        this.isMine = false;
        this.isFlagged = false;
        this.isChecked = false;
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

}
