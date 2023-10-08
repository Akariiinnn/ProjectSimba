package me.akariiinnn.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed, shiftPressed, ePressed;
    //DEBUG
    boolean checkDrawTime;

    public int keyBoard;
    public final int azerty = 0;
    public final int qwerty = 1;

    public final int dispositionsNum = 2;

    int up, down, left, right;

    GamePanel gp;

    public KeyHandler(GamePanel gp)
    {
        this.gp = gp;
        keyBoard = azerty;

        setKeyBoard();
    }

    public void setKeyBoard()
    {
        switch(keyBoard) {
            case azerty: up = 90; down = 83; left = 81; right = 68; break;

            case qwerty: up = 87; down = 83; left = 65; right = 68; break;

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        // TITLE STATE
        if(gp.gameState == gp.titleState) {

            if(gp.ui.titleScreenState == 0) {
                if(code == up || code == KeyEvent.VK_UP)
                {
                    gp.ui.commandNum--;
                    if(gp.ui.commandNum < 0)
                    {
                        gp.ui.commandNum = 3;

                    }
                }
                if(code == down || code == KeyEvent.VK_DOWN)
                {
                    gp.ui.commandNum++;
                    if(gp.ui.commandNum > 3)
                    {
                        gp.ui.commandNum = 0;
                    }
                }
                if(code == KeyEvent.VK_ENTER || code == KeyEvent.VK_E)
                {
                    if(gp.ui.commandNum == 0) {
                        gp.ui.titleScreenState = 1;
                    }
                    if(gp.ui.commandNum == 1) {
                        // TODO
                    }
                    if(gp.ui.commandNum == 2) {
                        System.exit(0);
                    }
                    if(gp.ui.commandNum == 3) {
                        keyBoard++;
                        if(keyBoard > dispositionsNum - 1)
                        {
                            keyBoard = 0;
                        }
                        setKeyBoard();
                    }
                }
            }else if(gp.ui.titleScreenState == 1)
            {
                if(code == up || code == KeyEvent.VK_UP)
                {
                    gp.ui.commandNum--;
                    if(gp.ui.commandNum < 0)
                    {
                        gp.ui.commandNum = 4;

                    }
                }
                if(code == down || code == KeyEvent.VK_DOWN)
                {
                    gp.ui.commandNum++;
                    if(gp.ui.commandNum > 4)
                    {
                        gp.ui.commandNum = 0;
                    }
                }
                if(code == KeyEvent.VK_ENTER || code == KeyEvent.VK_E)
                {
                    if(gp.ui.commandNum == 0) {
                        // TODO
                        gp.playMusic(0);
                        gp.gameState = gp.playState;
                    }
                    if(gp.ui.commandNum == 1) {
                        // TODO
                        gp.playMusic(0);
                        gp.gameState = gp.playState;
                    }
                    if(gp.ui.commandNum == 2) {
                        // TODO
                        gp.playMusic(0);
                        gp.gameState = gp.playState;
                    }
                    if(gp.ui.commandNum == 3) {
                        // TODO
                        gp.playMusic(0);
                        gp.gameState = gp.playState;
                    }
                    if(gp.ui.commandNum == 4) {
                        gp.ui.titleScreenState = 0;
                        gp.ui.commandNum = 0;
                    }
                }
            }
        }

        // PLAY STATE
        if(gp.gameState == gp.playState) {
            if (code == up || code == KeyEvent.VK_UP) {
                upPressed = true;
            }
            if (code == left || code == KeyEvent.VK_LEFT) {
                leftPressed = true;
            }
            if (code == down || code == KeyEvent.VK_DOWN) {
                downPressed = true;
            }
            if (code == right || code == KeyEvent.VK_RIGHT) {
                rightPressed = true;
            }
            if (code == KeyEvent.VK_SHIFT) {
                shiftPressed = true;
            }
            if (code == KeyEvent.VK_E) {
                ePressed = true;
            }
            if (code == KeyEvent.VK_ESCAPE) {
                gp.gameState = gp.pauseState;
                upPressed = false;
                leftPressed = false;
                rightPressed = false;
                downPressed = false;
            }

            //DEBUG
            if (code == KeyEvent.VK_T) {
                if (!checkDrawTime) {
                    checkDrawTime = true;
                } else if (checkDrawTime) {
                    checkDrawTime = false;
                }
            }
        }
        // PAUSE STATE
        else if(gp.gameState == gp.pauseState)
        {
            if (code == KeyEvent.VK_ESCAPE) {
                gp.gameState = gp.playState;
            }
        }

        //DIALOGUE STATE
        else if(gp.gameState == gp.dialogueState)
        {
            upPressed = false;
            downPressed = false;
            rightPressed = false;
            leftPressed = false;
            if (code == KeyEvent.VK_SPACE) {
                gp.gameState = gp.playState;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        // PLAY STATE
        if(gp.gameState == gp.playState) {
            if (code == up || code == KeyEvent.VK_UP) {
                upPressed = false;
            }
            if (code == left || code == KeyEvent.VK_LEFT) {
                leftPressed = false;
            }
            if (code == down || code == KeyEvent.VK_DOWN) {
                downPressed = false;
            }
            if (code == right || code == KeyEvent.VK_RIGHT) {
                rightPressed = false;
            }
            if (code == KeyEvent.VK_SHIFT) {
                shiftPressed = false;
            }
            if (code == KeyEvent.VK_E) {
                ePressed = false;
            }

        }
    }
}
