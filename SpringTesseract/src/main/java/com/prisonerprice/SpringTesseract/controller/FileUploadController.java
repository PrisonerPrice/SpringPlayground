package com.prisonerprice.SpringTesseract.controller;

import com.prisonerprice.SpringTesseract.service.FileUploadService;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

@RestController
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    @GetMapping("/")
    public String index() {
        return "upload";
    }

    @PostMapping("/upload")
    public RedirectView uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) throws IOException, TesseractException {
        String text = fileUploadService.uploadFile(file);
        redirectAttributes.addFlashAttribute("file", file);
        redirectAttributes.addFlashAttribute("text", text);
        return new RedirectView("result");
    }

}
