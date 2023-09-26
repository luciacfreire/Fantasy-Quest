// CLASE CABALLERO QUE HEREDA DE JUGADOR
public class Caballero extends Jugador {
    private int armadura;
    private int energia;

    public Caballero(String nombre, int salud, int nivel, String habilidadEspecial, int armadura, int energia) {
        super(nombre, salud, nivel, habilidadEspecial);
        this.armadura = armadura;
    }
}
