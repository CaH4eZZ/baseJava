package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstarctStorage {

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i] != null && uuid.equals(storage[i].getUuid()))
                return i;
        }
        return -1;
    }

    protected void insertIntoStorage(Resume r, int index){
        storage[size] = r;
    }

    protected void deleteFromStorage(String uuid, int index){
        storage[index] = storage[size - 1];
        storage[size - 1] = null;
    }
}
