package elisadari.UN5W3d1praticaS3L1.exceptions;

public class NotFoundEx extends RuntimeException{
    public NotFoundEx(String msg){
        super(msg);
    }
    public NotFoundEx(long id){
        super(id+" not found!");
    }
}
