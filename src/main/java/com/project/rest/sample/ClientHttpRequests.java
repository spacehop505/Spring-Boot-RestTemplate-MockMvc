package com.project.rest.sample;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.rest.models.Fruit;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class ClientHttpRequests {
    private ObjectMapper objectMapper = new ObjectMapper();


    public List<Fruit> toListFromJson(String jsonFile) {
        List<Fruit> fruitList = new ArrayList<>();
        try {
            // Fruit[] fruitObj = objectMapper.readValue(jsonFile, Fruit[].class);
            fruitList = objectMapper.readValue(jsonFile, new TypeReference<ArrayList<Fruit>>() {
            });
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return fruitList;
    }

    // @GET
    public List<Fruit> getUri() {
        List<Fruit> fruitListResponse = new ArrayList<>();
        try {
            URI uri = new URIBuilder().setScheme("http").setHost("localhost").setPort(8080).setPath("/client/fruits").build();
            System.out.println(uri);
            HttpGet httpGet = new HttpGet(uri);
            httpGet.setHeader("Accept", "application/json");

            CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse response = httpClient.execute(httpGet);

            HttpEntity entity = response.getEntity();
            String textJsonArray = EntityUtils.toString(entity);
            //System.out.println("test: " + textJsonArray);

            fruitListResponse = toListFromJson(textJsonArray);
            // System.out.println(fruitListResponse);

            response.close();
        } catch (Exception e) {
            System.out.println("Error: getUri Exception");
        }
        return fruitListResponse;
    }

    // @GET
    public List<Fruit> getUriId(int id) {
        List<Fruit> fruitListResponse = new ArrayList<>();
        try {
            URI uri = new URIBuilder().setScheme("http").setHost("localhost").setPort(8080).setPath("/client/fruit/" + id).build();
            System.out.println(uri);
            HttpGet httpGet = new HttpGet(uri);
            httpGet.setHeader("Accept", "application/json");

            CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse response = httpClient.execute(httpGet);

            HttpEntity entity = response.getEntity();
            String textJsonArray = EntityUtils.toString(entity);
            // System.out.println(textJsonArray);
            fruitListResponse = toListFromJson(textJsonArray);
            response.close();
        } catch (Exception e) {
            System.out.println("Error: getUri Exception");
        }
        return fruitListResponse;
    }


    // @PUT
    public void putUri(int id, String name, double price, int quantity) {
        try {
            URI uri = new URIBuilder().setScheme("http").setHost("localhost").setPort(8080)
                    .setPath("client/create/fruit/" + id).build();
            System.out.println(uri.toString());
            HttpPut httpPut = new HttpPut(uri);
            httpPut.setHeader("Accept", "text/html");
            CloseableHttpClient httpClient = HttpClients.createDefault();

            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
            nameValuePairs.add(new BasicNameValuePair("id", String.valueOf(id)));
            nameValuePairs.add(new BasicNameValuePair("name", name));
            nameValuePairs.add(new BasicNameValuePair("price", String.valueOf(price)));
            nameValuePairs.add(new BasicNameValuePair("quantity", String.valueOf(quantity)));
            httpPut.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            CloseableHttpResponse response = httpClient.execute(httpPut);
            response.close();
        } catch (Exception e) {
            System.out.println("Error: putUri Exception");
        }
    }


    // @POST
    public void postUri(int id, String name, double price, int quantity) {
        try {
            URI uri = new URIBuilder().setScheme("http").setHost("localhost").setPort(8080)
                    .setPath("/client/update/fruit/" + id).build();
            HttpPost httpPost = new HttpPost(uri);
            httpPost.setHeader("Accept", "text/html");
            CloseableHttpClient httpClient = HttpClients.createDefault();

            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
            nameValuePairs.add(new BasicNameValuePair("id", String.valueOf(id)));
            nameValuePairs.add(new BasicNameValuePair("name", name));
            nameValuePairs.add(new BasicNameValuePair("price", String.valueOf(price)));
            nameValuePairs.add(new BasicNameValuePair("quantity", String.valueOf(quantity)));
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            CloseableHttpResponse response = httpClient.execute(httpPost);
            response.close();
        } catch (Exception e) {
            System.out.println("Error: postUri Exception");
        }
    }

    // @DELETE
    public void deleteUri(int id) {
        try {
            URI uri = new URIBuilder().setScheme("http").setHost("localhost").setPort(8080)
                    .setPath("/client/delete/fruit/" + id).build();
            HttpDelete httpDelete = new HttpDelete(uri);
            httpDelete.setHeader("Accept", "text/html");

            CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse response = httpClient.execute(httpDelete);
            response.close();
        } catch (Exception e) {
            System.out.println("Error: deleteUri Exception");
        }
    }


}
