package com.bridgelabz;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class CensusAnalyser {
    private static final String INDIA_CENSUS_CSV_FILE_PATH = "C:\\Users\\SHEKHAR\\IdeaProjects\\IndianCensusAnalyzer\\src\\test\\resources\\IndiaStateCensusData.csv";
    //method to load Indian State Census Information from csv file
    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            CsvToBeanBuilder<IndianCensusCSV> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(IndianCensusCSV.class);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<IndianCensusCSV> csvToBean = csvToBeanBuilder.build();
            Iterator<IndianCensusCSV> censusCSVIterator = csvToBean.iterator();;
            int namOfEntries = 0;
            while (censusCSVIterator.hasNext()) {
                namOfEntries++;
                IndianCensusCSV censusData = censusCSVIterator.next();
            }
            return namOfEntries;
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
        catch (Exception x) {
            throw new CensusAnalyserException(x.getMessage(),
                    CensusAnalyserException.ExceptionType.CSV_HEADER_PROBLEM);
        }
    }

}
