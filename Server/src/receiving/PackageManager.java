package receiving;

import java.io.DataInputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vlusslus on 31.07.2017.
 */
public class PackageManager {

    private static Map<Short,Class<? extends SendingPackage>> packages = new HashMap<>();

    static {
        packages.put((short) 1, AuthorizingPackage.class);
    }

    public static SendingPackage getPackage(short id, DataInputStream dis) {
        try {
            return packages.get(id).newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (InstantiationException e) {
            e.printStackTrace();
            return null;
        }
    }
}
