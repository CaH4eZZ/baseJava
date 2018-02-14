package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final int storageMaxSize = 10000;

    private Resume[] storage = new Resume[storageMaxSize];
    private int size = 0;

    public void clear() {
        storage = new Resume[storageMaxSize];
        size = 0;
    }

    public void save(Resume r) {
        if (size >= storageMaxSize){
            System.out.println("Array storage is full");
            return;
        } else if (r == null) {
            System.out.println("Can't save empty Resume");
            return;
        } if (getIndex(r.getUuid()) != -1){
            System.out.println("Can't save Resume, because already exist.");
            return;
        }
        storage[size] = r;
        size++;
    }

    public void update(Resume r){
        if (r == null || r.getUuid() == null ){
            System.out.println("Resume is empty.");
            return;
        }
        int index = getIndex(r.getUuid());
        if (index == -1) {
            System.out.println("Resume is not found");
            return;
        }
        storage[index] = r;
    }

    public Resume get(String uuid) {
        if (uuid == null){
            System.out.println("uuid is null");
            return null;
        }
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Resume is not found");
            return null;
        }
        return storage[index];
    }

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

        storage[index] = storage[size-1];
        storage[size-1] = null;
        size--;
        return;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage,size());
    }

    public int size() {
        return size;
    }

    private int getIndex (String uuid){
        for (int i=0;i<size;i++){
            if (storage[i] != null && uuid.equals(storage[i].getUuid()))
                return i;
        }
        return -1;
    }
}
