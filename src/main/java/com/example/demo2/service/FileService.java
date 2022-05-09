package com.example.demo2.service;

import com.example.demo2.domain.csv.MealCsv;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface FileService {
    List<MealCsv> readFromCsv(InputStream inputStream) throws IOException;
}
