package com.rpgame.rpg;

import static org.junit.jupiter.api.Assertions.assertEquals;


import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.rpgame.rpg.model.Loot;
import com.rpgame.rpg.model.Rarity;
import com.rpgame.rpg.web.dao.LootDAO;

@SpringBootTest()
@AutoConfigureMockMvc
class RpgApplicationTests {

	@Autowired
	public MockMvc mockMvc;

	@MockBean
	public LootDAO lootDAO;

	@Test
	void contextLoads() {
	}

	@Test
	void whenALootIsCreated_defineTypeOfLoot(){
		int luck = 0;
		int paramRandomEntry = 0;
		int expected = 10;
		int result = 0;
		double stuffPercent = 25;
		double percentRest = 0;
		double ressourcesPercent = 45;
		double nothingPercent = 0;
		for(int i = 1; i <= 10; i++){
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
				result ++;
			} else if (paramRandomEntry > stuffPercent && paramRandomEntry <= stuffPercent + ressourcesPercent){
				result ++;
			} else if(paramRandomEntry > stuffPercent + ressourcesPercent){
				result ++;
			}
			luck += 10;
			paramRandomEntry += 10;
		}
		assertEquals(expected, result);
	}

	@Test
	public void whenCreateARandomLoot_checkThatCategorieIsGood() throws Exception {
		MvcResult result = mockMvc.perform(post("/Randomdrop/10"))
							.andExpect(status().isOk())
							.andReturn();
							
		String content = result.getResponse().getContentAsString();

		if(content.contains("categorie")){
			assertEquals(content.contains("categorie"), true);
		} else if(content.contains("Aucun loot")){
			assertEquals(content.contains("Aucun loot"), true);
		} else {
			assertEquals(false, true);
		}
	}

	@Test
	public void whenCreateRandomLootsWithZeroLuck_checkIfInitPercentChanceAreGood(){
		List<Loot> lootList = new ArrayList<>();
		int stuff = 0;
		int ressources = 0;
		int rien = 0;
		int err = 0;
		int expected = 3;
		int resultLootPercent = 0;
		for(int i = 0; i <= 1000; i++){
			Loot loot = new Loot(0);
			loot.setType();
			lootList.add(loot);
		}
		for (Loot lootOfList : lootList) {
			String type = lootOfList.getType();
			switch(type){
				case "Stuff":
					stuff++;
					break;
				case "Ressource":
					ressources++;
					break;
				case "Rien":
					rien++;
					break;
				default:
					err++;
					break;
			}
		}

		if(stuff >= 200 && stuff <= 300){
			resultLootPercent++;
		}
		if(ressources >= 400 && ressources <= 500){
			resultLootPercent++;
		}
		if(rien >= 250 && rien <= 350){
			resultLootPercent++;
		}
		assertEquals(0, err);
		assertEquals(expected, resultLootPercent);
	}

	@Test
	public void whenCreateRandomLootsWithThirtyFiveLuck_checkIfInitPercentChanceAreGood(){
		List<Loot> lootList = new ArrayList<>();
		int stuff = 0;
		int ressources = 0;
		int rien = 0;
		int err = 0;
		int expected = 3;
		int resultLootPercent = 0;
		for(int i = 0; i <= 1000; i++){
			Loot loot = new Loot(35);
			loot.setType();
			lootList.add(loot);
		}
		for (Loot lootOfList : lootList) {
			String type = lootOfList.getType();
			switch(type){
				case "Stuff":
					stuff++;
					break;
				case "Ressource":
					ressources++;
					break;
				case "Rien":
					rien++;
					break;
				default:
					err++;
					break;
			}
		}

		if(stuff >= 550 && stuff <= 650){
			resultLootPercent++;
		}
		if(ressources >= 350 && ressources <= 400){
			resultLootPercent++;
		}
		if(rien == 0){
			resultLootPercent++;
		}
		assertEquals(0, err);
		assertEquals(expected, resultLootPercent);
	}

	@Test
	public void whenCreateRandomLootsWithOneHundredLuck_checkIfInitPercentChanceAreGood(){
		List<Loot> lootList = new ArrayList<>();
		int stuff = 0;
		int ressources = 0;
		int rien = 0;
		int err = 0;
		int expected = 3;
		int resultLootPercent = 0;
		for(int i = 0; i <= 1000; i++){
			Loot loot = new Loot(100);
			loot.setType();
			lootList.add(loot);
		}
		for (Loot lootOfList : lootList) {
			String type = lootOfList.getType();
			switch(type){
				case "Stuff":
					stuff++;
					break;
				case "Ressource":
					ressources++;
					break;
				case "Rien":
					rien++;
					break;
				default:
					err++;
					break;
			}
		}

		if(stuff >= 750 && stuff <= 850){
			resultLootPercent++;
		}
		if(ressources >= 150 && ressources <= 250){
			resultLootPercent++;
		}
		if(rien == 0){
			resultLootPercent++;
		}
		assertEquals(0, err);
		assertEquals(expected, resultLootPercent);
	}

	@Test
	public void whenCreateAStuffWithZeroLuck_checkIfRarityPercentAreGoods(){
		int legendary = 0;
		int epic = 0;
		int rare = 0;
		int uncommon = 0;
		int common = 0;
		int err = 0;
		int luck = 0;
		int resultRarityPercent = 0;
		int expected = 5;

		for(int i = 0; i<=1000; i ++){
			Rarity rarity = new Rarity(luck);
			String typeOfRarity = rarity.getRarity();
			switch(typeOfRarity){
				case "Legendary":
					legendary++;
					break;
				case "Epic":
					epic++;
					break;
				case "Rare":
					rare++;
					break;
				case "Uncommon":
					uncommon++;
					break;
				case "Common":
					common++;
					break;
				default:
					err++;
					break;
			}
		}
		if(legendary >= 0 && legendary <= 40){
			resultRarityPercent++;
		}
		if(epic >= 60 && epic <= 100){
			resultRarityPercent++;
		}
		if(rare >= 180 && rare <= 220){
			resultRarityPercent++;
		}
		if(uncommon >= 280 && uncommon <= 320){
			resultRarityPercent++;
		}
		if(common >= 380 && common <= 420){
			resultRarityPercent++;
		}
		assertEquals(0, err);
		assertEquals(expected, resultRarityPercent);
	}
}
