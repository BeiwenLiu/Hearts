package sample;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * Created by BLiu on 2/16/2016.
 */
public class DataHolder {
    public SimpleIntegerProperty points1 = new SimpleIntegerProperty();
    public SimpleIntegerProperty points2 = new SimpleIntegerProperty();
    public SimpleIntegerProperty points3 = new SimpleIntegerProperty();
    public SimpleIntegerProperty points4 = new SimpleIntegerProperty();

    public Integer getPoints1() {
        return points1.get();
    }

    public Integer getPoints2() {
        return points2.get();
    }

    public Integer getPoints3() {
        return points3.get();
    }

    public Integer getPoints4() {
        return points4.get();
    }
}
