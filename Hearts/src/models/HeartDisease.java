package models;

import java.io.*;
import java.util.*;


class HeartDisease {

    public static void main(String[] args) {
        try {
            File currentDir = new File("\\src\\models\\heart.csv");
            System.out.println(currentDir.getAbsolutePath());
            // Step 1: Load and preprocess the dataset
            String filePath = currentDir.getAbsolutePath() + "\\src\\models\\heart.csv";
            System.out.println(filePath);

            Dataset dataset = new Dataset(filePath);
            //dataset.preprocess();

            // Step 2: Bootstrap the dataset for Random Forest
            int numTrees = 100;
            RandomForest randomForest = new RandomForest(numTrees);

            randomForest.train(dataset);

            // Step 3: Save the Random Forest
            String RFmodelPath = currentDir.getAbsolutePath() + "\\src\\models\\models\\random_forest_model.dat";
            randomForest.saveForest(RFmodelPath);
            System.out.println("Random Forest model saved to: " + RFmodelPath);

            // Step 4: Load the Random Forest
            RandomForest loadedForest = RandomForest.loadForest(RFmodelPath);
            System.out.println("Random Forest model loaded successfully.");

            Map<String, Double> testData = new HashMap<>();
            testData.put("age", 33.0);
            testData.put("fbs", 1.0);
            testData.put("ecg", 1.0);
            testData.put("rbp", 160.0);
            testData.put("cpt", 2.0);
            testData.put("cholesterol", 203.0);

            int prediction = loadedForest.predict(testData);
            System.out.println("Predicted Heart Disease Risk: " + prediction);
            switch(prediction) {
                case 1 :  System.out.println("You have a very low risk. Keep it Up!");
                    break;
                case 2 :  System.out.println("You have a very moderate risk. Keep Doing what You're Doing");
                    break;
                case 3 :  System.out.println("You are at high-risk of getting heart disease! Please Do as Follows: ");
                    break;
            }


            // Step 6: Visualize the first decision tree
            System.out.println("Visualization of the first tree:");
            loadedForest.printTree(0);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}