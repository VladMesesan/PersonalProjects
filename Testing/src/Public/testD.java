package Public;

import NoAccessModifier.A;
import Private.B;
import Protected.C;

public class testD {
    public static void main(String[] args) {
        A a = new A(1);
        B b = new B(2);
        C c = new C(3);
        D d = new D(4);
    }
}
