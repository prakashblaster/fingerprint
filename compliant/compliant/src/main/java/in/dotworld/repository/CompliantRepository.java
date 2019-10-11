package in.dotworld.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.dotworld.model.Compliant;


public interface CompliantRepository extends JpaRepository<Compliant, Integer> {

}
