package com.edu.edu_musica_aa2.util;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.edu.edu_musica_aa2.models.entity.Canciones;
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



@Component("views/canciones/home")
public class ListarCancionesPDF extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        // ?format=pdf
        document.setPageSize(PageSize.A4);
        document.open();

        @SuppressWarnings("unchecked")
        List<Canciones> listaCancion = (List<Canciones>) model.get("canciones");
        
        Font fuentePersonalizada = FontFactory.getFont(FontFactory.TIMES_BOLD, 15, new Color(255, 255, 255));

        Font fuenteTable = FontFactory.getFont(FontFactory.TIMES_BOLD, 10, Color.black);
    
        PdfPCell celda = null;
        celda = new PdfPCell(new Phrase("Listado de Canciones", fuentePersonalizada));
        celda.setBorder(0);
        celda.setBackgroundColor(new Color(49, 46, 129));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(8);
        ;
        
        
        PdfPTable tablaTitulo = new PdfPTable(1);
        tablaTitulo.addCell(celda);
        tablaTitulo.setSpacingAfter(15);
        
        PdfPTable tablaCancion = new PdfPTable(5);
        tablaCancion.addCell("Id");
        tablaCancion.addCell("Titulo");
        tablaCancion.addCell("Duracion");
        tablaCancion.addCell("Lanzamiento");
        tablaCancion.addCell("Genero");
        
        // Agregar datos de artistas
        listaCancion.forEach((cancion) -> {
            tablaCancion.addCell(new Phrase(cancion.getId().toString(),fuenteTable));
            tablaCancion.addCell(new Phrase(cancion.getTitulo(),fuenteTable));
            tablaCancion.addCell(new Phrase(cancion.getDuracion(),fuenteTable));
            tablaCancion.addCell(new Phrase(cancion.getAlbumnes().getFecha(),fuenteTable));
            tablaCancion.addCell(new Phrase(cancion.getGenero().getNombre(),fuenteTable));
        });
        
        // Agregar la tabla al documento
        document.add(tablaTitulo);
        document.add(tablaCancion);
        document.addTitle("Canciones PDF");
        
    }
    
}
