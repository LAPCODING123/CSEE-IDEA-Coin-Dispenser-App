/**@author
 * - Front End: Aman...
 * - Hardware Software Integration: Lorenzo Pedroza
 * - Back End: Lorenzo Pedroza
 *
 */

package com.csee_idea_coin_dispenser.csee.dbti.bluetoothcoindispenser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private Button addQuarterButton, subtractQuarterButton, addNickelButton, subtractNickelButton,
            addDimeButton, subtactDimeButton;
    private EditText amountToDispenseEditText;
    private static int quartersInDispenser = 0;
    private static int nickelsInDispenser = 0;
    private static int dimesInDispenser = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
