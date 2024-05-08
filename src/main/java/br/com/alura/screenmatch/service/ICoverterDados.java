package br.com.alura.screenmatch.service;

public interface ICoverterDados {
    <T> T obterDados(String json, Class<T> classe);
}
