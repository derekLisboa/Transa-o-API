package com.java.transacao_api.business.service;

import java.time.OffsetDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.mockito.junit.jupiter.MockitoExtension;

import com.java.transacao_api.business.services.TransacaoService;
import com.java.transacao_api.controller.dtos.EstatisticasResponseDTO;
import com.java.transacao_api.controller.dtos.TransacaoRequestDTO;
import com.java.transacao_api.infrastructure.exceptions.UnprocessableEntity;

@ExtendWith(MockitoExtension.class)
public class TransacaoServiceTeste {

    @InjectMocks
    TransacaoService transacaoService;

    TransacaoRequestDTO transacao;
    
    EstatisticasResponseDTO estatisticas;
    
    @BeforeEach
    void setup(){
        transacao = new TransacaoRequestDTO(20.0, OffsetDateTime.now());
        estatisticas = new EstatisticasResponseDTO(1L, 20.0, 20.0, 20.0, 20.0);

    }

    @Test
    void deveAdicionarTransacaoComSucesso(){

        transacaoService.adicionarTransacoes(transacao);

        List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacoes(5000);

        assertTrue(transacoes.contains(transacao));
    }

    @Test
    void deveLancarExcecaoCasoValorNegativo(){
        UnprocessableEntity exception = assertThrows(UnprocessableEntity.class,
             () -> transacaoService.adicionarTransacoes(new TransacaoRequestDTO(-10.0, OffsetDateTime.now())));

        assertEquals("Valor não pode ser menor que 0", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoCasoDataOuHoraMaiorQueAtual(){
        UnprocessableEntity exception = assertThrows(UnprocessableEntity.class,
             () -> transacaoService.adicionarTransacoes(new TransacaoRequestDTO(10.0, OffsetDateTime.now().plusDays(1))));

        assertEquals("Data e hora maiores que a data e horas atuais", exception.getMessage());
    }


    @Test
    void deveLimparTransacaoComSucesso(){

        transacaoService.limparTransacoes();

        List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacoes(5000);

        assertTrue(transacoes.isEmpty());
    }

    @Test
    void deveBuscarTransacoesDentroDoIntervalo(){

        TransacaoRequestDTO dto = new TransacaoRequestDTO(10.0, OffsetDateTime.now().minusHours(1));

        transacaoService.adicionarTransacoes(transacao);
        transacaoService.adicionarTransacoes(dto);

        List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacoes(60);

        assertTrue(transacoes.contains(transacao));
        assertFalse(transacoes.contains(dto));

    }
}
