package com.java.transacao_api.business.services;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.java.transacao_api.controller.dtos.TransacaoRequestDTO;
import com.java.transacao_api.infrastructure.exceptions.UnprocessableEntity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransacaoService {

    private final List<TransacaoRequestDTO> listaTransacoes = new ArrayList<>();

    public void adicionarTransacoes(TransacaoRequestDTO dto){

        log.info("Iniciado o processamento de gravar transações");
        

        if(dto.dataHora().isAfter(OffsetDateTime.now())){
            log.error("Data e hora maiores que a data e horas atuais");
            throw new UnprocessableEntity("Data e hora maiores que a data e horas atuais");
        }
        if(dto.valor() < 0){
            log.error("Valor não pode ser menor que 0");
            throw new UnprocessableEntity("Valor não pode ser menor que 0");
        }

        listaTransacoes.add(dto);
    }

}
