
package models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Dataset {
    private List<Map<String, Object>> data;

    public Dataset(String filePath) {
        this.data = loadData(filePath);
    }

    private List<Map<String, Object>> loadData(String filePath) {
        List<Map<String, Object>> dataList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            String[] headers = br.readLine().split(",");

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Map<String, Object> row = new HashMap<>();

                for (int i = 0; i < headers.length; i++) {
                    String key = headers[i];
                    String value = values[i];


                    if (key.equals("id")){
                        //row.put(key, value);
                    } else if (key.equals("cpt") || key.equals("rbp")) {
                        row.put(key, value.equals("N/A")  ? null : Double.parseDouble(value));
                    } else if ( key.equals("fbs") || key.equals("ecg") ) {
                        row.put(key, Double.parseDouble(value)); //!!
                    }
                    else if ( key.equals("age") ) {
                        row.put(key, isInt(value)  ? Double.parseDouble(value) : (int) Math.ceil(Double.parseDouble(value))); //!!!!!
                    } else if (key.equals("chol")){
                        row.put(key, value);
                    } else if (key.equals("heart_disease")){
                        row.put(key, Integer.parseInt(value));
                    }else {

                    }
                }
                dataList.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataList;
    }

    public List<Map<String, Object>> getData() {
        return data;
    }

    public boolean isInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}