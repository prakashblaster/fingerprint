package in.dotworld.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import in.dotworld.model.Compliant;
import in.dotworld.service.FileStorageService;

@RestController
public class DownloadController {
	@Autowired
	private FileStorageService fileStorageService;

	@GetMapping("/downloadFile/{no}")
	public ResponseEntity<Resource> downloadFile(@PathVariable int no, HttpServletRequest request) {
		Compliant databaseFile = fileStorageService.getFile(no);

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(databaseFile.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION,
						"attachment; filename=\"" + databaseFile.getAttachment() + "\"")
				.body(new ByteArrayResource(databaseFile.getData()));
	}

}
