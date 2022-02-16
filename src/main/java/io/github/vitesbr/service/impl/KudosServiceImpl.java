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
import io.github.vitesbr.utils.JExtenso;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class KudosServiceImpl implements KudosService {

	@Autowired UserRepository userRepository;
	@Autowired KudosEntryRepository kudosEntryRepository;
	@Autowired KudosEntryDetailRepository kudosEntryDetailRepository;
	@Autowired KudosConversionRepository kudosConversionRepository;

	@Override
	public List<KudosEntryDTO> getKudosEntry(Integer idUserReceived) {
		List<KudosEntryDTO> result = new ArrayList<KudosEntryDTO>();

		User user = this.userRepository
				.findById(idUserReceived)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found"));

		List<KudosEntry> kudosEntryList = this.kudosEntryRepository.findByUserReceivedOrderByDateTimeDesc(user);

		for (KudosEntry ke : kudosEntryList) {

			KudosEntryDTO dto = new KudosEntryDTO();

			dto.setId(ke.getId());
			dto.setUserGiven(ke.getUserGiven());
			dto.setUserReceived(ke.getUserReceived());
			dto.setDateTime(ke.getDateTime());

			List<KudosEntryDetail> kudosEntryDetailList = this.kudosEntryDetailRepository.findByKudosEntryOrderByPointsDesc(ke);

			dto.setDetails(new ArrayList<>());

			kudosEntryDetailList.forEach(ked -> dto.getDetails().add(
					new KudosEntryDetailDTO(ked.getId(), ked.getKudosConversion(), ked.getPoints(), ked.getReward())));

			dto.getDetails().stream().forEach( d -> {
				dto.addRewards(d.getReward());
				dto.addKudos(d.getKudosConversion().getName());
			});

			JExtenso extenso = new JExtenso(dto.getRewards());
			dto.setRewardsDescription(extenso.toString());

			result.add(dto);
		}

		return result;
	}

	@Override
	@Transactional
	public void giveKudos(Integer idUserGiven, Integer idUserReceived, Integer points) {

		User userGiven = this.userRepository
				.findById(idUserGiven)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));;

		User userReceived = this.userRepository
				.findById(idUserReceived)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));;

		List<KudosConversion> pointsConversionList = this.kudosConversionRepository.findAllByOrderByPointsDesc();

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

			ke.setUserGiven(userGiven);
			ke.setUserReceived(userReceived);
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
