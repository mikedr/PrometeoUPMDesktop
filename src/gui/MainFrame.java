package gui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.chart.JFreeChart;

import com.itextpdf.awt.DefaultFontMapper;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

import controller.Controller;
import model.FilaExcel;
import model.Pasteurization;

public class MainFrame extends JFrame {
	
	private ChartPanel chartPanel;
	private ResultsContainer resultsContainer;
	private JFileChooser fileChooser;
	private Controller controller;
	private TablePanel tablePanel;
	private JMenuBar menuBar;
	
	public MainFrame () {
		super("Prometeo UP Meter");
		setup();
		instaciateComponents();
		addComponents();
	}
	
	private void addComponents() {
		add(tablePanel,BorderLayout.WEST);
		add(chartPanel,BorderLayout.CENTER);
		add(resultsContainer,BorderLayout.EAST);
		setJMenuBar(createMenuBar());
	}
	
	private void addExportarMenuItem() {
		JMenu exportarMenuItem = new JMenu("Exportar resultados");
		JMenuItem medicionesXlsMenuItem = new JMenuItem("Mediciones");
		medicionesXlsMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] columns = { "Tiempo" , "Temperatura" };
				List<FilaExcel> filas = new ArrayList<FilaExcel>();
				List<LocalTime> tiempos = controller.getDb().getPasteurization().getTiempos();
				List<Float> temperaturas = controller.getDb().getPasteurization().getTemperaturas();
				int i = 0;
				for(Float temperatura : temperaturas) {
					FilaExcel fila = new FilaExcel(temperatura,tiempos.get(i));
					filas.add(fila);
					i++;
				}
				Workbook workbook = new XSSFWorkbook();
				Sheet sheet = workbook.createSheet("Mediciones");
				org.apache.poi.ss.usermodel.Font headerFont = workbook.createFont();
				headerFont.setBold(true);
				headerFont.setFontHeightInPoints((short)17);
				headerFont.setColor(IndexedColors.BLUE.getIndex());
				
				CellStyle headerCellStyle = workbook.createCellStyle();
				headerCellStyle.setFont(headerFont);
				
				Row heeaderRow = sheet.createRow(0);
				
				for(int j = 0; j < columns.length ; j++) {
					Cell cell = heeaderRow.createCell(j);
					cell.setCellValue(columns[j]);
					cell.setCellStyle(headerCellStyle);
				}
				
				int rowNum = 0;
				
				for(FilaExcel unaFila : filas) {
					Row row = sheet.createRow(++rowNum);
					row.createCell(0).setCellValue((unaFila.getTiempo().toString()));
					row.createCell(1).setCellValue(unaFila.getTemperatura());
				}
				
				for(int k = 0; k < columns.length ; k++) {
					sheet.autoSizeColumn(k);
				}
				try {
					workbook.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		JMenuItem reporteMenuItem = new JMenuItem("Reporte");
		reporteMenuItem.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			JFreeChart chart = controller.getChart().getChart();
			Document document = new Document (new Rectangle(1000,707));
			try {
				PdfWriter writer;
				writer = PdfWriter.getInstance(document,
				new FileOutputStream("TestingPDF.pdf"));
				document.open();
				
		        Font fontbold = FontFactory.getFont("Arial", 40, Font.BOLD);
		        Paragraph p = new Paragraph("Reporte pasteurización", fontbold);
		        p.setSpacingAfter(20);
		        p.setAlignment(1); // Center
		        document.add(p);
				
				PdfPTable table = new PdfPTable(2);
//					PdfPCell cell = new PdfPCell(new Paragraph("Resultados"));
				PdfPCell cell = new PdfPCell();
				cell.setColspan(2);
				table.addCell(cell);
				table.addCell(controller.getDb().getTempInicial());
				table.addCell(Float.toString(controller.getPasteurization().getTempInicial())+controller.getDb().getDegreesCelsius());
				table.addCell(controller.getDb().getTempMax());
				table.addCell(Float.toString(controller.getPasteurization().getTempMaxima())+controller.getDb().getDegreesCelsius());
				table.addCell(controller.getDb().getTempFinal());
				table.addCell(Float.toString(controller.getPasteurization().getTempFinal())+controller.getDb().getDegreesCelsius());
				table.addCell(controller.getDb().getTempCorte());
				table.addCell(Float.toString(controller.getPasteurization().getTempCorte())+controller.getDb().getDegreesCelsius());
				table.addCell(controller.getDb().getTiempTotal());
				table.addCell(controller.getPasteurization().getTiempTotal().toString());
				table.addCell(controller.getDb().getTiempUp());
				table.addCell(controller.getPasteurization().getTiempUP().toString());
				table.addCell(controller.getDb().getUp());
				table.addCell(Float.toString(controller.getPasteurization().getUp()));				
				document.add(table);
				
				PdfContentByte cb = writer.getDirectContent();
				PdfTemplate tp = cb.createTemplate(1000, 707);
				Graphics2D g2d = tp.createGraphics(800, 600,
				new DefaultFontMapper());
				Rectangle2D r2d = new Rectangle2D.Double(200, 150, 600, 400);
				chart.draw(g2d, r2d);
				g2d.dispose();
				cb.addTemplate(tp, 0, 0);
			} catch(Exception ex) {
				ex.printStackTrace();
			}
			document.close();
		}
	});
	exportarMenuItem.add(medicionesXlsMenuItem);
	exportarMenuItem.add(reporteMenuItem);
	menuBar.getMenu(0).add(exportarMenuItem);
	}

	private JMenuBar createMenuBar() {
		menuBar = new JMenuBar();
		
		JMenu medicionesMenu = new JMenu("Mediciones");
		JMenu dispositivoMenu = new JMenu("Dispositivo");
		JMenu aplicacionMenu = new JMenu("Prometeo UPM");
		
		JMenuItem importarMenuItem = new JMenuItem("Importar mediciones");

		JMenuItem aboutMenuItem = new JMenuItem("Acerca de");
		JMenuItem salirMenuItem = new JMenuItem("Salir");

		
		medicionesMenu.add(importarMenuItem);

		aplicacionMenu.add(aboutMenuItem);
		aplicacionMenu.addSeparator();
		aplicacionMenu.add(salirMenuItem);

		JMenuItem gestionarMenuItem = new JMenuItem("Gestionar");
		dispositivoMenu.add(gestionarMenuItem);

		salirMenuItem.setMnemonic(KeyEvent.VK_I);
		salirMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));

		importarMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
					try {
						controller.loadFromFile(fileChooser.getSelectedFile());
						tablePanel.refresh();
						resultsContainer.enableCalculadoraUP();
						resultsContainer.setMeasurementsParameters(controller.getPasteurization());
						chartPanel.removeAll();
						chartPanel.revalidate();
						chartPanel.repaint();
						chartPanel.createChartTemp(controller.getMeasurements());
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(MainFrame.this, "Couldnt not load data from file","Error",JOptionPane.ERROR_MESSAGE);
					}					
				}
			}
		});
		
		salirMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int action = JOptionPane.showConfirmDialog(MainFrame.this, 
						"¿Desea cerrar la aplicación?", 
						"Confirmar", JOptionPane.OK_CANCEL_OPTION);
				if(action == JOptionPane.OK_OPTION) {
					System.exit(0);		
				}
			}
		});
		
		menuBar.add(medicionesMenu);
		menuBar.add(dispositivoMenu);
		menuBar.add(aplicacionMenu);
		
		return menuBar;
	}

	private void instaciateComponents() {
		controller = new Controller();
		chartPanel = new ChartPanel(controller);
		resultsContainer = new ResultsContainer(controller);
		resultsContainer.setTempCorteListener(new PasteurizationListener(){
			public void pasteurizationEmitted(Pasteurization pasteurization) {
				chartPanel.removeAll();
				chartPanel.revalidate();
				chartPanel.repaint();
				chartPanel.createChartTempAndUP(pasteurization);
			}
			
		});
		resultsContainer.setAgregadorDeMenu(new AgregadorDeMenu() {
			public void agregarMenuExportar() {
				addExportarMenuItem();
			}
			
		});
		fileChooser = new JFileChooser();
		fileChooser.addChoosableFileFilter(new MedicionFileFilter());
		tablePanel = new TablePanel();
		tablePanel.setData(controller.getMeasurements());
	}

	private void setup() {
		setLayout(new BorderLayout());
		setSize(800, 600);
		setMinimumSize(new Dimension(1280,720));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
}
