package views;

import models.Id;

import java.util.List;

public class IdTextView {
    private Id id;

    public IdTextView(Id idToDisplay) {
        this.id = idToDisplay;
    }
    @Override public String toString() {
        return this.id.toString();
    } 
}