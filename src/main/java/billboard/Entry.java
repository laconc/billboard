package billboard;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author dash
 */
public class Entry {
    private final SimpleStringProperty first;
    private final SimpleStringProperty second;
 
    Entry (String first, String second) {
        this.first = new SimpleStringProperty(first);
        this.second = new SimpleStringProperty(second);
    }
 
    public String getFirst() {
        return first.get();
    }
    public void setFirst(String first) {
        this.first.set(first);
    }
        
    public String getSecond() {
        return second.get();
    }
    public void setSecond(String second) {
        this.second.set(second);
    }
}
