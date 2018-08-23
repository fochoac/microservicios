package net.unir.aplicacion.util;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ClienteRestUtil {

	private ClienteRestUtil() {

	}

	public static <T> T obtenerObjetos(final String url, Class claseRetorno) {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		try {
			// Define a HttpGet request; You can choose between HttpPost, HttpDelete or
			// HttpPut also.
			// Choice depends on type of method you will be invoking.
			HttpGet getRequest = new HttpGet(url);

			// Set the API media type in http accept header
			getRequest.addHeader("accept", "application/json");

			// Send the request; It will immediately return the response in HttpResponse
			// object
			HttpResponse response = httpClient.execute(getRequest);

			// verify the valid error code first
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != 200) {
				throw new RuntimeException("Failed with HTTP error code : " + statusCode);
			}

			// Now pull back the response object
			HttpEntity httpEntity = response.getEntity();
			String apiOutput = EntityUtils.toString(httpEntity);

			// Lets see what we got from API
			System.out.println(apiOutput); // <user id="10"><firstName>demo</firstName><lastName>user</lastName></user>

			Gson gson = new Gson();

			return (T) gson.fromJson(apiOutput, claseRetorno);

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// Important: Close the connect
			httpClient.getConnectionManager().shutdown();
		}
		return null;
	}

	

}
