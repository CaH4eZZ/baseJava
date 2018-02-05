/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        this.storage = new Resume[10000];
    }

    void save(Resume r) {
        if (r == null) return;

        int storageSize = this.size();
        this.storage[storageSize] = r;
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
        int storageSize = this.size();

        for (int i=0;i<storageSize;i++) {
            if (uuid.equals(this.storage[i].uuid)){
                deleteIdx = i;
            }
        }
        if (deleteIdx != -1){
            for (int k=deleteIdx;k<storageSize;k++){
                if (k < this.storage.length)
                    this.storage[k] = this.storage[k+1];
                else
                    this.storage[k] = null;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        int storageSize = this.size();
        Resume[] newStorage = new Resume[storageSize];
        for (int i=0;i<storageSize;i++){
           newStorage[i] = this.storage[i];
        }
        return newStorage;
    }

    int size() {
        int storageSize = 0;
        for(int i=0;i<this.storage.length;i++){
            if (this.storage[i] != null)
                storageSize++;
            else
                break;
        }
        return storageSize;
    }
}
