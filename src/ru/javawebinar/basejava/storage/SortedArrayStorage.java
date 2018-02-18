package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstarctStorage {

    @Override
    protected int getIndex(String uuid) {
        return Arrays.binarySearch(storage, 0, size, new Resume(uuid));
    }

     @Override
    public void save(Resume r) {
        if (size >= MAX_SIZE) {
            System.out.println("Array storage is full");
            return;
        }
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            System.out.println("Can't save Resume, because already exist.");
            return;
        }
        int newIndex = -index - 1;
        System.arraycopy(storage, newIndex, storage, newIndex + 1, size - newIndex);
        storage[newIndex] = r;
        size++;
    }

    @Override
    public void delete(String uuid) {
        if (uuid == null || "".equals(uuid)) {
            System.out.println("uuid can't be null");
            return;
        }
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume is not found");
            return;
        }
        //если массив длинной 1 или удалется последний элемент
        if (size == 1 || index == MAX_SIZE) {
            storage[index] = null;
        } else {
            System.arraycopy(storage, index + 1, storage, index, size);
        }
        size--;
    }
}
