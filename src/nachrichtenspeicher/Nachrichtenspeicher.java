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
    public void addAnElement(String elementNeu)throws Exception {
        if(elementNeu.length() > 20){
            throw new ToLongStringException("Der neue String ist zu lang.");
        }else if(DEFAULT_CAPACITY == list.size()){
            ListenerObjekt listenerObjekt = new ListenerObjekt();
            //Überarbeiten!
            listenerObjekt.notifyOverWrite(getElement(0));
            String elementS1 = getElement(0);
            set(1,elementS1);
            String elementS2 = getElement(1);
            set(2,elementS2);
            String elementS3 = getElement(2);
            set(3,elementS3);
            String elementS4 = getElement(3);
            set(4,elementS4);
            set(0, elementNeu);
        }else{
            list.add(elementNeu);
        }
    }


    @Override
    public int getSize() {
        for(int i = 0; i<list.size(); i++){
            listSize++;
        }
        return listSize;
    }


    @Override
    public void removeAllElements()throws Exception {
        if(list.size()==0){
            throw new NoDataException();
        }else{
            for(String element: list){
                list.remove(element);
            }
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
        File file = new File("C:\\Users\\Danny\\Desktop"setFileName());
        FileOutputStream fis = new FileOutputStream(file);
        if(!file.exists()){
            throw new FileNotFoundException();
        }else if(list.size()==0){
            throw new NoDataException();
        }else{
            setFileName();
            file.createNewFile();
            for(int i = 0; i<list.size(); i++){
                String elementS = list.get(i);
                fis.write(Integer.parseInt(elementS));
            }
            fis.close();
        }
    }


    @Override
    public void reload() throws Exception{
        File file = new File("C:\\Users\\Danny\\Desktop");
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
    public void setFileName() {
        String namePart = "Nachrichtenspeicher_Inhalt";
        int nummer = 0;
        nummer++;
        System.out.println(namePart+nummer);
    }
}
