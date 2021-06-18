import NoAccessModifier.A;
import Private.B;
import Protected.C;
import Public.D;

public class Main extends C{

    public static void main(String[] args) {

        A a = new A(1); //no access modifier - VISIBLE TO THE PACKAGE
        B b = new B(2); //private - VISIBLE TO THE SAME CLASS
        C c = new C(3); //protected - VISIBLE TO THE PACKAGE AND ALL SUBCLASSES
        D d = new D(4); //public - VISIBLE EVERYWHERE

        a.readLetter(); //no access modifier
        b.readLetter(); //private
        c.readLetter(); //protected
        d.readLetter(); //public

    }

}
