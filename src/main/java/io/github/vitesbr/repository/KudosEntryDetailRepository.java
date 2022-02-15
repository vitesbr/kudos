package io.github.vitesbr.repository;

import io.github.vitesbr.entity.KudosEntryDetail;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface KudosEntryDetailRepository extends JpaRepository<KudosEntryDetail, Integer> {
	@Query(value = "select * from kudos_entry_detail where id_kudos_entry = :idKudosEntry order by points DESC", nativeQuery = true)
    List<KudosEntryDetail> getByIdKudosEntry( @Param("idKudosEntry") Integer idKudosEntry);
}
