package InheritanceTest;

import NoAccessModifier.A;

public class testAinheritance extends A { //constructor has no access modifier, only visible in the same package

    A a = new A(6);
}
