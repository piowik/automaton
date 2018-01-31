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

public class GUI2D {
    private int[] neighborsToNotDie = {2, 3};
    private int[] neighborsToStartLiving = {3};

    private JFrame mainFrame;
    private GPanel planePanel;
    private JCheckBox wrapCheckbox;
    private JTextField cellsToLiveTextField;
    private JTextField cellsToBecomeAliveTextField;
    private Automaton currentGame;
    private Map<CellCoordinates, CellState> cellsMap = new HashMap<>();
    private JSpinner rSpinner, tickSpinner;
    private JComboBox<String> gameComboBox;
    private JComboBox<String> neighborhoodComboBox;
    private JComboBox<String> insertComboBox;
    private JButton autoModeButton;
    private boolean isAuto = false;

    private int selectedGame = 0;
    private int selectedNeighborhood = 0;
    private int width;
    private int height;
    private final int window_width = 720;
    private final int window_height = 800;
    private int ZOOM;

    private int prevNeighborhood = 0;

    public static void main(String[] args) {
        new GUI2D();
    }

    private GUI2D() {
        prepareGUI();
    }

    private void prepareGUI() {
        mainFrame = new JFrame();
        mainFrame.setTitle("Automaton");
        mainFrame.setSize(window_width, window_height);
        mainFrame.setLayout(null); //using no layout managers

        gameComboBox = new JComboBox<>();
        neighborhoodComboBox = new JComboBox<>();
        JLabel cellsToLiveLabel = new JLabel("To live");
        JLabel cellsToBecomeAliveLabel = new JLabel("To be born");
        cellsToLiveTextField = new JTextField("23");
        cellsToBecomeAliveTextField = new JTextField("3");
        wrapCheckbox = new JCheckBox("Wrap");
        JLabel rLabel = new JLabel("R:");
        SpinnerModel rModel = new SpinnerNumberModel(1, 1, 20, 1);
        rSpinner = new JSpinner(rModel);
        JButton startGameButton = new JButton("New automaton");

        JButton nextStepButton = new JButton("Step");//creating instance of JButton
        JButton next10Steps = new JButton("10 steps");//creating instance of JButton
        JButton next5000Steps = new JButton("5000 steps");//creating instance of JButton
        autoModeButton = new JButton("Auto");
        JLabel tickSpinnerLabel = new JLabel("Ticks per second");
        SpinnerModel tickModel = new SpinnerNumberModel(10, 1, 50, 1);
        tickSpinner = new JSpinner(tickModel);
        JLabel insertLabel = new JLabel("Insert");
        insertComboBox = new JComboBox<>();

        planePanel = new GPanel();

        autoModeButton.setEnabled(false);
        nextStepButton.setEnabled(false);
        next10Steps.setEnabled(false);
        next5000Steps.setEnabled(false);

        // initialize comboBoxes
        gameComboBox.addItem("Game of Life");
        gameComboBox.addItem("Wireworld");
        gameComboBox.addItem("Langton Ant");
        neighborhoodComboBox.addItem("Moore Neighborhood");
        neighborhoodComboBox.addItem("VonNeuman Neighborhood");

        updateInterface(0);

        gameComboBox.addActionListener(e -> {
            selectedGame = ((JComboBox) e.getSource()).getSelectedIndex();
            updateInterface(selectedGame);
        });
        neighborhoodComboBox.addActionListener(e -> selectedNeighborhood = ((JComboBox) e.getSource()).getSelectedIndex());
        planePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mousePressed(e);
                int posX = e.getX() / ZOOM;
                int posY = e.getY() / ZOOM;
                if (currentGame instanceof LangtonAnt)
                    return;
                String selectedStructureToInsert = insertComboBox.getSelectedItem() + ".txt";
                Map<CellCoordinates, CellState> structureToInsert = new HashMap<>();
                if (currentGame instanceof GameOfLife) {
                    if (selectedStructureToInsert.contains("Cell"))
                        structureToInsert.put(new Coords2D(posX, posY), BinaryState.ALIVE);
                    else
                        structureToInsert = GameOfLife.convert(MapReader.readMapFromFile(selectedStructureToInsert, posX, posY));
                    currentGame.insertStructure(structureToInsert);
                } else if (currentGame instanceof WireWorld) {
                    if (selectedStructureToInsert.contains("Wire"))
                        structureToInsert.put(new Coords2D(posX, posY), WireElectronState.WIRE);
                    else if (selectedStructureToInsert.contains("Electron"))
                        structureToInsert.put(new Coords2D(posX, posY), WireElectronState.ELECTRON_HEAD);
                    else
                        structureToInsert = WireWorld.convert(MapReader.readMapFromFile(selectedStructureToInsert, posX, posY));
                    currentGame.insertStructure(structureToInsert);
                }
                mainFrame.validate();
                mainFrame.repaint();
            }
        });
        nextStepButton.addActionListener(e -> nextState(1));
        next10Steps.addActionListener(e -> nextState(10));
        next5000Steps.addActionListener(e -> nextState(5000));
        autoModeButton.addActionListener(e -> {
            if (!isAuto) {
                isAuto = true;
                (new Automate()).execute();
                autoModeButton.setText("Stop");
            } else {
                isAuto = false;
                autoModeButton.setText("Auto");
            }
        });
        startGameButton.addActionListener(e -> {
            if (isAuto) {
                isAuto = false;
                autoModeButton.setText("Auto");
            }
            if (selectedGame == 0) {// GameOfLife
                String stringToParse2 = cellsToBecomeAliveTextField.getText();
                String stringToParse1 = cellsToLiveTextField.getText();
                if (stringToParse1.length() > 0) {
                    neighborsToNotDie = parseValues(stringToParse1);
                }
                if (stringToParse2.length() > 0) {
                    neighborsToStartLiving = parseValues(stringToParse2);
                }
            }
            CellNeighborhood neighborhood;
            int r = (int) rSpinner.getValue();
            switch (selectedGame) {
                case 0:
                    width = 95;
                    height = 95;
                    ZOOM = 7;
                    break;

                case 1:
                    width = 73;
                    height = 73;
                    ZOOM = 9;
                    break;

                case 2:
                    width = 73;
                    height = 73;
                    ZOOM = 9;
                    break;
            }
            if (selectedNeighborhood == 0)
                neighborhood = new MoorNeighborhood(width, height, wrapCheckbox.isSelected(), r);
            else
                neighborhood = new VonNeumanNeighborhood(width, height, wrapCheckbox.isSelected(), r);
            switch (selectedGame) {
                case 0:
                    startGameOfLife(neighborhood);
                    break;

                case 1:
                    startWireWorld(neighborhood);
                    break;

                case 2:
                    startLangtonAnt(neighborhood);
                    break;
            }
            cellsMap = currentGame.getCells();
            mainFrame.validate();
            mainFrame.repaint();

            nextStepButton.setEnabled(true);
            next10Steps.setEnabled(true);
            next5000Steps.setEnabled(true);
            autoModeButton.setEnabled(true);
        });

        // layout
        // 1st row
        gameComboBox.setBounds(0, 0, 150, 40);
        neighborhoodComboBox.setBounds(150, 0, 150, 40);
        cellsToLiveLabel.setBounds(300, 0, 100, 20);
        cellsToBecomeAliveLabel.setBounds(300, 20, 100, 20);
        cellsToLiveTextField.setBounds(400, 0, 50, 20);
        cellsToBecomeAliveTextField.setBounds(400, 20, 50, 20);
        wrapCheckbox.setBounds(450, 0, 60, 40);
        rLabel.setBounds(510, 0, 20, 40);
        rSpinner.setBounds(530, 0, 40, 40);
        startGameButton.setBounds(570, 0, 130, 40);
        // 2nd row
        nextStepButton.setBounds(0, 40, 100, 40);
        next10Steps.setBounds(100, 40, 100, 40);
        next5000Steps.setBounds(200, 40, 100, 40);
        autoModeButton.setBounds(300, 40, 100, 40);
        tickSpinnerLabel.setBounds(400, 40, 110, 40);
        tickSpinner.setBounds(510, 40, 60, 40);
        insertLabel.setBounds(570, 40, 50, 40);
        insertComboBox.setBounds(620, 40, 80, 40);
        // 3rd row
        planePanel.setBounds(0, 80, window_width, window_height - 80);

        // 1st row
        mainFrame.add(neighborhoodComboBox);
        mainFrame.add(gameComboBox);
        mainFrame.add(cellsToBecomeAliveLabel);
        mainFrame.add(cellsToLiveLabel);
        mainFrame.add(cellsToBecomeAliveTextField);
        mainFrame.add(cellsToLiveTextField);
        mainFrame.add(wrapCheckbox);
        mainFrame.add(rLabel);
        mainFrame.add(rSpinner);
        mainFrame.add(startGameButton);
        // 2nd row
        mainFrame.add(nextStepButton);
        mainFrame.add(next10Steps);
        mainFrame.add(next5000Steps);
        mainFrame.add(autoModeButton);
        mainFrame.add(tickSpinnerLabel);
        mainFrame.add(tickSpinner);
        mainFrame.add(insertLabel);
        mainFrame.add(insertComboBox);
        // 3rd row
        mainFrame.add(planePanel);

        WindowListener wndCloser = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };
        mainFrame.addWindowListener(wndCloser);
        mainFrame.setVisible(true);
    }

    private int[] parseValues(String str) {
        int[] result = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            result[i] = Character.getNumericValue(str.charAt(i));
        }
        return result;
    }

    private void startWireWorld(CellNeighborhood neighborhood) {
        UniformStateFactory uniformStateFactory = new UniformStateFactory(WireElectronState.VOID);
        currentGame = new WireWorld(neighborhood, uniformStateFactory, width, height);
    }

    private void startGameOfLife(CellNeighborhood neighborhood) {
        UniformStateFactory uniformStateFactory = new UniformStateFactory(DEAD);
        currentGame = new GameOfLife(neighborhood, uniformStateFactory, width, height, neighborsToNotDie, neighborsToStartLiving);
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
            while (isAuto) {
                try {
                    nextState(1);
                    Thread.sleep(1000 / (int) tickSpinner.getValue());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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

    private void nextState(int steps) {
        for (int i = 0; i < steps; i++) {
            currentGame = currentGame.nextState();
            cellsMap = currentGame.getCells();
        }
        mainFrame.validate();
        mainFrame.repaint();
    }

    private void updateInterface(int gameId) {
        insertComboBox.removeAllItems();
        switch (gameId) {
            case 0:
                insertComboBox.addItem("Cell");
                insertComboBox.addItem("Blinker");
                insertComboBox.addItem("Glider");
                insertComboBox.addItem("Gun");
                insertComboBox.addItem("Engine");
                insertComboBox.setEnabled(true);
                cellsToBecomeAliveTextField.setEnabled(true);
                cellsToLiveTextField.setEnabled(true);
                neighborhoodComboBox.setEnabled(true);
                neighborhoodComboBox.setSelectedIndex(prevNeighborhood);
                break;
            case 1:
                insertComboBox.addItem("Wire");
                insertComboBox.addItem("Electron head");
                insertComboBox.addItem("XOR");
                insertComboBox.setEnabled(true);
                cellsToBecomeAliveTextField.setEnabled(false);
                cellsToLiveTextField.setEnabled(false);
                neighborhoodComboBox.setEnabled(true);
                neighborhoodComboBox.setSelectedIndex(prevNeighborhood);
                break;
            case 2:
                insertComboBox.setEnabled(false);
                cellsToBecomeAliveTextField.setEnabled(false);
                cellsToLiveTextField.setEnabled(false);
                neighborhoodComboBox.setEnabled(false);
                prevNeighborhood = neighborhoodComboBox.getSelectedIndex();
                neighborhoodComboBox.setSelectedIndex(1);
                break;
        }
    }
}
