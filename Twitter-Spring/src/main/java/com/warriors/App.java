package com.warriors;



import java.util.ArrayList;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

/**
 * @param <Tweet>
 *
 */
@EnableWebMvc
@Controller

public class App
{
	
	ObjectMapper mapper = new ObjectMapper();
	
	//Twitter set up to access the API - done by Rohini
	public Twitter gettwitter() {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		.setOAuthConsumerKey("GfncVY17T236MbmxXcjnG10Mb")
		.setOAuthConsumerSecret("rWBQ5vygVGz0IKUF5ARziJK2cLaqF2TJ6XGlkaciML0N7D0X7i")
		.setOAuthAccessToken("1044672710075277312-BqEdf12WsiDGMA0jQ2m2VBtmOcnEOM")
		.setOAuthAccessTokenSecret("bt7AVbSTTtYbIR2Wal3YxClhPbY70aKNUWnK7QCPXT0RI");
		
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();
		return twitter;
	}
	
	//Home Page mapping
	@RequestMapping("/")
	public String getHome() {
		return "home";
	}
 
	//Twitter API to getTags from getUserLists - done by Lalitha
	@RequestMapping(value="{getTags}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String getTags(@PathVariable final String getTags, Model model) throws TwitterException, JsonProcessingException{
		App app= new App();
		Twitter twitter=app.gettwitter();
		
		String jsonResult = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(twitter.getUserLists(getTags).get(0));
		JSONObject json = new JSONObject(jsonResult);
		
		ArrayList<Object> li = new ArrayList<Object>();
		li.add(json.get("id"));
		li.add(json.get("name"));
		li.add(json.get("fullName"));
		li.add(json.get("slug"));
		li.add(json.get("uri"));
		li.add(json.getJSONObject("user").getString("screenName"));
		li.add(json.getJSONObject("user").getString("location"));
		li.add(json.getJSONObject("user").getString("description"));
		
		model.addAttribute("tags", li);
		
		return "tags";
	}

	
    //Twitter API to get user status - done by Lalitha
	@RequestMapping(value="/status/{getName}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String getUserStatus(@PathVariable final String getName, Model model) throws TwitterException, JsonProcessingException{
		App app= new App();
		Twitter twitter=app.gettwitter();
		
		String jsonResult = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(twitter.lookupUsers(getName).get(0).getStatus());
		JSONObject json = new JSONObject(jsonResult);
		model.addAttribute("status",json.getString("text"));
		return "status";
	}
	
    
	//Twitter API to show friendship between two users - done by Lalitha
	@RequestMapping(value="/showFriendship/{getName}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String showFriendship(@PathVariable final String getName, Model model) throws TwitterException, JsonProcessingException{
		App app= new App();
		Twitter twitter=app.gettwitter();
		
		String jsonResult = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(twitter.showFriendship("warriors", "BradPitt"));
		
		JSONObject json = new JSONObject(jsonResult);
		model.addAttribute("accessLevel", json.getString("accessLevel"));
		model.addAttribute("targetUserId", json.getString("targetUserId"));
		model.addAttribute("targetUserScreenName", json.getString("targetUserScreenName"));
		model.addAttribute("sourceUserId", json.getString("sourceUserId"));
		model.addAttribute("sourceUserScreenName", json.getString("sourceUserScreenName"));
		model.addAttribute("targetFollowedBySource", json.getString("targetFollowedBySource"));
		return "showFriendship";
	}
	
	//Twitter API to create friendship with a user - done by Rohini
	@RequestMapping(value="/create/{getName}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String createFriendship(@PathVariable final String getName,Model model) throws TwitterException, JsonProcessingException{
		App app= new App();
		Twitter twitter=app.gettwitter();
		
		String jsonResult = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(twitter.createFriendship(getName));
		
		JSONObject json = new JSONObject(jsonResult);
		model.addAttribute("accessLevel", json.getString("accessLevel"));
		model.addAttribute("id", json.getString("id"));
		model.addAttribute("name", json.getString("name"));
		model.addAttribute("email", json.getString("email"));
		model.addAttribute("screenName", json.getString("screenName"));
		model.addAttribute("location", json.getString("location"));
		model.addAttribute("description", json.getString("description"));
		model.addAttribute("url", json.getString("url"));
		model.addAttribute("followersCount", json.getString("followersCount"));
		model.addAttribute("status",json.getJSONObject("status").getString("text"));
		model.addAttribute("statusRangeStart",json.getJSONObject("status").getString("displayTextRangeStart"));
		model.addAttribute("statusRangeEnd",json.getJSONObject("status").getString("displayTextRangeEnd"));
		
		return "friendship";
	}

   
	//Twitter API to retweet a tweet - done by Rohini
	@RequestMapping(value="/reTweet/{getName}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String reTweet(@PathVariable final String getName, Model model) throws TwitterException, JsonProcessingException{
		App app= new App();
		Twitter twitter=app.gettwitter();
		
		if(twitter.getRetweets(929960910) != null) {
		    twitter.unRetweetStatus(929960910);
		}
		String jsonResult = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(twitter.retweetStatus(929960910));
		JSONObject json = new JSONObject(jsonResult);

		model.addAttribute("reTweet", json.getString("text"));
		return "reTweet";
		}

	
	//Twitter API to unretweet a tweet - done by Rohini
	@RequestMapping(value="/unRetweet/{getName}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String unRetweet(@PathVariable final String getName, Model model) throws TwitterException, JsonProcessingException{
		App app= new App();
		Twitter twitter=app.gettwitter();
		if(twitter.getRetweets(929960910) == null) {
		    twitter.retweetStatus(929960910);
		}
		
		String jsonResult = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(twitter.unRetweetStatus(929960910));
		
		JSONObject json = new JSONObject(jsonResult);
		
		model.addAttribute("unRetweet",json.getString("text"));
		return "unRetweet";
	}

	//Twitter API to get trends - done by Pooja
	@RequestMapping(value="/trends/{getName}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String getTrends(@PathVariable final String getName, Model model) throws TwitterException, JsonProcessingException{
		App app= new App();
		Twitter twitter=app.gettwitter();
		
		String jsonResult = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(twitter.getAvailableTrends().get(0));
		
		JSONObject json = new JSONObject(jsonResult);
		
		model.addAttribute("woeid", json.getString("woeid"));
		model.addAttribute("placeName", json.getString("placeName"));
		model.addAttribute("placeCode", json.getString("placeCode"));
		model.addAttribute("name", json.getString("name"));
		model.addAttribute("url", json.getString("url"));
		
		return "trends";
	}

	//Twitter API to update status - done by Pooja
	@RequestMapping(value="/update/status", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String updateStatus(@RequestParam("status")String status, Model model) throws TwitterException, JsonProcessingException{
		App app= new App();
		Twitter twitter=app.gettwitter();
		String jsonResult ="";
		try {
			
		jsonResult = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(twitter.updateStatus(status));
		}
        catch(TwitterException te) {
        	model.addAttribute("error", "This status is duplicate , please provide some other status");
        	return "error";
		}
		JSONObject json = new JSONObject(jsonResult);
		model.addAttribute("createdAt", json.getString("createdAt"));
		model.addAttribute("id", json.getString("id"));
		model.addAttribute("text", json.getString("text"));
		model.addAttribute("source", json.getString("source"));
		model.addAttribute("geoLocation", json.getString("geoLocation"));
		model.addAttribute("place", json.getString("place"));
		model.addAttribute("retweetCount", json.getString("retweetCount"));
		model.addAttribute("hashtagEntities", json.getString("hashtagEntities"));
		
		return "updateStatus"; 

	}

	//Twitter API to get home timeline - done by Pooja
	@GetMapping(value="/timeline/{getName}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String getTimeline(@PathVariable final String getName, Model model) throws TwitterException, JsonProcessingException{
		App app= new App();
		Twitter twitter=app.gettwitter();
		
		String jsonResult = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(twitter.getHomeTimeline().get(5));
		
		JSONObject json = new JSONObject(jsonResult);
		model.addAttribute("createdAt", json.getString("createdAt"));
		model.addAttribute("id", json.getString("id"));
		model.addAttribute("text", json.getString("text"));
		model.addAttribute("source", json.getString("source"));
		model.addAttribute("geoLocation", json.getString("geoLocation"));
		model.addAttribute("place", json.getString("place"));
		model.addAttribute("retweetCount", json.getString("retweetCount"));
		model.addAttribute("userName", json.getJSONObject("user").getString("name"));
		model.addAttribute("screenName", json.getJSONObject("user").getString("screenName"));
		
		return "timeline";

	}


}


   









    	 
    	   
    


