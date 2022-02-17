package nachrichtenspeicher;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import java.io.*;

public class TestNachrichtenspeicher {
    //1) Genau definieren, was dieser Nachrichtenspeicher können soll
    // Darf eine bereits implementierte Methode benutzt werden?
    //2) Erst danach den Nachrichtenspeicher selbst entwickeln laut Testdefinition


    private INachrichtenspeicher getAnObjct(){
        return new Nachrichtenspeicher();
    }

    private IListenerObjekt getAnLObjct(){
        return new ListenerObjekt();
    }
/*
    private INachrichtenspeicher in;
    private IListenerObjekt lo;


    @BeforeEach
    public void getNachrichtenSpeicherObject(){
        in = this.getAnObjct();
        lo = this.getAnLObjct();
    }
*/
    /**
     * to save 5 elements successfully
     */
    @Test
    public void toSavefiveMessages()throws Exception{
        INachrichtenspeicher  in = this.getAnObjct();
        in.addAnElement("Hallo");
        in.addAnElement("Hello");
        in.addAnElement("Apfel");
        in.addAnElement("Birne");
        in.addAnElement("Orange");
        int l = in.getSize();
        Assert.assertEquals(5, l);
    }


    /**
     * to save a String with a length bigger than 20s
     */
    @Test(expected = ToLongStringException.class)
    public void toSaveAveryLongElement()throws Exception{
        INachrichtenspeicher  in = this.getAnObjct();
        in.addAnElement("Hallo liebes Deutschland");
    }


    /**
     * check the reaction of the listener object in case of
     * that more than 5 elements should be saved
     */
    @Test
    public void toSaveSixElements() throws Exception{
        INachrichtenspeicher  in = this.getAnObjct();
        IListenerObjekt lo = this.getAnLObjct();
        in.addAnElement("Hallo");
        in.addAnElement("Hello");
        in.addAnElement("Apfel");
        in.addAnElement("Birne");
        in.addAnElement("Orange");
        in.addAnElement("Traube");
        lo.notifyOverWrite(in.getElement(0));
    }


    /**
     * to check is the size of the list unchangeable in
     * case of saving more than 5 elements
     */
    @Test
    public void toKeepSize()throws Exception{
        INachrichtenspeicher  in = this.getAnObjct();
        in.addAnElement("Hallo");
        in.addAnElement("Hello");
        in.addAnElement("Apfel");
        in.addAnElement("Birne");
        in.addAnElement("Orange");
        in.addAnElement("Traube");
        int l = in.getSize();
        Assert.assertEquals(5,l);
    }


    /**
     * all elements in the system should be deleted
     */
    @Test
    public void toDeleteAllMessages()throws Exception{
        INachrichtenspeicher  in = this.getAnObjct();
        in.addAnElement("Hallo");
        in.addAnElement("Hello");
        in.addAnElement("Apfel");
        in.removeAllElements();
        int l = in.getSize();
        Assert.assertEquals(0, l);
    }



    /**
     * delete elements in the list, although nothing is there
     */
    @Test(expected = NoDataException.class)
    public void deleteAllMessages()throws Exception{
        INachrichtenspeicher  in = this.getAnObjct();
        in.removeAllElements();
    }




    /**
     * check the correctness of an output
     */
    @Test
    public void toGetAnElement()throws Exception{
        INachrichtenspeicher  in = this.getAnObjct();
        in.addAnElement("Hallo");
        in.addAnElement("Hello");
        in.addAnElement("Apfel");
        String element = in.getElement(2);
        Assert.assertEquals("Apfel",element);
    }


    /**
     * check, there are any content in the file, which we already loaded into it
     */
    @Test
    public void outputStreamToAfile()throws Exception{
        INachrichtenspeicher  in = this.getAnObjct();
        in.addAnElement("Hallo");
        in.addAnElement("Hello");
        in.addAnElement("Apfel");
        in.persist();
        File file = new File("C:\\Users\\Danny\\Desktop\\Nachrichtenspeicher_Inhalt_01.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        int zahl = fileInputStream.read();
        Assert.assertTrue(zahl!=0);
    }




    /**
     * check, the reaction of the system, if the file
     * does have any content to read out
     */
    @Test
    public void toGetPersistentContent()throws Exception{
        INachrichtenspeicher  in = this.getAnObjct();
        in.reload();
        int l = in.getSize();
        Assert.assertTrue(l>0);
    }

    /**
     * to check does the file have content from the list
     */
    @Test
    public void toReadContentFromAfile() throws Exception {
        INachrichtenspeicher in = this.getAnObjct();
        File file = new File("...\\Nachrichtenspeicher_Inhalt_01.txt");
        file.createNewFile();
        in.addAnElement("jdfösljdflsjdf");
        in.addAnElement("jdfösljdflsjdf");
        in.persist();
        FileInputStream fis = new FileInputStream(file);
        int isContent = fis.read();
        Assert.assertTrue(isContent > 0);
    }


    /**
     * check will the method create a new filename.
     */
    @Test
    public void getAfileName(){
        INachrichtenspeicher  in = this.getAnObjct();
        String name = in.setFileName();
        Assert.assertNotNull(name);
    }
}
