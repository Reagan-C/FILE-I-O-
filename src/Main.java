import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class Main {


    public static void main(String[] args) {
        String fileToRead;
        String environment = "";

        if(args.length == 1) {
            for (String arg : args) {
                environment = arg;
            }
        } else if (args.length > 1) {
            System.out.println("Argument passed is more than required");
            return;
        } else {
            environment = "production";
        }
        System.out.println("Welcome to my App");
        System.out.println("Your Environment : " + environment);

        if (environment.equalsIgnoreCase("production")) {
            fileToRead = "config.txt";
        } else if (environment.equalsIgnoreCase("development")) {
            fileToRead = "config.txt.dev";
        } else if (environment.equalsIgnoreCase("staging")) {
            fileToRead = "config.txt.staging";
        } else {
            System.out.println("Environment does not exist, please try again");
            return;
        }

        System.out.println("Filename = " + fileToRead);
        ConfigParser config = new ConfigParser();
        System.out.println(config.getValue("dbname"));
        System.out.println(config.getValue("application.name"));
    }
    public static class ConfigParser {
        private final String fileToRead;
        Map<String, String> keyValuePairs = new HashMap<>();

        public ConfigParser(String fileToRead) {
            this.fileToRead = fileToRead;
            readToMap();
        }

        public ConfigParser() {
            fileToRead = "config.txt";
            readToMap();
        }

        public String getValue (String key) {
            if (key != null) {
                return keyValuePairs.get(key);
            }
            return "No value exists for the given key";
        }

        private void readToMap() {
            String[] myList = new String[]{};
//            Try with resources employed while reading data from the config file
            try (BufferedReader myReader = new BufferedReader(new FileReader(fileToRead))) {
                String keys ;
                while ((keys = myReader.readLine()) != null) {

                    if (keys.contains("=")) {
                        myList = keys.split("=");
                    }
                    if (myList.length > 1) {
                        if (myList[0].equals("name")) {
                            myList[0] = "application." + myList[0];
                        }
                        if (myList[0].equals("port")) {
                            myList[0] = "application." + myList[0];
                        }
                        if (myList[0].equals("context-url")) {
                            myList[0] = "application." + myList[0];
                        }
                        keyValuePairs.putIfAbsent(myList[0], myList[1]);
                    }
                }
                System.out.println(keyValuePairs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}