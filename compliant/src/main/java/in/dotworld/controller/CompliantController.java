package in.dotworld.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import in.dotworld.model.Compliant;
import in.dotworld.service.FileStorageService;

@RestController
@RequestMapping("/compliants")
public class CompliantController {
	@Autowired
	private FileStorageService service;

	@PostMapping("")
	@ResponseStatus(value = HttpStatus.CREATED)
	public String saveCompliant(@RequestBody Compliant compliant) {
		return service.saveCompliant(compliant);
	}
   
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("")
	public List<Compliant> getAllCompliant() {
		return service.getAllCompliant();
	}

	@GetMapping("/{id}/compliantType")
	public String getCompliantType(@PathVariable String id) {
		return service.getCompliantType(id);
	}

	@GetMapping("/{id}/description")
	public String getCompliantDesc(@PathVariable String id) {
		return service.getDescription(id);
	}

	@GetMapping("/{id}/attachment")
	public String getCompliantAttachment(@PathVariable String id) {
		return service.getAttachment(id);
	}

	@GetMapping("/{id}")
	public Compliant getCompliant(@PathVariable String id) {
		return service.getCompliantById(id);
	}

	@PutMapping("/{id}")
	@ResponseStatus(value = HttpStatus.CREATED)
	public String updateAll(@Valid @RequestBody Compliant compliant, @PathVariable String id) {
		return service.update(compliant, id);

	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public String deleteCompliant(@PathVariable String id) {
		return service.deleteCompliant(id);
	}
}