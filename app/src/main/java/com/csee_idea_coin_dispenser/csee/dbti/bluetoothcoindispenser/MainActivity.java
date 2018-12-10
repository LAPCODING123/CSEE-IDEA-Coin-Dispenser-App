/**@author
 * - Front End: Aman,  Lorenzo Pedroza...
 * - Hardware Software Integration: Lorenzo Pedroza
 * - Back End: Lorenzo Pedroza
 *
 */

package com.csee_idea_coin_dispenser.csee.dbti.bluetoothcoindispenser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Currency;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    //TODO maybe put textviews to show amount in dispener? yeah I should
    private ImageButton addQuarterButton, subtractQuarterButton, addNickelButton, subtractNickelButton,
            addDimeButton, subtactDimeButton;
    private Button dispenseButton;
    private EditText amountToDispenseEditText;
    private TextView quartersToDispenseTextView, nickelsToDispenseTextView, dimesToDispenseTextView,
            quartersInDispesnerTextView, nickelsInDispenserTextView, dimesInDispenserTextView;
    private static int quartersInDispenser = 0; //only one dispenser so static
    private static int nickelsInDispenser = 0;
    private static int dimesInDispenser = 0;
    private int quartersToDispense = 0;
    private int nickelsToDispense = 0;
    private int dimesToDispense = 0;
    private double amountToDispense = 0.0;
    private boolean customCoinAmount = false; //by default uses algorithm to deposit most available coin
    private double oldAmount;
    private DecimalFormat decimalFormat2Places = new DecimalFormat();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        decimalFormat2Places.setCurrency(Currency.getInstance(Locale.US));

        addQuarterButton = findViewById(R.id.addQuarterButtonID);
        subtractQuarterButton = findViewById(R.id.subtactQuarterButtonID);
        addNickelButton = findViewById(R.id.addNickelButtonID);
        subtractNickelButton = findViewById(R.id.subtractNickelButtonID);
        addDimeButton = findViewById(R.id.addDimeButtonID);
        subtactDimeButton = findViewById(R.id.subtractDimeButtonID);
        dispenseButton = findViewById(R.id.dispenseButtonID);
        amountToDispenseEditText = findViewById(R.id.amountToDispenseEditTextID);

        quartersToDispenseTextView = findViewById(R.id.amountOfQuartersToDispenseTextViewID);
        dimesToDispenseTextView = findViewById(R.id.amountOfDimesToDispenseTextViewID);
        nickelsToDispenseTextView = findViewById(R.id.amountOfNickelsToDispenseTextViewID);

        quartersInDispesnerTextView = findViewById(R.id.quartersInDispenserTextViewID);
        dimesInDispenserTextView = findViewById(R.id.dimesInDispenserTextViewID);
        nickelsInDispenserTextView = findViewById(R.id.nickelsInDispenserTextViewID);

        //check coins in dispenser(through Bluetooth of course)//TODO add Bluetooth functionality. Add listener to update coin amounts in dispenser
        quartersInDispenser = 3;
        nickelsInDispenser = 3;
        dimesInDispenser = 3;

        quartersInDispesnerTextView.setText(String.valueOf((int)formatToUSDDecPlaces((55.30 -
                formatToUSDDecPlaces(55.30%UnitedStatesCoinCurrency.DIME.VALUE_IN_USD))
                /UnitedStatesCoinCurrency.DIME.VALUE_IN_USD))); //should yield dimes to dispense
        nickelsInDispenserTextView.setText(decimalFormat2Places.getCurrency().getDisplayName());
        dimesInDispenserTextView.setText(String.valueOf(formatToUSDDecPlaces(0.5%0.1))); //TODO probably need to multiply values to whole numbers as decimal modulus too inacuarte



        updateAmountToDispense();

        //make visible and invisible the buttons as needed on start up
        validateAddingCoins(quartersToDispense, quartersInDispenser, addQuarterButton);
        validateSubtractingCoins(quartersToDispense, quartersInDispenser, subtractQuarterButton);
        validateAddingCoins(nickelsToDispense, nickelsInDispenser, addNickelButton);
        validateSubtractingCoins(nickelsToDispense, nickelsInDispenser, subtractNickelButton);
        validateAddingCoins(dimesToDispense, dimesInDispenser, addDimeButton);
        validateSubtractingCoins(dimesToDispense, dimesInDispenser, subtactDimeButton);

        addQuarterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCoins(quartersToDispense, quartersInDispenser, addQuarterButton);
                updateAmountToDispense();
            }
        });
/*
        subtractQuarterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subtractCoins(quartersToDispense, quartersInDispenser, subtractQuarterButton);
                updateAmountToDispense();
            }
        });

        addNickelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCoins(nickelsToDispense, nickelsInDispenser, addNickelButton);
                updateAmountToDispense();
            }
        });

        subtractNickelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subtractCoins(nickelsToDispense, nickelsInDispenser, subtractNickelButton);
                updateAmountToDispense();
            }
        });

        addDimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCoins(dimesToDispense, dimesInDispenser, addDimeButton);
                updateAmountToDispense();
            }
        });

        subtactDimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subtractCoins(dimesToDispense, dimesInDispenser, addDimeButton);
                updateAmountToDispense();
            }
        });

        dispenseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amountToDispense == 0.0)
                    Toast.makeText(MainActivity.this, "Nothing to dispense", Toast.LENGTH_LONG).show();
                /*
                else
                    //dispense

            }
        });

        //https://stackoverflow.com/questions/7391891/how-to-check-if-an-edittext-was-changed-or-not/7391925
        amountToDispenseEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                oldAmount = Double.parseDouble(amountToDispenseEditText.getText().toString()); //capture old amount
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                customCoinAmount = false;
            }

            @Override
            public void afterTextChanged(Editable s) {
                captureInputtedAmountToDispense();
                //update coin amounts
                //maybe if equal amounts: randomize or default to quarter. Lorenzo says default to quarter
                if (quartersInDispenser > (nickelsInDispenser) && quartersInDispenser > dimesInDispenser )
                    modifyCoinAmountToDesiredCoinAmount(quartersToDispense);
                else if (nickelsInDispenser > dimesInDispenser && nickelsInDispenser > quartersInDispenser)
                    modifyCoinAmountToDesiredCoinAmount(nickelsToDispense);
                else if (dimesInDispenser > nickelsInDispenser && dimesInDispenser > quartersInDispenser)
                    modifyCoinAmountToDesiredCoinAmount(dimesToDispense);

                updateAmountToDispense();
            }
        });
*/
    }

    private void captureInputtedAmountToDispense(){
        double inputtedAmount;

        if(!amountToDispenseEditText.getText().toString().equals(""))
        {
            inputtedAmount = Double.parseDouble(amountToDispenseEditText.getText().toString());
            if (inputtedAmount%5 != 0)//cannot dispense pennies
            {
                Toast.makeText(MainActivity.this, "Dispenser does not dispense pennies", Toast.LENGTH_LONG).show();
                amountToDispenseEditText.setText(Double.toString(oldAmount)); //clear invalid entry
                return;
            }
            customCoinAmount = true;
            amountToDispense = inputtedAmount;
        }
        else
            Toast.makeText(MainActivity.this, "Please enter an amount", Toast.LENGTH_LONG).show();
    }

    private void addCoins(int amountOfCoinsToDispense, int typeOfThatCoinInDispenser, ImageButton additionButton){ //Maybe need to make synchronized?
        if(validateAddingCoins(amountOfCoinsToDispense, typeOfThatCoinInDispenser, additionButton))
        {
            amountOfCoinsToDispense++;
            customCoinAmount = true;
        }
    }

    private void subtractCoins(int amountOfCoinsToDispense, int typeOfThatCoinInDispenser, ImageButton subtractionButton){

        if(validateSubtractingCoins(amountOfCoinsToDispense, typeOfThatCoinInDispenser, subtractionButton))
        {
            amountOfCoinsToDispense--;
            customCoinAmount = true;
        }
    }

    private boolean validateAddingCoins(int amountOfCoinsToDispense, int typeOfThatCoinInDispenser, ImageButton additionButton){
        if(amountOfCoinsToDispense > typeOfThatCoinInDispenser+1) //antacipate it will be a valid addition
        {
            additionButton.setVisibility(View.INVISIBLE);
            return false;
        }
        else
        {
            additionButton.setVisibility(View.VISIBLE);
            return true;
        }
    }

    private boolean validateSubtractingCoins(int amountOfCoinsToDispense, int typeOfThatCoinInDispenser, ImageButton subtractionButton){
        if (amountOfCoinsToDispense < 1)  //antacpitate it will be a valid subtaction
        {
            subtractionButton.setVisibility(View.INVISIBLE);
            return false;
        }
        else{
            subtractionButton.setVisibility(View.VISIBLE);
            return true;
        }
    }

    private void modifyCoinAmountToDesiredCoinAmount(int desiredCoinAmount, UnitedStatesCoinCurrency coinType)
    {
        customCoinAmount = true;
        //could be recursive? like subract until desired amount is 0
        double remainder = 0.0;
        double totalDesiredToBeDispensed = amountToDispense;
        if (coinType == UnitedStatesCoinCurrency.QUARTER)
        {
            totalDesiredToBeDispensed  = totalDesiredToBeDispensed - UnitedStatesCoinCurrency.QUARTER.VALUE_IN_USD*desiredCoinAmount;
            if(dimesInDispenser > nickelsInDispenser)
            {
                dimesToDispense = (int)(totalDesiredToBeDispensed/10 - totalDesiredToBeDispensed%10); //Get dimes to dispense;
                totalDesiredToBeDispensed = totalDesiredToBeDispensed%10;
                nickelsToDispense = (int)(totalDesiredToBeDispensed/5);
                totalDesiredToBeDispensed = totalDesiredToBeDispensed%5;
            }

            else
            {
                totalDesiredToBeDispensed = totalDesiredToBeDispensed%5;
                dimesToDispense = (int)(totalDesiredToBeDispensed/5 - totalDesiredToBeDispensed%5); //Get dimes to dispense;
                totalDesiredToBeDispensed = totalDesiredToBeDispensed%10;
                nickelsToDispense = (int)(totalDesiredToBeDispensed/10);
            }
        }

        else if (desiredCoinAmount == nickelsToDispense)
        {
            totalDesiredToBeDispensed  = totalDesiredToBeDispensed - 0.05*desiredCoinAmount;
            if(dimesInDispenser > quartersInDispenser)
            {
                dimesToDispense = (int)(totalDesiredToBeDispensed/10 - totalDesiredToBeDispensed%10); //Get dimes to dispense;
                totalDesiredToBeDispensed = totalDesiredToBeDispensed%10;
                quartersToDispense = (int)(totalDesiredToBeDispensed/25);
                totalDesiredToBeDispensed = totalDesiredToBeDispensed%25;
            }

            else
            {
                totalDesiredToBeDispensed = totalDesiredToBeDispensed%25;
                quartersToDispense = (int)(totalDesiredToBeDispensed/25 - totalDesiredToBeDispensed%25); //Get dimes to dispense;
                totalDesiredToBeDispensed = totalDesiredToBeDispensed%10;
                dimesToDispense = (int)(totalDesiredToBeDispensed/10);
            }
        }

        else if (desiredCoinAmount == dimesToDispense)
        {
            totalDesiredToBeDispensed  = totalDesiredToBeDispensed - 0.10*desiredCoinAmount;
            totalDesiredToBeDispensed  = totalDesiredToBeDispensed - 0.05*desiredCoinAmount;
            if(nickelsInDispenser > quartersInDispenser)
            {
                nickelsToDispense = (int)(totalDesiredToBeDispensed/5 - totalDesiredToBeDispensed%5); //Get dimes to dispense;
                totalDesiredToBeDispensed = totalDesiredToBeDispensed%5;
                quartersToDispense = (int)(totalDesiredToBeDispensed/25);
                totalDesiredToBeDispensed = totalDesiredToBeDispensed%25;
            }

            else
            {
                totalDesiredToBeDispensed = totalDesiredToBeDispensed%25;
                quartersToDispense = (int)(totalDesiredToBeDispensed/25 - totalDesiredToBeDispensed%25); //Get dimes to dispense;
                totalDesiredToBeDispensed = totalDesiredToBeDispensed%5;
                nickelsToDispense = (int)(totalDesiredToBeDispensed/5);
            }
        }
    }

    private void updateAmountToDispense(){
        amountToDispense = 0.25*quartersToDispense + 0.05*nickelsToDispense + 0.10*dimesToDispense;
        amountToDispenseEditText.setText(Double.toString(amountToDispense)); //In future release follow the advice of this warning
        quartersToDispenseTextView.setText(Integer.toString(quartersToDispense));
        nickelsToDispenseTextView.setText(Integer.toString(nickelsToDispense));
        dimesToDispenseTextView.setText(Integer.toString(dimesToDispense));

    }

    private double formatToUSDDecPlaces(double doubleValue){
        return Double.parseDouble(decimalFormat2Places.format(doubleValue));
    }

    /*
    private int coinsToDispenseFromTotal(double total, UnitedStatesCoinCurrency coin){
        return (int)
    }
    */


}
