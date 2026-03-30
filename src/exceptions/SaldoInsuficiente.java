package exceptions;

public class SaldoInsuficiente extends Exception{
    public SaldoInsuficiente(){
        super("Saldo insuficiente para a operação.");
    }
}
