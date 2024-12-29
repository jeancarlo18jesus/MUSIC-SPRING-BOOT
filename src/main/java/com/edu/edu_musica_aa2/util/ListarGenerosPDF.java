package com.edu.edu_musica_aa2.util;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.edu.edu_musica_aa2.models.entity.Generos;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component("views/generos/home")
public class ListarGenerosPDF  extends AbstractPdfView{

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
       // ?format=pdf
        document.setPageSize(PageSize.A4);
        document.open();

        @SuppressWarnings("unchecked")
        List<Generos> listaGenero = (List<Generos>) model.get("generos");
        
        Font fuentePersonalizada = FontFactory.getFont(FontFactory.TIMES_BOLD, 15, new Color(255, 255, 255));

        Font fuenteTable = FontFactory.getFont(FontFactory.TIMES_BOLD, 10, Color.black);
    
        PdfPCell celda = null;
        celda = new PdfPCell(new Phrase("Listado de Generos", fuentePersonalizada));
        celda.setBorder(0);
        celda.setBackgroundColor(new Color(49, 46, 129));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(8);
        ;
        
        
        PdfPTable tablaTitulo = new PdfPTable(1);
        tablaTitulo.addCell(celda);
        tablaTitulo.setSpacingAfter(15);
        
        PdfPTable tablaGenero = new PdfPTable(2);
        tablaGenero.addCell("Id");
        tablaGenero.addCell("Nombre");

        
        // Agregar datos de artistas
        listaGenero.forEach((genero) -> {
            tablaGenero.addCell(new Phrase(genero.getId().toString(),fuenteTable));
            tablaGenero.addCell(new Phrase(genero.getNombre(),fuenteTable));
            
        });
        
        // Agregar la tabla al documento
        document.add(tablaTitulo);
        document.add(tablaGenero);
        document.addTitle("Generos PDF");

    }
    
}
