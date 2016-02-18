package algorithm.bab.util;

/**
 * Created by Admin on 14.07.15.
 */
public class Path {

    private int[][] p;
    private int[] mi;
    private int[] mj;
    private int pathCount;

    public Path(Var var) {
        int originalSize = var.getOriginalSize();
        pathCount = 0;
        mi = new int[originalSize];
        mj = new int[originalSize];
        p = new int[originalSize][2];
        for (int i = 0; i < originalSize; i++) {
            mi[i] = i;
            mj[i] = i;
            p[i][0] = -2;
            p[i][1] = -2;
        }
    }

    public Path(Path path) {
        p = Other.INSTANCE.cloneMatrix(path.p);
        mi = path.mi.clone();
        mj = path.mj.clone();
        pathCount = path.pathCount;
    }

    public void getPath(int[] d) {
        int x = d[0];
        int y = d[1];
        p[pathCount][0] = mi[x];
        p[pathCount][1] = mj[y];
        pathCount++;
        mi[x] = mi[y];

        mi = remove(mi, y);
        mj = remove(mj, y);
    }

    void getPath(int[] d, int[] mi, int[] mj, int pathCount) {
        int x = d[0];
        int y = d[1];
        p[pathCount][0] = mi[x];
        p[pathCount][1] = mj[y];
        pathCount++;
        mi[x] = mi[y];

        mi = remove(mi, y);
        mj = remove(mj, y);
    }

    public int[] remove(int[] mi, int y) {
        int[] tmp = new int[mi.length - 1];
        System.arraycopy(mi, 0, tmp, 0, y);
        System.arraycopy(mi, y + 1, tmp, y, mi.length - y - 1);
        return tmp;
    }

    private void getPathAlternative(double[][] array, int[] d) {
        int x = d[0];
        int y = d[1];
        int len = array[0].length;
        int di = -1;
        boolean yes = true;
        for (int i = 0; i < len; i++) {
            if (array[i][y] == Double.POSITIVE_INFINITY && i != x) {
                for (int j = 0; j < len; j++) {
                    if (array[i][j] == Double.POSITIVE_INFINITY && j != y) {
                        yes = false;
                        break;
                    }
                }
                if (yes) {
                    di = i;
                    break;
                }
                yes = true;
            }
        }
        array[di][x] = Double.POSITIVE_INFINITY;
    }
//
    public void computeLastElement(Var var) {
        int originalSize = var.getOriginalSize();
        p[originalSize - 2][0] = mi[0];
        p[originalSize - 2][1] = mj[1];
        p[originalSize - 1][0] = mi[1];
        p[originalSize - 1][1] = mj[0];
    }

    private void getPathForZero(double[][] array, int[] d) {
        int x = d[0];//0
        int y = d[1];//2
        int mx = mi[x];
        int my = mj[y];
        int di = -1;
        int dj = -1;
        for (int i = 0; i < mi.length; i++) {// перепилить поиск в быстрый поиск.todo
            if (mi[i] == my) {
                di = i;
                break;
            }
        }
        for (int i = 0; i < mj.length; i++) {// перепилить поиск в быстрый поиск.todo
            if (mj[i] == mx) {
                dj = i;
                break;
            }
        }
        if (di != -1 && dj != -1) {
            array[di][dj] = Double.POSITIVE_INFINITY;
        }
        //mi = remove(mi, x);
        //mj = remove(mj, y);
    }


    public int[][] getP() {
        return p;
    }

    public void setP(int[][] p) {
        this.p = p;
    }

    public int[] getMi() {
        return mi;
    }

    public void setMi(int[] mi) {
        this.mi = mi;
    }

    public int[] getMj() {
        return mj;
    }

    public void setMj(int[] mj) {
        this.mj = mj;
    }

    public int getPathCount() {
        return pathCount;
    }

    public void setPathCount(int pathCount) {
        this.pathCount = pathCount;
    }
}
