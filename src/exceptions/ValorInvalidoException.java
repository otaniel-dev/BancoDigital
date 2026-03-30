package exceptions;

public class ValorInvalidoException extends Exception{
    public ValorInvalidoException(){
        super("O valor da operação deve ser maior que zero.");
    }
}
