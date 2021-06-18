package Protected;

public class C {
    protected int id;

    protected C() {
    }

    protected C(int id) {
        this.id = id;
    }

   protected void readLetter() {
        System.out.println(id);
    }

    protected int getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = id;
    }
}
