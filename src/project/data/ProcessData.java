package project.data;

import java.util.Optional;

public class ProcessData {
    //region fields
    /**
     * Единственный и неповторимый экземпляр класса.
     */
    private static final ProcessData instance;
    /**
     * Объект менеджера файлов.
     */
    private final FileManager fileManager;
    /**
     * Данные пользователя.
     */
    private final UserData userData;
    //endregion

    static {
        instance = new ProcessData();
    }

    //region public
    /**
     * Возвращает единственный экземпляр данного класса.
     * @return Возвращает экземпляр.
     */
    public static ProcessData getInstance() {
        return instance;
    }

    /**
     * Возвращает текущий запрос юзера в обертке {@link Optional}, так как запрос может отсутствовать.
     * @return {@link Optional} обертка запроса юзера.
     */
    public Optional<Query> getCurrentQuery() {
        return Optional.ofNullable(userData.getActiveQuery());
    }

    /**
     * Передает менеджер файлов, который доступен только из этого класса.
     * @return Единственный экземпляр класса {@link FileManager}.
     */
    public FileManager getFileManager() {
        return fileManager;
    }
    //endregion

    //region private
    /**
     * Единственный конструктор данного класса, инициализирующий так же основные компоненты рабочего процесса программы.
     */
    private ProcessData() {
        fileManager = new FileManager();
        userData = fileManager.loadUserData();
    }
    //endregion
}
