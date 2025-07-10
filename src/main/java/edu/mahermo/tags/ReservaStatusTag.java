package edu.mahermo.tags;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Etiqueta personalizada para mostrar el estado de una reserva con estilos
 * Para usarla, hay que utilizar: <mahermo:reservaStatus estado="${reserva.estado}" />
 */
public class ReservaStatusTag extends SimpleTagSupport {
    
    private String estado;
    private boolean showIcon = true;
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public void setShowIcon(boolean showIcon) {
        this.showIcon = showIcon;
    }
    
    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        
        if (estado == null || estado.trim().isEmpty()) {
            out.print("<span class=\"estado-desconocido\">Estado desconocido</span>");
            return;
        }
        
        String cssClass;
        String displayText;
        String icon = "";
        
        switch (estado.toLowerCase()) {
            case "activa":
                cssClass = "estado-activa";
                displayText = "Activa";
                icon = showIcon ? "&#10003; " : "";
                break;
            case "cancelada":
                cssClass = "estado-cancelada";
                displayText = "Cancelada";
                icon = showIcon ? "&#10007; " : "";
                break;
            default:
                cssClass = "estado-desconocido";
                displayText = estado;
                icon = showIcon ? "? " : "";
                break;
        }
        // %s indica que, cuando se procese este fragmento, será reemplazado por el valor real de la cadena: 
        out.print(String.format("<span class=\"%s\">%s%s</span>", 
                  cssClass, icon, displayText));
    }
}
