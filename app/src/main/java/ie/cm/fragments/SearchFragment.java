package ie.cm.fragments;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import ie.cm.R;

public class SearchFragment extends CoffeeFragment implements AdapterView.OnItemSelectedListener, TextWatcher {


    public SearchFragment() {
        // Required empty public constructor
    }

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public void onAttach(Context c) {
        super.onAttach(c);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter
                .createFromResource(getActivity(), R.array.coffeeTypes,
                        android.R.layout.simple_spinner_item);

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = ((Spinner) activity.findViewById(R.id.searchCoffeeTypeSpinner));
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(this);

        EditText nameText = (EditText) activity.findViewById(R.id.searchCoffeeNameEditText);
        nameText.addTextChangedListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selected = parent.getItemAtPosition(position).toString();

        if (selected != null) {
            if (selected.equals("All Types")) {
                coffeeFilter.setFilter("all");
            } else if (selected.equals("Favourites")) {
                coffeeFilter.setFilter("favourites");
            }
            coffeeFilter.filter("");
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        coffeeFilter.filter(s);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}