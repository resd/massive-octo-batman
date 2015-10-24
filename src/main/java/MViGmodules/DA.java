package MViGmodules;

import java.util.ArrayList;

/**
 * @author Admin
 * @since 14.07.15
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
//
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
            if (temp.hasStructHW() && temp.getStructHW().getHWithSum() >= minSum) { // todo  minSum or min ?
                if (temp.hasStructHWout()) {
                    da.get(index).setStructHW(null);
                } else {
                    da.remove(temp);
                }
            } else if (temp.hasStructHWout() && temp.getStructHWout().getHWithoutSum() >= minSum) {
                if (temp.hasStructHW()) {
                    da.get(index).setStructHWout(null);
                } else {
                    da.remove(temp);
                }
            } else {
                index++;
            }
            temp = null;
        }
    }

    public Struct checkSa(Struct struct, double minLowerBound) {
        if (struct.hasStructHW() && struct.getStructHW().getHWithSum() > minLowerBound) {
            struct.setStructHW(null);
        }
        if (struct.hasStructHWout() && struct.getStructHWout().getHWithoutSum() > minLowerBound) {
            struct.setStructHWout(null);
        }
        if (!struct.hasStructHW() && !struct.hasStructHWout() && !struct.getGeneralStruct().isLowerBound()) {
            struct = null;
        }
        return struct;
    }

    public void checkLowerBound(Struct struct, double minLowerBound) {
        if (struct == null) return;
        if (!struct.getGeneralStruct().isLowerBound()) {
            return;
        }

    }
}
