package com.UPX_IV.mobilizaUrbis.config;

import com.UPX_IV.mobilizaUrbis.entity.Categoria;
import com.UPX_IV.mobilizaUrbis.repository.CategoriaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CategoriaRepository categoriaRepository;

    public DataInitializer(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (categoriaRepository.count() == 0){
             Categoria buracoVia = new Categoria();
             buracoVia.setNome("Buraco na Via");

             Categoria iluminacao = new Categoria();
             iluminacao.setNome("Iluminação Pública");

             Categoria sinalizacaoDanificada = new Categoria();
             sinalizacaoDanificada.setNome("Sinalização Danificada");

             Categoria lixoAcumulado = new Categoria();
             lixoAcumulado.setNome("Lixo Acumulado");

             categoriaRepository.saveAll(Arrays.asList(buracoVia,iluminacao,sinalizacaoDanificada,lixoAcumulado));

            System.out.println(">>> Categorias criadas!");
        }
        else {
            System.out.println(">>> Tabela de categorias ja está populado!");
        }

    }
}
