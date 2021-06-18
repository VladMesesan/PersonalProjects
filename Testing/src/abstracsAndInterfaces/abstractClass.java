package abstracsAndInterfaces;

public abstract class abstractClass {

    public abstract int calculateRectangleArea(int a, int b);

    public void canHaveNotAbstractMethod () { //abstract classes can have abstract and non-abstract methods
        System.out.println("Yes"); //not required to be implemented in child class, since method is not abstract
    }

}
