package br.com.sysagrega.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.sysagrega.model.IProposta;
import br.com.sysagrega.model.Enums.TipoArquivoRelatorio;
import br.com.sysagrega.model.imp.CustoExecucao;
import br.com.sysagrega.util.jsf.FacesUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * Classe util que gera o relatório das três formas: passando conexão, passando
 * ResultSet e passando uma lista de objetos.
 * 
 * @author Elton
 *
 */
public class RelatorioUtil {

	private static String saida;

	/**
	 * Esse tipo de geração de relatório é útil quando a query com o banco pode
	 * mudar dinamicamente. Exemplo: utilização de um filtro.
	 * 
	 * @return String navigation rule que exibe o relatório
	 */
	public String geraRelatorioPassandoResultSet() {
		saida = null;
		String jasper = getDiretorioReal("/jasper/nome_arquivo.jasper");
		Connection conexao = null;

		try {
			// Abro a conexão com o banco
			// conexao = new Conexao().getConexao();
			// Gero o ResultSet que será enviado para o Jasper a partir da
			// conexão aberta
			// JRResultSetDataSource jrsds = new
			// JRResultSetDataSource(getResultSet(conexao));
			// Mando o jasper gerar o relatório
			// JasperPrint print = JasperFillManager.fillReport(jasper, null,
			// jrsds);
			// Gero o PDF
			// preenchePdf(print);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// Sempre mando fechar a conexão, mesmo que tenha dado erro
				if (conexao != null)
					conexao.close();
			} catch (SQLException e) {

			}
		}

		return "exibeRelatorio";
	}

	/**
	 * Esse tipo de geração de relatório é uma alternativa aos outros dois.
	 * Nesse exemplo utilizo um subrelatório param mostrar essa técnica que
	 * também pode ser empregada.
	 * 
	 * @return String navigation rule que exibe o relatório
	 */
	public String geraRelatorioPassandoListaDeObjetos() {
		saida = null;
		String jasper = getDiretorioReal("/jasper/nome_relatorio.jasper");
		Connection conexao = null;

		try {
			// Conexão com o banco para o segundo relatório
			// conexao = new Conexao().getConexao();
			// criação dos parametros
			Map<String, Object> map = new HashMap<String, Object>();
			// conexão com o banco que será utilizada pelo subrelatório
			map.put("REPORT_CONNECTION", conexao);
			// pego o caminho do diretório onde se encontra o subrelatório
			map.put("SUBREPORT_DIR", getDiretorioReal("/jasper/") + "/");
			// ArrayList<Aluno> alunos = getListaAlunos(conexao);

			// JRBeanCollectionDataSource ds = new
			// JRBeanCollectionDataSource(alunos);
			/*
			 * Mando o jasper gerar o relatório. Nesse caso passo o map, já que
			 * ele tem dois parâmetros que serão utilizados
			 */
			// JasperPrint print = JasperFillManager.fillReport(jasper, map,
			// ds);
			// Gero o PDF
			// preenchePdf(print);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "exibeRelatorio";
	}

	/**
	 * Método para preencher o PDF do relatório
	 * 
	 * @param print
	 *            JasperPrint
	 * @throws JRException
	 */
	private static void preenchePdf(JasperPrint print, String nomeRelatorio) throws JRException {

		saida = InterfaceConstants.IREPORT_PATH_WIN + nomeRelatorio + TipoArquivoRelatorio.PDF.getDescricao();
		// Exporto para PDF
		JasperExportManager.exportReportToPdfFile(print, saida);

		File arquivo = new File(saida);
		int tamanho = (int) arquivo.length();

		// Obtêm o response jsf
		HttpServletResponse response = FacesUtil.getResponseJsf();

		try {

			response.setContentType("application/pdf"); // tipo do conteúdo na resposta
			response.setContentLength(tamanho); // ajuda na barra de progresso
			response.setHeader("Content-Disposition", "attachment; filename=RelatorioProposta.pdf");

			OutputStream output = response.getOutputStream();
			Files.copy(arquivo.toPath(), output); // escreve bytes no fluxo de saída

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public static void geraRelatorio(String nomeRelatorio, List<IProposta> proposta) {

		String jasper = getDiretorioReal(
				InterfaceConstants.IREPORT_PATH_JASPER + nomeRelatorio + TipoArquivoRelatorio.JASPER.getDescricao());

		try {

			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(proposta, false);
			

			Map<String, Object> param = new HashMap<String, Object>();
			
			// Carrega imagem logo agrega padrão
			//BufferedImage image = getImagePath();

			//param.put("IMAGE_PATH", image);
			param.put("SUBREPORT_DIR", getDiretorioReal("/jasper/") + "/");

			JasperPrint print = JasperFillManager.fillReport(jasper, param, ds);
			// Gero o PDF
			preenchePdf(print, nomeRelatorio);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método para retornar o caminho completo do diretório onde se encontra o
	 * arquivo 'jasper' e o arquivo 'pdf'
	 * 
	 * @param diretorio
	 *            String diretório a ser localizado na aplicação
	 * @return String caminho completo
	 */
	private static String getDiretorioReal(String diretorio) {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		return session.getServletContext().getRealPath(diretorio);
	}

	private static BufferedImage getImagePath() throws IOException {
		
		File file = new File(getDiretorioReal(InterfaceConstants.IREPORT_PATH_IMAGE));
		FileInputStream fis = new FileInputStream(file);
		
		return ImageIO.read(fis);

	}

	/**
	 * Método para retornar o nome da aplicação
	 * 
	 * @return String nome da aplicacao
	 */
	private static String getContextPath() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		return session.getServletContext().getContextPath();
	}

	public String getSaida() {
		return saida;
	}

	public void setSaida(String saida) {
		this.saida = saida;
	}

}
