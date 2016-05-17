import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;


public class HttpClient
{
	public static String Get(String url)
	{
		try
		{
			URL _url = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) _url.openConnection();
			connection.setRequestMethod("GET");
			
			String json = readInputStream(connection.getInputStream());
			
			return json;
		} catch (Throwable t)
		{
			return null;
		}
	}
	
	public static String Post(String url, Object body)
	{
		try
		{
			URL _url = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) _url.openConnection();
			connection.setRequestMethod("POST");
			connection.setChunkedStreamingMode(url.getBytes().length);
			connection.setDoOutput(true);
			
			String json = new Gson().toJson(body);
			writeOutputStream(connection.getOutputStream(), json);
			String response = readInputStream(connection.getInputStream());
			
			return response;
		} catch (Throwable t)
		{
			return null;
		}
	}
	
	public static String Put(String url, Object body)
	{
		try
		{
			URL _url = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) _url.openConnection();
			connection.setRequestMethod("PUT");
			connection.setChunkedStreamingMode(url.getBytes().length);
			connection.setDoOutput(true);
			
			String json = new Gson().toJson(body);
			writeOutputStream(connection.getOutputStream(), json);
			String response = readInputStream(connection.getInputStream());
			
			return response;
		} catch (Throwable t)
		{
			return null;
		}
	}

	public static String Delete(String url, Object body)
	{
		try
		{
			URL _url = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) _url.openConnection();
			connection.setRequestMethod("DELETE");
			connection.setChunkedStreamingMode(url.getBytes().length);
			connection.setDoOutput(true);
			
			String json = new Gson().toJson(body);
			writeOutputStream(connection.getOutputStream(), json);
			String response = readInputStream(connection.getInputStream());
			
			return response;
		} catch (Throwable t)
		{
			return null;
		}
	}
	
	private static String readInputStream(InputStream stream)
	{
		try
		{
			BufferedReader bReader = new BufferedReader(new InputStreamReader(stream));
			
			String json = "";
			String linha = "";
			
			while((linha = bReader.readLine()) != null)
				json += linha;
			bReader.close();
			
			return json;
		} catch (Throwable t)
		{
			return null;
		}
		
	}
	
	private static void writeOutputStream(OutputStream stream, String json) throws Exception
	{
		BufferedWriter bWriter = new BufferedWriter(
				new OutputStreamWriter(stream));
		bWriter.write(json);
		bWriter.flush();
		bWriter.close();
	}
}
