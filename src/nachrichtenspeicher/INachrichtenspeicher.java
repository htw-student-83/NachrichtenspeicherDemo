package nachrichtenspeicher;

public interface INachrichtenspeicher {

    /**
     * add an element to the list if the length of it is max 20,
     * is the length of the list 5 so the already saved element at index 0 will be overwritten by the new element,
     * the other elements move one step to the right and an object listiner will get the info.
     * @param element
     * @throws Exception
     */
    void addAnElement(String element)throws Exception;



    /**
     * get the length of the List.
     * @return the length
     */
    int getSize();


    /**
     * get an element from the list
     * @param i is the index, where the element is that we want to get
     * @throws Exception
     * @return the element
     */
    String getElement(int i) throws Exception;


    /**
     * save all messages in a file
     * @throws Exception
     */
    void persist() throws Exception;


    /**
     * reload all messages from a file and eventually overwrite
     * these with already exist messages in the list
     * @throws Exception
     */
    void reload() throws Exception;


    /**
     * create a new name of a file, where the content of the list will write into.
     * @return
     */
    String setFileName();


    /**
     * delete all elements in the list
     */
    void removeAllElements()throws Exception;

}
