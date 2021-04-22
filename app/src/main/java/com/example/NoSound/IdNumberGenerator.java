package com.example.NoSound;

public class IdNumberGenerator {
    private String Identifier;

    private String getIteration() {
        return null;
    }

    private void saveIteration(int input) {
        String toBeSaved = String.valueOf(input);
    }

    public String generateIdNumber() {
        String iter = getIteration();
        int newIter = Integer.parseInt(iter);
        newIter++;
        saveIteration(newIter);
        return Identifier + newIter;
    }

    public void setIdentifier(String Id){
        this.Identifier = Id;
    }
}
