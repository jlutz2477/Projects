/**
 * 
 */
package prj5;

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * @author srinjoydey
 * @version 2022.04.21
 * 
 * This class represents a singly linked list that will hold a
 *          collection
 *          of interconnected node that will each hold a generic data type.
 * 
 * 
 * @param <T>
 *            A generic data type that the singly linked list will hold
 * 
 */
public class SLList<T> {
    // ~ Fields.....................................
    private Node<T> firstNode;
    private int numberOfEntries;

    /**
     * Constructor for the SLList class. This method initializes the firstNode
     * field to be null and the numberOfEntries field to be 0
     */
    public SLList() {
        firstNode = null;
        numberOfEntries = 0;
    }


    /**
     * Getter method for numberOfEntries. This method should return the value
     * stored in the numberOfEntries field
     * 
     * @return int
     *         The number of elements in the list
     */
    public int getNumberOfEntries() {
        return this.numberOfEntries;
    }


    /**
     * Getter method for the firstNode field. This method should return the
     * Node referenced by the firstNode field.
     * 
     * @return Node
     *         The first node in the singly linked list.
     */
    public Node<T> getFirstNode() {
        return this.firstNode;
    }


    /**
     * Method to indicate whether or not the linked list is empty. The linked
     * list is considered empty if the numberOfEntries is equal to 0
     * @return 
     *      True if linked list is empty, false if not
     */
    public boolean isEmpty() {
        return this.getNumberOfEntries() == 0;
    }


    /**
     * Method to clear the linked list. This method will set the firstNode to
     * null leaving the rest of the nodes to be garbage collected. This method
     * will also update the number of entries to be 0;
     */
    public void clear() {
        firstNode = null;
        numberOfEntries = 0;
    }


    /**
     * Method to add a value to the linked list. This method will create a
     * new Node and add it to the end of the linked list using a helper method
     * 
     * @param newEntry
     *            The data value that will be added to the end of the list
     */
    public void add(T newEntry) {
        Node<T> newNode = new Node<T>(newEntry);

        if (isEmpty()) {
            firstNode = newNode;
        }
        else {
            Node<T> lastNode = getNodeAt(this.getNumberOfEntries() - 1);
            lastNode.setNext(newNode);
        }
        numberOfEntries++;

    }


    /**
     * Method to get the node at a specified entry. This method will primarily
     * be used inside the add method to find the last entry and add the
     * new entry after this last entry.
     * 
     * @param position
     *            The index (position) that we want to get the node at
     * @return Node
     *         The Node that is at the given position
     */
    private Node<T> getNodeAt(int position) {
        Node<T> curr = this.getFirstNode();
        for (int i = 0; i < position; i++) {
            curr = curr.getNext();
        }
        return curr;

    }


    /**
     * Method to create and return a new iterator. This method will create the
     * new iterator from the nested iterator class.
     * 
     * @return Iterator
     *         A new iterator that will allow for traversing a list.
     */
    public Iterator<T> iterator() {
        return new SLLIterator<T>();
    }


    /**
     * toString method for the SLList class. This method will return a string
     * representation of the linked list, allowing for easier testing and
     * debugging
     * 
     * @return String
     *         A string representation for the object
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        //builder.append("[");
        Node<T> curr = this.getFirstNode();
        
        if (isEmpty()) {
            return "[]";
        }
        else {
            while (curr.getNext() != null) {
                builder.append(curr.getData().toString());
                //builder.append(", ");
                curr = curr.getNext();
            }
            builder.append(curr.getData().toString());
            builder.append("=====");
            return builder.toString();
        }
        /**
        if (!isEmpty()) {
            while (curr.getNext() != null) {
                builder.append(curr.getData().toString());
                //builder.append(", ");
                curr = curr.getNext();
            }
            builder.append(curr.getData().toString());
        }
        //builder.append("]");
        builder.append("=====");

        return builder.toString();
        */
    }
    
    /**
     * Method to return the data located at a specific entry in the list. 
     * This method will take an integer and return the data of the node 
     * located at this position
     * @param position 
     *        The position of the node that we want to access
     * @return T
     *         The data located in the node at the desired position
     */
    public T getDataAtNode(int position) {
        return getNodeAt(position).getData();
    }
    

    /**
     * 
     * @author srinjoydey
     * @version 2022.04.19
     *          Node class that will be used to hold the data that the linked
     *          list
     *          contains. A node will have two fields, one to hold the assigned
     *          data and one to hold the reference to the next node
     */
    private static class Node<T> {
        // ~ Fields..................................................
        private T data;
        private Node<T> next;

        // ~ Constructor...............................................

        /**
         * Constructor for the node class. This constructor only takes in the
         * parameter of the data and sets the next field to be null
         * 
         * @param dataPortion
         *            The data that the node will hold
         */
        public Node(T dataPortion) {
            this.data = dataPortion;
            this.next = null;
        } // end constructor




        /**
         * Getter method for the data field. This method should return the value
         * referenced by the data field.
         * 
         * @return T
         *         The data that the node holds
         */
        public T getData() {
            return this.data;
        }


        /**
         * Setter method for the next field. This method should taken in a node
         * parameter and set the next field to this parameter
         * 
         * @param newNext
         *            The new node that the next field will now hold
         */
        public void setNext(Node<T> newNext) {
            this.next = newNext;
        }


        /**
         * Getter method for the next field. Tis method should return the Node
         * referenced by the next field
         * 
         * @return Node
         *         The node returned should be the node that was referenced by
         *         the next field.
         */
        public Node<T> getNext() {
            return this.next;
        }

    } // end private node class


    /**
     * 
     * @author srinjoydey
     * @version 2022.04.19
     * 
     *          Iterator class for the singly linked list class. This class will
     *          allow traversing the linked list to be easier with the methods
     *          that
     *          are implemented below.
     * @param <E>
     *          Data type to be used
     */
    public class SLLIterator<A> implements Iterator<T> {
        // ~ Fields........................
        
        private Node<T> nextNode;

        // ~ Constructor.................................
        /**
         * Constructor for SLLIterator<E>
         */
        public SLLIterator() {
            nextNode = firstNode;
        }

        /**
         * Determines if there is a next node
         * @return
         *      True if there is a next node, false if not
         */
        public boolean hasNext() {
            return nextNode != null;
        }


        /**
         * Calls the next node and returns its data
         * @return T
         *          The data of the next node 
         * @throws NoSuchElementException
         *          If there are no more elements
         */
        public T next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException("No more nodes");
            }
            else {
                T data = nextNode.getData();
                nextNode = nextNode.getNext();
                return data;
                //
            }
            

        }
    }

}
