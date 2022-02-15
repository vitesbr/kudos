package io.github.vitesbr.repository;

import io.github.vitesbr.entity.KudosConversion;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface KudosConversionRepository extends JpaRepository<KudosConversion, Integer> {
	@Query(value = "select * from kudos_conversion order by POINTS DESC", nativeQuery = true)
    List<KudosConversion> getByPointsDesc();
}
