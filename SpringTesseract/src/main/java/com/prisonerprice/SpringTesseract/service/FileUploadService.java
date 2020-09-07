package com.prisonerprice.SpringTesseract.service;

import com.prisonerprice.SpringTesseract.repository.PaperRepository;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileUploadService {

    @Autowired
    private PaperRepository paperRepository;

    public String uploadFile(MultipartFile file) throws IOException, TesseractException {
        File currDir = new File(".");
        Path path = Paths.get(currDir.getAbsolutePath() + file.getOriginalFilename());
        File dstDir = new File(path.toString());
        try (OutputStream os = new FileOutputStream(dstDir)) {
            os.write(file.getBytes());
        }

        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath(currDir.getAbsolutePath());
        String text = tesseract.doOCR(dstDir);
        System.out.println(text);
        return text;
    }
}
