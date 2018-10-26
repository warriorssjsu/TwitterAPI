package com.warriors;

import java.util.Random;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = App.class)
public class AppTest {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Before
    public void setup () {
        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
        this.mockMvc = builder.build();
    }
    
    //Test case for showFriendship() -done by Lalitha
    @Test
    public void testShowFriendship() throws Exception {

        MockHttpServletRequestBuilder builder =
                            MockMvcRequestBuilders.get("/showFriendship/warriors");


        this.mockMvc.perform(builder)
        .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.model().attribute("targetUserId", "2473006742"))
                    .andExpect(MockMvcResultMatchers.model().attribute("targetUserScreenName", "bradpitt"))
                    .andExpect(MockMvcResultMatchers.model().attribute("sourceUserId", "26270913"))
                    .andExpect(MockMvcResultMatchers.model().attribute("sourceUserScreenName", "warriors"))
                    .andExpect(MockMvcResultMatchers.model().attribute("targetFollowedBySource", "false"));
    }
    
  //Test case for getTags() -done by Lalitha
    @Test
    public void testGetTags() throws Exception {

        MockHttpServletRequestBuilder builder =
                            MockMvcRequestBuilders.get("/Basketball");


        this.mockMvc.perform(builder)
        .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.model()
                    .attribute("tags", Matchers.hasSize(8)));
                    //["1002897", "NBA Players", "@BasketballOrg/nba-players", "nba-players", "/BasketballOrg/lists/nba-players", "BasketballOrg", "Santa Monica, CA", "Fundamental Basketball Resource and Guide. Enhancing the abilities of basketball players, teams and coaches"]")));
    }
    
    
  //Test case for getUserStatus() -done by Lalitha
    @Test
    public void testGetUserStatus() throws Exception {

        MockHttpServletRequestBuilder builder =
                            MockMvcRequestBuilders.get("/status/warriors");


        this.mockMvc.perform(builder)
        .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.model().attributeExists("status"));

    }

    
  //Test case for createFriendship() -done by Rohini
    @Test
    public void testCreateFriendship() throws Exception {

        MockHttpServletRequestBuilder builder =
                            MockMvcRequestBuilders.get("/create/warriors");


        this.mockMvc.perform(builder)
        .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.model().attribute("name","Golden State Warriors"))
                    .andExpect(MockMvcResultMatchers.model().attribute("email","null"))
                    .andExpect(MockMvcResultMatchers.model().attribute("screenName","warriors"))
                    .andExpect(MockMvcResultMatchers.model().attribute("location","Oakland, CA"))
                    .andExpect(MockMvcResultMatchers.model().attribute("description","🏆🏆🏆🏆🏆🏆 • #DubNation • #WarriorsGround"))
                    .andExpect(MockMvcResultMatchers.model().attribute("url","https://t.co/Z122zHL6yV"));

    }
    
    
  //Test case for reTweet() -done by Rohini
    @Test
    public void testReTweet() throws Exception {

        MockHttpServletRequestBuilder builder =
                            MockMvcRequestBuilders.get("/reTweet/warriors");


        this.mockMvc.perform(builder)
        .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.model().attribute("reTweet","RT @dugg: Sept. 21: Ron Paul on CNN's Late Edition with Wolf Blitzer: Ron Paul discusses the bailout of America's fina.. http://tinyurl.com…"));

    }
    
    
  //Test case for unRetweet -done by Rohini
    @Test
    public void testUnReTweet() throws Exception {

        MockHttpServletRequestBuilder builder =
                            MockMvcRequestBuilders.get("/unRetweet/warriors");


        this.mockMvc.perform(builder)
        .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.model().attribute("unRetweet","Sept. 21: Ron Paul on CNN's Late Edition with Wolf Blitzer: Ron Paul discusses the bailout of America's fina.. http://tinyurl.com/3pdzqs"));

    }
    
    
  //Test case for getTrends() -done by Pooja
    @Test
    public void testGetTrends() throws Exception {

        MockHttpServletRequestBuilder builder =
                            MockMvcRequestBuilders.get("/trends/warriors");


        this.mockMvc.perform(builder)
        .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    
                    //Commenting out the dynamic data which will give different results evrytime 
                    //because of the updates done on twitter
                    
                   // .andExpect(MockMvcResultMatchers.model().attribute("woeid","1"))
                   // .andExpect(MockMvcResultMatchers.model().attribute("placeName","Supername"))
                   // .andExpect(MockMvcResultMatchers.model().attribute("placeCode","19"))
                   // .andExpect(MockMvcResultMatchers.model().attribute("name","Worldwide"))
                    .andExpect(MockMvcResultMatchers.model().attribute("url","http://where.yahooapis.com/v1/place/1"));

    }
    
    //Test case for updateStatus() -done by Pooja
    @Test
    public void testUpdateStatus() throws Exception {
    	
    	Random r = new Random();
    	String status = "Testing Update status" +" " +r.nextInt();
        MockHttpServletRequestBuilder builder =        		
                            MockMvcRequestBuilders.get("/update/status").param("status", status);


        this.mockMvc.perform(builder)
        .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.model().attribute("text",status))
                    .andExpect(MockMvcResultMatchers.model().attribute("source","<a href=\"https://Warriors-TwitterAPI.com\" rel=\"nofollow\">Twitter-SpringBoot</a>"))
                    .andExpect(MockMvcResultMatchers.model().attribute("geoLocation","null"))
                    .andExpect(MockMvcResultMatchers.model().attribute("place","null"))
                    .andExpect(MockMvcResultMatchers.model().attribute("retweetCount","0"));

    }
    
  //Test case for updateStatus() duplicate -done by Pooja
    @Test
    public void testUpdateStatusDuplicate() throws Exception {
    	
    	String status = "Testing Update status";
        MockHttpServletRequestBuilder builder =        		
                            MockMvcRequestBuilders.get("/update/status").param("status", status);


        this.mockMvc.perform(builder)
        .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.model().attribute("error","This status is duplicate , please provide some other status"));
                    

    }
    
  //Test case for getTimeline() -done by Pooja
    @Test
    public void testGetTimeline() throws Exception {

        MockHttpServletRequestBuilder builder =
                            MockMvcRequestBuilders.get("/timeline/warriors");


        this.mockMvc.perform(builder)
        .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                  //Commenting out the dynamic data which will give different results evrytime 
                    //because of the updates done on twitter
                    //.andExpect(MockMvcResultMatchers.model().attribute("source","<a href=\"http://twitter.com\" rel=\"nofollow\">Twitter Web Client</a>"))
                    //.andExpect(MockMvcResultMatchers.model().attribute("retweetCount","834"))
                    .andExpect(MockMvcResultMatchers.model().attribute("userName","Warriors"))
                    .andExpect(MockMvcResultMatchers.model().attribute("screenName","Warrior86219812"));;

    }
    


}
