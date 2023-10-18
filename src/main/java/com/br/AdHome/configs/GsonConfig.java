package com.br.AdHome.configs;

import java.time.LocalDateTime;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonConfig {
	public static Gson getGson() {
		GsonBuilder gsonBuilder = new GsonBuilder();

		// Registrar o LocalDateTimeAdapter para LocalDateTime
		gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter());

		return gsonBuilder.create();
	}
}
