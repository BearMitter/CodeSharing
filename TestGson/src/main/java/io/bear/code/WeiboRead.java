package io.bear.code;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import org.junit.Test;

import javax.net.ssl.HttpsURLConnection;

import com.google.gson.Gson;

public class WeiboRead {
	static Gson gson = new Gson();
	static String access_token = "The_ACCESS_TOKEN";
	
	@Test
	public void aTest() throws MalformedURLException, IOException {
		ArrayList<String> statuses = readFriendTimeline(access_token);
		System.out.println(statuses.get(1));
		statuses.forEach(e -> System.out.println(e));
	}

	@Test
	public void test() throws IOException {
		String url = "https://api.weibo.com/2/comments/create.json";
		HttpsURLConnection connection = (HttpsURLConnection) new URL(url).openConnection();

		byte[] postData = ("access_token="+"access_token"+"&id=4164944369081141&comment=AreUOK").getBytes(StandardCharsets.UTF_8);

		connection.setDoOutput(true);
		connection.setRequestProperty("Accept-Charset", "UTF_8");
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		connection.setRequestProperty("charset", "utf-8");

		try (OutputStream output = connection.getOutputStream()) {
			output.write(postData);
		}

		InputStream response = connection.getInputStream();
		Scanner scanner = new Scanner(response);
		String responseBody = scanner.useDelimiter("\\A").next();
		scanner.close();
		System.out.println(responseBody);
	}

	public static void main(String[] args) throws MalformedURLException, IOException {
		ArrayList<String> statuses = readFriendTimeline(access_token);
		statuses.forEach(e -> System.out.println(e));
		String response=writeCommentById(access_token, 
				"/**WEIBO API TEST -- This comment is created by program automatically, sorry if  bother! */",
				statuses.get(0)
				);
		System.out.println(response);
	}

	public static ArrayList<String> readUserTimeline(String s1) throws MalformedURLException, IOException {
		String url = "https://api.weibo.com/2/statuses/user_timeline/ids.json";
		String param1 = s1;
		String charset = "UTF-8";
		String query = String.format("access_token=%s", URLEncoder.encode(param1, charset));

		HttpsURLConnection connection = (HttpsURLConnection) new URL(url + "?" + query).openConnection();
		connection.setRequestProperty("Accept-Charset", charset);
		InputStream response = connection.getInputStream();

		Scanner scanner = new Scanner(response);
		String responseBody = scanner.useDelimiter("\\A").next();
		scanner.close();

		@SuppressWarnings("unchecked")
		Map<String, Object> jsonObject = gson.fromJson(responseBody, Map.class);

		@SuppressWarnings("unchecked")
		ArrayList<String> statuses = (ArrayList<String>) jsonObject.get("statuses");

		return statuses;
	}

	public static ArrayList<String> readFriendTimeline(String s1) throws MalformedURLException, IOException {
		String url = "https://api.weibo.com/2/statuses/friends_timeline/ids.json";
		String param1 = s1;
		String charset = "UTF-8";
		String query = String.format("access_token=%s", URLEncoder.encode(param1, charset));

		HttpsURLConnection connection = (HttpsURLConnection) new URL(url + "?" + query).openConnection();
		connection.setRequestProperty("Accept-Charset", charset);
		InputStream response = connection.getInputStream();

		Scanner scanner = new Scanner(response);
		String responseBody = scanner.useDelimiter("\\A").next();
		scanner.close();

		@SuppressWarnings("unchecked")
		Map<String, Object> jsonObject = gson.fromJson(responseBody, Map.class);

		@SuppressWarnings("unchecked")
		ArrayList<String> statuses = (ArrayList<String>) jsonObject.get("statuses");

		return statuses;
	}

	public static String readStatusById(String s1, String s2) throws MalformedURLException, IOException {
		String url = "https://api.weibo.com/2/statuses/show.json";
		String charset = "UTF-8";
		String query = String.format("access_token=%s" + "&id=%s", URLEncoder.encode(s1, charset),
				URLEncoder.encode(s2, charset));

		HttpsURLConnection connection = (HttpsURLConnection) new URL(url + "?" + query).openConnection();
		connection.setRequestProperty("Accept-Charset", charset);
		InputStream response = connection.getInputStream();

		Scanner scanner = new Scanner(response);
		String responseBody = scanner.useDelimiter("\\A").next();
		scanner.close();

		@SuppressWarnings("unchecked")
		Map<String, Object> jsonObject = gson.fromJson(responseBody, Map.class);

		String text = new String(((String) jsonObject.get("text")).getBytes(), "UTF-8");

		if (jsonObject.get("retweeted_status") == null)
			return text;
		else {
			@SuppressWarnings("unchecked")
			Map<String, Object> retweeted_status = (Map<String, Object>) jsonObject.get("retweeted_status");
			return text + " ¡¾RETWEET¡¿ " + (new String(retweeted_status.get("text").toString().getBytes(), "UTF-8"));
		}
	}

	public static String writeCommentById(String s1, String s2, String s3) throws MalformedURLException, IOException {

		String url = "https://api.weibo.com/2/comments/create.json";
		HttpsURLConnection connection = (HttpsURLConnection) new URL(url).openConnection();

		byte[] postData = ("access_token=" + s1 + "&comment=" + s2 + "&id=" + s3).getBytes(StandardCharsets.UTF_8);

		connection.setDoOutput(true);
		connection.setRequestProperty("Accept-Charset", "UTF_8");
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		connection.setRequestProperty("charset", "utf-8");

		try (OutputStream output = connection.getOutputStream()) {
			output.write(postData);
		}

		InputStream response = connection.getInputStream();
		Scanner scanner = new Scanner(response);
		String responseBody = scanner.useDelimiter("\\A").next();
		scanner.close();
		return responseBody;
	}

}
