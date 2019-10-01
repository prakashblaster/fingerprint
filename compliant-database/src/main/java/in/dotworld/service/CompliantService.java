package in.dotworld.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import in.dotworld.dto.InputRequest;
import in.dotworld.entity.CompliantModel;
import in.dotworld.repository.CompliantRepository;

@Service
public class CompliantService {

	@Autowired
	private CompliantRepository cRepository;

	public String saveCompliant(InputRequest<CompliantModel> request) {
		request.setTimeZone(Calendar.getInstance().getTimeZone().getDisplayName());
		CompliantModel compliantModel = request.getCompliant();
		UUID uuid = UUID.randomUUID();
		compliantModel.setId(uuid);
		compliantModel.getCompliantType().toUpperCase();
		
		cRepository.save(compliantModel);
		return "compliant created successfully....";
	}

	public String updateType(int no, String compliantType, InputRequest<CompliantModel> request) {
		CompliantModel compliantModel = cRepository.findById(no).get();
		if (compliantModel != null) {
			compliantModel.setCompliantType(compliantType);
			cRepository.saveAndFlush(compliantModel);
		} else {
			throw new RuntimeException("compliant not found");
		}
		return "compliant updated successfully";
	}

	public String updateDescription(int no, String description, InputRequest<CompliantModel> request) {
		CompliantModel compliantModel = cRepository.findById(no).get();
		if (compliantModel != null) {
			compliantModel.setDescription(description);
			cRepository.saveAndFlush(compliantModel);
		} else {
			throw new RuntimeException("compliant not found");
		}
		return "compliant updated successfully";
	}
	

	public ResponseEntity uploadFile(MultipartFile file,int no) {
		
		CompliantModel cModel = cRepository.findById(no).get();
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		cModel.setAttachment(fileName);
		try {
			cModel.setFile(file.getBytes());
			cRepository.save(cModel);
		} catch (IOException e) {
			e.printStackTrace();
		}		
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/files/download/")
				.path(file.getOriginalFilename()).path("/db")
				.toUriString();
		return ResponseEntity.ok(fileDownloadUri);
	}
	
	
//	public ResponseEntity downloadFromDB( int no) {
//		CompliantModel c = cRepository.findById(no).get();
//		
//		return ResponseEntity.ok()
//				.contentType(MediaType.parseMediaType(c.getFile())
//				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + no + "\"")
//				.body(c.getAttachment());
//	}
//	
}
