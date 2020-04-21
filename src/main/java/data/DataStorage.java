package data;

import java.util.HashMap;
import java.util.Map;

public class DataStorage {
    private static ThreadLocal<Map<String, Object>> storage = new ThreadLocal<>();

    public static Object getValue(String key){
        init();
        return storage.get().get(key);
    }

    public static void setValue(String key, Object value){
        init();
        storage.get().put(key, value);
    }

    private static void init(){
        if(storage.get() == null) {
            storage.set(new HashMap<>());
        }
    }
}
