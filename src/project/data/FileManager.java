package project.data;

import project.dialog.Message;

import java.io.*;
import java.net.URLDecoder;
import java.util.Optional;

/**
 * Менеджер файлов, представляющий собой класс, выполняющий всю input-output работу для сохранения и загрузки данных.
 */
public class FileManager {
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


    public void saveQuery(Query query) {

    }

    protected FileManager() {
        message = Message.getInstance();
        try {
            appPath = new File(URLDecoder.decode(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath(), "utf-8")).getParent();
        } catch (Exception e) {
            message.showUnprocessedException(e);
        }
        userInfoPath = appPath + "/userInfo.txt";
        String[] userData = loadUserSavesData();
        String userSavesDirectory;

        if (userData != null) {
            userSavesDirectory = userData[1];
            lastQueryName = userData[2];
        }
    }

    protected UserData loadUserData() {

    }

    //region private

    /**
     * Загружает путь к директории с сохраненными запросами, указанный юзером.
     * @return Массив данных юзера.
     */
    private String[] loadUserSavesData() {
        File usersInfo = new File(userInfoPath);

        if (usersInfo.exists()) {
            return findUserData();
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
    private String[] findUserData() {

        try (Reader reader = new FileReader(userInfoPath);
             BufferedReader bufferedReader = new BufferedReader(reader)) {
            String userName = System.getProperty("user.name");
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(delimiter);
                if (parts[0].equals(userName)) return parts;
            }
        } catch (IOException e) {
            message.showUnprocessedException(e);
        }
        return null;
    }

    //endregion
}
