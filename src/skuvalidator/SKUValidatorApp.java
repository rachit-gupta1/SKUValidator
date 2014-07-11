/*
 * SKUValidatorApp.java
 */

package skuvalidator;

import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class SKUValidatorApp extends SingleFrameApplication {

    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
        show(new SKUValidatorView(this));
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of SKUValidatorApp
     */
    public static SKUValidatorApp getApplication() {
        return Application.getInstance(SKUValidatorApp.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        launch(SKUValidatorApp.class, args);
        DbHandler handler = new DbHandler();
        handler.createEntry("http://indfas.alice/", "men", "abc,def,ghi,jkl,mno,pqr,stu,vwx,yz");
    }
}
