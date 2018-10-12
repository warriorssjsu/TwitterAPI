/*package com.warriors;

import static org.junit.Assert.*;

import org.junit.Test;

public class AppTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
*/
package com.warriors;

import static org.junit.Assert.*;


import org.junit.Test;


import static org.junit.Assert.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import twitter4j.Twitter;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc


//@SpringBootTest

public class AppTest {
	
	@Autowired
	private MockMvc mockMvc;
 
	@MockBean
	private App app;
	


	@Test
	public void test() throws Exception {
		App a = new App();
		String uri = "/user/Warriors";
	 MvcResult result =   mockMvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
		  String content = result.getResponse().getContentAsString();
		
		  int status = result.getResponse().getStatus();
		  System.out.println("Status is :" + status);
		  System.out.println("Content is :" + content);
		   
	/*	mockMvc.perform(MockMvcRequestBuilders.get("/user/Warriors").accept(MediaType.ALL_VALUE))
		 // . andExpect(content().contentType(MediaType.ALL_VALUE))
		 //  .andExpect(jsonPath("$.message").value("ToDo to update doesnÂ´t exist"))
		   
		.andDo(print());*/


		/*mockMvc.perform(MockMvcRequestBuilders.get("/users/warriors").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.id").exists());*/
		   
		mockMvc.perform((get("/user/Warriors")))
      .andExpect(status().isOk());
   // andExpect(content().contentType(MediaType.ALL_VALUE));
		mockMvc.perform((get("/followers/Warriors")))
	      .andExpect(status().isOk());
	   //   .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
		mockMvc.perform((get("/Cricket")))
	      .andExpect(status().isOk());
		mockMvc.perform((get("/create/Warriors")))
	      .andExpect(status().isOk());
		mockMvc.perform((get("/tweets/Warriors")))
	      .andExpect(status().isOk());
		//mockMvc.perform((get("/unRetweet/Warriors")))
	     // .andExpect(status().isOk());
		//mockMvc.perform((get("/trends/Warriors")))
	  //   .andExpect(status().isOk());
		mockMvc.perform((get("/update/Warriors")))
	      .andExpect(status().isOk());
		mockMvc.perform((get("/timeline/Warriors")))
	      .andExpect(status().isOk());
		
}

}
