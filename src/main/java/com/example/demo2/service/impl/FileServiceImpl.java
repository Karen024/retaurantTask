package com.example.demo2.service.impl;

import com.example.demo2.domain.csv.MealCsv;
import com.example.demo2.service.FileService;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Service
@AllArgsConstructor
public class FileServiceImpl implements FileService {
    @Override
    public List<MealCsv> readFromCsv(InputStream inputStream) throws IOException {
        try (Reader reader = new BufferedReader(new InputStreamReader(inputStream)))
        {
            CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
            CSVReader csvReader = new CSVReaderBuilder(reader).withCSVParser(parser).build();

            CsvToBean<MealCsv> csvToBean = new CsvToBeanBuilder<MealCsv>(csvReader)
                    .withType(MealCsv.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<MealCsv> mealCsvs = csvToBean.parse();
            System.out.println(mealCsvs);
            return mealCsvs;
        }
    }
}
