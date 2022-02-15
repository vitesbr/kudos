package io.github.vitesbr.repository;

import io.github.vitesbr.entity.KudosEntry;
import io.github.vitesbr.entity.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface KudosEntryRepository extends JpaRepository<KudosEntry, Integer> {
	List<KudosEntry> findByUserReceivedOrderByDateTimeDesc(User u);
}
