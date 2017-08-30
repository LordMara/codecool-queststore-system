import java.util.Scanner;
import java.nio.file.NoSuchFileException;

public abstract class AbstractDAO<T> implements DAOInterface<T> {
    String path;
    ArrayList objectsList;

    public void loadObject() {
        try (Scanner readFile = new Scanner(new File(this.path))) {
            this.objectsList.add(readFile.nextLine());
        } catch (NoSuchFileException e) {
            System.out.println("This file not exist in choosen directory");
            e.printStackTrace();
        }
    }

    public void saveObject(T object) {

    }
}