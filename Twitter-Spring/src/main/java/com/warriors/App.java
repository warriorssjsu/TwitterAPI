package com.warriors;


import java.awt.Image;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.security.auth.message.MessagePolicy.Target;
import javax.validation.constraints.NotNull;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.social.TwitterProperties;
import org.springframework.cglib.core.GeneratorStrategy;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import twitter4j.*;
import twitter4j.api.FriendsFollowersResources;
import twitter4j.api.TweetsResources;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Hello world!
 * @param <Tweet>
 *
 */
@EnableWebMvc
@Controller

public class App
{
	
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
	
	
	@RequestMapping("/")
	public String getHome() {
		return "home";
	}
 
	@RequestMapping(value="{getTags}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String getUserList(@PathVariable final String getTags, Model model) throws TwitterException{
		App app= new App();
		Twitter twitter=app.gettwitter();
		model.addAttribute("tags",twitter.getUserLists(getTags));
		return "tags";
	}


	@RequestMapping(value="/user/{getName}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String lookupUser(@PathVariable final String getName, Model model) throws TwitterException{
		App app= new App();
		Twitter twitter=app.gettwitter();
		model.addAttribute("status",twitter.lookupUsers(getName).get(0).getStatus());
		return "status";
	}
	

	@RequestMapping(value="/followers/{getName}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String getfriendsFollowers(@PathVariable final String getName, Model model) throws TwitterException{
		App app= new App();
		Twitter twitter=app.gettwitter();
		model.addAttribute("followers", twitter.showFriendship("warriors", "Rohini"));
		return "followers";
	}
	
	/*@RequestMapping(value="/followers/{getName}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public FriendsFollowersResources getfriendsFollowers(@PathVariable final String getName, Model model) throws TwitterException{
		App app= new App();
		Twitter twitter=app.gettwitter();
		
		return twitter.friendsFollowers();
	}*/

	@RequestMapping(value="/create/{getName}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String createFriendship(@PathVariable final String getName,Model model) throws TwitterException{
		App app= new App();
		Twitter twitter=app.gettwitter();
		model.addAttribute("friendship", twitter.createFriendship(getName));
		return "friendship";
	}

   
	@RequestMapping(value="/tweets/{getName}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String reTweet(@PathVariable final String getName, Model model) throws TwitterException{
		App app= new App();
		Twitter twitter=app.gettwitter();
		
		if(twitter.getRetweets(929960910) != null) {
		    twitter.unRetweetStatus(929960910);
		}

		model.addAttribute("tweets", twitter.retweetStatus(929960910));
		return "tweets";
		}

	
	@RequestMapping(value="/unRetweet/{getName}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String unRetweet(@PathVariable final String getName, Model model) throws TwitterException{
		App app= new App();
		Twitter twitter=app.gettwitter();
		if(twitter.getRetweets(929960910) == null) {
		    twitter.retweetStatus(929960910);
		}
		
		model.addAttribute("unRetweet",twitter.unRetweetStatus(929960910));
		return "unRetweet";
	}

	@RequestMapping(value="/trends/{getName}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String getTrends(@PathVariable final String getName, Model model) throws TwitterException{
		App app= new App();
		Twitter twitter=app.gettwitter();
		model.addAttribute("trends", twitter.getAvailableTrends());
		return "trends";
	}

	//update status on twitter
	@RequestMapping(value="/update/{getName}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String updateStatus(@PathVariable final String getName, Model model) throws TwitterException{
		App app= new App();
		Twitter twitter=app.gettwitter();
		Random r = new Random();
		Status status = twitter.updateStatus("Just Another Meassage- Warriors"+r.nextInt()).getQuotedStatus();
		model.addAttribute("updateStatus",status );
		return "updateStatus"; 

	}

	//get home timeline

	@GetMapping(value="/timeline/{getName}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String getTimeline(@PathVariable final String getName, Model model) throws TwitterException{
		App app= new App();
		Twitter twitter=app.gettwitter();
		model.addAttribute("timeline",twitter.getHomeTimeline());
		return "timeline";

	}


}


   









    	 
    	   
    


