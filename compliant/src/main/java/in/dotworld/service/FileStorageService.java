package in.dotworld.service;

import java.io.IOException;
import java.util.Calendar;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import in.dotworld.dto.InputRequest;
import in.dotworld.exception.FileStorageException;
import in.dotworld.exception.MyFileNotFoundException;
import in.dotworld.model.Compliant;
import in.dotworld.repository.CompliantRepository;

@Service
public class FileStorageService {


	@Autowired
	private CompliantRepository cRepository;

	public Compliant storeFile(MultipartFile file,int no) {
		
		Compliant compliant=cRepository.findById(no).get();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        compliant.setAttachment(fileName);

        try {
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            compliant.setFileType(file.getContentType());
            compliant.setData(file.getBytes());
            return cRepository.save(compliant);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Compliant getFile(int no) {
        return cRepository.findById(no)
                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + no));
    }

   
	public String saveCompliant(InputRequest<Compliant> request) {
		request.setTimeZone(Calendar.getInstance().getTimeZone().getDisplayName());
		Compliant compliantModel = request.getCompliant();
		UUID uuid=UUID.randomUUID();
		compliantModel.setId(uuid.toString());
		String type=compliantModel.getCompliantType();
		compliantModel.setCompliantType(type.toUpperCase());
		cRepository.save(compliantModel);
		return "compliant created successfully";

	}

	public String updateType(int no, String compliantType, InputRequest<Compliant> request) {
		Compliant compliantModel = cRepository.findById(no).get();
		if (compliantModel != null) {
			compliantModel.setCompliantType(compliantType.toUpperCase());
			cRepository.saveAndFlush(compliantModel);
		} else {
			throw new RuntimeException("compliant not found");
		}
		return "compliant updated successfully";
	}

	public String updateDescription(int no, String description, InputRequest<Compliant> request) {
		Compliant compliantModel = cRepository.findById(no).get();
		if (compliantModel != null) {
			compliantModel.setDescription(description);
			cRepository.saveAndFlush(compliantModel);
		} else {
			throw new RuntimeException("compliant not found");
		}
		return "compliant updated successfully";
	}
	
	public String deleteCompliant(int no) {
		Compliant compliant=cRepository.findById(no).get();
		cRepository.delete(compliant);
		return "deleted successfully";
		
	}

}