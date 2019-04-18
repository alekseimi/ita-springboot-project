package ita.springboot.application.web;

import ita.springboot.application.exception.StorageException;
import ita.springboot.application.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/fileupload")
public class FileUploadController {

    @Autowired
    private FileStorageService storageService;

    @PostMapping
    public String upload(@RequestParam MultipartFile file) {
        storageService.uploadFile(file);
        return "redirect:/success";
    }

    @GetMapping
    public String showRegistrationForm() {
        return "/fileupload";
    }

    @ExceptionHandler(StorageException.class)
    public String handleStorageFileNotFound(StorageException e) {
        return "redirect:/failure";
    }

}
