package com.br.emprestimo.service.impl;

import com.br.emprestimo.model.ClienteEmprestimoModel;
import com.br.emprestimo.model.OfertaEmprestimoModel;
import com.br.emprestimo.model.TaxaEmprestimoModel;
import com.br.emprestimo.model.TipoEmprestimoModel;
import com.br.emprestimo.service.OfertaProdutoEmprestimoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmprestimoPessoalServiceImpl implements OfertaProdutoEmprestimoService {

    private static final Double TAXA_JURO_EMPRESTIMO_PESSOAL = 4.0;
    @Override
    public Optional<OfertaEmprestimoModel> calcularOferta(ClienteEmprestimoModel clienteModel) {
        List<Boolean> listaRegras = new ArrayList<>();
        listaRegras.add(clienteModel.getValorRenda() > 0);

        boolean atendeRegra = true;

        for (boolean regra: listaRegras) {
            if(!regra) {
                atendeRegra = regra;
            }
        }

        if (atendeRegra) {
            return Optional.of(OfertaEmprestimoModel
                    .builder()
                    .cliente(clienteModel)
                    .tipoEmprestimo(TipoEmprestimoModel.EMPRESTIMO_PESSOAL)
                    .taxaJuros(TaxaEmprestimoModel.builder().taxa(TAXA_JURO_EMPRESTIMO_PESSOAL).build())
                    .build());
        }
        return Optional.ofNullable(null);
    }
}
