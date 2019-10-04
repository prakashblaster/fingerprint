package in.dotworld.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import in.dotworld.dto.InputRequest;
import in.dotworld.entity.CompliantModel;
import in.dotworld.service.CompliantService;

@RestController
public class CompliantController {
	@Autowired
	private CompliantService service;
	
	@PostMapping("/addCompliant")
	@ResponseStatus(value=HttpStatus.CREATED)
	public String saveCompliant(@RequestBody InputRequest<CompliantModel> request) {
		return service.saveCompliant(request);
	}
	
	@PutMapping("/update/type/{no}/{compliantType}")
	@ResponseStatus(value=HttpStatus.CREATED)
	public String updateCompliantType(@PathVariable int no, @PathVariable String compliantType,
			@RequestBody InputRequest<CompliantModel> request) {
		return service.updateType( no,compliantType, request);
	}
	
	@PutMapping("/update/description/{no}/{description}")
	@ResponseStatus(value=HttpStatus.CREATED)
	public String updateCompliantDescription(@PathVariable int no, @PathVariable String description,
			@RequestBody InputRequest<CompliantModel> request) {
		return service.updateDescription(no, description, request);
	}
	
	@PostMapping(value = "/upload/db/{no}",
			consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseStatus(value=HttpStatus.CREATED)
	public String uploadFile(@RequestParam("file")MultipartFile file,@PathVariable int no) {
		
		return service.uploadFile(file, no);
		 
	}
	
//	@GetMapping("/download/{no}/db")
//	public ResponseEntity downloadFromDB(@PathVariable int no) {
//		return service.downloadFromDB(no);
//	}
	

	

}
