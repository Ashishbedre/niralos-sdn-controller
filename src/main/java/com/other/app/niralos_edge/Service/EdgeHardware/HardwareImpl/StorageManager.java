package com.other.app.niralos_edge.Service.EdgeHardware.HardwareImpl;

import java.util.HashSet;
import java.util.Set;

public class StorageManager {
    private static StorageManager instance;
    private Set<String> storageSet;

    private StorageManager() {
        this.storageSet = new HashSet<>();
    }

    // Singleton pattern: ensures only one instance is created
    public static synchronized StorageManager getInstance() {
        if (instance == null) {
            instance = new StorageManager();
        }
        return instance;
    }

    // Add a storage type with index
    public boolean addStorage(String storageType, int index) {
        String storageEntry = storageType + index;
        return storageSet.add(storageEntry); // Adds if not already present
    }

    // Remove a storage type with index
    public boolean removeStorage(String storageType, int index) {
        String storageEntry = storageType + index;
        return storageSet.remove(storageEntry); // Removes if present
    }

    // Check if a storage entry exists
    public boolean containsStorage(String storageType, int index) {
        String storageEntry = storageType + index;
        return storageSet.contains(storageEntry);
    }

    // Method to get the first available index from 0 to 10 for a storage type
    public int getFirstAvailableIndex(String storageType) {
        for (int index = 0; index <= 10; index++) {
            if (!storageSet.contains(storageType + index)) {
                return index;
            }
        }
        return -1; // Return -1 if all indices from 0 to 10 are occupied
    }
}
