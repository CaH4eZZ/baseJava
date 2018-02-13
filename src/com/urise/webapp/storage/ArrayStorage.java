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
        } if (this.get(r.getUuid()) != null){
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
        }  if (this.get(r.getUuid()) == null){
            System.out.println("Resume is not found");
            return;
        }

        for(int i=0;i<this.size;i++){
            if (r.getUuid().equals(this.storage[i].getUuid())){
                this.storage[i] = r;
                return;
            }
        }
    }

    public Resume get(String uuid) {
        if (uuid == null){
            System.out.println("uuid is null");
            return null;
        }

        for (int i=0;i<this.size;i++) {
            if (this.storage[i] != null && uuid.equals(this.storage[i].getUuid())){
                return this.storage[i];
            }
        }
        System.out.println("Resume is not found");
        return null;
    }

    public void delete(String uuid) {
        if (uuid == null || "".equals(uuid)) {
            System.out.println("uuid can't be null");
            return;
        }

        for (int i=0;i<this.size;i++) {
            if (this.storage[i] != null && uuid.equals(this.storage[i].getUuid())){
                this.storage[i] = this.storage[this.size-1];
                this.storage[this.size-1] = null;
                this.size--;
                return;
            }
        }
        System.out.println("Resume is not found");
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
}
