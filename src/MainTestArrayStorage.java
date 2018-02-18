import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.ArrayStorage;
import ru.javawebinar.basejava.storage.Storage;

/**
 * Test for com.urise.webapp.storage.ArrayStorage
 */
public class MainTestArrayStorage {
    static final Storage ARRAY_STORAGE = new ArrayStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume("uuid1");
        Resume r2 = new Resume("uuid2");
        Resume r3 = new Resume("uuid3");
        Resume r5 = new Resume("uuid5");
        Resume r4 = new Resume("uuid4");

        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);
        ARRAY_STORAGE.save(r5);
        ARRAY_STORAGE.save(r4);

        printAll();

        //System.out.println("Index:"+ Arrays.binarySearch(ARRAY_STORAGE.getAll(),0,4,r4));

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        printAll();
        ARRAY_STORAGE.delete(r3.getUuid());
        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}
