package com.example.cowcounter;

import android.util.Log;

/**
 * Created by Imtiaz on 15.11.2019.
 */

public class CowDTO {
    private int id;
    private int breed;

    public CowDTO(int id, int breed){
        this.id = id;
        this.breed = breed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBreed() {
        return breed;
    }

    public void setBreed(int breed) {
        this.breed = breed;
    }

    public void printObject() {
        Log.i( "myTag","id--->"+this.id+"\nbreed--->"+this.breed);
    }
}
