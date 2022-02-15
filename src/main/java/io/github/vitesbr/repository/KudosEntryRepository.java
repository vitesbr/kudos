package io.github.vitesbr.repository;

import io.github.vitesbr.entity.KudosEntry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface KudosEntryRepository extends JpaRepository<KudosEntry, Integer> {
	@Query(value = "select * from kudos_entry where id_user_received = :idUserReceived order by date_time DESC", nativeQuery = true)
    List<KudosEntry> getByIdUserReceived( @Param("idUserReceived") Integer idUserReceived);
}
