package com.ad.teamnine.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

@Service
public class CsvService {

    private final ResourceLoader resourceLoader;

    public CsvService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public void readCsvFile(String filePath) {
        try {
            // 使用ResourceLoader加载CSV文件
            Resource resource = resourceLoader.getResource(filePath);
            InputStream inputStream = resource.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);

            // 逐行读取CSV文件
            String line;
            while ((line = reader.readLine()) != null) {
            	String[] fields = line.split(",");
                for (String field : fields) {
                    System.out.print(field + " ");
                }
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
