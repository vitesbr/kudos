package io.github.vitesbr.service.impl;

import io.github.vitesbr.dto.KudosEntryDTO;
import io.github.vitesbr.dto.KudosEntryDetailDTO;
import io.github.vitesbr.entity.KudosConversion;
import io.github.vitesbr.entity.KudosEntry;
import io.github.vitesbr.entity.KudosEntryDetail;
import io.github.vitesbr.entity.User;
import io.github.vitesbr.repository.KudosConversionRepository;
import io.github.vitesbr.repository.KudosEntryDetailRepository;
import io.github.vitesbr.repository.KudosEntryRepository;
import io.github.vitesbr.repository.UserRepository;
import io.github.vitesbr.service.KudosService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class KudosServiceImpl implements KudosService {

	@Autowired UserRepository userRepository;
	@Autowired KudosEntryRepository kudosEntryRepository;
	@Autowired KudosEntryDetailRepository kudosEntryDetailRepository;
	@Autowired KudosConversionRepository kudosConversionRepository;

	@Override
	public List<KudosEntryDTO> getKudosEntry(Integer idUserReceived) {
		List<KudosEntryDTO> result = new ArrayList<KudosEntryDTO>();

		Optional<User> user = this.userRepository.findById(idUserReceived);

		List<KudosEntry> kudosEntryList = this.kudosEntryRepository.getByIdUserReceived(user.get().getId());

		for (KudosEntry ke : kudosEntryList) {
			KudosEntryDTO dto = new KudosEntryDTO();

			dto.setId(ke.getId());
			dto.setUserGiven(ke.getUserGiven());
			dto.setUserReceived(ke.getUserReceived());
			dto.setDateTime(ke.getDateTime());

			List<KudosEntryDetail> kudosEntryDetailList = this.kudosEntryDetailRepository.getByIdKudosEntry(ke.getId());

			dto.setDetails(new ArrayList<>());

			kudosEntryDetailList.forEach(ked -> dto.getDetails().add(
					new KudosEntryDetailDTO(ked.getId(), ked.getKudosConversion(), ked.getPoints(), ked.getReward())));

			dto.getDetails().stream().forEach( d -> {
				dto.addRewards(d.getReward());
				dto.addKudos(d.getKudosConversion().getName());
			});

			result.add(dto);
		}

		return result;
	}

	@Override
	@Transactional
	public void giveKudos(Integer idUserGiven, Integer idUserReceived, Integer points) {

		Optional<User> userGiven = this.userRepository.findById(idUserGiven);
		Optional<User> userReceived = this.userRepository.findById(idUserReceived);
		List<KudosConversion> pointsConversionList = this.kudosConversionRepository.getByPointsDesc();

		int restPoints = points.intValue();
		List<KudosEntryDetailDTO> entryDetailDtoList = new ArrayList<>();

		for (KudosConversion kcp : pointsConversionList) {
			while (restPoints >= kcp.getPoints().intValue()) {

				KudosEntryDetailDTO dto = new KudosEntryDetailDTO();

				dto.setKudosConversion(kcp);
				dto.setPoints(kcp.getPoints());
				dto.setReward(kcp.getReward());

				entryDetailDtoList.add(dto);
				restPoints -= kcp.getPoints().intValue();
			}
		}

		if (entryDetailDtoList.size() > 0) {
			KudosEntry ke = new KudosEntry();

			ke.setUserGiven(userGiven.get());
			ke.setUserReceived(userReceived.get());
			ke.setDateTime(LocalDate.now());

			this.kudosEntryRepository.save(ke);

			List<KudosEntryDetail> kedList = entryDetailDtoList
					.stream()
					.map( dto -> {

						KudosEntryDetail ked = new KudosEntryDetail(
								ke,
								dto.getKudosConversion(),
								dto.getPoints(),
								dto.getReward()
								);

						return ked;
					}).collect(Collectors.toList());

			this.kudosEntryDetailRepository.saveAll(kedList);
		}

	}
}
