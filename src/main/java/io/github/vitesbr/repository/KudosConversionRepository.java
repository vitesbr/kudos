package io.github.vitesbr.repository;

import io.github.vitesbr.entity.KudosConversion;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface KudosConversionRepository extends JpaRepository<KudosConversion, Integer> {
	List<KudosConversion> findAllByOrderByPointsDesc();
}
