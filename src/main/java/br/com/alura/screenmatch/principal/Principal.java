package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.DadosEpisodio;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.ConverterDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private final String ENDEECO ="https://www.omdbapi.com/?t=";
    private final String APIKEY = "&apikey=b156ae7a";
    private ConsumoApi consumoApi = new ConsumoApi();
    ConverterDados converterDados = new ConverterDados();

    public void exibeMenu() {
        System.out.println("Digite o nome da série: ");
        Scanner leitura = new Scanner(System.in);
        String nomeSerie = leitura.nextLine();
        var json =  consumoApi.obterDados(
                ENDEECO + nomeSerie.replace(" ", "+") + APIKEY);
        DadosSerie dadosSerie = converterDados.obterDados(json, DadosSerie.class);
        System.out.println(dadosSerie);

        List<DadosTemporada> temporadas = new ArrayList<>();
        for(int i = 1; i<= dadosSerie.totalTemporadas(); i++) {
            json = consumoApi.obterDados(
                    ENDEECO + nomeSerie.replace(" ", "+") +"&season=" + i + APIKEY
            );
            DadosTemporada dadosTemporada = converterDados.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }
        temporadas.forEach(System.out::println);
//        for(int i = 0; i < dadosSerie.totalTemporadas(); i++) {
//            List<DadosEpisodio> episodioTemporada = temporadas.get(i).listaDeEpisodios();
//            for(int j = 0; j < episodioTemporada.size(); j++) {
//                System.out.println(episodioTemporada.get(j).titulo());
//
//            }
            temporadas.forEach(t -> t.listaDeEpisodios().forEach(e -> System.out.println(e.titulo())));
        }

    }



