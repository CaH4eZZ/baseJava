/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int storageSize = 0;

    void clear() {
        this.storage = new Resume[10000];
        this.storageSize = 0;
    }

    void save(Resume r) {
        if (r == null) return;

        this.storage[this.storageSize] = r;
        this.storageSize++;
    }

    Resume get(String uuid) {
        for (int i=0;i<this.storage.length;i++) {
            if (this.storage[i] != null && uuid.equals(this.storage[i].uuid)){
                return this.storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        if (uuid == null || "".equals(uuid)) return;

        int deleteIdx = -1;

        for (int i=0;i<this.storageSize;i++) {
            if (uuid.equals(this.storage[i].uuid)){
                deleteIdx = i;
            }
        }
        if (deleteIdx != -1){
            for (int k=deleteIdx;k<this.storageSize;k++){
                if (k < this.storage.length)
                    this.storage[k] = this.storage[k+1];
                else
                    this.storage[k] = null;
            }
        }
        this.storageSize--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] newStorage = new Resume[this.storageSize];
        for (int i=0;i<this.storageSize;i++){
           newStorage[i] = this.storage[i];
        }
        return newStorage;
    }

    int size() {
        return this.storageSize;
    }
}
