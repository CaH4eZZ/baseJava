package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstarctStorageTest {
    protected Storage storage;
    private final String UUID_1 = "uuid1";
    private final String UUID_2 = "uuid2";
    private final String UUID_3 = "uuid3";
    private final String UUID_4 = "uuid4";

    public AbstarctStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp(){
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0,storage.size());
    }

    @Test
    public void size() {
        Assert.assertEquals(4,storage.size());
    }

    @Test
    public void update() {
        Resume r3 = new Resume(UUID_3);
        storage.update(r3);
        Assert.assertEquals(r3, storage.get(UUID_3));
    }

    @Test
    public void get() {
        Resume r2 = new Resume(UUID_2);
        Assert.assertEquals(r2,storage.get(UUID_2));
    }

    @Test
    public void getAll() {
        Resume[] expectedArray = new Resume[4];
        expectedArray[0] = new Resume(UUID_1);
        expectedArray[1] = new Resume(UUID_2);
        expectedArray[2] = new Resume(UUID_3);

        Resume[] actualArray = storage.getAll();

        Assert.assertArrayEquals(expectedArray, actualArray);
    }


    @Test
    public void save() {
        Resume r4 = new Resume(UUID_4);
        storage.save(r4);
        Assert.assertEquals(r4, storage.get(UUID_4));
    }

    @Test
    public void delete() {
        storage.delete(UUID_1);
        Resume r1 = new Resume(UUID_1);
        Resume[] resumeArray = storage.getAll();
        for(Resume resume : resumeArray){
            Assert.assertNotEquals(r1, resume);
        }
    }

    @Test(expected = ExistStorageException.class)
    public void ExistStorageExceptionTest(){
        storage.save(new Resume(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void NotExistStorageExceptionTest(){
        storage.get(UUID_4);
    }

    @Test(expected = StorageException.class)
    public void StorageExceptionTest(){
        for(int i=storage.size(); i<=AbstarctStorage.MAX_SIZE; i++) {
            storage.save(new Resume());
        }
    }
}