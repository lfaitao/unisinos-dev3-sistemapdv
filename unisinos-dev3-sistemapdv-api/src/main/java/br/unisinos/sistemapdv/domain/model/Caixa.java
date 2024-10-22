package br.unisinos.sistemapdv.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by lfaitao on 26/03/2017.
 */
@Entity
@Table(name = "CAIXAS")
public class Caixa {

    /*
     * Atributos
     */

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @NotNull
    private Integer numeroCaixa;

    @NotNull
    private Double qtDinheiro;

    @NotNull
    private Double qtDinheiroMinimo;

    @NotNull
    private Double qtDinheiroMaximo;

    @NotNull
    private Date diaFiscal;

    @NotNull
    private boolean diaFiscalAberto;

    @NotNull
    private boolean diaFiscalFechado;

    @NotNull
    private boolean caixaAberto;

    /*
     * Construtores
     */

    private Caixa() {
        // Necessário por causa do Spring
    }

    public Caixa(Integer numeroCaixa) {
        this.numeroCaixa = numeroCaixa;
        this.qtDinheiro = 0.0;
        this.qtDinheiroMinimo = 100.0;
        this.qtDinheiroMaximo = 1000.0;
        this.diaFiscal = new Date();
        this.diaFiscalAberto = false;
        this.diaFiscalFechado = false;
        this.caixaAberto = false;
    }

    /*
     * Metodos
     */

    public Long getId() {
        return id;
    }

    public double getQtDinheiro() {
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

    public void setQtDinheiroMaximo(double qtDinheiroMaximo) {
        this.qtDinheiroMaximo = qtDinheiroMaximo;
    }

    public boolean isCaixaAberto() {
        return caixaAberto;
    }

    public void setCaixaAberto(boolean caixaAberto) {
        this.caixaAberto = caixaAberto;
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

    public Integer getNumeroCaixa() {
        return numeroCaixa;
    }

    public void setNumeroCaixa(Integer numeroCaixa) {
        this.numeroCaixa = numeroCaixa;
    }

    public void suprirCaixa(Double valor) {
        this.qtDinheiro += valor;
    }

    public void sangrarCaixa(Double valor) {
        this.qtDinheiro -= valor;
    }

    public void abrirDiaFiscal() {
        this.diaFiscal = new Date();
        this.diaFiscalAberto = true;
        this.diaFiscalFechado = false;
    }

    public void fecharDiaFiscal() {
        this.diaFiscalAberto = false;
        this.diaFiscalFechado = true;
    }
}
