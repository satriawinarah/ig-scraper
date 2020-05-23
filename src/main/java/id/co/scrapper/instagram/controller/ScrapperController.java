package id.co.scrapper.instagram.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import id.co.scrapper.instagram.service.ScrapperService;

@RestController
public class ScrapperController {

	Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ScrapperService scrapperService;
	
	@GetMapping("api/v1/hashtag")
	public void processHashTag(@RequestParam("scrap") String scrap) {
		log.info("Processing request with hastag: {}", scrap);
		
		scrapperService.processHashtag(scrap);
	}
    
	@GetMapping("api/v1/post")
	public void processPost(@RequestParam("scrap") String scrap) {
		log.info("Processing post by short code : {}", scrap);
		
		scrapperService.processPost(scrap);
	}
	
}