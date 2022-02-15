package io.github.vitesbr;

import io.github.vitesbr.entity.KudosConversion;
import io.github.vitesbr.entity.User;
import io.github.vitesbr.repository.KudosConversionRepository;
import io.github.vitesbr.repository.UserRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class KudosApplication {

    @Bean
    public CommandLineRunner init(
            @Autowired UserRepository userRepository,
            @Autowired KudosConversionRepository kudosConversionRepository
//            ,@Autowired KudosService kudosService
    ) {
        return args -> {

        	List<User> users = new ArrayList() {
        		private static final long serialVersionUID = 1L;
        		{
        			add(new User("Batman", "https://pngimg.com/uploads/batman/batman_PNG102.png"));
        			add(new User("Captain America", "https://pngimg.com/uploads/captain_america/captain_america_PNG87.png"));
        			add(new User("Lara Croft", "https://pngimg.com/uploads/lara_croft/lara_croft_PNG65.png"));
        			add(new User("Wonder Woman", "https://pngimg.com/uploads/wonder_woman/wonder_woman_PNG43.png"));
        		}
        	};

        	users.stream().forEach(u -> userRepository.save(u));

        	List<KudosConversion> kudos = new ArrayList() {
        		private static final long serialVersionUID = 1L;
        		{
        			add(new KudosConversion("OK", 5, new BigDecimal("2")));
        			add(new KudosConversion("NICE", 10, new BigDecimal("5")));
        			add(new KudosConversion("GOOD", 20, new BigDecimal("8")));
        			add(new KudosConversion("GREAT", 50, new BigDecimal("15")));
        			add(new KudosConversion("SUPER", 100, new BigDecimal("25")));
        		}
        	};

        	kudos.stream().forEach(k -> kudosConversionRepository.save(k));

        	users = userRepository.findAll();
        	kudos = kudosConversionRepository.findAll();

        	System.out.println("\nUsers\n");
        	users.forEach(u -> System.out.println(u));

        	System.out.println("\nKudos Conversion\n");
        	kudos.forEach(k -> System.out.println(k));

        	/**
        	 * Criar Kudos dados para teste
        	 */

        	/*
        	kudosService.giveKudos(1, 2, 70);

        	System.out.println("\nKudos Entry Test\n");

        	List<KudosEntryDTO> kudosEntry = kudosService.getKudosEntry(2);
        	kudosEntry.forEach(ke -> System.out.println(ke));
        	*/

        	System.out.println("\n\n");
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(KudosApplication.class, args);
    }
}
