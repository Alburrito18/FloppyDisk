package com.example.NoSound;

/**
 * Listener for passing data from a fragment to an activity
 */
public interface OnDataPass {
    /**
     * Passes info about customer name and ID
     * @param customerName a String representing the customers company name
     * @param customerID a String representing the customers unique ID
     */
    public void onDataPass(String customerName, String customerID);
}
