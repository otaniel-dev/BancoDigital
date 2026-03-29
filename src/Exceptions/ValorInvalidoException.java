package Exceptions;

public class ValorInvalidoException extends Exception{
    public ValorInvalidoException(){
        super("Não é possivel operar com um valor negativo.");
    }
}
