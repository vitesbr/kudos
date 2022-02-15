package io.github.vitesbr.service;

import java.util.List;

import io.github.vitesbr.dto.KudosEntryDTO;

public interface KudosService {
	List<KudosEntryDTO> getKudosEntry(Integer idUserReceived);
	void giveKudos(Integer idUserGiven, Integer idUserReceived, Integer points);
}
