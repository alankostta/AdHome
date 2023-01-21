package com.br.AdHome.AdHome.configs;

import java.io.IOException;

import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import com.br.AdHome.AdHome.models.Endereco;
import com.google.gson.Gson;

public class ViacepService {
	
	public Endereco getEndereco(String cep) throws ClientProtocolException, IOException, ParseException{ 
		
		Endereco endereco = null;
		
		HttpGet request = new HttpGet("https://viacep.com.br/ws/"+cep+"/json");
		
		try(CloseableHttpClient httpClient = HttpClientBuilder.create().disableRedirectHandling().build();
				CloseableHttpResponse response = httpClient.execute(request)){
			
			HttpEntity entity = response.getEntity();
			
			if(entity != null) {
				
				String result = EntityUtils.toString(entity);
				
				Gson gson = new Gson();
				
				endereco = gson.fromJson(result, Endereco.class);
			}
		}
		return endereco;
	}

}
