//CLASE ESQUELETO QUE HEREDA DE PERSONAJE
public class Esqueleto extends Personaje {
    private int resistenciaMagia;
    private String habilidadEspecial;

    public Esqueleto(String nombre, int salud, int nivel, int energia, int resistenciaMagia, String habilidadEspecial) {
        super(nombre, salud, nivel, energia);
        this.resistenciaMagia = resistenciaMagia;
        this.habilidadEspecial = habilidadEspecial;
    }
}
