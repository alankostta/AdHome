package com.br.Ad.Ad.configs;

import java.io.IOException;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.springframework.stereotype.Service;

import com.br.Ad.Ad.dto.EnderecoDto;
import com.google.gson.Gson;

@Service
public class ViacepService {
	
	public EnderecoDto getEndereco(String cep) throws Exception {

		EnderecoDto enderecoDto = null;
		HttpGet request = new HttpGet("https://viacep.com.br/ws/"+cep+"/json/");

		try (CloseableHttpClient httpClient = HttpClientBuilder.create().disableRedirectHandling().build();
				@SuppressWarnings("deprecation")
				CloseableHttpResponse response = httpClient.execute(request)) {

			HttpEntity entity = response.getEntity();
			if (entity != null) {
				String result = EntityUtils.toString(entity);
				Gson gson = new Gson();
				enderecoDto = gson.fromJson(result, EnderecoDto.class);
			}
		} catch (IOException ex) {
			throw new Exception("Erro ao recuperar dados do CEP: " + ex.getMessage());
		}
		return enderecoDto;
	}
}
