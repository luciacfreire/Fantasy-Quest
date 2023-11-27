# Fantasy Quest

## Descripción

Fantasy Quest es un juego de rol en el que el jugador asume el papel de un héroe que debe embarcarse en una búsqueda épica para salvar el reino de las garras de un malvado dragón. El juego incluirá personajes con habilidades únicas, varios tipos de enemigos, múltiples niveles de dificultad y elementos interactivos en los escenarios.

Los jugadores pueden elegir entre varios personajes, cada uno con habilidades únicas. Por ejemplo, un guerrero puede tener fuertes ataques cuerpo a cuerpo, mientras que un mago puede lanzar hechizos poderosos. Además, habrá diferentes tipos de enemigos en cada escenario, desde goblins y esqueletos hasta dragones y zombies. Cada enemigo tendrá sus propias estadísticas y ataques.

Hemos añadido 3 biomas; El bosque encantado, La mazmorra y La montaña nevada. Cada uno de estos escenarios tendrá enemigos y objetos característicos.

El juego tendrá una pantalla principal, un mapa donde el personaje principal buscará el camino para encontrar y derrotar al dragón, sin embargo por el camino se encontrará enemigos con los que tendrá que pelear para poder seguir avanzando. Estos combates aparecen al tocar al enemigo, abriéndose una pantalla secundaria.

### Interfaces

MapaPanel :
https://i.ytimg.com/vi/7Pg_vPJEajk/maxresdefault.jpg

Combate:
https://cdn.andro4all.com/andro4all/2020/09/oie241727462OkHn8UM.jpg



## UML Diagram

```mermaid
classDiagram
    Entity <|-- "1" Player
    Entity <|-- "1" Boss
    Entity : +int life
    Entity : +int maxLife
    Entity : +int worldX
    Entity : +int worldY
    Entity : +int Speed
    Entity : +String direction
    Entity : +Rectangle solidArea
    Entity : +int solidAreaDefaultX
    Entity : +int solidAreaDefaultY
    Entity : +boolean collisionOn
    Entity : +receiveDamage(int damage)
    Entity : +boolean isDefeated()
    Entity : +BufferedImage[][] loadAnimations(String path)
    
    class Player{
    -int aniTick
    -int aniIndex
    -final int aniSpeed
    -int playerDir
    -int lastPlayerDir
    -int lastAniIndex
    -boolean moving
    +final int screenX
    +final int screenY
    +Player(GamePanel gp, KeyboardInputs keyI)
    +setDefaultValues()
    +update()
    +setDirection(String direction) 
    +pickUpObject(int i)
    +BufferedImage getInitialImage()
    +draw(Graphics g) 
    +updateAnimationTick()
    }
    
    class Boss{
      +Boss(GamePanel gp)
      +setDefaultLife()
      +getBossImage()
      +draw (Graphics g)
    }
    class KeyboardInputs{
    +boolean upPressed
    +boolean downPressed
    +boolean leftPressed
    +boolean rightPressed
    +boolean attackPressed
    +KeyboardInputs(GamePanel gamePanel)
    +keyTyped(KeyEvent e)
    +keyPressed(KeyEvent e)
    +keyReleased(KeyEvent e)
    
    }
    
    class AssetSetter{
    +AssetSetter(GamePanel gp)
    +setObject()
    }
    
    class Battle{
    -BufferedImage backgroundImage
    +boolean playerTurn
    +int lastPlayerAttackPower
    +int lastBossAttackPower
    +String lastAttacker
    +Battle(GamePanel gamePanel)
    +update()
    -int generateRandomAttackPower(int min, int max)
    +getBackgroundImage()
    +draw (Graphics g)
    
    }
    
    class CollisionCheck{
    -GamePanel gp
    +CollisionCheck(GamePanel gp)
    -boolean checkCollision(int row, int col)
    -boolean checkCollisionElem
    -int calculateRow(float worldY)
    -int calculateCol(float worldX)
    +checkTile(Entity entity) 
    +int checkObject(Entity entity, boolean player)
    }
    class Game{
    -GameWindow gameWindow
    -GamePanel gamePanel
    +Game()
    }
    class GamePanel{
    +final int originalSizeTile
    +final int sizeTile
    +final int maxScreenCol 
    +int maxScreenRow 
    +final int screenWidth 
    +final int screenHeight 
    +final int maxWorldCol 
    +final int maxWorldRow 
    +final int worldWidth 
    +final int worldHeight 
    +Player player
    +Boss boss
    +SuperObject obj[]
    +AssetSetter aSetter
    +UI ui
    +Thread gameThread
    -final int FPS
    +CollisionCheck collCheck
    +int gameState;
    +final int titleState
    +final int playState
    +final int pauseState
    +final int battleState
    +Battle battle
    +GamePanel()
    +setUpGame()
    +startGameThread()
    +paintComponent(Graphics g)
    +update()
    +run()
    
    }
    
    class GameWindow{
    +GameWindow
    -JFrame window
    +GameWindow(GamePanel gamePanel)
    
    }
    
    class UI{
    +boolean messageOn
    +String message
    +int commandNum
    +UI (GamePanel gamePanel)
    +showMessage (String text)
    +draw (Graphics g)
    +DrawRectMessage()
    +drawPlayerLife()
    +drawBossLife()
    +drawTitleScreen()
    +BufferedImage getImage()
    +drawPauseScreen()
    +int getXforCenteredText(String text)
    }
    class UtilityTool{
    +BufferedImage scaledImage(BufferedImage original, int width, int height)
    }
    
    class OBJ_Door{
    
    +OBJ_Door(GamePanel gamePanel)
     
    }
    class OBJ_Heart{
    +OBJ_Heart(GamePanel gamePanel)
    }
    
    class  OBJ_LessSpeed{
    +OBJ_LessSpeed(GamePanel gamePanel)
    
    }
    
    class OBJ_MoreSpeed{
    +OBJ_MoreSpeed(GamePanel gamePanel)
    }
    class  SuperObject{
    +BufferedImage image
    +BufferedImage image2
    +BufferedImage image3
    +String name
    +boolean collision
    +int worldX
    +int worldY
    +Rectangle solidArea
    +int solidAreaDefaultX
    +int solidAreaDefaultY
    }
    class Tile{
    +BufferedImage image
    +boolean collision
    }
    class TileManager{
    +Tile[] tile
    +Tile[] tileElem
    +int[][] objectMap
    +int[][] mapTileNum
    +TileManager(GamePanel gamePanel)
    +getElemImage()
    +loadObjectMap(String filePath)
    +getTileImage()
    +setup(int index, String imageName, int x, int y, boolean collision, Tile[] tile)
    +loadMap(String filePath)
    +draw(Graphics g)
    
    }
    class constants{

    }

    class PlayerConstants {
        +final int WALK_DOWN
        +final int WALK_UP
        +final int WALK_LEFT
        +final int WALK_RIGHT
        +final int WALK_SPRITES
    }

    class GameStatesConstants {
        +final int titleState
        +final int playState
        +final int pauseState
        +final int battleState
        +final int winState
        +final int loseState
        }

    constants *-- GameStatesConstants
    constants *-- PlayerConstants
    SuperObject  *-- OBJ_MoreSpeed
    SuperObject  *-- OBJ_LessSpeed
    SuperObject  *-- OBJ_Heart
    SuperObject  *-- OBJ_Door
    Tile *-- TileManager
```