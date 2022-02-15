package nachrichtenspeicher;

public interface IListenerObjekt {

    /**
     * to overwrite a message, if the list has already 5 elements
     * @param element
     */
    void notifyOverWrite(String element);
}
