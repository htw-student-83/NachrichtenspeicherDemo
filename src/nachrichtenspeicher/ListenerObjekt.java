package nachrichtenspeicher;

public class ListenerObjekt implements IListenerObjekt {

    @Override
    public void notifyOverWrite(String overWrittenMessage) {
        System.out.println("Soeben wurde das Element am Index 0 ├╝berschrieben.");
        System.out.println("Das urspr. Element war: " + overWrittenMessage);
    }
}
