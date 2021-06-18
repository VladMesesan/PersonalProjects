package abstracsAndInterfaces;

public class testClass extends abstractClass implements interface1{
    @Override
    public int calculateRectangleArea(int a, int b) {  //method declared in abstractClass, must be overridden here
        return (2*a + 2*b);
    }

    @Override
    public boolean hasInterace() { //method declared in interface, must be overridden here
        return true;
    }
}
