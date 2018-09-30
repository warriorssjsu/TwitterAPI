package com.warriors;


import java.util.List;
import java.util.Scanner;

import javax.security.auth.message.MessagePolicy.Target;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.social.TwitterProperties;
import org.springframework.cglib.core.GeneratorStrategy;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
@Controller
@RestController
@RequestMapping("/")
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
    
/* public static final String TWITTER_BASE_URI= "/tweets";*/
 
 /*public void addViewControllers(ViewControllerRegistry registry) {
     registry.addViewController("/").setViewName("index.html");
 }*/

 
 @RequestMapping(value="{getTags}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
 public ResponseList<UserList> getUserList(@PathVariable final String getTags) throws TwitterException{
	 App app= new App();
		Twitter twitter=app.gettwitter();
	 return twitter.getUserLists(getTags);
 }
 
 
 @RequestMapping(value="/user/{getName}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
 public Status lookupUser(@PathVariable final String getName) throws TwitterException{
	 App app= new App();
		Twitter twitter=app.gettwitter();
	 return twitter.lookupUsers(getName).get(0).getStatus();
 }
 
 @RequestMapping(value="/followers/{getName}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
 public FriendsFollowersResources getfriendsFollowers(@PathVariable final String getName) throws TwitterException{
	 App app= new App();
		Twitter twitter=app.gettwitter();
	 return twitter.friendsFollowers();
 }
 
 
 @RequestMapping(value="/create/{getName}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
 public User createFriendship(@PathVariable final String getName) throws TwitterException{
	 App app= new App();
		Twitter twitter=app.gettwitter();
	 return twitter.createFriendship(getName);
 }
 

 @RequestMapping(value="/tweets/{getName}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
 public TweetsResources getTweets(@PathVariable final String getName) throws TwitterException{
	 App app= new App();
		Twitter twitter=app.gettwitter();
	 return twitter.tweets();
 }

 
//update status on twitter
@RequestMapping(value="/update/{getName}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public Status updateStatus(@PathVariable final String getName) throws TwitterException{
	 App app= new App();
		Twitter twitter=app.gettwitter();
	 return twitter.updateStatus("Hi, Just trying to update").getQuotedStatus();
	 
}

//get home timeline
@RequestMapping(value="/timeline/{getName}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public ResponseList<Status> getTimeline(@PathVariable final String getName) throws TwitterException{
	 App app= new App();
		Twitter twitter=app.gettwitter();
	 return twitter.getHomeTimeline();
	 
}

}


   









    	 
    	   
    


