package com.luxoft.converter.service.file;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Pavel on 10.11.2016.
 */
public class ConcurrentMapVirtualFileStorage implements VirtualFileStorage {

    private static final Set<String> ALLOWED_FILE_KINDS;

    static {
        ALLOWED_FILE_KINDS = new HashSet<>();
        ALLOWED_FILE_KINDS.add(TEST_FILE);
        ALLOWED_FILE_KINDS.add(QUESTIONS_FILE);
        ALLOWED_FILE_KINDS.add(ANSWERS_FILE);
        ALLOWED_FILE_KINDS.add(RESULTS_FILE);
    }

    private final Lock fileLock = new ReentrantLock();

    private final Map<String, File> fileKindToFile = new HashMap<>();
    private File lastChosenFile = null;

    public void storeFile(String fileKind, File file) {
        if (isKindAllowed(fileKind)) {
            fileLock.lock();
            try {
                fileKindToFile.put(fileKind, file);
                lastChosenFile = file;
            } finally {
                fileLock.unlock();
            }
        }
    }

    public File getFile(String fileKind) {
        if (isKindAllowed(fileKind)) {
            fileLock.lock();
            try {
                return fileKindToFile.get(fileKind);
            } finally {
                fileLock.unlock();
            }
        }
        return null;
    }

    @Override
    public File getLastChosenFile() {
        fileLock.lock();
        try {
            return lastChosenFile;
        } finally {
            fileLock.unlock();
        }
    }

    private boolean isKindAllowed(String fileKind) {
        return ALLOWED_FILE_KINDS.contains(fileKind);
    }

    @Override
    public String getFileKindExtension(String fileKind) {
        String result;
        switch (fileKind) {
            case (TEST_FILE):
                result = TEST_FILE_EXTENSION;
                break;
            case (QUESTIONS_FILE):
                result = QUESTIONS_FILE_EXTENSION;
                break;
            case (ANSWERS_FILE):
                result = ANSWERS_FILE_EXTENSION;
                break;
            default:
                result = null;
        }
        return result;
    }

    @Override
    public boolean isValid() {
        for (String allowedFileKind : ALLOWED_FILE_KINDS) {
            if(fileKindToFile.get(allowedFileKind) == null){
                return false;
            }
        }
        return true;
    }
}

