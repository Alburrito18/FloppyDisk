package com.example.NoSound;

import com.example.NoSound.Business.BusinessData;

/**
 * Listener for passing data from a fragment to an activity
 */
public interface OnDataPass {
    public void onDataPass(BusinessData businessData,String orderID);
    public void onEmployeePass(Employee employee);
}
