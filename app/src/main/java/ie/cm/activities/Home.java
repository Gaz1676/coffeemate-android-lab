package ie.cm.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import ie.cm.R;
import ie.cm.fragments.CoffeeFragment;
import ie.cm.models.Coffee;

public class Home extends Base {

    TextView recentList;


    // refactored onCreate() method to make use of a helper method (setupCoffees())

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.drawable.ic_launcher1);

        recentList = (TextView) findViewById(R.id.recentlyAddedListEmpty);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            // removed Eventlisteners settings
            // we now handle onClick() events in the layout (via the xml)
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Information", Snackbar.LENGTH_LONG)
                        .setAction("More Info...", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                openInfoDialog(Home.this);
                            }
                        }).show();
            }
        });
        setupCoffees();
    }

    public void add(View v) {
        goToActivity(this, Add.class, null);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!coffeeList.isEmpty())
            recentList.setText("");
        else
            recentList.setText(getString(R.string.recentlyViewedListEmptyMessage));

        coffeeFragment = CoffeeFragment.newInstance(); //get a new Fragment instance
        getFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_layout, coffeeFragment)
                .commit(); // add it to the current activity

    }

    // adds a few Coffee objects to static coffeeList
    // run the App again to confirm you're not seeing a blank screen anymore, but your coffees
    public void setupCoffees() {
        coffeeList.add(new Coffee("Standard Black", "Some Shop", 2.5, 1.99, false));
        coffeeList.add(new Coffee("Regular Joe", "Joe's Place", 3.5, 2.99, true));
        coffeeList.add(new Coffee("Espresso", "Ardkeen Stores", 4.5, 1.49, true));
    }
}
