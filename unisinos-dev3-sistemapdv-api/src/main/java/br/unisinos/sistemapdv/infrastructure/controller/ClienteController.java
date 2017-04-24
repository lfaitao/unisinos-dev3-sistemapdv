package br.unisinos.sistemapdv.infrastructure.controller;

import br.unisinos.sistemapdv.application.repository.ClienteRepository;
import br.unisinos.sistemapdv.domain.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @ResponseBody
    @CrossOrigin(origins = "*")
    @GetMapping("/clientes/all")
    public List<Cliente> get() {
        return clienteRepository.findAll();
    }

    @ResponseBody
    @CrossOrigin(origins = "*")
    @GetMapping("/clientes")
    public List<Cliente> get(@RequestParam String nome, @RequestParam String cpf) {
        List<Cliente> clientes = clienteRepository.findByNomeOrCpfContaining(nome, cpf);
        return clientes;
    }

    @ResponseBody
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/cliente/{cpf}")
    public Cliente get(@PathVariable String cpf) {
        return clienteRepository.findOneByCpf(cpf);
    }

    @ResponseBody
    @CrossOrigin(origins = "*")
    @PostMapping("/clientes")
    public Cliente post(@RequestBody Cliente cliente) {
        Cliente clienteSalvo = clienteRepository.save(cliente);
        return clienteSalvo;
    }

    @ResponseBody
    @CrossOrigin(origins = "*")
    @PutMapping("/clientes")
    public void put(@RequestBody Cliente cliente) {
        Cliente clienteExistente = clienteRepository.findOne(cliente.getId());
        clienteExistente.atualizar(cliente);
        clienteRepository.save(clienteExistente);
    }

    @ResponseBody
    @CrossOrigin(origins = "*")
    @DeleteMapping("/clientes/{id}")
    public void delete(@PathVariable Long id) {
        clienteRepository.delete(id);
    }
}