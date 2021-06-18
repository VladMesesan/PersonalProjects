package abstracsAndInterfaces;

public interface interface1 {

    public abstract boolean hasInterace (); //public and abstract modifiers are redundant for interfaces

    static boolean canHaveNonAbstractMethod(){
        return true;
    } //yes, static or default methods only

    //Interfaces can have static, default or abstract methods only

}
