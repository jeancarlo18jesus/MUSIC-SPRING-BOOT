package com.edu.edu_musica_aa2.util;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.edu.edu_musica_aa2.models.entity.Albumnes;
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

@Component("views/albumnes/home")
public class ListarAlbumnesPDF extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        // ?format=pdf
        document.setPageSize(PageSize.A4);
        document.open();

        @SuppressWarnings("unchecked")
        List<Albumnes> listaAlbumn = (List<Albumnes>) model.get("albumnes");
        
        Font fuentePersonalizada = FontFactory.getFont(FontFactory.TIMES_BOLD, 15, new Color(255, 255, 255));

        Font fuenteTable = FontFactory.getFont(FontFactory.TIMES_BOLD, 10, Color.black);
    
        PdfPCell celda = null;
        celda = new PdfPCell(new Phrase("Listado de Albumnes", fuentePersonalizada));
        celda.setBorder(0);
        celda.setBackgroundColor(new Color(49, 46, 129));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(8);
        ;
        
        
        PdfPTable tablaTitulo = new PdfPTable(1);
        tablaTitulo.addCell(celda);
        tablaTitulo.setSpacingAfter(15);
        
        PdfPTable tablaAlbumn = new PdfPTable(5);
        tablaAlbumn.addCell("Id");
        tablaAlbumn.addCell("Titulo");
        tablaAlbumn.addCell("Artista");
        tablaAlbumn.addCell("Lanzamiento");
        tablaAlbumn.addCell("Nacionalidad");
        
        
        // Agregar datos de artistas
        listaAlbumn.forEach((albumn) -> {
            tablaAlbumn.addCell(new Phrase(albumn.getId().toString(),fuenteTable));
            tablaAlbumn.addCell(new Phrase(albumn.getTitulo(),fuenteTable));
            tablaAlbumn.addCell(new Phrase(albumn.getArtista().getNombre(),fuenteTable));
            tablaAlbumn.addCell(new Phrase(albumn.getFecha(),fuenteTable));
            tablaAlbumn.addCell(new Phrase(albumn.getArtista().getNacionalidad(),fuenteTable));
        });
        
        // Agregar la tabla al documento
        document.add(tablaTitulo);
        document.add(tablaAlbumn);
        document.addTitle("Albumnes PDF");
    }
    
}
