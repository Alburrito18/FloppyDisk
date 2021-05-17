package com.example.NoSound.OrderView;

public class OrderView {
    private String CustomerIDProfile;
    private String NameProfile;
    private String DateProfile;


    public String getCustomerIDProfile() {
        return CustomerIDProfile;
    }

    public void setCustomerIDProfile(String CustomerIDProfile) {
        CustomerIDProfile = CustomerIDProfile;
    }

    public String getNameProfile() {
        return NameProfile;
    }

    public void setNameProfile(String nameProfile) {
        NameProfile = nameProfile;
    }

    public String getDateProfile() {
        return DateProfile;
    }

    public void setDateProfile(String dateProfile) {
        DateProfile = dateProfile;
    }


    public OrderView(String CustomerIDProfile, String NameProfile, String DateProfile) {
        this.CustomerIDProfile = CustomerIDProfile;
        this.NameProfile = NameProfile;
        this.DateProfile = DateProfile;
    }
}
