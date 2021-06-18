package InheritanceTest;

import Private.B;

public class testBinheritance extends B { //the constructor is private so this other class can't access it

    B b = new B(6);
}
