/*
 * Course: CS2852
 * Spring 2019
 * File header contains class TreeMap2
 * Name: crossj
 * Created 5/12/2019
 */
package crossj;

import java.util.*;

/**
 * CS2852 Spring 2019
 * Class Creates a Tree of Nodes that contain a key and value,
 * the key is the letter of the current char in the string added and the
 * value is a list of all words that contain the letter and all letters
 * up the tree creating a prefix
 * @author crossj
 * @version created on 5/12/2019
 */
public class TreeOfMaps {

    /**
     * Node that contains the information of the key it represents
     * the value that is a list of words that contains the key and previous
     * keys, and a next that contains every node that comes after the key
     */
    private static class Node {

        /**
         * Instance variables here
         */
        private List<Node> next = new ArrayList<>();
        private String key;
        private List<String> value;


        /**
         * Constructor
         *
         * @param key key of node
         */
        public Node(String key) {
            this.key = key;
        }

    }

    private Node root;

    /**
     * Constructor
     */
    public TreeOfMaps() {
        root = new Node("Contents");
    }

    /**
     * Returns the list that has the specified prefix
     *
     * @param key prefix
     * @return list of all words that have prefix
     */
    public List<String> get(String key) {
        if (key.length() == 0) {
            return root.value;
        }
        return get(key.toUpperCase(), root);
    }

    /**
     * Recursive call to get the list for the specified prefix
     * @param key String wanted
     * @param subroot starting node
     * @return returns list of contents
     */
    private List<String> get(String key, Node subroot) {
        List<String> toReturn;
        if (key.length() > 0) {
            String character = key.substring(0, 1);
            Node node = nodeOfKeyWanted(character, subroot);
            if (node == null) {
                return new LinkedList<>();
            }
            toReturn = get(key.substring(1), node);
        } else {
            toReturn = subroot.value;
        }
        return toReturn;
    }

    /**
     * Returns the node if the keys match and null if no such key/node exists in list
     * @param key key wanted checked
     * @param subroot wanted starting node
     * @return reuturns node if found and null if not found
     */
    private Node nodeOfKeyWanted(String key, Node subroot) {
        Iterator iterator = subroot.next.iterator();
        Node toReturn = null;
        while (iterator.hasNext()) {
            Node node = (Node) iterator.next();
            if (node.key.equalsIgnoreCase(key)) {
                toReturn = node;
            }
        }
        return toReturn;
    }

    /**
     * Adds the wanted String into a tree of keys
     * @param key String wanted added
     */
    public void add(String key) {
        if (key == null) {
            throw new NullPointerException("Tree does not support null");
        } else if (key.length() == 0) {
            return;
        }
        add(key.toUpperCase(), root, key);
    }

    /**
     * Recursive Add method that keeps adding until there is nothing left
     * @param key String of keys
     * @param subroot node to start the adding from
     * @param mainString total string wanted added to each separate list
     */
    private void add(String key, Node subroot, String mainString) {
        //Updates the value information inside the node to incorporate the new word
        if (subroot.value == null) {
            LinkedList<String> list = new LinkedList<>();
            list.add(mainString);
            subroot.value = list;
        } else {
            List<String> originalList = subroot.value;
            originalList.add(mainString);
            subroot.value = originalList;
        }

        if (key.length() != 0) {
            String character = key.substring(0, 1);
            Node node = nodeOfKeyWanted(character, subroot);
            if (node == null) {
                node = new Node(character);
                subroot.next.add(node);
            }

            add(key.substring(1), node, mainString);
        }
    }



}
