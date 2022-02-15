package nachrichtenspeicher;

public class NoDataException extends Exception{
    public NoDataException(){
        super();
    }

    public NoDataException(String message){
        super(message);
    }
}
