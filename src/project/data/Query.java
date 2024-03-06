package project.data;

import java.io.Serializable;

public class Query implements Serializable {
    private boolean modified;

    public boolean isModified() {
        return modified;
    }
}
