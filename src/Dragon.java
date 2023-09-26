//CLASE DRAGON QUE HEREDA DE ENEMIGO
public class Dragon extends Enemigo {
    private int fuegoAliento;

    public Dragon(String nombre, int salud, int nivelDificultad, int fuegoAliento) {
        super(nombre, salud, nivelDificultad);
        this.fuegoAliento = fuegoAliento;
    }
}
