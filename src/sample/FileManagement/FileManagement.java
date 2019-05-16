package sample.FileManagement;

import sample.Manager.DatabaseManager;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

public class FileManagement {

    private DatabaseManager databaseManager = DatabaseManager.getInstance();
    private PrintWriter printWriter;

    public FileManagement() throws FileNotFoundException, UnsupportedEncodingException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        printWriter = new PrintWriter(databaseManager.currentUser.getUsername() + ".txt", "UTF-8");
    }

    public void writeNewLine(String s) {
        printWriter.println(s);
    }

    public void closeWriter() {
        printWriter.close();
    }
}
