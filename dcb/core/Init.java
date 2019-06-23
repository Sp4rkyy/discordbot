package dcb.core;

import dcb.util.BlackListUtility;
import dcb.util.Config;

public class Init {

    public static void main(String[] args){
        //prepare all files
        Config config = new Config();
        new BlackListUtility();

        if(Boolean.parseBoolean(config.load("activated"))){
            new Thread(new XCore()).start();
        }
    }
}
