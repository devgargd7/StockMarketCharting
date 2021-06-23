package com.stockmarketcharting;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.http.MediaType;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stockmarketcharting.dto.addCompanyDto;
import com.stockmarketcharting.dto.companyDto;
import com.stockmarketcharting.dto.getStockPriceDto;
import com.stockmarketcharting.dto.ipoDto;
import com.stockmarketcharting.dto.stockCodeDto;
import com.stockmarketcharting.model.StockExchange;

@SpringBootTest
class DemoApplicationTests {


	@Autowired
	MockMvc mvc;
	
	@Test
	void contextLoads(){
	}
	
	@Test
	void getAllCompaniesTest() throws Exception{
		String uri = "/admin/companies";
		   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
		      .accept(org.springframework.http.MediaType.APPLICATION_JSON)).andReturn();
		   
		   int status = mvcResult.getResponse().getStatus();
		   assertEquals(200, status);
	}

	@Test
	void getBySectorTest() throws Exception{
		String uri = "/admin/companies/sector/1";
		   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
		      .accept(MediaType.APPLICATION_JSON)).andReturn();
		   
		   int status = mvcResult.getResponse().getStatus();
		   assertEquals(200, status);
	}
	
	@Test
	void getAllCompaniesMatchingTest() throws Exception{
		String uri = "/admin/companies/sta";
		   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
		      .accept(MediaType.APPLICATION_JSON)).andReturn();
		   
		   int status = mvcResult.getResponse().getStatus();
		   assertEquals(200, status);
	}
	
//	@Test
//	void deleteCompanyTest() throws Exception{
//		String uri = "/admin/companies/delete/{id}";
//		   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
//		      .accept(MediaType.APPLICATION_JSON)).andReturn();
//		   
//		   int status = mvcResult.getResponse().getStatus();
//		   assertEquals(200, status);
//	}
	
	@Test
	void updateCompanyTest() throws Exception{
		String uri = "/admin/companies/update/17";
		ObjectMapper objectMapper = new ObjectMapper();

		companyDto companyDto = new com.stockmarketcharting.dto.companyDto("state bank of india","finance","BSE,","",0L,"","test");
//		stockCodeDto stockCodeDto =new stockCodeDto("123456","BSE");
		stockCodeDto[] stockCodeDtos = new stockCodeDto[] {new stockCodeDto("500112","BSE")};
		addCompanyDto addCompanyDto = new com.stockmarketcharting.dto.addCompanyDto(companyDto, stockCodeDtos);
	      String json =  objectMapper.writeValueAsString(addCompanyDto);
		
		   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				   .contentType(MediaType.APPLICATION_JSON)
				   .content(json)
		      .accept(MediaType.APPLICATION_JSON)).andReturn();
		   
		   int status = mvcResult.getResponse().getStatus();
		   assertEquals(200, status);
//		   mvcResult.getResponse().
//		   uri = "/admin/companies/delete/{id}";
//		   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
//		      .accept(MediaType.APPLICATION_JSON)).andReturn();
//		   
//		   int status = mvcResult.getResponse().getStatus();
//		   assertEquals(200, status);
	}
	
	@Test
	void addCompanyTest() throws Exception{
		String uri = "/admin/copmanies/add";
		ObjectMapper objectMapper = new ObjectMapper();

		companyDto companyDto = new companyDto("test company","finance","BSE,","",0L,"","test");
//		stockCodeDto stockCodeDto =new stockCodeDto("123456","BSE");
		stockCodeDto[] stockCodeDtos = new stockCodeDto[] {new stockCodeDto("123456","BSE")};
		addCompanyDto addCompanyDto = new addCompanyDto(companyDto, stockCodeDtos);
	      String json =  objectMapper.writeValueAsString(addCompanyDto);
		
		   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				   .contentType(MediaType.APPLICATION_JSON)
				   .content(json)
		      .accept(MediaType.APPLICATION_JSON)).andReturn();
		   
		   int status = mvcResult.getResponse().getStatus();
		   assertEquals(200, status);
	}
	
	@Test
	void getStockPriceTest() throws Exception{
		String uri = "/user/companies/stockprice";
		ObjectMapper objectMapper = new ObjectMapper();
		StockExchange StockExchange = new StockExchange(1L,"BSE","bombay stock exchange","mumbai","");
		getStockPriceDto getStockPriceDto =new getStockPriceDto("state bank of india",StockExchange,"2019-06-07","2019-06-09");
		
	      String json =  objectMapper.writeValueAsString(getStockPriceDto);
		
		   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				   .contentType(MediaType.APPLICATION_JSON)
				   .content(json)
		      .accept(MediaType.APPLICATION_JSON)).andReturn();
		   
		   int status = mvcResult.getResponse().getStatus();
		   assertEquals(200, status);
	}
	
	@Test
	void getAllIposTest() throws Exception{
		String uri = "/admin/ipos";
		   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
		      .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		   
		   int status = mvcResult.getResponse().getStatus();
		   assertEquals(200, status);
	}
	
	@Test
	void getAllIposUserTest() throws Exception{
		String uri = "/user/ipos";
		   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
		      .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		   
		   int status = mvcResult.getResponse().getStatus();
		   assertEquals(200, status);
	}
	
//	@Test
//	void deleteIpoTest() throws Exception{
//		String uri = "/admin/ipos/detele";
//		   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
//		      .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
//		   
//		   int status = mvcResult.getResponse().getStatus();
//		   assertEquals(200, status);
//	}
	
//	@Test
//	void updateIpoTest() throws Exception{
//		String uri = "/admin/ipos/update";
//		   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
//		      .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
//		   
//		   int status = mvcResult.getResponse().getStatus();
//		   assertEquals(200, status);
//	}
	
	@Test
	void addIpoTest() throws Exception{
		String uri = "/admin/ipos/add";
		ObjectMapper objectMapper = new ObjectMapper();
		ipoDto ipoDto = new com.stockmarketcharting.dto.ipoDto("state bank of india","NSE",100L,10000L,"2021-06-20 13:30:30","");
	      String json =  objectMapper.writeValueAsString(ipoDto);
		
		   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				   .contentType(MediaType.APPLICATION_JSON)
				   .content(json)
		      .accept(MediaType.APPLICATION_JSON)).andReturn();
		   
		   int status = mvcResult.getResponse().getStatus();
		   assertEquals(200, status);
	}
}
