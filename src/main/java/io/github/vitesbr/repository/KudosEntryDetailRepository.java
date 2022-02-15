package io.github.vitesbr.repository;

import io.github.vitesbr.entity.KudosEntry;
import io.github.vitesbr.entity.KudosEntryDetail;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface KudosEntryDetailRepository extends JpaRepository<KudosEntryDetail, Integer> {
	List<KudosEntryDetail> findByKudosEntryOrderByPointsDesc(KudosEntry ke);
}
