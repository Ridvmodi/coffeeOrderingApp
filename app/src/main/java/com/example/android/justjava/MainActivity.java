package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;
    String nameOfTheCustomer;
    boolean hasWhippedCream = false, hasChocolate = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText Name = findViewById(R.id.name);
        nameOfTheCustomer = Name.getText().toString();

//        String message = "Name: " + nameOfTheCustomer + "\n";
        CheckBox whippedCream = findViewById(R.id.whipped_cream_checkbox);
        CheckBox chocolate = findViewById(R.id.chocolate_checkbox);
        hasWhippedCream = whippedCream.isChecked();
        hasChocolate = chocolate.isChecked();
        String message = "Toppings:";
        if(hasWhippedCream){
            message += " Whipped Cream";
        }
        if(hasChocolate) {
            message += " Chocolate";
        }
        message += "\nQuantity: " + quantity + "\n";

        message += "Total: $" + calculatePrice();
        message += "\nThank you!";
//        displayMessage(message);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for: " + nameOfTheCustomer);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if(intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * This method will calculate the order price
     */
    public int calculatePrice() {
        int price = 5;
        if(hasWhippedCream) {
            price += 1;
        }
        if(hasChocolate) {
            price += 2;
        }
        return quantity * price;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        if(quantity < 100)
        quantity++;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if(quantity > 0)
            quantity--;
        displayQuantity(quantity);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}