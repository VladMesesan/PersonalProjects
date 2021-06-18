package Protected;

import NoAccessModifier.A;
import Private.B;
import Public.D;

public class testC{
    public static void main(String[] args) {
        A a = new A(1);
        B b = new B(2);
        C c = new C(3);
        D d = new D(4);
    }
}
