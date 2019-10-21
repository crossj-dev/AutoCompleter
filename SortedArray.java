/*
 * Course: CS2852
 * Spring 2019
 * File header contains class IndexedArray
 * Name: crossj
 * Created 3/25/2019
 */
package crossj;

import edu.msoe.cs2852.SortedArrayList;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * CS2852 Spring 2019
 * Class purpose:
 *
 * @author crossj
 * @version created on 3/25/2019 at 12:06 PM
 */
public class SortedArray implements AutoCompleter {
    private List<String> contents = new SortedArrayList<>();
    private List<String> contentsCopy = new SortedArrayList<>();
    private boolean initialized;
    private long time = 0;
    private long time2 = 0;

    /**
     * Description of what the strategy does
     * @return description
     */
    public String strategyDescription(){
        return "Creates a Sorted ArrayList and goes through the list by " +
                "using binary search";
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
        if(!initialized){
            throw new IllegalStateException("Must call initialize() prior to calling this method");
        }
        time2 = 0;
        long beforeTime = System.nanoTime();
        contentsCopy.clear();
        if (prefix.length()>0){
            int index = Collections.binarySearch(contents, prefix);
            index = fixIndex(index);
            char lastChar = prefix.charAt(prefix.length()-1);
            lastChar = (char)(lastChar + 1);
            String end = prefix.substring(0, prefix.length() - 1) + lastChar;
            int endIndex = Collections.binarySearch(contents, end);
            endIndex = fixIndex(endIndex);

            for(int i = index; i < endIndex; i++){
                contentsCopy.add(contents.get(i));
            }
        } else {
            contentsCopy.addAll(contents);
        }
        time = System.nanoTime() - beforeTime;
        return contentsCopy;
    }

    /**
     * Fixes int if negative from the collection.binarysearch
     * @param index index of binarysearch
     * @return fixed index
     */
    private int fixIndex(int index){
        if(index<0){
            index = Math.abs(index + 1);
        }
        return index;
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
                String string = scan.nextLine();
                contents.add(string);
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

    /**
     * Checks the original list and makes sure that the prefix is
     * contained within the list, uses indexed approach
     *
     * @param target word wanted checked within the list
     * @return returns true if the original list contains the prefix
     * @throws IllegalStateException throws if initialize was not accessed previously
     */
    public boolean contains(String target) throws IllegalStateException {
        if(!initialized){
            throw new IllegalStateException("Must call initialize() prior to calling this method");
        }
        target = target.toLowerCase();
        return contents.contains(target);
    }




}
