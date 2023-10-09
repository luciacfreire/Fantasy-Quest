# Fantasy Quest

## Descripción

En este nuestro juego de Fantasy Quest nuestra idea es la de crear un rpg con una categoría de juego de rol, basado en un mundo de fanstasía.

Hemos añadido 3 biomas; Bosque encantado, mazmorras y montañas nevadas.

Fantasy Quest es un juego en el que el jugador asume el papel de un héroe que debe embarcarse en una búsqueda épica para salvar el reino de las garras de un malvado dragón. El juego incluirá personajes con habilidades únicas, varios tipos de enemigos, múltiples niveles de dificultad y elementos interactivos en los escenarios.

Los jugadores pueden elegir entre varios personajes jugables, cada uno con habilidades únicas. Por ejemplo, un guerrero puede tener ataques cuerpo a cuerpo fuertes, mientras que un mago puede lanzar hechizos poderosos. Además, habrá diferentes tipos de enemigos en cada escenario, desde goblins y esqueletos hasta dragones y zombies. Cada enemigo tendrá sus propias estadísticas y ataques.

El juego tendrá una pantalla principal que mostrará el mapa y a mayores que mostrará la pantalla de combate.

# INTERFACES

Mapa : https://play-lh.googleusercontent.com/Fore1EPrxEERYP1mhB_UP9tGUARzrRHm5uzO2vVV0zC8wpolkXS9cRfZnOtegcDPtzs=w526-h296-rw

Combate:https://www.fantasymundo.com/wp-content/uploads/2021/11/EN3.png

## UML Diagram


```mermaid
classDiagram
    Personaje <|-- "0..1" Caballero
    Personaje <|-- "0..1" Mago
    Personaje <|-- "0..1" Elfo
    Personaje <|-- "1" Dragon
    Personaje <|-- "*" Esqueleto
    Personaje <|-- "*" Goblin
    Personaje <|-- "*" Zombie
    Personaje : -int salud
    Personaje : -String nombre
    Personaje: -int nivel
    Personaje: -int energia
    Personaje : +saltar()
    Personaje : +caminar()
    Personaje : +atacar()
    Personaje : +defender()
    Juego "1" *-- "1..*" Personaje
    Juego : +int dificultad
    Juego : +string mapa
    Juego : +string misiones
    Juego : +iniciarJuego()
    Juego : +elegirDificultad()
    Juego : +cargarMapa()
    Juego : +finJuego()
    Juego "1" *-- "1" Mapa
    Mapa : +string nombre
    Mapa : +string descripcion
    Mapa : +cargarElementos()
    Mapa : +cargarEnemigos()
    Elementos o-- Mapa
    Elementos : +string nombre
    Elementos : +int ubicacion 
    Elementos : +aparecerElemento()
    Elementos <|-- "1..*" Alimentos
    Elementos <|-- "1..*" Armas
    class Alimentos {
    +int puntosRecuperar
    +recuperarVida()   
    }
    class Armas{
    -int daño
    -atacar()
    -disminuirMunicion()
    }
    class Caballero{
      -String habilidadEspecial
      -int armadura
    }
    class Mago{
      -String habilidadEspecial
      -int hechizos
    }
    class Elfo{
      -String habilidadEspecial
      -int agilidad
    }
    class Dragon{
      -String habilidadEspecial
      -int fuegoAliento
    }
    class Esqueleto{
      -String habilidadEspecial
      -int resistenciaMagia
    }
    class Goblin{
      -String habilidadEspecial
      -int velocidad
    }
    class Zombie{
      -String habilidadEspecial
      -int fuerza
    }

```
