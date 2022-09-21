package com.rpgame.rpg;

import static org.junit.jupiter.api.Assertions.assertEquals;


import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest()
@AutoConfigureMockMvc
class RpgApplicationTests {

	@Autowired
	public MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	@Test
	void whenALootIsCreated_defineTypeOfLoot(){
		int luck = 0;
		int paramRandomEntry = 0;
		String expected = "Stuff";
		String result = "";
		double stuffPercent = 25;
		double percentRest = 0;
		double ressourcesPercent = 45;
		double nothingPercent = 0;

		if(luck > 30){
			percentRest = luck - 30;
		} else {
			percentRest = 0;
		}

		if(luck <= 55){
			stuffPercent += luck;
			ressourcesPercent -= percentRest;
		} else {
			stuffPercent = 80;
			ressourcesPercent = 20;
		}

		if(luck <= 30) {
			nothingPercent -= luck;
		} else {
			nothingPercent = 0;
		}

		if(paramRandomEntry <= stuffPercent){
			result = "Stuff";
		} else if ( paramRandomEntry > stuffPercent && paramRandomEntry < stuffPercent + ressourcesPercent){
			result = "Ressources";
		} else {
			result = "Rien";
		}
		assertEquals(expected, result);
	}

	@Test
	public void testGetLoot() throws Exception {
		mockMvc.perform(get("/Loot"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].type", is("Ressource")));
	}

	@Test
	public void testGetLuckStat() throws Exception {
		mockMvc.perform(get("/Stats/1"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.luck", is(50)));
	}

	@Test
	public void testCreateRandomLoot() throws Exception {
		mockMvc.perform(post("/Randomdrop/10"))
			.andExpect(status().isOk());			
	}

}
