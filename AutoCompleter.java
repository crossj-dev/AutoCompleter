/*
 * Course: CS2852
 * Spring 2019
 * File header contains class AutoCompleter
 * Name: crossj
 * Created 3/22/2019
 */
package crossj;

import java.io.IOException;
import java.util.List;

/**
 * CS2852 Spring 2019
 * Class purpose:
 *
 * @author crossj
 * @version created on 3/22/2019 at 9:25 AM
 */
public interface AutoCompleter {
    /**
     * Loads the dictionary file
     * @param filename String the gets the path of the chosen file
     * @throws IOException throws if unable to open file
     */
    void initialize(String filename) throws IOException;

    /**
     * Returns the list of all prefix matches
     * @param prefix wanted search comparison
     * @return returns list of the prefix matches
     */
    List<String> allThatBeginWith(String prefix);

    /**
     * Returns number of nanoseconds of last call
     * @return returns long of number of nanoseconds required to do last call
     */
    long getLastOperationTime();

    /**
     * Describes what the strategy does
     * @return description
     */
    String strategyDescription();

    /*
    BenchMarking times only incorporate time to do AllThatBeginsWith Method:
        words.txt:
            Indexed ArrayList:
                Hitting F key: 101 Milliseconds
            Indexed LinkedList:
                Hitting F key: 00:05.843
            Iterated ArrayList:
                Hitting F key: 00:02.871
            Iterated LinkedList:
                Hitting F key: 00:04.302
            Sorted ArrayList:
                Hitting F key: 4 Milliseconds
            TreeOfMaps:
                Hitting F key: 60 Microseconds

        top-1m.csv (without printing it out to textArea):
            Sorted ArrayList:
                Hitting V key: 29 Milliseconds
            TreeOfMaps:
                Hitting V key: 45 Microseconds
            Indexed ArrayList:
                Hitting V key: 00:01.162
            Iterated ArrayList:
                Hitting V key: 03:36.180

    Discussion:
    Strategy One(TreeOfMaps):

    MODIFICATIONS:
        to the treeMap were made due to the treeMap not being able to do
    exactly what was needed, I had no control over the order of nodes and what connected
    to what, thus I changed it to my own implementation of the same idea, I made a tree that
    had nodes of a key and value, the value being the list that contained everything that
    had that prefix up to the node in the tree, in order to know where to go after the current node
    the node.next was a list of nodes that contains all the keys related or that branch out from the current node
    I could'nt do just setting of the left or right because I couldn't know the number of nodes
    needed to be added thus I did a list, this is still constant time because even though I don't
    know every node to be added from the list uploaded it always has a max because only so many
    chars exist for the keys to be, thus still constant,

    TIME COMPLEXITY:
        the big Oh of the this implementation is O(1) because
    you only need to go down one branch in order to find the list of all the begins with that prefix
    and don't need to bother with the rest of the data, this is faster than all the other complexities
    of the other strategies

    FAULTS:
        Needs a lot of storage since each node contains a value of all that begins with that
    prefix so will have duplicates going up the tree, leafs will all be duplicate information
    of items within the list in root

    POSITIVES:
        from the time stamps it is shown that it is faster than every other implementation
    and the information does not need to be sorted in order for this to happen, when initializing
    the top-1m.csv took about 54 seconds which was faster than the 3 minutes for the initialization of
    the sorted array list




    Strategy Two(Sorted Array):

    MODIFICATIONS:
        used everything except the indexOf method from the edu.msoe.cs2852 sortedArrayList
    because the indexOf method inside the class just returned -1 if not found and I
    needed the actual index that Collections.binarySearch returned thus created my own
    implementation of that method

    TIME COMPLEXITY:
        since it uses Collections.binarySearch to find the indexes of where to start
    and stop, the big Oh is O(log(n)), it does not go up to O(n) even when inside the for loop because
    it has a set amount of what to add and chunks the data if n is fixed then that is constant time
    which is the second fastest complexity from all strategies

    FAULTS:
        Needs to be sorted Data to work properly, slower initialization than the tree of maps

    POSITIVES:
        One list and so it does not have duplicates and so less data is used,
    faster than the every other implementation other than TreeOfMaps

    */
}