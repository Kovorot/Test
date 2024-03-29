package project.data;

import project.dialog.Message;

import java.io.*;
import java.net.URLDecoder;

/**
 * Менеджер файлов, представляющий собой класс, выполняющий всю input-output работу для сохранения и загрузки данных.
 */
public class FileManager {
    //region fields
    /**
     * Объект класса {@link Message}, для взаимодействия с юзером.
     */
    private final Message message;
    /**
     * Разделитель, использующийся для отделения различных данных в файликах.
     */
    private final String delimiter = " -//-: ";
    /**
     * Путь к папке, в которой находится исполняемый файл программы.
     */
    private String appPath;
    /**
     * Путь к файлу, в котором находится информация обо всех юзерах, пользовавшихся программой в виде пар: имя пользователя - путь к папке с запросами.
     */
    private String userInfoPath;
    /**
     * Название последнего сохраненного и открытого юзером запроса.
     */
    private String lastQueryName;
    /**
     * Путь к папке с сохраненными запросами юзера.
     */
    private String userSavesDirectory;
    /**
     * Имя пользователя в системе.
     */
    private String username;
    //endregion

    //region public
    public void saveQuery(Query query) {

    }

    public void setSavesDirectory(String path) {
        String[] parts = findUserInfoData();
        BufferedWriter br;
        // TODO: 12.03.2024 Заменить текстовый файл на xml
    }

    public void removeSavesDirectory() {
        setSavesDirectory("");
    }
    //endregion

    //region protected
    protected FileManager() {
        message = Message.getInstance();
        try {
            appPath = new File(URLDecoder.decode(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath(), "utf-8")).getParent();
        } catch (Exception e) {
            message.showUnprocessedException(e);
        }
        userInfoPath = appPath + "/userInfo.txt";
        username = System.getProperty("user.name");
        String[] userData = loadUserSavesData();

        if (userData != null) {
            userSavesDirectory = userData[1];
            lastQueryName = userData[2];
        }
        checkRootLegality();
    }

    protected UserData loadUserData() {

        if (new File(userSavesDirectory + "/" + lastQueryName + ".dat").exists()) {

        }
        return null;
    }
    //endregion

    //region private

    /**
     * Загружает путь к директории с сохраненными запросами, указанный юзером.
     * @return Массив данных юзера.
     */
    private String[] loadUserSavesData() {
        File usersInfo = new File(userInfoPath);

        if (usersInfo.exists()) {
            return findUserInfoData();
        } else {
            try {
                if (!usersInfo.createNewFile()) {
                    message.showUnprocessedException();
                }
            } catch (IOException e) {
                message.showUnprocessedException(e);
            }
        }
        return null;
    }

    /**
     * Ищет юзера и директорию с сохраненными запросами, указанную им в файле с данными о всех юзерах.
     * @Return Массив данных юзера.
     */
    private String[] findUserInfoData() {

        try (Reader reader = new FileReader(userInfoPath);
             BufferedReader bufferedReader = new BufferedReader(reader)) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(delimiter);
                if (parts[0].equals(username)) return parts;
            }
        } catch (IOException e) {
            message.showUnprocessedException(e);
        }
        return null;
    }

    /**
     * Проверяет корректность пути к папке с сохраненными запросами юзера. Если путь будет некорректный, метод удалит его из сохраненных путей.
     * Это необходимо для того, чтобы юзер не мог засрать общий диск и хранил свои запросы только у себя на диске C.
     */
    private void checkRootLegality() {

        if (userSavesDirectory != null && !userSavesDirectory.startsWith("C:")) {
            message.warn("В пути к папке с сохраненными запросами обнаружена ошибка.\n" +
                    "Данная папка может находиться только на диске C. Текущий путь будет удален.");
            userSavesDirectory = null;
        }
    }
    //endregion
}
