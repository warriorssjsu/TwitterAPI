package com.warriors;

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


}
