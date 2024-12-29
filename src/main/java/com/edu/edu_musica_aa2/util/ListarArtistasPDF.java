package com.edu.edu_musica_aa2.util;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.edu.edu_musica_aa2.models.entity.Artista;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("views/artistas/home")
public class ListarArtistasPDF extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        // ?format=pdf
        document.setPageSize(PageSize.A4);
        document.open();

        @SuppressWarnings("unchecked")
        List<Artista> listaArtista = (List<Artista>) model.get("artistas");
        
        Font fuentePersonalizada = FontFactory.getFont(FontFactory.TIMES_BOLD, 15, new Color(255, 255, 255));

        Font fuenteTable = FontFactory.getFont(FontFactory.TIMES_BOLD, 10, Color.black);
    
        PdfPCell celda = null;
        celda = new PdfPCell(new Phrase("Listado de Artistas", fuentePersonalizada));
        celda.setBorder(0);
        celda.setBackgroundColor(new Color(49, 46, 129));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(8);
        ;
        
        
        PdfPTable tablaTitulo = new PdfPTable(1);
        tablaTitulo.addCell(celda);
        tablaTitulo.setSpacingAfter(15);
        
        PdfPTable tablaArtista = new PdfPTable(4);
        tablaArtista.addCell("Id");
        tablaArtista.addCell("Nombre");
        tablaArtista.addCell("Fecha");
        tablaArtista.addCell("Nacionalidad");
        // agregar un paddind a la row 
      
        // Agregar datos de artistas
        listaArtista.forEach((artista) -> {
            tablaArtista.addCell(new Phrase(artista.getId().toString(),fuenteTable));
            tablaArtista.addCell(new Phrase(artista.getNombre(),fuenteTable));
            tablaArtista.addCell(new Phrase(artista.getFecha(),fuenteTable));
            tablaArtista.addCell(new Phrase(artista.getNacionalidad(),fuenteTable));
        });
        
        // Agregar la tabla al documento
        document.add(tablaTitulo);
        document.add(tablaArtista);
        document.addTitle("Artistas PDF");
        
    }
}
