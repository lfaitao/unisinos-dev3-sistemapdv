package br.unisinos.sistemapdv.infrastructure.dto;

import br.unisinos.sistemapdv.domain.model.Caixa;

import java.util.Date;

/**
 * Created by lfaitao on 4/20/17.
 */
public class CaixaDTO {

    private Integer numeroCaixa;
    private Double qtDinheiro;
    private Double qtDinheiroMinimo;
    private Double qtDinheiroMaximo;
    private Date diaFiscal;
    private boolean diaFiscalAberto;
    private boolean diaFiscalFechado;
    private boolean caixaAberto;

    public CaixaDTO(Caixa caixa) {
        this.numeroCaixa = caixa.getNumeroCaixa();
        this.qtDinheiro = caixa.getQtDinheiro();
        this.qtDinheiroMinimo = caixa.getQtDinheiroMinimo();
        this.qtDinheiroMaximo = caixa.getQtDinheiroMaximo();
        this.diaFiscal = caixa.getDiaFiscal();
        this.diaFiscalAberto = caixa.isDiaFiscalAberto();
        this.diaFiscalFechado = caixa.isDiaFiscalFechado();
        this.caixaAberto = caixa.isCaixaAberto();
    }

    public Integer getNumeroCaixa() {
        return numeroCaixa;
    }

    public void setNumeroCaixa(Integer numeroCaixa) {
        this.numeroCaixa = numeroCaixa;
    }

    public Double getQtDinheiro() {
        return qtDinheiro;
    }

    public void setQtDinheiro(Double qtDinheiro) {
        this.qtDinheiro = qtDinheiro;
    }

    public Double getQtDinheiroMinimo() {
        return qtDinheiroMinimo;
    }

    public void setQtDinheiroMinimo(Double qtDinheiroMinimo) {
        this.qtDinheiroMinimo = qtDinheiroMinimo;
    }

    public Double getQtDinheiroMaximo() {
        return qtDinheiroMaximo;
    }

    public void setQtDinheiroMaximo(Double qtDinheiroMaximo) {
        this.qtDinheiroMaximo = qtDinheiroMaximo;
    }

    public Date getDiaFiscal() {
        return diaFiscal;
    }

    public void setDiaFiscal(Date diaFiscal) {
        this.diaFiscal = diaFiscal;
    }

    public boolean isDiaFiscalAberto() {
        return diaFiscalAberto;
    }

    public void setDiaFiscalAberto(boolean diaFiscalAberto) {
        this.diaFiscalAberto = diaFiscalAberto;
    }

    public boolean isDiaFiscalFechado() {
        return diaFiscalFechado;
    }

    public void setDiaFiscalFechado(boolean diaFiscalFechado) {
        this.diaFiscalFechado = diaFiscalFechado;
    }

    public boolean isCaixaAberto() {
        return caixaAberto;
    }

    public void setCaixaAberto(boolean caixaAberto) {
        this.caixaAberto = caixaAberto;
    }

}
