package com.jianguo.app.didi;

import com.squareup.okhttp.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by mxy on 2016/9/8.
 */
public class DidiTicketsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }




    String post(String url,String json) throws IOException {

            OkHttpClient client = new OkHttpClient();

            RequestBody formBody = new FormEncodingBuilder()

                    .add("channel", "77901372b80c1198451552f8d7e586cc")

                    .add("phone", "bug")

                    .add("subject", "XXXXXXXXXXXXXXX")

                    .build();



            Request request = new Request.Builder()

                    .url(url)

                    .post(formBody)

                    .build();


            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {

                return response.body().string();

            } else {

                throw new IOException("Unexpected code " + response);

            }

        }

}
