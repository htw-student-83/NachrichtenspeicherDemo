package nachrichtenspeicher;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Nachrichtenspeicher implements INachrichtenspeicher {
    private int listSize = 0;
    private static final int DEFAULT_CAPACITY = 5;
    //private String element[];
    List<String> list = new ArrayList<>();

    @Override
    public void addAnElement(String elementNeu) throws Exception{
        if(elementNeu.length() > 20){
            throw new ToLongStringException("Der neue String ist zu lang.");
        }else if(DEFAULT_CAPACITY == list.size()){
            ListenerObjekt listenerObjekt = new ListenerObjekt();
            //Überarbeiten!
            listenerObjekt.notifyOverWrite(getElement(0));
            String element0 = getElement(0);
            String element1 = getElement(1);
            String element2 = getElement(2);
            String element3 = getElement(3);
            removeAllElements();
            list.add(0, elementNeu);
            list.add(1, element0);
            list.add(2, element1);
            list.add(3, element2);
            list.add(4, element3);
        }else{
            list.add(elementNeu);
        }
    }


    @Override
    public int getSize() {
        return list.size();
    }


    @Override
    public void removeAllElements()throws Exception {
        if(list.size()==0){
            throw new NoDataException();
        }else{
            list.clear();
            //for(int i = 0; i<list.size(); i++){
            //    list.remove(i);
            //}
        }
    }


    @Override
    public void set(int i, String message) {
        list.add(i,message);
    }


    @Override
    public String getElement(int i) {
        if(i > getSize() || i < 0 ){
            throw new ArrayIndexOutOfBoundsException("Übergebener Index kann nicht " +
                    "außerhalb der Liste sein...");
        }else {
            return list.get(i);
        }
    }


    @Override
    public void persist() throws Exception {
        if(list.size()==0){
            throw new NoDataException();
        }else{
            String fileName = setFileName();
            File path = new File("..\\" + fileName);
            path.createNewFile();
            OutputStream outputStream;
            outputStream = new FileOutputStream(path);
            for(int i = 0; i<list.size(); i++){
                String elementS = list.get(i);
                outputStream.write(elementS.getBytes());
            }
            outputStream.close();
        }
    }


    @Override
    public void reload() throws Exception{
        //Diese Stelle muss noch überarbeitet werden.
        File file = new File("..\\Nachrichtenspeicher_Inhalt_01");
        FileInputStream fis = new FileInputStream(file);
        String line = "";
        if(!file.exists()){
            throw new FileNotFoundException();
        }else if(fis.read()==0){
            throw new NoDataException();
        }else{
            while((fis.toString())!=null){
                line = fis.toString();
                for(int i = 0; i<line.length(); i++){
                    list.add(i,line);
                }
            }
        }
    }


    @Override
    public String setFileName() {
        String namePart = "Nachrichtenspeicher_Inhalt_0";
        int nummer = 0;
        nummer++;
        return namePart+nummer;
    }
}
