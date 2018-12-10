/*@Author Lorenzo Pedroza
*
* */

package com.csee_idea_coin_dispenser.csee.dbti.bluetoothcoindispenser;

public enum UnitedStatesCoinCurrency {
    HALF_DOLLAR(0.50),
    QUARTER(0.25),
    DIME(0.10),
    NICKEL(0.05),
    PENNEY(0.01);
    //https://stackoverflow.com/questions/8811815/is-it-possible-to-assign-numeric-value-to-an-enum-in-java

    public final double VALUE_IN_USD;

    UnitedStatesCoinCurrency(double VALUE_IN_USD){
        this.VALUE_IN_USD = VALUE_IN_USD;
    }
}
