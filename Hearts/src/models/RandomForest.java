
package models;


import java.io.*;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.*;


class RandomForest implements Serializable {
    private static final long serialVersionUID = 1L;
    public int numTrees;
    public List<DecisionTree> trees;
    public int treeCounter = 0;
    public RandomForest(int numTrees) {
        this.numTrees = numTrees;
        this.trees = new ArrayList<>();
    }

    public void train(Dataset dataset) {
        List<Map<String, Object>> data = dataset.getData();
        Random rand = new Random();

        for (int i = 0; i < numTrees; i++) {
            List<Map<String, Object>> bootstrapSample = Bootstrap.sample(data, rand);
            DecisionTree tree = new DecisionTree(5);
            tree.train(bootstrapSample);
            trees.add(tree);
            treeCounter++;
            System.out.println(treeCounter);
            tree.printTree();
        }
    }

    public int predict(Map<String, Double> input) {
        int sum = 0;
        for (DecisionTree tree : trees) {
            System.out.println(tree.predict(input) + " TREE - " );
            sum += tree.predict(input);
        }
        double result = (double) sum / trees.size();
        if (result >= .05 && result < .10)
            return 2;
        if (result >= .10 )
            return 3;
        if (result < .05 )
            return 1;
        return 1;
    }
    public void printTree(int index) {
        if (index >= 0 && index < trees.size()) {
            trees.get(index).printTree();
        } else {
            System.out.println("Tree index out of bounds.");
        }
    }
    public void saveForest(String filePath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(this);
        }
    }

    public static RandomForest loadForest(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (RandomForest) ois.readObject();
        }
    }
}