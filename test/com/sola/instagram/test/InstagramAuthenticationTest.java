package com.sola.instagram.test;

import java.net.URLEncoder;

import junit.framework.Assert;

import org.junit.Test;

import com.sola.instagram.auth.InstagramAuthentication;

public class InstagramAuthenticationTest {
	
	private static final String REDIRECT_URI = "your_redirect_uri";
	private static final String APP_SECRET = "your_app_secret";
	private static final String CLIENT_ID = "your_client_id";
	private static final String COMMENTS_SCOPE = "comments";
	private static final String LIKES_SCOPE = "likes";
	private static final String RELATIONSHIPS_SCOPE = "relationships";
	private static final String BASIC_SCOPE = "basic";
	
	@Test
	public void testBuildAuthUrl() throws Exception {
		/*
		 * Test that no Exception is thrown 
		 */
		String commentsLikesScope =new StringBuilder(COMMENTS_SCOPE).append("+").append(RELATIONSHIPS_SCOPE).append("+").append(LIKES_SCOPE).toString();
		String resultingAuthURL = new StringBuilder(
				"https://api.instagram.com/oauth/authorize/?client_id=")
				.append(URLEncoder.encode(CLIENT_ID, "UTF-8")).append("&redirect_uri=")
				.append(URLEncoder.encode(REDIRECT_URI, "UTF-8")).append("&response_type=code&scope=")
				.append(URLEncoder.encode(commentsLikesScope, "UTF-8")).toString();

		InstagramAuthentication auth =  new InstagramAuthentication();
		String authUrl = auth.setRedirectUri(REDIRECT_URI)
                .setClientSecret(APP_SECRET)
                .setClientId(CLIENT_ID)
                .setScope(commentsLikesScope)
                .getAuthorizationUri();
		
		System.out.printf("actual: %s%n", authUrl);
		System.out.printf("expected: %s%n", resultingAuthURL);
		
		Assert.assertEquals(resultingAuthURL, authUrl);
	}
	
	@Test
	public void testDefaultScopeAuthUrl() throws Exception {
		/*
		 * Test that no Exception is thrown 
		 */
		String defaultScope = BASIC_SCOPE;
		String resultingAuthURL = new StringBuilder(
				"https://api.instagram.com/oauth/authorize/?client_id=")
				.append(URLEncoder.encode(CLIENT_ID, "UTF-8")).append("&redirect_uri=")
				.append(URLEncoder.encode(REDIRECT_URI, "UTF-8")).append("&response_type=code&scope=")
				.append(URLEncoder.encode(defaultScope, "UTF-8")).toString();

		InstagramAuthentication auth =  new InstagramAuthentication();
		String authUrl = auth.setRedirectUri(REDIRECT_URI)
                .setClientSecret(APP_SECRET)
                .setClientId(CLIENT_ID)
                .getAuthorizationUri();
		
		System.out.printf("actual: %s%n", authUrl);
		System.out.printf("expected: %s%n", resultingAuthURL);
		
		Assert.assertEquals(resultingAuthURL, authUrl);
	}
}
