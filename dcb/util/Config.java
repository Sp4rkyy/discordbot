package dcb.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Config {

    private static Properties properties;

    public Config(){
        // init blacklist if it hasn't happened before
        if(properties == null){
            System.out.println("[INFO] Init config");
            if(!initproperties()){
                System.out.println("[INFO] Init config failed");
            }
            // try updating config
            updateconfig();
        }
    }

    private boolean initproperties(){
        //Check if config file exist
        File configfile = new File("sys.config");
        if (!configfile.exists()) {
            //Create the file
            createconfigfile();
        }
        // load properties
        properties = new Properties();
        InputStream input;
        try {
            input = new FileInputStream("sys.config");
            properties.load(input);
            input.close();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private void createconfigfile(){
        properties = new Properties();

        properties.setProperty("activated", "false");
        properties.setProperty("bot_token", "");
        properties.setProperty("bot_command_indicator", "x!");
        properties.setProperty("bot_activate_modules", "false");
        properties.setProperty("bot_activate_coremodule", "false");
        properties.setProperty("bot_activate_coremodule_backgroundtask", "false");
        properties.setProperty("bot_status", "with humans");
        properties.setProperty("bot_admin_id", "");
        properties.setProperty("bot_sayhellotonew", "true");

        writetofile();
    }

    private void updateconfig(){
        // check if our config is updated to the latest version
        // ( all properties should be included, add them if not )

        // create list containing all properties
        HashMap<String, String> propcheck = new HashMap<String, String>();
        // add properies and values
        propcheck.put("activated","false");
        propcheck.put("bot_token","");
        propcheck.put("bot_command_indicator","x!");
        propcheck.put("bot_activate_modules","false");
        propcheck.put("bot_activate_coremodule","false");
        propcheck.put("bot_activate_coremodule_backgroundtask","false");
        propcheck.put("bot_status","with humans");
        propcheck.put("bot_admin_id","");
        propcheck.put("bot_sayhellotonew","true");
        // check if the properties from the list are in our config.
        for(Map.Entry<String, String> entry : propcheck.entrySet()){
            if(properties.getProperty(entry.getKey()) == null){
                // add property to properties
                properties.setProperty(entry.getKey(), entry.getValue());
            }
        }
        // write to file
        writetofile();
    }

    public boolean updateproperties(String property, String value){
        // Update property

        // check if property exists
        if(properties.getProperty(property) != null){
            // property exists
            properties.setProperty(property, value);
            // write to file
            writetofile();
        }
        return false;
    }

    private void writetofile(){
        try{
            properties.store(new FileOutputStream("sys.config"), null);
        }catch (Exception e){
            e.printStackTrace();
            System.exit(-1); //should quit; something is definitely wrong here
        }
    }

    public String load(String  property) {
        //get value from property
        String result = "";
        try {
            result = properties.getProperty(property);
        }catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public String version() {
        return 1;
    }
}