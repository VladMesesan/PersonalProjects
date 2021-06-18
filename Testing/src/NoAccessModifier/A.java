package NoAccessModifier;

public class A {
    int id;

    A() {
    }

    public A(int id) {
        this.id = id;
    }

    void readLetter() {
        System.out.println(id);
    }
}
