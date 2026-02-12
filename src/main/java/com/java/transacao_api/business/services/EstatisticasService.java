package com.java.transacao_api.business.services;

import java.util.DoubleSummaryStatistics;
import java.util.List;

import org.springframework.stereotype.Service;

import com.java.transacao_api.controller.dtos.EstatisticasResponseDTO;
import com.java.transacao_api.controller.dtos.TransacaoRequestDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EstatisticasService {

    public final TransacaoService transacaoService;

    public EstatisticasResponseDTO calcularEstatisticasTransacoes(Integer intervaloBusca){
        List<TransacaoRequestDTO> transacoes =  transacaoService.buscarTransacoes(intervaloBusca);
        
        DoubleSummaryStatistics estatisticasTransacoes = transacoes.stream()
                                .mapToDouble(TransacaoRequestDTO::valor).summaryStatistics();
        
        return new EstatisticasResponseDTO(estatisticasTransacoes.getCount(),
        estatisticasTransacoes.getSum(),
        estatisticasTransacoes.getAverage(),
        estatisticasTransacoes.getMin(),
        estatisticasTransacoes.getMax());
    }

}