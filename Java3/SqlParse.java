
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class SqlParse {
    public static void main(String[] args) {
        saveRequestFromTravianServer("https://ts5.travian.ru/map.sql", "map.txt");
        System.out.println(createRequestForTmpTable("map.txt"));
    }

    public static void saveRequestFromTravianServer(String url, String fileName){
        try {
            URL website = new URL(url);
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream(fileName);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            rbc.close();
            fos.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static String createRequestForTmpTable(String filename) {
        String requestForCreate = null;
        try {
            FileInputStream inputStream = new FileInputStream(filename);
  //          while
        }catch (FileNotFoundException e){
            System.out.println("File not found");
        }catch (IOException e){
            e.getMessage();
        }

        return requestForCreate;
    }

}
