package nachrichtenspeicher;

public class ListenerObjekt implements IListenerObjekt {

    @Override
    public void notifyOverWrite(String overWrittenMessage) {
        System.out.println("Soeben wurde das erste Element überschrieben.");
        System.out.println("Das urspr. Element war: " + overWrittenMessage);
    }
}
