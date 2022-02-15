package nachrichtenspeicher;

public class ToLongStringException extends Exception{

    public ToLongStringException(){
        super();
    }

    public ToLongStringException(String message){
        super(message);
    }
}
