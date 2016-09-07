package com.jianguo.util;

import java.io.IOException;

import javax.servlet.ServletException;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

public class ss {
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	// TODO Auto-generated method stub
	private void sssss() throws ServletException, IOException {
		// TODO Auto-generated method stub


		OkHttpClient client = new OkHttpClient();
		//	String post() throws IOException {
		String sss = "{\"name\": \"Notification Channel\",\"sys\": true}";

		RequestBody body = RequestBody.create(JSON, sss);

		Request requestq = new Request.Builder()
		.url("https://api.leancloud.cn/1.1/classes/_Conversation")
		.addHeader("X-LC-Id", "AtwJtfIJPKQFtti8D3gNjMmb-gzGzoHsz")
		.addHeader("X-LC-Key", "spNrDrtGWAXP633DkMMWT65B")
		.addHeader("Content-Type", "application/json")
		.post(body)
		.build();

		Response responseq = client.newCall(requestq).execute();
		if (responseq.isSuccessful()) {
			//	        return responseq.body().string();
			System.out.println(responseq.body().string());
		} else {
			throw new IOException("Unexpected code " + responseq);
		}
	}
}

