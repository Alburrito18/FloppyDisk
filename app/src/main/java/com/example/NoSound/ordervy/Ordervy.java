package com.example.NoSound.ordervy;

public class Ordervy {
    private String KundIDProfil;
    private String NamnProfil;
    private String DagensDatumProfil;

    public String getKundIDProfil() {
        return KundIDProfil;
    }

    public void setKundIDProfil(String kundIDProfil) {
        KundIDProfil = kundIDProfil;
    }

    public String getNamnProfil() {
        return NamnProfil;
    }

    public void setNamnProfil(String namnProfil) {
        NamnProfil = namnProfil;
    }

    public String getDagensDatumProfil() {
        return DagensDatumProfil;
    }

    public void setDagensDatumProfil(String dagensDatumProfil) {
        DagensDatumProfil = dagensDatumProfil;
    }

    /**
     * Contructor for the class Ordervy
     * @param KundIDProfil String representing customers order number
     * @param NamnProfil String representing customers name
     * @param DagensDatumProfil String representing the date
     */
    public Ordervy(String KundIDProfil, String NamnProfil, String DagensDatumProfil) {
        this.KundIDProfil = KundIDProfil;
        this.NamnProfil = NamnProfil;
        this.DagensDatumProfil = DagensDatumProfil;
    }
}
