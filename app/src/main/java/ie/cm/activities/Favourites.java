package ie.cm.activities;

import android.os.Bundle;

import ie.cm.R;
import ie.cm.fragments.CoffeeFragment;

public class Favourites extends Base {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favourites);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // gets a new Fragment instance
        // adds & replaces it to the current activity
        coffeeFragment = CoffeeFragment.newInstance();
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_layout, coffeeFragment)
                .commit();

    }
}