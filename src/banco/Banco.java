package banco;

import clientes.Cliente;
import contas.Conta;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Banco {
    private String nome;
    private List<Conta> contas;
    private List<Cliente> clientes;

    public Banco(String nome) {
        this.nome = nome;
        this.contas = new ArrayList<>();
        this.clientes = new ArrayList<>();
    }

    public void adicionarConta(Conta conta){
        contas.add(conta);
    }

    public void adicionarCliente(Cliente cliente){
        clientes.add(cliente);
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public List<Conta> getContasPorCPF(String cpf){
        return contas.stream()
                .filter(c -> c.getCliente().getCpf().equals(cpf))
                .collect(Collectors.toList());
    }

    public List<Conta> getContasPorCliente(Cliente cliente) {
        return contas.stream().filter(c -> c.getCliente().equals(cliente)).collect(Collectors.toList());
    }
}
