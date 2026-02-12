package com.java.transacao_api.business.services;

import java.util.DoubleSummaryStatistics;
import java.util.List;

import org.springframework.stereotype.Service;

import com.java.transacao_api.controller.dtos.EstatisticasResponseDTO;
import com.java.transacao_api.controller.dtos.TransacaoRequestDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EstatisticasService {

    public final TransacaoService transacaoService;

    public EstatisticasResponseDTO calcularEstatisticasTransacoes(Integer intervaloBusca){
        
        log.info("Iniciada busca de estatisticas de transações pelo periodo de tempo" +intervaloBusca);
        List<TransacaoRequestDTO> transacoes =  transacaoService.buscarTransacoes(intervaloBusca);
        
        DoubleSummaryStatistics estatisticasTransacoes = transacoes.stream()
                                .mapToDouble(TransacaoRequestDTO::valor).summaryStatistics();
        
        log.info("Estatisticas retornadas com sucesso");
        
        return new EstatisticasResponseDTO(estatisticasTransacoes.getCount(),
        estatisticasTransacoes.getSum(),
        estatisticasTransacoes.getAverage(),
        estatisticasTransacoes.getMin(),
        estatisticasTransacoes.getMax());
    }

}