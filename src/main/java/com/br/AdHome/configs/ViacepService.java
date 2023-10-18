package com.br.AdHome.configs;

import java.io.IOException;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.springframework.stereotype.Service;
import com.br.AdHome.dto.EnderecoDto;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class ViacepService {
	  private Gson gson;

	    public ViacepService(Gson gson) {
	        this.gson = gson;
	    }
	
	public EnderecoDto getEndereco(String cep) throws Exception {

		EnderecoDto enderecoDto = null;
		HttpGet request = new HttpGet("https://viacep.com.br/ws/"+cep+"/json/");

		try (CloseableHttpClient httpClient = HttpClientBuilder.create().disableRedirectHandling().build();
				@SuppressWarnings("deprecation")
				CloseableHttpResponse response = httpClient.execute(request)) {

			HttpEntity entity = response.getEntity();
			if (entity != null) {
				String result = EntityUtils.toString(entity);
				// Remova os campos extras do JSON
				JsonObject jsonObject = JsonParser.parseString(result).getAsJsonObject();

				// Remover os campos indesejados
				jsonObject.remove("ibge");
				jsonObject.remove("gia");
				jsonObject.remove("ddd");
				jsonObject.remove("siafi");

				String resultModificado = jsonObject.toString();
				// Desserialize o JSON reduzido
				this.gson = GsonConfig.getGson();
				enderecoDto = this.gson.fromJson(resultModificado, EnderecoDto.class);
			}
		} catch (IOException ex) {
			throw new Exception("Erro ao recuperar dados do CEP: " + ex.getMessage());
		}
		return enderecoDto;
	}
}