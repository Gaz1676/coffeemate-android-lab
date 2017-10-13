package ie.cm.activities;

import android.os.Bundle;

import ie.cm.R;
import ie.cm.fragments.SearchFragment;

public class Search extends Base {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // gets a new Fragment instance
        // adds & replaces it to the current activity
        coffeeFragment = SearchFragment.newInstance();
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_layout, coffeeFragment)
                .commit();

    }
}