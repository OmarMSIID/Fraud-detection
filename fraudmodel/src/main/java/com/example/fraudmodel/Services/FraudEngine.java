package com.example.fraudmodel.Services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import org.apache.commons.csv.CSVFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import smile.classification.RandomForest;
import smile.data.DataFrame;
import smile.data.formula.Formula;
import smile.io.Read;

@Service
public class FraudEngine {
    private final Logger logger = LoggerFactory.getLogger(FraudEngine.class);
    private static final String MODEL_PATH = "fraud_model.bin";
    private static final String DATASET_PATH = "fraud_dataset.csv";

    private RandomForest model;

    @PostConstruct
    public void inialTheModel(){
        File modelFile = new File(MODEL_PATH);
        if (modelFile.exists()) {
            logger.info("find the model saved ");
            try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(modelFile))){
                this.model = (RandomForest) ois.readObject();
                logger.info("the model uploaded with success");
            }catch(Exception exception){
                logger.error("issue in the uploading of the model : "+exception.getMessage());
            }
        }
        else{
            logger.info("training the model ");
            training();
        }
    }

    void training(){
        try{
            CSVFormat csvFormat = CSVFormat.DEFAULT.withFirstRecordAsHeader();
            logger.info("loading data in DataFram");
            DataFrame df = Read.csv(DATASET_PATH, csvFormat);
            logger.info("size of the data set "+df.size());
            logger.info(df.toString());
            logger.info("select the feature");
            Formula formula = Formula.lhs("isFraud");
            logger.info("trainging the model");
            this.model=RandomForest.fit(formula, df);
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(MODEL_PATH))){
                oos.writeObject(this.model);
                logger.info("store the model with success");
            } catch (Exception e) {
                logger.error("can not store the model");
            }

        }catch(Exception e){
            logger.error("issue in training the model 2 : "+e.getMessage());
        }
    }

    
}
