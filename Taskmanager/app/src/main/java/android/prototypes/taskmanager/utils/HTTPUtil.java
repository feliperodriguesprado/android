package android.prototypes.taskmanager.utils;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class HTTPUtil {

    public static String get (String urlStr) {

        URL url;
        URLConnection urlConnection;
        InputStream inputStream;
        Scanner scanner = null;
        String content = null;

        try {
            url = new URL(urlStr);
            urlConnection = url.openConnection();
            inputStream = urlConnection.getInputStream();
            scanner = new Scanner(inputStream);
            content = scanner.useDelimiter("\\A").next();
            scanner.close();
        } catch (Exception e) {
            if (scanner != null) {
                scanner.close();
            }
        }

        return content;
    }

}
