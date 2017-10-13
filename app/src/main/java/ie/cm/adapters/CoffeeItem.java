package ie.cm.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;

import ie.cm.R;
import ie.cm.models.Coffee;

public class CoffeeItem {
    View view;

    public CoffeeItem(Context context, ViewGroup parent,
                      OnClickListener deleteListener, Coffee coffee) {

        // inflating the current row
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.coffeerow, parent, false);
        // setting the rows id to coffeeId for editing
        view.setId(coffee.coffeeId);

        updateControls(coffee);
        // ‘Tagging’ the Delete Image with a Coffee for Deleting
        ImageView imgDelete = (ImageView) view.findViewById(R.id.imgDelete);
        imgDelete.setTag(coffee);
        imgDelete.setOnClickListener(deleteListener);
    }

    // Updating the ‘Row’ with Coffee Data
    private void updateControls(Coffee coffee) {
        ((TextView) view.findViewById(R.id.rowCoffeeName)).setText(coffee.name);
        ((TextView) view.findViewById(R.id.rowCoffeeShop)).setText(coffee.shop);
        ((TextView) view.findViewById(R.id.rowPrice)).setText("€" + new DecimalFormat("0.00")
                .format(coffee.price));
        ((TextView) view.findViewById(R.id.rowRating)).setText("" + coffee.rating);

        ImageView imgIcon = (ImageView) view.findViewById(R.id.RowImage);

        if (coffee.favourite) {
            imgIcon.setImageResource(R.drawable.ic_favourite_on);
        } else {
            imgIcon.setImageResource(R.drawable.ic_favourite_off);
        }
    }
}
