package in.dotworld.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import in.dotworld.entity.CompliantModel;

public interface CompliantRepository extends JpaRepository<CompliantModel, Integer> {

}
