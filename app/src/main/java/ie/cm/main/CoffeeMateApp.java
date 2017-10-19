package ie.cm.main;

import android.app.Application;
import android.util.Log;

import ie.cm.db.DBManager;

public class CoffeeMateApp extends Application {
    //public List <Coffee>  coffeeList = new ArrayList<Coffee>();
    public DBManager  dbManager = new DBManager(this);

    @Override
    public void onCreate()
    {
        super.onCreate();
        Log.v("coffeemate", "CoffeeMate App Started");
        dbManager.open();
    }

    @Override
    public void onTerminate()
    {
        super.onTerminate();
        dbManager.close();
    }
}