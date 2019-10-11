package in.dotworld.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import in.dotworld.dto.InputRequest;
import in.dotworld.model.Compliant;
import in.dotworld.service.FileStorageService;

@RestController
public class CompliantController {
	@Autowired
	private FileStorageService service;
	
	@PostMapping("/addCompliant")
	@ResponseStatus(value=HttpStatus.CREATED)
	public String saveCompliant(@RequestBody InputRequest<Compliant> request) {
		return service.saveCompliant(request);
	}
	
	@PutMapping("/update/type/{no}/{compliantType}")
	@ResponseStatus(value=HttpStatus.CREATED)
	public String updateCompliantType(@PathVariable int no, @PathVariable String compliantType,
			@RequestBody InputRequest<Compliant> request) {
		return service.updateType( no,compliantType, request);
	}
	
	@PutMapping("/update/description/{no}/{description}")
	@ResponseStatus(value=HttpStatus.CREATED)
	public String updateCompliantDescription(@PathVariable int no, @PathVariable String description,
			@RequestBody InputRequest<Compliant> request) {
		return service.updateDescription(no, description, request);
	}
}