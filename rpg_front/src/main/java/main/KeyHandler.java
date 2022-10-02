package main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;

    public boolean upPressed, downPressed, leftPressed, rightPressed;

    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        //TITLE STATE
        if(gp.gameState == gp.titleState){
            if(gp.ui.titleScreenState == 0){
                if(code == KeyEvent.VK_Z || code == KeyEvent.VK_UP){
                    gp.ui.commandNum--;
                    if(gp.ui.commandNum < 0){
                        gp.ui.commandNum = 3;
                    }

                }
                if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
                    gp.ui.commandNum++;
                    if(gp.ui.commandNum > 3){
                        gp.ui.commandNum = 0;
                    }

                }
                if(code == KeyEvent.VK_ENTER){
                    if(gp.ui.commandNum == 0){
                        gp.ui.titleScreenState = 1;
                    }
                    if(gp.ui.commandNum == 1){
                        //LOAD
                    }
                    if(gp.ui.commandNum == 2){
                        //OPTIONS
                    }
                    if(gp.ui.commandNum == 3){
                        System.exit(0);
                    }
                }
            } else if(gp.ui.titleScreenState == 1){
                if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){
                    gp.ui.commandNum++;
                    if(gp.ui.commandNum > 3){
                        gp.ui.commandNum = 0;
                    }

                }
                if(code == KeyEvent.VK_Q || code == KeyEvent.VK_LEFT){
                    gp.ui.commandNum--;
                    if(gp.ui.commandNum < 0){
                        gp.ui.commandNum = 3;
                    }

                }
                if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
                    if(gp.ui.commandNum == 4){
                        gp.ui.commandNum = 0;
                    }
                    gp.ui.commandNum = 4;
                }
                if(gp.ui.commandNum == 4 && code == KeyEvent.VK_Z || code == KeyEvent.VK_UP){
                    gp.ui.commandNum = 0;
                }
                if(code == KeyEvent.VK_ENTER){
                    if(gp.ui.commandNum == 0){
                        //Start as Knight
                        gp.gameState = gp.playState;
                    }
                    if(gp.ui.commandNum == 1){
                        //Start as mage
                        gp.gameState = gp.playState;
                    }
                    if(gp.ui.commandNum == 2){
                        //Start as archer
                        gp.gameState = gp.playState;
                    }
                    if(gp.ui.commandNum == 3){
                        //Start as Drud
                        gp.gameState = gp.playState;
                    }
                    if(gp.ui.commandNum == 4){
                        //back to title screen
                        gp.gameState = 0;
                        gp.ui.titleScreenState = 0;
                        gp.ui.commandNum = 0;
                    }
                }
            }
        }

        //PLAY STATE
        if(gp.gameState == gp.playState){
            if(code == KeyEvent.VK_Z){
                upPressed = true;
            }
            if(code == KeyEvent.VK_Q){
                leftPressed = true;
            }
            if(code == KeyEvent.VK_S){
                downPressed = true;
            }
            if(code == KeyEvent.VK_D){
                rightPressed = true;
            }
        }
        if(gp.gameState == gp.playState || gp.gameState == gp.pauseState){
            if(code == KeyEvent.VK_ESCAPE){
                if(gp.gameState == gp.playState){
                    gp.gameState = gp.pauseState;
                } else if(gp.gameState == gp.pauseState){
                    gp.gameState = gp.playState;
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_Z){
            upPressed = false;
        }
        if(code == KeyEvent.VK_Q){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        // TODO Auto-generated method stub
        
    }
    
}
