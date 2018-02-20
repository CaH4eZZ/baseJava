package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstarctStorage {

    @Override
    protected int getIndex(String uuid) {
        return Arrays.binarySearch(storage, 0, size, new Resume(uuid));
    }

    @Override
    protected void insertIntoStorage(Resume r, int index) {
        index = -index - 1;

        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = r;
    }

    protected void deleteFromStorage(String uuid, int index) {
        //если массив длинной 1 или удалется последний элемент
        int countEl = size - (index+1);
        if (countEl > 0) {
            System.arraycopy(storage, index + 1, storage, index, countEl);
        }
    }
}
