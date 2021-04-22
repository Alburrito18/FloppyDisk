package com.example.NoSound;

public class IdNumberGenerator {
    private String Identifier;

    private static IdNumberGenerator instance;

    private IdNumberGenerator(){}

    public static IdNumberGenerator getInstance() {
        if(instance == null) {
            instance = new IdNumberGenerator();
        }
        return instance;
    }

    private String getIteration() {
        /* if (file found) {
                return(get it)
           }
           else {
                saveIteration(0)
                return 0
           }
         */
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
