package monneyFarming.repository;


import monneyFarming.model.InitValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InitValueRepository extends JpaRepository<InitValue, Long> {
}
