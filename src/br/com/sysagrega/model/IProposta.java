package br.com.sysagrega.model;

import java.util.Set;

import br.com.sysagrega.model.imp.CustoAdministrativo;
import br.com.sysagrega.model.imp.CustoBdiComissao;
import br.com.sysagrega.model.imp.CustoDeslocamento;
import br.com.sysagrega.model.imp.CustoExecucao;
import br.com.sysagrega.model.imp.CustoOperacional;
import br.com.sysagrega.model.imp.CustoSeguranca;

public interface IProposta {

	boolean isNovo();

	boolean isExistente();

	double getCalculoValorTotalCustosBdiComissao();

	double getCalculoValorTotalCustosAdministraticos();

	double getCalculoValorTotalCustosSeguranca();

	double getCalculoValorTotalCustosOperacionais();

	double getCalculoValorTotalCustosDeslocamento();

	double getCalculoValorTotalCustosExecucao();

	void setDespesasBdiComissao(Set<CustoBdiComissao> despesasBdiComissao);

	Set<CustoBdiComissao> getDespesasBdiComissao();

	void setDespesasAdministrativas(Set<CustoAdministrativo> despesasAdministrativas);

	Set<CustoAdministrativo> getDespesasAdministrativas();

	void setDespesasSeguranca(Set<CustoSeguranca> despesasSeguranca);

	Set<CustoSeguranca> getDespesasSeguranca();

	void setDespesasOperacionais(Set<CustoOperacional> despesasOperacionais);

	Set<CustoOperacional> getDespesasOperacionais();

	void setDespesasDeslocamentos(Set<CustoDeslocamento> despesasDeslocamentos);

	Set<CustoDeslocamento> getDespesasDeslocamentos();

	void setCustos(Set<CustoExecucao> custos);

	Set<CustoExecucao> getCustos();

}