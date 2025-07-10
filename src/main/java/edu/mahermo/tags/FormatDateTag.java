package edu.mahermo.tags;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Etiqueta personalizada para formatear fechas del tipoLocalDateTime
 * Para usarla, hay que utilizar: <mahermo:formatDate date="${reserva.fechaHoraInicio}" pattern="dd/MM/yyyy HH:mm" />
 */
public class FormatDateTag extends SimpleTagSupport {
    
    private LocalDateTime date;
    private String pattern = "dd/MM/yyyy HH:mm"; // Patrón por defecto
    
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    
    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
    
    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        
        if (date != null) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
                String formattedDate = date.format(formatter);
                out.print(formattedDate);
            } catch (Exception e) {
                // Si hay un error en el formato, mostrará la fecha por defecto: 
                out.print(date.toString());
            }
        } else {
            out.print("N/A");
        }
    }
}