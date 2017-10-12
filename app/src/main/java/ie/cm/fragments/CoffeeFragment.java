package ie.cm.fragments;

import android.app.AlertDialog;
import android.app.ListFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import ie.cm.R;
import ie.cm.activities.Base;
import ie.cm.activities.Edit;
import ie.cm.adapters.CoffeeListAdapter;
import ie.cm.models.Coffee;

public class CoffeeFragment extends ListFragment implements OnClickListener, AbsListView.MultiChoiceModeListener {
    protected Base activity;
    protected static CoffeeListAdapter listAdapter;
    protected ListView listView;

    public CoffeeFragment() {
        // Required empty public constructor
    }

    public static CoffeeFragment newInstance() {
        return new CoffeeFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (Base) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Notice that the OnClickListener parameter, is a reference to the class itself (this)
        // so the CoffeeFragment class needs to implement this interface
        listAdapter = new CoffeeListAdapter(activity, this, Base.coffeeList);
        setListAdapter(listAdapter);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, parent, savedInstanceState);

        listView = (ListView) v.findViewById(android.R.id.list);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(this);

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onClick(View view) {
        if (view.getTag() instanceof Coffee) {
            onCoffeeDelete((Coffee) view.getTag());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ((CoffeeListAdapter) getListAdapter()).notifyDataSetChanged();
    }

    @Override // this will execute every time a user selects a single row (or listItem) in the list
    public void onListItemClick(ListView l, View v, int position, long id) {
        Bundle activityInfo = new Bundle(); // Creates a new Bundle object
        activityInfo.putInt("coffeeID", v.getId()); //Passes in Coffees Id here
        Intent goEdit = new Intent(getActivity(), Edit.class); // Creates a new Intent
        goEdit.putExtras(activityInfo); // Added Bundle to the Intent
        getActivity().startActivity(goEdit); // Launch the Intent
    }

    // Here we use the Views 'Tag' to see if it's a coffee, and if it is, that's the one we delete.
    public void onCoffeeDelete(final Coffee coffee) {
        String stringName = coffee.name;
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage("Are you sure you want to Delete the \'Coffee\' " + stringName + "?");
        builder.setCancelable(false);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            //  this is an important piece of the puzzle.
            public void onClick(DialogInterface dialog, int id) {
                Base.coffeeList.remove(coffee); // remove from our list
                listAdapter.coffeeList.remove(coffee); // update adapters data
                listAdapter.notifyDataSetChanged(); // refresh adapter
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    /* ************ MultiChoiceModeListener methods (begin) *********** */
    @Override
    public boolean onCreateActionMode(ActionMode actionMode, Menu menu)
    {
        MenuInflater inflater = actionMode.getMenuInflater();
        inflater.inflate(R.menu.delete_list_context, menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu)
    {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem)
    {
        switch (menuItem.getItemId())
        {
            case R.id.menu_item_delete_coffee:
                deleteCoffees(actionMode);
                return true;
            default:
                return false;
        }

    }

    private void deleteCoffees(ActionMode actionMode)
    {
        for (int i = listAdapter.getCount() - 1; i >= 0; i--)
        {
            if (listView.isItemChecked(i))
            {
                Base.coffeeList.remove(listAdapter.getItem(i));
            }
        }
        actionMode.finish();
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyActionMode(ActionMode actionMode)
    {
    }

    @Override
    public void onItemCheckedStateChanged(ActionMode actionMode, int position, long id, boolean checked)
    {
    }

  /* ************ MultiChoiceModeListener methods (end) *********** */
}