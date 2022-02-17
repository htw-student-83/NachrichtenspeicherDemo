package nachrichtenspeicher;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Nachrichtenspeicher implements INachrichtenspeicher {
    private static final int DEFAULT_CAPACITY = 5;
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
            File file = new File(fileName + ".txt");
            try {
                file.createNewFile();
            }catch (Exception e){
                e.printStackTrace();
            }
            OutputStream outputStream;
            outputStream = new FileOutputStream(file);
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
        File file = new File("Nachrichtenspeicher_Inhalt_01.txt");
        InputStream inputStream = new FileInputStream(file);
        String element = "";
        try {
            removeAllElements();

            while((element = inputStream.toString()) != null){
                list.add(element);
            }
        }catch (FileNotFoundException | NoDataException e){
            e.printStackTrace();
        } finally {
            inputStream.close();
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
