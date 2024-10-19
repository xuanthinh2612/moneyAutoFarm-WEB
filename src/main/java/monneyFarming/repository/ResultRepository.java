package monneyFarming.repository;

import monneyFarming.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {

    @Query(value = "SELECT * FROM result ORDER BY result.id LIMIT :limitAmount", nativeQuery = true)
    List<Result> findAllLimitBy(@Param("limitAmount") Long limitAmount);
}
