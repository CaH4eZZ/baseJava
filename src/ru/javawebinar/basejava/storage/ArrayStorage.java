package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstarctStorage {

    @Override
    public void save(Resume r) {
        if (size >= MAX_SIZE) {
            System.out.println("Array storage is full");
            return;
        } else if (r == null) {
            System.out.println("Can't save empty Resume");
            return;
        }
        if (getIndex(r.getUuid()) != -1) {
            System.out.println("Can't save Resume, because already exist.");
            return;
        }
        storage[size] = r;
        size++;
    }

    @Override
    public void delete(String uuid) {
        if (uuid == null || "".equals(uuid)) {
            System.out.println("uuid can't be null");
            return;
        }
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Resume is not found");
            return;
        }

        storage[index] = storage[size - 1];
        storage[size - 1] = null;
    }

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i] != null && uuid.equals(storage[i].getUuid()))
                return i;
        }
        return -1;
    }
}
