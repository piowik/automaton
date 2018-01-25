package automaton.view;

import automaton.automaton.Automaton;
import automaton.automaton.GameOfLife;
import automaton.automaton.LangtonAnt;
import automaton.automaton.WireWorld;
import automaton.cell.LangtonCell;
import automaton.coordinates.CellCoordinates;
import automaton.coordinates.Coords2D;
import automaton.factory.UniformStateFactory;
import automaton.neighborhood.CellNeighborhood;
import automaton.neighborhood.MoorNeighborhood;
import automaton.neighborhood.VonNeumanNeighborhood;
import automaton.state.AntState;
import automaton.state.BinaryState;
import automaton.state.CellState;
import automaton.state.WireElectronState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

import static automaton.state.BinaryState.ALIVE;
import static automaton.state.BinaryState.DEAD;

public class TestGUI {
    private int[] neighborsToNotDie = {2, 3};
    private int[] neighborsToStartLiving = {3};

    private JFrame mainFrame;
    private GPanel planePanel = new GPanel();
    private JCheckBox wrapCheckbox;
    private Automaton currentGame;
    private Map<CellCoordinates, CellState> cellsMap = new HashMap<>();
    private JSpinner rSpinner, tickSpinner;
    private JComboBox jComboBox = new JComboBox();
    private JComboBox neighborhoodComboBox = new JComboBox();
    private JButton autoMode;
    private boolean isAuto = false;

    private int selectedGame = 0;
    private int selectedNeighborhood = 0;
    private int width;
    private int height;
    private int ZOOM;

    public static void main(String[] args) {
        new TestGUI();
    }

    private TestGUI() {
        prepareGUI();
    }

    private void prepareGUI() {

        mainFrame = new JFrame();//creating instance of JFrame
        mainFrame.setTitle("Automaton");
        mainFrame.setSize(550, 640);
        mainFrame.setLayout(null);//using no layout managers

        jComboBox.addItem("Game of Life");
        jComboBox.addItem("Wireworld");
        jComboBox.addItem("Langton Ant");
        neighborhoodComboBox.addItem("Moore Neighborhood");
        neighborhoodComboBox.addItem("VonNeuman Neighborhood");
        jComboBox.addActionListener(e -> {
            selectedGame = ((JComboBox) e.getSource()).getSelectedIndex();
            System.out.println(selectedGame);
        });
        neighborhoodComboBox.addActionListener(e -> selectedNeighborhood = ((JComboBox) e.getSource()).getSelectedIndex());

        neighborhoodComboBox.setBounds(0, 0, 150, 40);
        jComboBox.setBounds(150, 0, 100, 40);

        planePanel.setBounds(0, 80, 540, 560);
        planePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (currentGame instanceof GameOfLife) {
                    int posX = e.getX() / ZOOM;
                    int posY = e.getY() / ZOOM;
                    Map<CellCoordinates, CellState> testStructure = GameOfLife.convert(MapReader.readMapFromFile("structure.txt", posX, posY));
                    currentGame.insertStructure(testStructure);
                    System.out.println(e.getX() + ":" + e.getY());
                }
            }
        });
        JButton nextStepButton = new JButton("Step");//creating instance of JButton
        nextStepButton.setBounds(0, 40, 100, 40);
        nextStepButton.addActionListener(e -> {
            currentGame = currentGame.nextState();
            cellsMap = currentGame.getCells();
            mainFrame.validate();
            mainFrame.repaint();
        });
        JButton next10Steps = new JButton("10 steps");//creating instance of JButton
        next10Steps.setBounds(100, 40, 100, 40);
        next10Steps.addActionListener(e -> {
            for (int i = 0; i <= 10; i++)
                currentGame = currentGame.nextState();

            cellsMap = currentGame.getCells();
            mainFrame.validate();
            mainFrame.repaint();

        });
        JButton next1000Steps = new JButton("1000 steps");//creating instance of JButton
        next1000Steps.setBounds(200, 40, 100, 40);
        next1000Steps.addActionListener(e -> {
            for (int i = 0; i <= 1000; i++)
                currentGame = currentGame.nextState();

            cellsMap = currentGame.getCells();
            mainFrame.validate();
            mainFrame.repaint();

        });
        autoMode = new JButton("Auto 100");
        autoMode.setBounds(300, 40, 100, 40);
        autoMode.addActionListener(e -> {
            if (!isAuto) {
                isAuto = true;
                (new Automate()).execute();
                autoMode.setText("Stop");
            }
            else {
                isAuto = false;
                autoMode.setText("Auto 100");
            }

        });
        autoMode.setEnabled(false);
        nextStepButton.setEnabled(false);
        next10Steps.setEnabled(false);
        next1000Steps.setEnabled(false);
        wrapCheckbox = new JCheckBox("Wrap");
        wrapCheckbox.setBounds(250, 0, 100, 40);
        JLabel rLabel = new JLabel("R:");
        rLabel.setBounds(350, 0, 30, 40);
        SpinnerModel rModel = new SpinnerNumberModel(1, 1, 20, 1);
        rSpinner = new JSpinner(rModel);
        rSpinner.setBounds(380, 0, 70, 40);

        JButton startGameButton = new JButton("Start");//creating instance of JButton
        startGameButton.setBounds(450, 0, 80, 40);
        startGameButton.addActionListener(e -> {
            CellNeighborhood neighborhood;
            int r = (int) rSpinner.getValue();
            if (selectedNeighborhood == 0)
                neighborhood = new MoorNeighborhood(width, height, wrapCheckbox.isSelected(), r);
            else // prevent false "might have not been initialized" warning
                neighborhood = new VonNeumanNeighborhood(width, height, wrapCheckbox.isSelected(), r);
            switch (selectedGame) {
                case 0:
                    width = 50;
                    height = 50;
                    ZOOM = 10;
                    startGameOfLife(neighborhood);
                    break;

                case 1:
                    width = 50;
                    height = 50;
                    ZOOM = 10;
                    startWireWorld(neighborhood);
                    break;

                case 2:
                    width = 100;
                    height = 100;
                    ZOOM = 5;
                    startLangtonAnt(neighborhood);
                    break;

            }
            cellsMap = currentGame.getCells();
            mainFrame.validate();
            mainFrame.repaint();

            nextStepButton.setEnabled(true);
            next10Steps.setEnabled(true);
            next1000Steps.setEnabled(true);
            autoMode.setEnabled(true);

        });
        SpinnerModel tickModel = new SpinnerNumberModel(300, 50, 1000, 1);
        tickSpinner = new JSpinner(tickModel);
        tickSpinner.setBounds(400, 40, 70, 40);


        // 1st row
        mainFrame.add(neighborhoodComboBox);
        mainFrame.add(jComboBox);
        mainFrame.add(wrapCheckbox);
        mainFrame.add(rLabel);
        mainFrame.add(rSpinner);
        mainFrame.add(startGameButton);
        // 2nd row
        mainFrame.add(nextStepButton);
        mainFrame.add(next10Steps);
        mainFrame.add(next1000Steps);
        mainFrame.add(autoMode);
        mainFrame.add(tickSpinner);
        mainFrame.add(planePanel);

        WindowListener wndCloser = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };
        mainFrame.addWindowListener(wndCloser);
        mainFrame.setVisible(true);
    }

    private void startWireWorld(CellNeighborhood neighborhood) {
        UniformStateFactory uniformStateFactory = new UniformStateFactory(WireElectronState.VOID);
        currentGame = new WireWorld(neighborhood, uniformStateFactory, width, height);
        Map<CellCoordinates, CellState> testStructure = WireWorld.convert(MapReader.readMapFromFile("xor.txt",10,10));
        currentGame.insertStructure(testStructure);

    }

    private void startGameOfLife(CellNeighborhood neighborhood) {
        UniformStateFactory uniformStateFactory = new UniformStateFactory(DEAD);
        currentGame = new GameOfLife(neighborhood, uniformStateFactory, width, height, neighborsToNotDie, neighborsToStartLiving);
        Map<CellCoordinates, CellState> testStructure = GameOfLife.convert(MapReader.readMapFromFile("gameoflife.txt",0,0));
        currentGame.insertStructure(testStructure);
    }

    private void startLangtonAnt(CellNeighborhood neighborhood) {
        UniformStateFactory uniformStateFactory = new UniformStateFactory(BinaryState.DEAD);
        currentGame = new LangtonAnt(neighborhood, uniformStateFactory, width, height);
        Map<CellCoordinates, CellState> testStructure = new HashMap<>();


        int antPos = width / 2;
        CellCoordinates testItem = new Coords2D(antPos, antPos);
        LangtonCell testItemState = new LangtonCell(AntState.EAST, 1, BinaryState.ALIVE);
        testStructure.put(testItem, testItemState);

        currentGame.insertStructure(testStructure);


    }


    class GPanel extends JPanel {

        @Override
        public void paintComponent(Graphics g) {
            if (cellsMap.size() != 0) {
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        CellState state = cellsMap.get(new Coords2D(j, i));
                        if (state == DEAD)
                            g.setColor(Color.WHITE);
                        else if (state == ALIVE)
                            g.setColor(Color.BLACK);
                        else if (state == WireElectronState.VOID)
                            g.setColor(Color.BLACK);
                        else if (state == WireElectronState.WIRE)
                            g.setColor(Color.YELLOW);
                        else if (state == WireElectronState.ELECTRON_HEAD)
                            g.setColor(Color.BLUE);
                        else if (state == WireElectronState.ELECTRON_TAIL)
                            g.setColor(Color.RED);
                        else if (state instanceof LangtonCell) {
                            LangtonCell lc = (LangtonCell) state;
                            if (lc.cellState == DEAD)
                                g.setColor(Color.WHITE);
                            else if (lc.cellState == ALIVE)
                                g.setColor(Color.BLACK);
                        }
                        g.fillRect(j * ZOOM, i * ZOOM, ZOOM, ZOOM);

                    }
                }
            }
        }
    }

    class Automate extends SwingWorker<String, Object> {
        @Override
        public String doInBackground() {
            for(int i = 0; i <= 100; i++){
                currentGame = currentGame.nextState();
                cellsMap = currentGame.getCells();
                mainFrame.validate();
                mainFrame.repaint();
                try {
                    Thread.sleep((int)tickSpinner.getValue());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!isAuto)
                    break;
            }
            return "done";
        }

        @Override
        protected void done() {
            try {
                mainFrame.validate();
                mainFrame.repaint();
            } catch (Exception ignore) {
            }
        }
    }


}
