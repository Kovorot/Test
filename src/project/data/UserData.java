package project.data;

public class UserData {
    private String savesDirectory;
    private Query activeQuery;

    public Query getActiveQuery() {
        return activeQuery;
    }

    protected UserData(String savesDirectory, Query activeQuery) {
        this.savesDirectory = savesDirectory;
        this.activeQuery = activeQuery;
    }
}
