package com.bhaskara.service;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class BhaskaraReport {
    public static String createPDF(Map<String, String> valores) {

        Bhaskara.setCoeficientes(valores.get("Valor A"), valores.get("Valor B"), valores.get("Valor C"));

        Bhaskara.calcularRaizes();

        Map<String, String> conteudoDoPDF = Bhaskara.takePropriedades();

        String MD5 = BhaskaraReport.createConteudoPDF(conteudoDoPDF);

        return MD5;
    }

    public static String createConteudoPDF(Map <String, String> conteudo){

        try {

            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("bhaskaraDescription.pdf"));

            Font fontChunk = FontFactory.getFont(FontFactory.HELVETICA, 16);

            Paragraph titulo = new Paragraph("Calculo de bhaskara");
            titulo.setFont(fontChunk);
            titulo.setAlignment(Element.ALIGN_CENTER);

            Paragraph subTitulo = new Paragraph("Aqui está o resultado da sua equação de Bhaskara");
            subTitulo.setFont(fontChunk);
            subTitulo.setAlignment(Element.ALIGN_CENTER);

            PdfPTable tabela = new PdfPTable(2);
            tabela.addCell("Valor A");
            tabela.addCell(conteudo.get("Valor A"));
            tabela.addCell("Valor B");
            tabela.addCell(conteudo.get("Valor B"));
            tabela.addCell("Valor C");
            tabela.addCell(conteudo.get("Valor C"));
            tabela.addCell("Valor Delta");
            tabela.addCell(conteudo.get("Valor Delta"));
            tabela.addCell("Valor X1");
            tabela.addCell(conteudo.get("Valor X1"));
            tabela.addCell("Valor X2");
            tabela.addCell(conteudo.get("Valor X2"));

            Image grafico = Image.getInstance("grafico.png");
            grafico.setAlignment(Element.ALIGN_CENTER);

            document.open();
            document.add(titulo);
            document.add(subTitulo);
            document.add(Chunk.NEWLINE);
            document.add(tabela);
            document.add(grafico);
            document.close();

            MessageDigest implementMd5 = MessageDigest.getInstance("MD5");

        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException | IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
