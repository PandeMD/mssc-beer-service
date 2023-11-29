package ms.cloud.guru.service.beer.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import ms.cloud.guru.service.beer.web.model.BeerDto;

@WebMvcTest
class BeerControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper; 
	
	@Test
	public void testGetBeerById() throws Exception {
		mockMvc.perform(get("/api/v1/beer/" +UUID.randomUUID().toString())
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		
	}

	@Test
	public void testSaveNewBeer() throws Exception {
		 BeerDto beerDto = BeerDto.builder().build();
		 
		 String beerDtoJson = objectMapper.writeValueAsString(beerDto);
		 
		 mockMvc.perform(post("/api/v1/beer/")
				 .contentType(MediaType.APPLICATION_JSON)
				 .content(beerDtoJson))
		         .andExpect(status().isCreated());
	}

	@Test
	public void testUpdateBeerById() throws Exception {
		BeerDto beerDto = BeerDto.builder().build();
		
		String beerDtoJson = objectMapper.writeValueAsString(beerDto);
		
		mockMvc.perform(put("/api/v1/beer/" +UUID.randomUUID().toString())
				.contentType(MediaType.APPLICATION_JSON)
				.content(beerDtoJson))
				.andExpect(status().isNoContent());
	}

}
