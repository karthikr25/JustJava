
/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import java.text.NumberFormat;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity=1,cost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCream=  findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCream.isChecked();

        CheckBox chocolate=  findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolate.isChecked();

        EditText objEditText = findViewById(R.id.name_EditText);
            String obj=  objEditText.getText().toString();



        createOrderSummary(hasWhippedCream,hasChocolate,obj);

    }

    public void createOrderSummary(boolean isWhipped,boolean hasChocolate,String obj)
    {   if(isWhipped==false && hasChocolate==false)
           cost=5;
        else if(isWhipped==false && hasChocolate==true)
            cost=7;
        else if(isWhipped==true && hasChocolate==false)
            cost=6;
        else
            cost=8;
        String priceMessage="Name: "+obj;
        priceMessage+= "\nAdd whipped cream: "+isWhipped;
        priceMessage+="\nChocolate: "+hasChocolate;
        priceMessage+=  "\nQuantity: "+quantity;
        priceMessage+="\nTotal: $"+cost*quantity;
        priceMessage+="\nThank You!";

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java Order for" +obj);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }



    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView =  findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    /**
     * This method displays the given price on the screen.

    private void displayPrice(int number) {
        TextView priceTextView =  findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }*/


    /**
     * This method displays the given text on the screen.
     */


    public void increment(View view)
    {
        if(quantity<=99)
        { quantity= quantity+1;
          display(quantity);}
        else
           display(100);
    }

    public void decrement(View view)
    {
       if(quantity>1) {
           quantity = quantity - 1;
           display(quantity);
       }
       else
            display(1);

    }


}
