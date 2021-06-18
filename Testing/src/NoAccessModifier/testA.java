package NoAccessModifier;

import Private.B;
import Protected.C;
import Public.D;

public class testA extends C{

    public static void main(String[] args) {
        A a = new A(1);
        B b = new B(2);
        C c0 = new C();
        C c = new testA(); // C c = new C(); does not work as shown above
        D d = new D(4);

    }
}
