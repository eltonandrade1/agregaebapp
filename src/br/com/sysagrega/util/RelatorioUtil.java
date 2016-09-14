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
 * Classe util que gera o relat�rio das tr�s formas: passando conex�o, passando
 * ResultSet e passando uma lista de objetos.
 * 
 * @author Elton
 *
 */
public class RelatorioUtil {

	private static String saida;

	/**
	 * Esse tipo de gera��o de relat�rio � �til quando a query com o banco pode
	 * mudar dinamicamente. Exemplo: utiliza��o de um filtro.
	 * 
	 * @return String navigation rule que exibe o relat�rio
	 */
	public String geraRelatorioPassandoResultSet() {
		saida = null;
		String jasper = getDiretorioReal("/jasper/nome_arquivo.jasper");
		Connection conexao = null;

		try {
			// Abro a conex�o com o banco
			// conexao = new Conexao().getConexao();
			// Gero o ResultSet que ser� enviado para o Jasper a partir da
			// conex�o aberta
			// JRResultSetDataSource jrsds = new
			// JRResultSetDataSource(getResultSet(conexao));
			// Mando o jasper gerar o relat�rio
			// JasperPrint print = JasperFillManager.fillReport(jasper, null,
			// jrsds);
			// Gero o PDF
			// preenchePdf(print);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// Sempre mando fechar a conex�o, mesmo que tenha dado erro
				if (conexao != null)
					conexao.close();
			} catch (SQLException e) {

			}
		}

		return "exibeRelatorio";
	}

	/**
	 * Esse tipo de gera��o de relat�rio � uma alternativa aos outros dois.
	 * Nesse exemplo utilizo um subrelat�rio param mostrar essa t�cnica que
	 * tamb�m pode ser empregada.
	 * 
	 * @return String navigation rule que exibe o relat�rio
	 */
	public String geraRelatorioPassandoListaDeObjetos() {
		saida = null;
		String jasper = getDiretorioReal("/jasper/nome_relatorio.jasper");
		Connection conexao = null;

		try {
			// Conex�o com o banco para o segundo relat�rio
			// conexao = new Conexao().getConexao();
			// cria��o dos parametros
			Map<String, Object> map = new HashMap<String, Object>();
			// conex�o com o banco que ser� utilizada pelo subrelat�rio
			map.put("REPORT_CONNECTION", conexao);
			// pego o caminho do diret�rio onde se encontra o subrelat�rio
			map.put("SUBREPORT_DIR", getDiretorioReal("/jasper/") + "/");
			// ArrayList<Aluno> alunos = getListaAlunos(conexao);

			// JRBeanCollectionDataSource ds = new
			// JRBeanCollectionDataSource(alunos);
			/*
			 * Mando o jasper gerar o relat�rio. Nesse caso passo o map, j� que
			 * ele tem dois par�metros que ser�o utilizados
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
	 * M�todo para preencher o PDF do relat�rio
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

		// Obt�m o response jsf
		HttpServletResponse response = FacesUtil.getResponseJsf();

		try {

			response.setContentType("application/pdf"); // tipo do conte�do na resposta
			response.setContentLength(tamanho); // ajuda na barra de progresso
			response.setHeader("Content-Disposition", "attachment; filename=RelatorioProposta.pdf");

			OutputStream output = response.getOutputStream();
			Files.copy(arquivo.toPath(), output); // escreve bytes no fluxo de sa�da

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
			
			// Carrega imagem logo agrega padr�o
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
	 * M�todo para retornar o caminho completo do diret�rio onde se encontra o
	 * arquivo 'jasper' e o arquivo 'pdf'
	 * 
	 * @param diretorio
	 *            String diret�rio a ser localizado na aplica��o
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
	 * M�todo para retornar o nome da aplica��o
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
