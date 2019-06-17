
import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Font;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class Launcher
{

  /**
   * launcherFrame
   *   menuPanel
   *     newGameButton
   *     loadGameButton
   *     settingsButton
   *     requirementsButton
   *     creditsButton
   *   settingsPanel
   *   requirementsPanel
   *   creditsPanel
   *   
   * gameFrame
   *    toggleMenu
   */

//===================================================================================  INSTANTIATION

  //  LAUNCHER
  JFrame launcherFrame;
  JPanel menuPanel = new JPanel();
  JPanel settingsPanel = new JPanel();
  JPanel requirementsPanel = new JPanel();
  JPanel creditsPanel = new JPanel();

  //  GAME
  public JFrame gameFrame = new JFrame();
  JLabel gameBoard = new JLabel();
  //  Toolbar
  JToggleButton toggleMenu = new JToggleButton("");
  JLabel winCounter = new JLabel();
  public static int wins = 0;

  //  Toggle Menu
  JPanel subMenuPanel = new JPanel();
  JButton btnSaveGame = new JButton("Save Game");
  JButton btnLoadGame = new JButton("Load Saved Game");
  JButton btnExitToMenu = new JButton("Exit to Menu");
  JLabel menuNoticeLabel = new JLabel("(This will save your game.)");

  //  Winner Overlay
  JLabel winnerDisplayLabel = new JLabel("");

  //  Column 0
  JLabel chip_0_0 = new JLabel("");
  JLabel chip_0_1 = new JLabel("");
  JLabel chip_0_2 = new JLabel("");
  JLabel chip_0_3 = new JLabel("");
  JLabel chip_0_4 = new JLabel("");
  JLabel chip_0_5 = new JLabel("");
  //  Column 1
  JLabel chip_1_0 = new JLabel("");
  JLabel chip_1_1 = new JLabel("");
  JLabel chip_1_2 = new JLabel("");
  JLabel chip_1_3 = new JLabel("");
  JLabel chip_1_4 = new JLabel("");
  JLabel chip_1_5 = new JLabel("");
  //  Column 2
  JLabel chip_2_0 = new JLabel("");
  JLabel chip_2_1 = new JLabel("");
  JLabel chip_2_2 = new JLabel("");
  JLabel chip_2_3 = new JLabel("");
  JLabel chip_2_4 = new JLabel("");
  JLabel chip_2_5 = new JLabel("");
  //  Column 3
  JLabel chip_3_0 = new JLabel("");
  JLabel chip_3_1 = new JLabel("");
  JLabel chip_3_2 = new JLabel("");
  JLabel chip_3_3 = new JLabel("");
  JLabel chip_3_4 = new JLabel("");
  JLabel chip_3_5 = new JLabel("");
  //  Column 4
  JLabel chip_4_0 = new JLabel("");
  JLabel chip_4_1 = new JLabel("");
  JLabel chip_4_2 = new JLabel("");
  JLabel chip_4_3 = new JLabel("");
  JLabel chip_4_4 = new JLabel("");
  JLabel chip_4_5 = new JLabel("");
  //  Column 5
  JLabel chip_5_0 = new JLabel("");
  JLabel chip_5_1 = new JLabel("");
  JLabel chip_5_2 = new JLabel("");
  JLabel chip_5_3 = new JLabel("");
  JLabel chip_5_4 = new JLabel("");
  JLabel chip_5_5 = new JLabel("");
  //  Column 6
  JLabel chip_6_0 = new JLabel("");
  JLabel chip_6_1 = new JLabel("");
  JLabel chip_6_2 = new JLabel("");
  JLabel chip_6_3 = new JLabel("");
  JLabel chip_6_4 = new JLabel("");
  JLabel chip_6_5 = new JLabel("");
  //  ColumnSelect
  JLabel position0 = new JLabel("");
  JLabel position1 = new JLabel("");
  JLabel position2 = new JLabel("");
  JLabel position3 = new JLabel("");
  JLabel position4 = new JLabel("");
  JLabel position5 = new JLabel("");
  JLabel position6 = new JLabel("");

  public static boolean playerIsRed = true;

  /**
   * Launch the application.
   */
  public static void main(String[] args)
  {
	  EventQueue.invokeLater(new Runnable()
    {
      public void run()
      {
        try
        {
          Launcher window = new Launcher();
          window.launcherFrame.setVisible(true);
          window.launcherFrame.setResizable(false);
          window.menuPanel.setVisible(true);
          window.settingsPanel.setVisible(false);
          window.requirementsPanel.setVisible(false);
          window.creditsPanel.setVisible(false);

//          Scanner winsReader = new Scanner(new File("/files/winCount.txt"));
//          wins = winsReader.nextInt();
//          
//          winsReader.close();
          
          window.processKeys();
        } catch (Exception e)
        {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the application.
   */
  public Launcher()
  {
    initialize();
  }

  //=====================================  INITIALIZATION  =====================================//
  private void initialize()
  {

    //===================================  LAUNCHER FRAME  ===================================//

    launcherFrame = new JFrame();
    launcherFrame.setFont(new Font("Roboto-Regular.ttf", Font.PLAIN, 18));
    launcherFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(
        Launcher.class.getResource("src/images/chip_red_scaled3.png")));
    launcherFrame.setTitle("Connect Four");
    launcherFrame.setBounds(100, 100, 600, 400);
    launcherFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    launcherFrame.getContentPane().setLayout(null);
    launcherFrame.getContentPane().setBackground(new Color(250, 250, 250));

    //============================  LAUNCHER FRAME : MENU PANEL  =============================//

    menuPanel.setBounds(0, 0, 595, 360);
    launcherFrame.getContentPane().add(menuPanel);
    menuPanel.setLayout(null);
    menuPanel.setBackground(new Color(250, 250, 250));

    JButton playGameButton = new JButton("Play a Game");
    playGameButton.setFont(new Font("Roboto-Regular.ttf", Font.PLAIN, 18));
    playGameButton.setBackground(new Color(245, 245, 245));
    playGameButton.setBounds(177, 40, 240, 60);    
    playGameButton.addActionListener(new ActionListener() 
    {
      public void actionPerformed(ActionEvent e) {
        launcherFrame.setVisible(false);
        gameFrame.setVisible(true);
      }
    });

    JLabel loadGameLabel = new JLabel(
        "You will have the option to resume a previously saved game once the game loads.");
    loadGameLabel.setBackground(new Color(250, 250, 250));
    loadGameLabel.setFont(new Font("Roboto-Regular.ttf", Font.PLAIN, 16));
    loadGameLabel.setBounds(0, 100, 580, 40);
    loadGameLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JButton settingsButton = new JButton("Settings");
    settingsButton.setBackground(new Color(245, 245, 245));
    settingsButton.setFont(new Font("Roboto-Regular.ttf", Font.PLAIN, 18));
    settingsButton.setBounds(177, 160, 240, 40);
    settingsButton.addActionListener(new ActionListener() 
    {
      public void actionPerformed(ActionEvent e) {
        menuPanel.setVisible(false);
        settingsPanel.setVisible(true);
      }
    });

    JButton requirementsButton = new JButton("System Requirements");
    requirementsButton.setBackground(new Color(245, 245, 245));
    requirementsButton.setFont(new Font("Roboto-Regular.ttf", Font.PLAIN, 18));
    requirementsButton.setBounds(177, 220, 240, 40);
    requirementsButton.addActionListener(new ActionListener() 
    {
      public void actionPerformed(ActionEvent e) {
        menuPanel.setVisible(false);
        requirementsPanel.setVisible(true);
      }
    });

    JButton creditsButton = new JButton("Developer Credits");
    creditsButton.setBackground(new Color(245, 245, 245));
    creditsButton.setFont(new Font("Roboto-Regular.ttf", Font.PLAIN, 18));
    creditsButton.setBounds(177, 280, 240, 40);
    creditsButton.addActionListener(new ActionListener() 
    {
      public void actionPerformed(ActionEvent e) {
        menuPanel.setVisible(false);
        creditsPanel.setVisible(true);
      }
    });

    menuPanel.add(playGameButton);
    menuPanel.add(loadGameLabel);
    menuPanel.add(settingsButton);
    menuPanel.add(requirementsButton);
    menuPanel.add(creditsButton);

    //==========================  LAUNCHER FRAME : SETTINGS PANEL  ===========================//

    settingsPanel.setBounds(0, 0, 595, 360);
    launcherFrame.getContentPane().add(settingsPanel);
    settingsPanel.setLayout(null);
    settingsPanel.setBackground(new Color(250, 250, 250));

    JCheckBox chckbxPlayAsRed = new JCheckBox("Play as Red");
    chckbxPlayAsRed.setFont(new Font("Roboto-Regular.ttf", Font.PLAIN, 18));
    chckbxPlayAsRed.setHorizontalAlignment(SwingConstants.CENTER);
    chckbxPlayAsRed.setSelected(true);
    chckbxPlayAsRed.setBackground(new Color(250, 250, 250));
    chckbxPlayAsRed.setBounds(200, 100, 200, 50);
    chckbxPlayAsRed.addActionListener(new ActionListener() 
    {
      public void actionPerformed(ActionEvent e) {
        playerIsRed = chckbxPlayAsRed.isSelected();
      }
    });


    JSlider slider = new JSlider();
    slider.setValue(1);
    slider.setSnapToTicks(true);
    slider.setBackground(new Color(250, 250, 250));
    slider.setForeground(new Color(0, 0, 0));
    slider.setMajorTickSpacing(1);
    slider.setMaximum(2);
    slider.setPaintTicks(true);
    slider.setBounds(200, 250, 200, 30);

    JLabel lblNewSettingsLabel = new JLabel("AI Difficulty");
    lblNewSettingsLabel.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewSettingsLabel.setFont(new Font("Roboto-Regular.tff", Font.PLAIN, 18));
    lblNewSettingsLabel.setBounds(200, 210, 200, 40);

    JLabel lblNewLabel_1 = new JLabel("Easy");
    lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel_1.setFont(new Font("Roboto-Regular.ttf", Font.PLAIN, 12));
    lblNewLabel_1.setBounds(190, 280, 40, 20);

    JLabel lblMedium = new JLabel("Medium");
    lblMedium.setHorizontalAlignment(SwingConstants.CENTER);
    lblMedium.setFont(new Font("Roboto-Regular.ttf", Font.PLAIN, 12));
    lblMedium.setBounds(270, 280, 60, 20);

    JLabel lblHard = new JLabel("Hard");
    lblHard.setHorizontalAlignment(SwingConstants.CENTER);
    lblHard.setFont(new Font("Roboto-Regular.ttf", Font.PLAIN, 12));
    lblHard.setBounds(370, 280, 40, 20);

    JButton settingsBackButton = new JButton("");
    settingsBackButton.setIcon(new ImageIcon(Launcher.class.getResource("src/images/arrow_back.png")));
    settingsBackButton.setBackground(new Color(245, 245, 245));
    settingsBackButton.setBounds(8, 8, 48, 48);
    settingsBackButton.addActionListener(new ActionListener() 
    {
      public void actionPerformed(ActionEvent e) {
        settingsPanel.setVisible(false);
        menuPanel.setVisible(true);
      }
    });

    settingsPanel.add(chckbxPlayAsRed);
    settingsPanel.add(slider);
    settingsPanel.add(lblNewSettingsLabel);
    settingsPanel.add(lblNewLabel_1);
    settingsPanel.add(lblMedium);
    settingsPanel.add(lblHard);
    settingsPanel.add(settingsBackButton);


    //========================  LAUNCHER FRAME : REQUIREMENTS PANEL  =========================//

    requirementsPanel.setBounds(0, 0, 595, 360);
    launcherFrame.getContentPane().add(requirementsPanel);
    requirementsPanel.setLayout(null);
    requirementsPanel.setBackground(new Color(250, 250, 250));

    JLabel requirementsLabelA = new JLabel();
    requirementsLabelA.setText("Requires a display resolution of 1920 by 1080 or greater.");
    requirementsLabelA.setHorizontalAlignment(SwingConstants.CENTER);
    requirementsLabelA.setFont(new Font("Roboto-Regular.ttf", Font.PLAIN, 18));
    requirementsLabelA.setBounds(0, 120, 595, 40);

    JLabel requirementsLabelB = new JLabel();
    requirementsLabelB.setVerticalAlignment(SwingConstants.BOTTOM);
    requirementsLabelB.setText("The game will run on screens with a lower resolution,");
    requirementsLabelB.setHorizontalAlignment(SwingConstants.CENTER);
    requirementsLabelB.setFont(new Font("Roboto-Regular.ttf", Font.PLAIN, 14));
    requirementsLabelB.setBounds(0, 160, 595, 20);

    JLabel requirementsLabelC = new JLabel();
    requirementsLabelC.setVerticalAlignment(SwingConstants.TOP);
    requirementsLabelC.setText("but the window will expand past the edges of the display.");
    requirementsLabelC.setHorizontalAlignment(SwingConstants.CENTER);
    requirementsLabelC.setFont(new Font("Roboto-Regular.ttf", Font.PLAIN, 14));
    requirementsLabelC.setBounds(0, 180, 595, 20);

    JButton requirementsBackButton = new JButton("");
    requirementsBackButton.setIcon(new ImageIcon(
        Launcher.class.getResource("/src/images/arrow_back.png")));
    requirementsBackButton.setBackground(new Color(245, 245, 245));
    requirementsBackButton.setBounds(8, 8, 48, 48);
    requirementsBackButton.addActionListener(new ActionListener() 
    {
      public void actionPerformed(ActionEvent e) {
        requirementsPanel.setVisible(false);
        menuPanel.setVisible(true);
      }
    });

    requirementsPanel.add(requirementsLabelA);
    requirementsPanel.add(requirementsLabelB);
    requirementsPanel.add(requirementsLabelC);
    requirementsPanel.add(requirementsBackButton);

    //==========================  LAUNCHER FRAME : CREDITS PANEL  ============================//

    creditsPanel.setBounds(0, 0, 595, 360);
    launcherFrame.getContentPane().add(creditsPanel);
    creditsPanel.setLayout(null);
    creditsPanel.setBackground(new Color(250, 250, 250));

    JLabel creditsTitleLabel = new JLabel();
    creditsTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
    creditsTitleLabel.setText("Developer Credits");
    creditsTitleLabel.setFont(new Font("Roboto-Regular.ttf", Font.BOLD, 24));
    creditsTitleLabel.setBounds(0, 0, 595, 64);

    JLabel collinNameLabel = new JLabel();
    collinNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
    collinNameLabel.setText("Collin Wischmeyer");
    collinNameLabel.setFont(new Font("Roboto-Regular.ttf", Font.BOLD, 18));
    collinNameLabel.setBounds(0, 64, 595, 32);

    JLabel collinTitlesLabel = new JLabel();
    collinTitlesLabel.setHorizontalAlignment(SwingConstants.CENTER);
    collinTitlesLabel.setText("Team Leader");
    collinTitlesLabel.setFont(new Font("Roboto-Regular.ttf", Font.PLAIN, 18));
    collinTitlesLabel.setVerticalAlignment(SwingConstants.TOP);
    collinTitlesLabel.setBounds(0, 96, 595, 40);

    JLabel martinNameLabel = new JLabel();
    martinNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
    martinNameLabel.setText("Martin Fehringer");
    martinNameLabel.setFont(new Font("Roboto-Regular.ttf", Font.BOLD, 18));
    martinNameLabel.setBounds(0, 136, 595, 32);

    JLabel martinTitlesLabel = new JLabel();
    martinTitlesLabel.setHorizontalAlignment(SwingConstants.CENTER);
    martinTitlesLabel.setText("Head of Graphical Design and Integration");
    martinTitlesLabel.setFont(new Font("Roboto-Regular.ttf", Font.PLAIN, 18));
    martinTitlesLabel.setVerticalAlignment(SwingConstants.TOP);
    martinTitlesLabel.setBounds(0, 168, 595, 40);

    JLabel benNameLabel = new JLabel();
    benNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
    benNameLabel.setText("Ben Tan");
    benNameLabel.setFont(new Font("Roboto-Regular.ttf", Font.BOLD, 18));
    benNameLabel.setBounds(0, 208, 595, 32);

    JLabel benTitlesLabel = new JLabel();
    benTitlesLabel.setHorizontalAlignment(SwingConstants.CENTER);
    benTitlesLabel.setText("");
    benTitlesLabel.setFont(new Font("Roboto-Regular.ttf", Font.PLAIN, 18));
    benTitlesLabel.setBounds(0, 240, 595, 40);

    JLabel erikNameLabel = new JLabel();
    erikNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
    erikNameLabel.setText("Erik Seroogy");
    erikNameLabel.setFont(new Font("Roboto-Regular.ttf", Font.BOLD, 18));
    erikNameLabel.setBounds(0, 280, 595, 32);

    JLabel erikTitlesLabel = new JLabel();
    erikTitlesLabel.setHorizontalAlignment(SwingConstants.CENTER);
    erikTitlesLabel.setText("");
    erikTitlesLabel.setFont(new Font("Roboto-Regular.ttf", Font.PLAIN, 18));
    erikTitlesLabel.setBounds(0, 312, 595, 40);    

    JButton creditsBackButton = new JButton("");
    creditsBackButton.setIcon(new ImageIcon(
        Launcher.class.getResource("/src/images/arrow_back.png")));
    creditsBackButton.setBackground(new Color(245, 245, 245));
    creditsBackButton.setBounds(8, 8, 48, 48);
    creditsBackButton.addActionListener(new ActionListener() 
    {
      public void actionPerformed(ActionEvent e) {
        creditsPanel.setVisible(false);
        menuPanel.setVisible(true);
      }
    });

    creditsPanel.add(creditsTitleLabel);
    creditsPanel.add(collinNameLabel);
    creditsPanel.add(collinTitlesLabel);
    creditsPanel.add(martinNameLabel);
    creditsPanel.add(martinTitlesLabel);
    creditsPanel.add(benNameLabel);
    creditsPanel.add(benTitlesLabel);
    creditsPanel.add(erikNameLabel);
    creditsPanel.add(erikTitlesLabel);
    creditsPanel.add(creditsBackButton);
    
    //=====================================  GAME FRAME  =====================================//
    gameFrame.setResizable(false);
    gameFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(
        Launcher.class.getResource("/src/images/chip_red_scaled3.png")));
    gameFrame.setTitle("Connect Four");
    gameFrame.setBounds(10, 10, 1006, 840);
    gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    gameFrame.getContentPane().setLayout(null);


    subMenuPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
    subMenuPanel.setBackground(new Color(250, 250, 250));
    subMenuPanel.setBounds(27, 74, 288, 300);
    subMenuPanel.setVisible(false);
    gameFrame.getContentPane().add(subMenuPanel);
    subMenuPanel.setLayout(null);


    btnSaveGame.setFont(new Font("Roboto", Font.PLAIN, 18));
    btnSaveGame.setBounds(24, 24, 240, 40);
    btnSaveGame.addActionListener(new ActionListener()
         {
           public void actionPerformed(ActionEvent e)
           {
             if (Board.winner == 0)
             {  
               try {
				Board.saveGame();
               		} catch (FileNotFoundException e1) {
					e1.printStackTrace();
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
				System.out.println("Error when saving game");
			}
             }
             toggleMenu.setSelected(false);
             subMenuPanel.setVisible(false);
           }
         });
     subMenuPanel.add(btnSaveGame);



    btnLoadGame.setFont(new Font("Roboto", Font.PLAIN, 18));
    btnLoadGame.setBounds(24, 88, 240, 40);
    btnLoadGame.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        try {
			Board.loadGame();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
        Board.winner = 0;
        updateWinnerDisplay();
        toggleMenu.setSelected(false);
        subMenuPanel.setVisible(false);
      }
    });
    subMenuPanel.add(btnLoadGame);
    


    subMenuPanel.add(btnExitToMenu);


    menuNoticeLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
    menuNoticeLabel.setHorizontalAlignment(SwingConstants.CENTER);
    menuNoticeLabel.setBounds(24, 198, 240, 20);
    subMenuPanel.add(menuNoticeLabel);



    toggleMenu.setIcon(new ImageIcon(Launcher.class.getResource("/src/images/icon_menu.png")));
    toggleMenu.setBounds(26, 26, 48, 48);
    toggleMenu.addActionListener(new ActionListener() 
    {
      public void actionPerformed(ActionEvent e) {
        subMenuPanel.setVisible(toggleMenu.isSelected());
      }
    });
    gameFrame.getContentPane().add(toggleMenu);

    winnerDisplayLabel.setIcon(null);
    winnerDisplayLabel.setBounds(0, 100, 1000, 700);
    gameFrame.getContentPane().add(winnerDisplayLabel);


    chip_0_0.setIcon(null);
    chip_0_0.setHorizontalAlignment(SwingConstants.CENTER);
    chip_0_0.setBounds(164, 679, 84, 84);
    gameFrame.getContentPane().add(chip_0_0);


    chip_0_1.setHorizontalAlignment(SwingConstants.CENTER);
    chip_0_1.setBounds(164, 581, 84, 84);
    gameFrame.getContentPane().add(chip_0_1);


    chip_0_2.setHorizontalAlignment(SwingConstants.CENTER);
    chip_0_2.setBounds(164, 483, 84, 84);
    gameFrame.getContentPane().add(chip_0_2);


    chip_0_3.setHorizontalAlignment(SwingConstants.CENTER);
    chip_0_3.setBounds(164, 385, 84, 84);
    gameFrame.getContentPane().add(chip_0_3);


    chip_0_4.setHorizontalAlignment(SwingConstants.CENTER);
    chip_0_4.setBounds(164, 287, 84, 84);
    gameFrame.getContentPane().add(chip_0_4);


    chip_0_5.setHorizontalAlignment(SwingConstants.CENTER);
    chip_0_5.setBounds(164, 189, 84, 84);
    gameFrame.getContentPane().add(chip_0_5);


    chip_1_0.setIcon(null);
    chip_1_0.setHorizontalAlignment(SwingConstants.CENTER);
    chip_1_0.setBounds(262, 679, 84, 84);
    gameFrame.getContentPane().add(chip_1_0);


    chip_1_1.setIcon(null);
    chip_1_1.setHorizontalAlignment(SwingConstants.CENTER);
    chip_1_1.setBounds(262, 581, 84, 84);
    gameFrame.getContentPane().add(chip_1_1);


    chip_1_2.setIcon(null);
    chip_1_2.setHorizontalAlignment(SwingConstants.CENTER);
    chip_1_2.setBounds(262, 483, 84, 84);
    gameFrame.getContentPane().add(chip_1_2);


    chip_1_3.setHorizontalAlignment(SwingConstants.CENTER);
    chip_1_3.setBounds(262, 385, 84, 84);
    gameFrame.getContentPane().add(chip_1_3);


    chip_1_4.setHorizontalAlignment(SwingConstants.CENTER);
    chip_1_4.setBounds(262, 287, 84, 84);
    gameFrame.getContentPane().add(chip_1_4);


    chip_1_5.setHorizontalAlignment(SwingConstants.CENTER);
    chip_1_5.setBounds(262, 189, 84, 84);
    gameFrame.getContentPane().add(chip_1_5);


    chip_2_0.setIcon(null);
    chip_2_0.setHorizontalAlignment(SwingConstants.CENTER);
    chip_2_0.setBounds(360, 679, 84, 84);
    gameFrame.getContentPane().add(chip_2_0);


    chip_2_1.setHorizontalAlignment(SwingConstants.CENTER);
    chip_2_1.setBounds(360, 581, 84, 84);
    gameFrame.getContentPane().add(chip_2_1);


    chip_2_2.setHorizontalAlignment(SwingConstants.CENTER);
    chip_2_2.setBounds(360, 483, 84, 84);
    gameFrame.getContentPane().add(chip_2_2);


    chip_2_3.setHorizontalAlignment(SwingConstants.CENTER);
    chip_2_3.setBounds(360, 385, 84, 84);
    gameFrame.getContentPane().add(chip_2_3);


    chip_2_4.setHorizontalAlignment(SwingConstants.CENTER);
    chip_2_4.setBounds(360, 287, 84, 84);
    gameFrame.getContentPane().add(chip_2_4);


    chip_2_5.setHorizontalAlignment(SwingConstants.CENTER);
    chip_2_5.setBounds(360, 189, 84, 84);
    gameFrame.getContentPane().add(chip_2_5);


    chip_3_0.setIcon(null);
    chip_3_0.setHorizontalAlignment(SwingConstants.CENTER);
    chip_3_0.setBounds(458, 679, 84, 84);
    gameFrame.getContentPane().add(chip_3_0);


    chip_3_1.setHorizontalAlignment(SwingConstants.CENTER);
    chip_3_1.setBounds(458, 581, 84, 84);
    gameFrame.getContentPane().add(chip_3_1);


    chip_3_2.setHorizontalAlignment(SwingConstants.CENTER);
    chip_3_2.setBounds(458, 483, 84, 84);
    gameFrame.getContentPane().add(chip_3_2);


    chip_3_3.setHorizontalAlignment(SwingConstants.CENTER);
    chip_3_3.setBounds(458, 385, 84, 84);
    gameFrame.getContentPane().add(chip_3_3);


    chip_3_4.setHorizontalAlignment(SwingConstants.CENTER);
    chip_3_4.setBounds(458, 287, 84, 84);
    gameFrame.getContentPane().add(chip_3_4);


    chip_3_5.setHorizontalAlignment(SwingConstants.CENTER);
    chip_3_5.setBounds(458, 189, 84, 84);
    gameFrame.getContentPane().add(chip_3_5);


    chip_4_0.setIcon(null);
    chip_4_0.setHorizontalAlignment(SwingConstants.CENTER);
    chip_4_0.setBounds(556, 679, 84, 84);
    gameFrame.getContentPane().add(chip_4_0);


    chip_4_1.setHorizontalAlignment(SwingConstants.CENTER);
    chip_4_1.setBounds(556, 581, 84, 84);
    gameFrame.getContentPane().add(chip_4_1);


    chip_4_2.setHorizontalAlignment(SwingConstants.CENTER);
    chip_4_2.setBounds(556, 483, 84, 84);
    gameFrame.getContentPane().add(chip_4_2);


    chip_4_3.setHorizontalAlignment(SwingConstants.CENTER);
    chip_4_3.setBounds(556, 385, 84, 84);
    gameFrame.getContentPane().add(chip_4_3);


    chip_4_4.setHorizontalAlignment(SwingConstants.CENTER);
    chip_4_4.setBounds(556, 287, 84, 84);
    gameFrame.getContentPane().add(chip_4_4);


    chip_4_5.setHorizontalAlignment(SwingConstants.CENTER);
    chip_4_5.setBounds(556, 189, 84, 84);
    gameFrame.getContentPane().add(chip_4_5);


    chip_5_0.setHorizontalAlignment(SwingConstants.CENTER);
    chip_5_0.setBounds(654, 679, 84, 84);
    gameFrame.getContentPane().add(chip_5_0);


    chip_5_1.setHorizontalAlignment(SwingConstants.CENTER);
    chip_5_1.setBounds(654, 581, 84, 84);
    gameFrame.getContentPane().add(chip_5_1);


    chip_5_2.setHorizontalAlignment(SwingConstants.CENTER);
    chip_5_2.setBounds(654, 483, 84, 84);
    gameFrame.getContentPane().add(chip_5_2);


    chip_5_3.setHorizontalAlignment(SwingConstants.CENTER);
    chip_5_3.setBounds(654, 385, 84, 84);
    gameFrame.getContentPane().add(chip_5_3);


    chip_5_4.setHorizontalAlignment(SwingConstants.CENTER);
    chip_5_4.setBounds(654, 287, 84, 84);
    gameFrame.getContentPane().add(chip_5_4);


    chip_5_5.setHorizontalAlignment(SwingConstants.CENTER);
    chip_5_5.setBounds(654, 189, 84, 84);
    gameFrame.getContentPane().add(chip_5_5);


    chip_6_0.setIcon(null);
    chip_6_0.setHorizontalAlignment(SwingConstants.CENTER);
    chip_6_0.setBounds(752, 679, 84, 84);
    gameFrame.getContentPane().add(chip_6_0);


    chip_6_1.setIcon(null);
    chip_6_1.setHorizontalAlignment(SwingConstants.CENTER);
    chip_6_1.setBounds(752, 581, 84, 84);
    gameFrame.getContentPane().add(chip_6_1);


    chip_6_2.setHorizontalAlignment(SwingConstants.CENTER);
    chip_6_2.setBounds(752, 483, 84, 84);
    gameFrame.getContentPane().add(chip_6_2);


    chip_6_3.setHorizontalAlignment(SwingConstants.CENTER);
    chip_6_3.setBounds(752, 385, 84, 84);
    gameFrame.getContentPane().add(chip_6_3);

    winCounter.setText("Wins: " + wins);
    winCounter.setHorizontalAlignment(SwingConstants.CENTER);
    winCounter.setForeground(Color.BLACK);
    winCounter.setFont(new Font("Roboto", Font.PLAIN, 38));
    winCounter.setBounds(89, 16, 798, 68);
    gameFrame.getContentPane().add(winCounter);


    chip_6_4.setHorizontalAlignment(SwingConstants.CENTER);
    chip_6_4.setBounds(752, 287, 84, 84);
    gameFrame.getContentPane().add(chip_6_4);


    chip_6_5.setHorizontalAlignment(SwingConstants.CENTER);
    chip_6_5.setBounds(752, 189, 84, 84);
    gameFrame.getContentPane().add(chip_6_5);


    position0.setIcon(new ImageIcon(Launcher.class.getResource("/src/images/positionindicator_scaled.png")));
    position0.setHorizontalAlignment(SwingConstants.CENTER);
    position0.setBounds(156, 100, 100, 87);
    position0.setVisible(Board.columnSelection == 0);
    gameFrame.getContentPane().add(position0);


    position1.setIcon(new ImageIcon(Launcher.class.getResource("/src/images/positionindicator_scaled.png")));
    position1.setHorizontalAlignment(SwingConstants.CENTER);
    position1.setBounds(254, 100, 100, 87);
    position1.setVisible(Board.columnSelection == 1);
    gameFrame.getContentPane().add(position1);


    position2.setIcon(new ImageIcon(Launcher.class.getResource("/src/images/positionindicator_scaled.png")));
    position2.setHorizontalAlignment(SwingConstants.CENTER);
    position2.setBounds(352, 100, 100, 87);
    position2.setVisible(Board.columnSelection == 2);
    gameFrame.getContentPane().add(position2);


    position3.setIcon(new ImageIcon(Launcher.class.getResource("/src/images/positionindicator_scaled.png")));
    position3.setHorizontalAlignment(SwingConstants.CENTER);
    position3.setBounds(450, 100, 100, 87);
    position3.setVisible(Board.columnSelection == 3);
    gameFrame.getContentPane().add(position3);


    position4.setIcon(new ImageIcon(Launcher.class.getResource("/src/images/positionindicator_scaled.png")));
    position4.setHorizontalAlignment(SwingConstants.CENTER);
    position4.setBounds(548, 100, 100, 87);
    position4.setVisible(Board.columnSelection == 4);
    gameFrame.getContentPane().add(position4);


    position5.setIcon(new ImageIcon(Launcher.class.getResource("/src/images/positionindicator_scaled.png")));
    position5.setHorizontalAlignment(SwingConstants.CENTER);
    position5.setBounds(646, 100, 100, 87);
    position5.setVisible(Board.columnSelection == 5);
    gameFrame.getContentPane().add(position5);


    position6.setIcon(new ImageIcon(Launcher.class.getResource("/src/images/positionindicator_scaled.png")));
    position6.setHorizontalAlignment(SwingConstants.CENTER);
    position6.setBounds(744, 100, 100, 87);
    position6.setVisible(Board.columnSelection == 6);
    gameFrame.getContentPane().add(position6);

    JLabel boardDisplayLabel = new JLabel("");
    boardDisplayLabel.setIcon(new ImageIcon(Launcher.class.getResource("/src/images/frame_game.png")));
    boardDisplayLabel.setBounds(0, 0, 1000, 800);
    gameFrame.getContentPane().add(boardDisplayLabel);

  }
  
  private void processKeys()
  {
    KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(
        new KeyEventDispatcher()
        { 
          public boolean dispatchKeyEvent(KeyEvent e)
          {
            if(e.getID() == KeyEvent.KEY_PRESSED)
            {
              System.out.println(e.getKeyCode());
              handleKeyRelease(e.getKeyCode());
            }
            return false;
          }
        });
    return;
  }

  private void updateIndicator()
  {
    position0.setVisible(Board.columnSelection == 0);
    position1.setVisible(Board.columnSelection == 1);
    position2.setVisible(Board.columnSelection == 2);
    position3.setVisible(Board.columnSelection == 3);
    position4.setVisible(Board.columnSelection == 4);
    position5.setVisible(Board.columnSelection == 5);
    position6.setVisible(Board.columnSelection == 6);
    return;
  }

  private void updateChips()
  {
    ImageIcon playerChip;
    ImageIcon computerChip;
    if (Launcher.playerIsRed == true)
    {
      playerChip = new ImageIcon(
          this.getClass().getResource("/src/images/chip_red_scaled3.png"));
      computerChip = new ImageIcon(
          this.getClass().getResource("/src/images/chip_yellow_scaled3.png"));
    }
    else
    {
      playerChip = new ImageIcon(
          this.getClass().getResource("/src/images/chip_yellow_scaled3.png"));
      computerChip = new ImageIcon(
          this.getClass().getResource("/src/images/chip_red_scaled3.png"));
    }

    for (int column = 0; column <= 6; column++)
    {
      for (int row = 0; row <= 5; row++)
      {
        switch (column)
        {
        case 0:
          switch (row)
          {
          case 0:
            if (Board.board[column][row] == 1)
              chip_0_0.setIcon(playerChip);
            else if (Board.board[column][row] == 2)
              chip_0_0.setIcon(computerChip);
            else chip_0_0.setIcon(null);
          case 1:
            if (Board.board[column][row] == 1)
              chip_0_1.setIcon(playerChip);
            else if (Board.board[column][row] == 2)
              chip_0_1.setIcon(computerChip);
            else chip_0_1.setIcon(null);
          case 2:
            if (Board.board[column][row] == 1)
              chip_0_2.setIcon(playerChip);
            else if (Board.board[column][row] == 2)
              chip_0_2.setIcon(computerChip);
            else chip_0_2.setIcon(null);
          case 3:
            if (Board.board[column][row] == 1)
              chip_0_3.setIcon(playerChip);
            else if (Board.board[column][row] == 2)
              chip_0_3.setIcon(computerChip);
            else chip_0_3.setIcon(null);
          case 4:
            if (Board.board[column][row] == 1)
              chip_0_4.setIcon(playerChip);
            else if (Board.board[column][row] == 2)
              chip_0_4.setIcon(computerChip);
            else chip_0_4.setIcon(null);
          case 5:
            if (Board.board[column][row] == 1)
              chip_0_5.setIcon(playerChip);
            else if (Board.board[column][row] == 2)
              chip_0_5.setIcon(computerChip);
            else chip_0_5.setIcon(null);
          }
        case 1:
          switch (row)
          {
          case 0:
            if (Board.board[column][row] == 1)
              chip_1_0.setIcon(playerChip);
            else if (Board.board[column][row] == 2)
              chip_1_0.setIcon(computerChip);
            else chip_1_0.setIcon(null);
          case 1:
            if (Board.board[column][row] == 1)
              chip_1_1.setIcon(playerChip);
            else if (Board.board[column][row] == 2)
              chip_1_1.setIcon(computerChip);
            else chip_1_1.setIcon(null);
          case 2:
            if (Board.board[column][row] == 1)
              chip_1_2.setIcon(playerChip);
            else if (Board.board[column][row] == 2)
              chip_1_2.setIcon(computerChip);
            else chip_1_2.setIcon(null);
          case 3:
            if (Board.board[column][row] == 1)
              chip_1_3.setIcon(playerChip);
            else if (Board.board[column][row] == 2)
              chip_1_3.setIcon(computerChip);
            else chip_1_3.setIcon(null);
          case 4:
            if (Board.board[column][row] == 1)
              chip_1_4.setIcon(playerChip);
            else if (Board.board[column][row] == 2)
              chip_1_4.setIcon(computerChip);
            else chip_1_4.setIcon(null);
          case 5:
            if (Board.board[column][row] == 1)
              chip_1_5.setIcon(playerChip);
            else if (Board.board[column][row] == 2)
              chip_1_5.setIcon(computerChip);
            else chip_1_5.setIcon(null);
          }
        case 2:
          switch (row)
          {
          case 0:
            if (Board.board[column][row] == 1)
              chip_2_0.setIcon(playerChip);
            else if (Board.board[column][row] == 2)
              chip_2_0.setIcon(computerChip);
            else chip_2_0.setIcon(null);
          case 1:
            if (Board.board[column][row] == 1)
              chip_2_1.setIcon(playerChip);
            else if (Board.board[column][row] == 2)
              chip_2_1.setIcon(computerChip);
            else chip_2_1.setIcon(null);
          case 2:
            if (Board.board[column][row] == 1)
              chip_2_2.setIcon(playerChip);
            else if (Board.board[column][row] == 2)
              chip_2_2.setIcon(computerChip);
            else chip_2_2.setIcon(null);
          case 3:
            if (Board.board[column][row] == 1)
              chip_2_3.setIcon(playerChip);
            else if (Board.board[column][row] == 2)
              chip_2_3.setIcon(computerChip);
            else chip_2_3.setIcon(null);
          case 4:
            if (Board.board[column][row] == 1)
              chip_2_4.setIcon(playerChip);
            else if (Board.board[column][row] == 2)
              chip_2_4.setIcon(computerChip);
            else chip_2_4.setIcon(null);
          case 5:
            if (Board.board[column][row] == 1)
              chip_2_5.setIcon(playerChip);
            else if (Board.board[column][row] == 2)
              chip_2_5.setIcon(computerChip);
            else chip_2_5.setIcon(null);
          }
        case 3:
          switch (row)
          {
          case 0:
            if (Board.board[column][row] == 1)
              chip_3_0.setIcon(playerChip);
            else if (Board.board[column][row] == 2)
              chip_3_0.setIcon(computerChip);
            else chip_3_0.setIcon(null);
          case 1:
            if (Board.board[column][row] == 1)
              chip_3_1.setIcon(playerChip);
            else if (Board.board[column][row] == 2)
              chip_3_1.setIcon(computerChip);
            else chip_3_1.setIcon(null);
          case 2:
            if (Board.board[column][row] == 1)
              chip_3_2.setIcon(playerChip);
            else if (Board.board[column][row] == 2)
              chip_3_2.setIcon(computerChip);
            else chip_3_2.setIcon(null);
          case 3:
            if (Board.board[column][row] == 1)
              chip_3_3.setIcon(playerChip);
            else if (Board.board[column][row] == 2)
              chip_3_3.setIcon(computerChip);
            else chip_3_3.setIcon(null);
          case 4:
            if (Board.board[column][row] == 1)
              chip_3_4.setIcon(playerChip);
            else if (Board.board[column][row] == 2)
              chip_3_4.setIcon(computerChip);
            else chip_3_4.setIcon(null);
          case 5:
            if (Board.board[column][row] == 1)
              chip_3_5.setIcon(playerChip);
            else if (Board.board[column][row] == 2)
              chip_3_5.setIcon(computerChip);
            else chip_3_5.setIcon(null);
          }
        case 4:
          switch (row)
          {
          case 0:
            if (Board.board[column][row] == 1)
              chip_4_0.setIcon(playerChip);
            else if (Board.board[column][row] == 2)
              chip_4_0.setIcon(computerChip);
            else chip_4_0.setIcon(null);
          case 1:
            if (Board.board[column][row] == 1)
              chip_4_1.setIcon(playerChip);
            else if (Board.board[column][row] == 2)
              chip_4_1.setIcon(computerChip);
            else chip_4_1.setIcon(null);
          case 2:
            if (Board.board[column][row] == 1)
              chip_4_2.setIcon(playerChip);
            else if (Board.board[column][row] == 2)
              chip_4_2.setIcon(computerChip);
            else chip_4_2.setIcon(null);
          case 3:
            if (Board.board[column][row] == 1)
              chip_4_3.setIcon(playerChip);
            else if (Board.board[column][row] == 2)
              chip_4_3.setIcon(computerChip);
            else chip_4_3.setIcon(null);
          case 4:
            if (Board.board[column][row] == 1)
              chip_4_4.setIcon(playerChip);
            else if (Board.board[column][row] == 2)
              chip_4_4.setIcon(computerChip);
            else chip_4_4.setIcon(null);
          case 5:
            if (Board.board[column][row] == 1)
              chip_4_5.setIcon(playerChip);
            else if (Board.board[column][row] == 2)
              chip_4_5.setIcon(computerChip);
            else chip_4_5.setIcon(null);
          }
        case 5:
          switch (row)
          {
          case 0:
            if (Board.board[column][row] == 1)
              chip_5_0.setIcon(playerChip);
            else if (Board.board[column][row] == 2)
              chip_5_0.setIcon(computerChip);
            else chip_5_0.setIcon(null);
          case 1:
            if (Board.board[column][row] == 1)
              chip_5_1.setIcon(playerChip);
            else if (Board.board[column][row] == 2)
              chip_5_1.setIcon(computerChip);
            else chip_5_1.setIcon(null);
          case 2:
            if (Board.board[column][row] == 1)
              chip_5_2.setIcon(playerChip);
            else if (Board.board[column][row] == 2)
              chip_5_2.setIcon(computerChip);
            else chip_5_2.setIcon(null);
          case 3:
            if (Board.board[column][row] == 1)
              chip_5_3.setIcon(playerChip);
            else if (Board.board[column][row] == 2)
              chip_5_3.setIcon(computerChip);
            else chip_5_3.setIcon(null);
          case 4:
            if (Board.board[column][row] == 1)
              chip_5_4.setIcon(playerChip);
            else if (Board.board[column][row] == 2)
              chip_5_4.setIcon(computerChip);
            else chip_5_4.setIcon(null);
          case 5:
            if (Board.board[column][row] == 1)
              chip_5_5.setIcon(playerChip);
            else if (Board.board[column][row] == 2)
              chip_5_5.setIcon(computerChip);
            else chip_5_5.setIcon(null);
          }
        case 6:
          switch (row)
          {
          case 0:
            if (Board.board[column][row] == 1)
              chip_6_0.setIcon(playerChip);
            else if (Board.board[column][row] == 2)
              chip_6_0.setIcon(computerChip);
            else chip_6_0.setIcon(null);
          case 1:
            if (Board.board[column][row] == 1)
              chip_6_1.setIcon(playerChip);
            else if (Board.board[column][row] == 2)
              chip_6_1.setIcon(computerChip);
            else chip_6_1.setIcon(null);
          case 2:
            if (Board.board[column][row] == 1)
              chip_6_2.setIcon(playerChip);
            else if (Board.board[column][row] == 2)
              chip_6_2.setIcon(computerChip);
            else chip_6_2.setIcon(null);
          case 3:
            if (Board.board[column][row] == 1)
              chip_6_3.setIcon(playerChip);
            else if (Board.board[column][row] == 2)
              chip_6_3.setIcon(computerChip);
            else chip_6_3.setIcon(null);
          case 4:
            if (Board.board[column][row] == 1)
              chip_6_4.setIcon(playerChip);
            else if (Board.board[column][row] == 2)
              chip_6_4.setIcon(computerChip);
            else chip_6_4.setIcon(null);
          case 5:
            if (Board.board[column][row] == 1)
              chip_6_5.setIcon(playerChip);
            else if (Board.board[column][row] == 2)
              chip_6_5.setIcon(computerChip);
            else chip_6_5.setIcon(null);
          }
        }
      }
    }
    return;
  }

  private void updateWinnerDisplay()
  {
    if (Board.winner == 0) winnerDisplayLabel.setVisible(false);
    else if (Board.winner == 1)
    {
      winnerDisplayLabel.setIcon(
          new ImageIcon(Launcher.class.getResource("/src/images/victory.png")));
      winnerDisplayLabel.setVisible(true);
    }
    else if (Board.winner == 2)
    {
      winnerDisplayLabel.setIcon(
          new ImageIcon(Launcher.class.getResource("/src/images/defeat.png")));
      winnerDisplayLabel.setVisible(true);
    }
    if (Board.winner == 1) winCounter.setText("Wins: 1");
  }

  public void updateBoard()
  {
    updateIndicator();
    updateChips();
    updateWinnerDisplay();
    return;
  }

  private void handleKeyRelease(int keyCode)
  { 
    if (Board.winner != 0) return;
    if (keyCode==37)
    {
      Board.moveColumnSelectionLeft();
    }

    if (keyCode==39)
    {
      Board.moveColumnSelectionRight();
    }
    if (keyCode==10 && !Board.columnIsFull(Board.columnSelection))
    {
      Board.addChip(Board.columnSelection, 1);
      Board.winCheck();
      updateBoard();
      if (Board.winner != 0) return;
//      Board.moveColumnSelectionRight();
//      Board.addChip(Board.columnSelection, 2);
//      Board.moveColumnSelectionLeft();
      int i = ComputerPlayer.playTile(Board.columnSelection);
      System.out.println("Computer Choice: " + i);
      Board.addChip(i, 2);
      if (Board.columnIsFull(Board.columnSelection))
      {
        Board.moveColumnSelectionRight();
      }

    }

    updateBoard();
    if (Board.winner == 1)
    {
      wins++;
    }
    return;
  }
  
}
