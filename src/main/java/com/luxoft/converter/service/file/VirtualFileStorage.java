package com.luxoft.converter.service.file;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Pavel on 10.11.2016.
 */
public interface VirtualFileStorage {

    //File kinds
    String TEST_FILE = "TEST_FILE";
    String QUESTIONS_FILE = "QUESTIONS_FILE";
    String ANSWERS_FILE = "ANSWERS_FILE";

    //File kind extensions
    String TEST_FILE_EXTENSION = "docx";
    String QUESTIONS_FILE_EXTENSION = "xlsx";
    String ANSWERS_FILE_EXTENSION = "xlsx";

    void storeFile(String fileKind, File file);

    File getFile(String fileKind);

    String getFileKindExtension(String fileKind);

}
