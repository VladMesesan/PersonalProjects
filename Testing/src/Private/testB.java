package Private;

import NoAccessModifier.A;
import Protected.C;
import Public.D;

public class testB {
    public static void main(String[] args) {
        A a = new A(1);
        B b = new B(2);
        C c = new C(3);
        D d = new D(4);
    }
}
