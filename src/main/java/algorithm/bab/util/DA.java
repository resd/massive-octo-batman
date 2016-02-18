package algorithm.bab.util;

import java.util.ArrayList;

/**
 * @author Admin
 * @since 14.07.15
 */
@SuppressWarnings("all")
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

    public boolean add(Struct struct) {
        da.add(struct);
        return true;
    }

    public int getSize() {
        return da.size();
    }

    public Struct get(int index) {
        return da.get(index);
    }

    public void remove(Struct struct) {
        da.remove(struct);
    }

    public void remove(int index) {
        da.remove(index);
    }

    public void clear() {
        da.clear();
    }

    public void get(Struct struct) {
        da.indexOf(struct);
    }

    public void checkDa(double minSum) {
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

    public boolean checkMin(Path path, Var var) {
        // ��������� ��� �� ������� ������, ��� ��� ���������� �������
        // �������� �� �� �������
        Struct temp = null;
        boolean stopHW = false;
        boolean stopHWO = false;
        int i;
        for (i = 0; i < da.size(); i++) {
            temp = da.get(i);
            if (temp.hasStructHWout() && temp.getStructHWout().getHWithoutSum() < var.getMin()) {
                //countBackWith++;
                StructHWout structHWout = temp.getStructHWout();
                path.setP(structHWout.getpNew().clone());
                var.setH(structHWout.getHWithoutSum());
                path.setMi(structHWout.getMi().clone());
                path.setMj(structHWout.getMj().clone());
                path.setPathCount(structHWout.getPathCountNew());
                var.setArray(Other.INSTANCE.cloneMatrix(structHWout.getM1()));
                stopHWO = true;
                break;
            } else if (temp.hasStructHW() && temp.getStructHW().getHWithSum() < var.getMin()) {
                StructHW structHW = temp.getStructHW();
                path.setP(structHW.getP().clone());
                var.setH(structHW.getHWithSum());
                path.setMi(structHW.getMiOld().clone());
                path.setMj(structHW.getMjOld().clone());
                path.setPathCount(structHW.getPathCount());
                var.setArray(Other.INSTANCE.cloneMatrix(structHW.getArray()));
                Normalize.INSTANCE.normalize(var.getArray());
                stopHW = true;
                break;
            }
        }
        if (stopHWO || stopHW) {
            if (temp.hasStructHWout() && temp.hasStructHW()) {
                if (stopHW) {
                    da.get(i).setStructHW(null);
                } else {
                    da.get(i).setStructHWout(null);
                }
            } else {
                da.remove(temp);
            }
        }
        return stopHWO || stopHW;
    }

    public void searchForLowestBound(Path path, Var var) {
        for (int i = 0; i < da.size(); i++) {
            if (da.get(i).hasGeneralStruct()) {
                GeneralStruct temp = da.get(i).getGeneralStruct();
                if (temp.isLowerBound() && temp.getH() <= var.getMinLeftBound()) {
                    var.setMinLeftBound(temp.getH());
                    path.setP(temp.getMinP());
                }
            }

        }
    }

    public void clearRestOfGeneralStructWithBiggerLowerBound(Var var) {
        double valueOfMinLeftBound = var.getMinLeftBound();
        int indexOfDa = 0;
        int daSize = da.size();
        boolean checked = false;
        for (int i = 0; i < daSize; i++) {
            if (da.get(indexOfDa).hasGeneralStruct()) {
                GeneralStruct temp = da.get(indexOfDa).getGeneralStruct();
                if (!checked && temp.isLowerBound() && temp.getH() == valueOfMinLeftBound) {
                    checked = true;
                    indexOfDa++; // todo check
                    continue;
                }
                if (temp.isLowerBound() && temp.getH() >= valueOfMinLeftBound && checked) {
                    da.remove(indexOfDa);
                } else {
                    indexOfDa++;
                }
            } else {
                indexOfDa++;
            }
        }
    }
}
