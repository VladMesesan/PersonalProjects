package Private;

public class B {
    private int id;

    private B() {
    }

    private B(int id) {
        this.id = id;
    }

    private void readLetter() {
        System.out.println(id);
    }
}
