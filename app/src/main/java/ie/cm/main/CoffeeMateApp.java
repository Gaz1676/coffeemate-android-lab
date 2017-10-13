package ie.cm.main;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ie.cm.models.Coffee;

public class CoffeeMateApp extends Application
{
    public List <Coffee>  coffeeList = new ArrayList<Coffee>();

    @Override
    public void onCreate()
    {
        super.onCreate();
        Log.v("coffeemate", "CoffeeMate App Started");
    }
}