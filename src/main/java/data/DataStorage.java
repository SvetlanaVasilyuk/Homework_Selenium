package data;

public class DataStorage {
    private static DataStorage instance;
    private int count;

    private DataStorage() {
    }

    public static synchronized DataStorage getInstance() {
        if (instance == null) {
            instance = new DataStorage();
        }
        return instance;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }
}
