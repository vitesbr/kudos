package io.github.vitesbr.controller;

import io.github.vitesbr.dto.GiveKudosDTO;
import io.github.vitesbr.dto.KudosEntryDTO;
import io.github.vitesbr.service.KudosService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/kudos")
public class KudosController {

	@Autowired KudosService kudosService;

    @GetMapping("{id}")
    public List<KudosEntryDTO> getById( @PathVariable Integer id ){
        return this.kudosService.getKudosEntry(id);
    }

    @PostMapping
    public void save( @RequestBody GiveKudosDTO dto ){
    	this.kudosService.giveKudos(dto.getIdUserGiven(), dto.getIdUserReceived(), dto.getPoints());
    }
}
