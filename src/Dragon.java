//CLASE DRAGON QUE HEREDA DE PERSONAJE
public class Dragon extends Personaje {
    private int fuegoAliento;
    private String habilidadEspecial;

    public Dragon(String nombre, int salud, int nivel, int energia, int fuegoAliento, String habilidadEspecial) {
        super(nombre, salud, nivel, energia);
        this.fuegoAliento = fuegoAliento;
        this.habilidadEspecial = habilidadEspecial;
    }
}
