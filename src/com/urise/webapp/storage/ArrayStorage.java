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
        this.storage = new Resume[this.storageMaxSize];
        this.size = 0;
    }

    public void save(Resume r) {
        if (this.size >= this.storageMaxSize){
            System.out.println("Array storage is full");
            return;
        } else if (r == null) {
            System.out.println("Can't save empty Resume");
            return;
        } if (this.getIndex(r.getUuid()) != -1){
            System.out.println("Can't save Resume, because already exist.");
            return;
        }
        this.storage[this.size] = r;
        this.size++;
    }

    public void update(Resume r){
        if (r == null || r.getUuid() == null ){
            System.out.println("Resume is empty.");
            return;
        }
        int index = this.getIndex(r.getUuid());
        if (index == -1){
            System.out.println("Resume is not found");
            return;
        }
        this.storage[index] = r;
        return;
    }

    public Resume get(String uuid) {
        if (uuid == null){
            System.out.println("uuid is null");
            return null;
        }
        int index = this.getIndex(uuid);
        if (index == -1){
            System.out.println("Resume is not found");
            return null;
        }
        return this.storage[index];
    }

    public void delete(String uuid) {
        if (uuid == null || "".equals(uuid)) {
            System.out.println("uuid can't be null");
            return;
        }
        int index = this.getIndex(uuid);
        if (index == -1){
            System.out.println("Resume is not found");
            return;
        }
        this.storage[index] = this.storage[this.size-1];
        this.storage[this.size-1] = null;
        this.size--;
        return;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(this.storage,this.size());
    }

    public int size() {
        return this.size;
    }

    private int getIndex (String uuid){
        for (int i=0;i<this.size;i++){
            if (this.storage[i] != null && uuid.equals(this.storage[i].getUuid()))
                return i;
        }
        return -1;
    }
}
