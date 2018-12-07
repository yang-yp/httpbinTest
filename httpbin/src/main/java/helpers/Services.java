package helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Services {
    public static final Logger LOG = LoggerFactory.getLogger(Services.class);
    private static String filePath = System.getProperty("beats.config.file.location");
    private static Map<String, String> services = getPropertyMap();

    public Services() {
    }

    public static String get(String key) {
        return (String)services.get(key);
    }

    public static Map<String, String> getPropertyMap() {
        HashMap services = new HashMap();
        File file = new File(filePath + "config.properties");
        FileInputStream fileInput = null;

        try {
            fileInput = new FileInputStream(file);
            Properties e = new Properties();
            e.load(fileInput);
            fileInput.close();
            Enumeration enuKeys = e.keys();

            while(enuKeys.hasMoreElements()) {
                String key = (String)enuKeys.nextElement();
                String value = e.getProperty(key);
                services.put(key, value);
            }
        } catch (FileNotFoundException var7) {
            var7.printStackTrace();
        } catch (IOException var8) {
            var8.printStackTrace();
        }

        Map services1 = getSysProperties(services);
        services1 = getTestRunConfig(services1);
        return services1;
    }

    private static Map<String, String> getSysProperties(Map<String, String> currentServices) {
        Iterator i$ = currentServices.entrySet().iterator();

        while(i$.hasNext()) {
            Entry entry = (Entry)i$.next();
            String key = (String)entry.getKey();
            if(System.getProperty(key) != null) {
                currentServices.put(key, System.getProperty(key));
            }
        }

        return currentServices;
    }

    private static Map<String, String> getTestRunConfig(Map<String, String> currentServices) {
        Iterator i$ = currentServices.entrySet().iterator();

        while(i$.hasNext()) {
            Entry entry = (Entry)i$.next();
            String key = (String)entry.getKey();
            if(((String)currentServices.get(key)).equals("baseUrl")) {
                String value = (String)currentServices.get("baseUrl");
                currentServices.put(key, value);
            }
        }

        return currentServices;
    }
}
