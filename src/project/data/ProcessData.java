package project.data;

import project.exception.InitializationException;

public class ProcessData {
    private static ProcessData instance;
    private final FileManager fileManager;
    private final UserData userData;

    public static void init() {
        instance = new ProcessData();
    }

    public static ProcessData getInstance() {
        if (instance == null) throw new InitializationException("Объект ProcessData не был инициализирован");
        return instance;
    }

    public Query getCurrentQuery() {
        return userData.getActiveQuery();
    }

    public FileManager getFileManager() {
        return fileManager;
    }

    private ProcessData() {
        fileManager = new FileManager();
        userData = fileManager.loadUserData();
    }
}
