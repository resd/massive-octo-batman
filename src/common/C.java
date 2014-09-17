package common;

/**
 * Created by Admin on 23.06.14.
 */
public class C {
    public static void p(Object object) {
        System.out.println(object);
    }

    public static void f(String s, Object... o) {
        System.out.format(s, o);
    }
}
