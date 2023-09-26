//CLASE ESQUELETO QUE HEREDA DE ENEMIGO
public class Esqueleto extends Enemigo {
    private int resistenciaMagia;

    public Esqueleto(String nombre, int salud, int nivelDificultad, int resistenciaMagia) {
        super(nombre, salud, nivelDificultad);
        this.resistenciaMagia = resistenciaMagia;
    }
}
