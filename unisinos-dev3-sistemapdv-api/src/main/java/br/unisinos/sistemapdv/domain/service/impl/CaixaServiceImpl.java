package br.unisinos.sistemapdv.domain.service.impl;

import br.unisinos.sistemapdv.domain.exception.ValidationException;
import br.unisinos.sistemapdv.domain.model.Caixa;
import br.unisinos.sistemapdv.domain.service.CaixaService;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by lfaitao on 5/7/17.
 */
@Service
public class CaixaServiceImpl implements CaixaService {

    private static final boolean CAIXA_ABERTO = true;
    private static final boolean CAIXA_FECHADO = false;

    /*
     * Atributos
     */

    private Caixa caixa;

    /*
     * Construtores
     */

    private CaixaServiceImpl() {
        // Necessário por causa do Spring
    }

    /*
     * Métodos
     */
    @Override
    public void abrirCaixa(Caixa caixa) {
        this.caixa = caixa;
        caixa.setCaixaAberto(CAIXA_ABERTO);
    }

    @Override
    public Caixa fecharCaixa() throws ValidationException {
        if (!isCaixaAberto()) {
            throw new ValidationException("O Caixa já está fechado!");
        } else {
            caixa.setCaixaAberto(CAIXA_FECHADO);
            return this.caixa;
        }
    }

    @Override
    public Caixa suprirCaixa(Double valor) throws ValidationException {
        if (isCaixaAberto()) {
            caixa.suprirCaixa(valor);
            return this.caixa;
        } else {
            throw new ValidationException("O Caixa deve estar aberto para que o suprimento seja realizado!");
        }
    }

    @Override
    public Caixa sangrarCaixa(Double valor) throws ValidationException {
        if (isCaixaAberto()) {
            caixa.sangrarCaixa(valor);
            return this.caixa;
        } else {
            throw new ValidationException("O Caixa deve estar aberto para que a sangria seja realizada!");
        }
    }

    @Override
    public Caixa abrirDiaFiscal() throws ValidationException {
        if (isCaixaAberto()) {
            if (podeAbrirDiaFiscalHoje()) {
                if (!isDiaFiscalAberto()) {
                    caixa.abrirDiaFiscal();
                    return this.caixa;
                } else {
                    throw new ValidationException("O dia fiscal já está aberto para este caixa.");
                }
            } else {
                throw new ValidationException("O dia fiscal já foi fechado hoje. Este caixa só poderá ser reaberto a partir das 00:00 de amanhã.");
            }
        } else {
            throw new ValidationException("O Caixa deve estar aberto para que a abertura do dia fiscal seja realizada!");
        }
    }

    private boolean podeAbrirDiaFiscalHoje() {
        boolean retorno;

        // Se o dia fiscal já foi fechado, verifica se já passou do dia do fechamento
        if (caixa.isDiaFiscalFechado()) {
            Calendar calHoje= Calendar.getInstance();
            Calendar calBanco= Calendar.getInstance();

            calHoje.setTime(new Date());
            calHoje.set(Calendar.HOUR_OF_DAY, 0);
            calHoje.set(Calendar.MINUTE, 0);
            calHoje.set(Calendar.SECOND, 0);
            calHoje.set(Calendar.MILLISECOND, 0);

            calBanco.setTime(caixa.getDiaFiscal());
            calBanco.set(Calendar.HOUR_OF_DAY, 0);
            calBanco.set(Calendar.MINUTE, 0);
            calBanco.set(Calendar.SECOND, 0);
            calBanco.set(Calendar.MILLISECOND, 0);

            retorno = calHoje.after(calBanco);
        } else {
            retorno = true;
        }

        return retorno;
    }

    @Override
    public Caixa fecharDiaFiscal() throws ValidationException {
        if (isCaixaAberto()) {
            if (isDiaFiscalAberto()) {
                caixa.fecharDiaFiscal();
                return this.caixa;
            } else {
                throw new ValidationException("O dia fiscal já está fechado para este caixa.");
            }
        } else {
            throw new ValidationException("O Caixa deve estar aberto para que o fechamento do dia fiscal seja realizada!");
        }
    }

    @Override
    public boolean isCaixaAberto() {
        return caixa.isCaixaAberto();
    }

    @Override
    public boolean isDiaFiscalAberto() {
        return caixa.isDiaFiscalAberto();
    }

}
