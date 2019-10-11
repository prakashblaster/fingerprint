package in.dotworld.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import in.dotworld.model.Compliant;
import in.dotworld.service.FileStorageService;


@RestController
public class FileUploadController {
	

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/uploadFile/{no}")
    public String uploadFile(@RequestParam("file") MultipartFile file,@PathVariable int no) {
    	Compliant fileName = fileStorageService.storeFile(file,no);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName.getAttachment())
                .toUriString();

        return  "uploaded successfully" +"\n"+fileDownloadUri;
    }

 
}