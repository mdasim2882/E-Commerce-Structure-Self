
package com.example.sweetcartapp.ShoppersRoom.HelperMethods;

public class Users {
    public int ID;
    public String name;

    public Users(int ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name; // What to display in the Spinner list.
    }
}
