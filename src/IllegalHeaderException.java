public class IllegalHeaderException extends RuntimeException {

    public IllegalHeaderException(){

    }
    public IllegalHeaderException(String Error){
        System.out.println("Fehler im Header");
    }
}
