/*
 * Course: CS2852
 * Spring 2019
 * File header contains class IndexedArray
 * Name: crossj
 * Created 3/25/2019
 */
package crossj;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * CS2852 Spring 2019
 * Class purpose:
 *
 * @author crossj
 * @version created on 3/25/2019 at 12:06 PM
 */
public class IteratedArray implements AutoCompleter {
    private ArrayList<String> contents = new ArrayList<>();
    private ArrayList<String> contentsCopy = new ArrayList<>();
    private boolean initialized;
    private long time = 0;
    private long time2 = 0;

    /**
     * Description of what the strategy does
     * @return description
     */
    public String strategyDescription(){
        return "Creates a ArrayList and goes through the list by " +
                "using an iterator to go through list";
    }


    /**
     * initializes by creating a list of data for the specified filename
     * @param fileName path to the desired file
     * @throws IllegalArgumentException throws if wrong type of file
     * @throws IOException throws if scanner cannot scan file
     */
    public void initialize(String fileName) throws IllegalArgumentException, IOException {
        time = 0;
        long beforeTime = System.nanoTime();
        if(fileName.endsWith(".txt")){
            readTXT(fileName);
        } else if (fileName.endsWith(".csv")){
            readCSV(fileName);
        } else {
            throw new IllegalArgumentException("File should be of type .txt or .csv");
        }
        initialized = true;
        time2 += System.nanoTime() - beforeTime;
    }

    /**
     * With every key pressed, updates list to desired user prefix
     * @param prefix desired filtering string
     * @return returns updated list
     * @throws IllegalStateException throws if initialize was not accessed previously
     */
    public List<String> allThatBeginWith(String prefix) throws IllegalStateException {
        time2 = 0;
        long beforeTime = System.nanoTime();
        contentsCopy.clear();
        contentsCopy.addAll(contents);
        if(!initialized){
            throw new IllegalStateException("Must call initialize() prior to calling this method");
        } else if (prefix.length()>0){
            Iterator<String> iterator = contents.iterator();
            iterator.forEachRemaining(line -> {
                if (prefix.length() > line.length()) {
                    contentsCopy.remove(line);
                } else if (!((line.substring(0, prefix.length())).equalsIgnoreCase(prefix))) {
                    contentsCopy.remove(line);
                }
            });
        }
        time = System.nanoTime() - beforeTime;
        return contentsCopy;
    }

    /**
     * Returns the last operation time
     * @return returns long of number
     * @throws IllegalStateException throws if initialize was not accessed previously
     */
    public long getLastOperationTime() throws IllegalStateException {
        if(!initialized){
            throw new IllegalStateException("Must call initialize() prior to calling this method");
        }
        long returnTime = 0;
        if(time != 0){
            //returns allThatBeginsWith time
            returnTime = time;
        } else if (time2 != 0) {
            //returns initialized time
            returnTime = time2;
        }
        return returnTime;
    }

    /**
     * Read a text file
     * @param fileName desired file path of user to read in file
     * @throws IOException throws if scanner cannot read in file
     * @throws IllegalArgumentException Throws if the file is empty
     */
    private void readTXT(String fileName) throws IOException, IllegalArgumentException {
        long beforeTime = System.nanoTime();
        try(Scanner scan = new Scanner(Paths.get(fileName))) {
            while (scan.hasNextLine()) {
                contents.add(scan.nextLine());
            }
            if (contents == null || contents.size() == 0) {
                throw new IllegalArgumentException("File has no contents");
            }
        }
        time2 = System.nanoTime() - beforeTime;
    }

    /**
     * Reads in a csv file from the desired path
     * @param fileName user desired path to file
     * @throws IOException throws if scanner cannot read in file
     * @throws IllegalArgumentException Throws if the file is empty
     */
    private void readCSV(String fileName) throws IOException, IllegalArgumentException{
        long beforeTime = System.nanoTime();
        try(Scanner scan = new Scanner(Paths.get(fileName))) {
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] words = line.split(",");
                if (words.length > 2 || words.length <= 0) {
                    throw new IllegalArgumentException(
                            "Could not read provided contents of chosen file, check formatting");
                }
                contents.add(words[1]);
            }
            if (contents == null || contents.size() == 0) {
                throw new IllegalArgumentException("File has no contents");
            }
        }
        time2 = System.nanoTime() - beforeTime;
    }

}
