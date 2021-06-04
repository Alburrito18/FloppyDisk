package com.example.NoSound;

import com.example.NoSound.BusinessView.BusinessData;

import java.io.IOException;

/**
 * Listener for passing data from a fragment to an activity
 */
public interface OnDataPass {
    public void onDataPass(BusinessData businessData);
    public void onEmployeePass(Employee employee) throws IOException;
    public void onPrefixPass(String city);
}
