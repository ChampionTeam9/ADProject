package com.ad.teamnine.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.ad.teamnine.model.*;
import com.ad.teamnine.repository.RecipeRepository;

@Service
public class CsvService {
	@Autowired
	RecipeRepository recipeRepo;
	
	private final ResourceLoader resourceLoader;

	public CsvService(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	public void readCsvFile(String filePath) {
		try {
			// 使用ResourceLoader加载CSV文件
			Resource resource = resourceLoader.getResource(filePath);
			InputStream inputStream = resource.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8); // 指定正确的编码方式
			BufferedReader reader = new BufferedReader(inputStreamReader);

			// 逐行读取CSV文件
			String line;
			reader.readLine();
			reader.readLine();
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
				String[] fields = line.split(",");
				Recipe recipe = new Recipe();
				if(Integer.parseInt(fields[0])==3) {
					break;
				}
				recipe.setId(Integer.parseInt(fields[0]));
				recipe.setName(fields[1]);
				recipe.setDescription(fields[2]);
				recipeRepo.save(recipe);
				
			}

			// 关闭资源
			reader.close();
			inputStreamReader.close();
			inputStream.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
