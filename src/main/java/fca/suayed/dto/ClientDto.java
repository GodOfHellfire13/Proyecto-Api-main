package fca.suayed.dto;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class ClientDto {
    private Long id;
    private String nombre;
    
    @ColumnName("apellido_paterno") // Mapea exactamente al nombre de la columna
    private String apellidoPaterno;
    
    @ColumnName("apellido_materno") // Mapea exactamente al nombre de la columna
    private String apellidoMaterno;
    
    private String rfc;

    // Getters y Setters con las anotaciones
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    @ColumnName("apellido_paterno")
    public String getApellidoPaterno() { return apellidoPaterno; }
    public void setApellidoPaterno(String apellidoPaterno) { this.apellidoPaterno = apellidoPaterno; }

    @ColumnName("apellido_materno")
    public String getApellidoMaterno() { return apellidoMaterno; }
    public void setApellidoMaterno(String apellidoMaterno) { this.apellidoMaterno = apellidoMaterno; }

    public String getRfc() { return rfc; }
    public void setRfc(String rfc) { this.rfc = rfc; }
}