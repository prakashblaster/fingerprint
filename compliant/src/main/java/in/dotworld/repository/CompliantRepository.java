package in.dotworld.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.dotworld.model.Compliant;


public interface CompliantRepository extends JpaRepository<Compliant, String> {

}
