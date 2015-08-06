package MViGmodules;

import java.util.ArrayList;

/**
 * Created by Admin on 14.07.15.
 */
public class DA {
    private ArrayList<Struct> da;

    public DA() {
        da = new ArrayList<>();
    }

    public ArrayList<Struct> getDa() {
        return da;
    }

    public void setDa(ArrayList<Struct> da) {
        this.da = da;
    }

    boolean isEmpty() {
        return da.isEmpty();
    }

    boolean add(Struct struct) {
        da.add(struct);
        return true;
    }

    int getSize() {
        return da.size();
    }

    Struct get(int index) {
        return da.get(index);
    }

    void remove(Struct struct) {
        da.remove(struct);
    }

    void checkDa(double minSum) {
        int index = 0;
        while (index != da.size()) {
            Struct temp = da.get(index);
            if (temp.isActivatehwo() && temp.getHWithSum() > minSum) { // todo  minSum or min ?
                da.remove(temp);
            } else if (temp.isActivatehw() && temp.getHWithoutSum() > minSum) {
                da.remove(temp);
            } else {
                index++;
            }
            temp = null;
        }
    }

}
