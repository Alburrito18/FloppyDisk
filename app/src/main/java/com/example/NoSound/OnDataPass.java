package com.example.NoSound;

import com.example.NoSound.BusinessView.BusinessData;

/**
 * Listener for passing data from a fragment to an activity
 */
public interface OnDataPass {
    public void onDataPass(BusinessData businessData);
    public void onEmployeePass(Employee employee);
}
