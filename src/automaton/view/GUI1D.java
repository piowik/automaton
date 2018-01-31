package automaton.view;

import automaton.automaton.Automaton;
import automaton.automaton.OneDimAutomaton;
import automaton.coordinates.CellCoordinates;
import automaton.coordinates.Coords1D;
import automaton.factory.UniformStateFactory;
import automaton.neighborhood.OneDimNeighborhood;
import automaton.state.BinaryState;
import automaton.state.CellState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static automaton.state.BinaryState.ALIVE;
import static automaton.state.BinaryState.DEAD;

public class GUI1D {
    private JFrame mainFrame;
    private final GPanel planePanel = new GPanel();
    private Automaton currentGame;
    private final JButton nextStepButton = new JButton("Next step");
    private final JButton allStepsButton = new JButton("All steps");
    private final JButton startGameButton = new JButton("Start");
    private final JLabel ruleLabel = new JLabel("Rule");
    private Map<CellCoordinates, CellState> cellsMap = new HashMap<>();
    private final ArrayList<Map<CellCoordinates, CellState>> mapArrayList = new ArrayList<>();
    private final JTextField ruleTextField = new JTextField("30");
    private final int size = 170;
    private final int stepsToDo = 80;

    private final int window_width = 700;
    private final int window_height = 440;
    private final int ZOOM = 4;
    private int steps;

    public static void main(String[] args) {
        new GUI1D();
    }

    private GUI1D() {
        prepareGUI();
    }

    private void prepareGUI() {
        mainFrame = new JFrame();//creating instance of JFrame
        mainFrame.setTitle("Automaton");
        mainFrame.setSize(window_width, window_height);
        mainFrame.setLayout(null);//using no layout managers

        startGameButton.addActionListener(e -> {
            String ruleText = ruleTextField.getText();
            boolean error = false;
            if (ruleText.isEmpty() || !isInteger(ruleText, 10))
                error = true;
            else {
                int ruleInteger = Integer.parseInt(ruleText);
                if (ruleInteger < 0 || ruleInteger >= 256)
                    error = true;
            }
            if (!error)
                startOneDim();
            else {
                JOptionPane.showMessageDialog(mainFrame, "0 <= rule < 256");
            }
        });
        nextStepButton.addActionListener(e -> {
            if (steps > 0) {
                currentGame = currentGame.nextState();
                cellsMap = currentGame.getCells();
                mapArrayList.add(cellsMap);
                steps--;
                mainFrame.revalidate();
                mainFrame.repaint();
            }
        });
        allStepsButton.addActionListener(e -> {
            for (int i = 0; i < steps; i++) {
                currentGame = currentGame.nextState();
                cellsMap = currentGame.getCells();
                mapArrayList.add(cellsMap);
            }
            steps = 0;
            mainFrame.revalidate();
            mainFrame.repaint();
        });

        ruleLabel.setBounds(0, 0, 30, 40);
        ruleTextField.setBounds(30, 0, 40, 40);
        startGameButton.setBounds(70, 0, 100, 40);
        nextStepButton.setBounds(250, 0, 100, 40);
        allStepsButton.setBounds(350, 0, 100, 40);
        planePanel.setBounds(0, 40, window_width, window_height - 40);

        mainFrame.add(ruleLabel);
        mainFrame.add(ruleTextField);
        mainFrame.add(startGameButton);
        mainFrame.add(nextStepButton);
        mainFrame.add(allStepsButton);
        mainFrame.add(planePanel);

        WindowListener wndCloser = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };
        mainFrame.addWindowListener(wndCloser);
        mainFrame.setVisible(true);
    }

    private void startOneDim() {
        mapArrayList.clear();
        steps = stepsToDo;
        UniformStateFactory uniformStateFactory = new UniformStateFactory(DEAD);
        currentGame = new OneDimAutomaton(new OneDimNeighborhood(size), uniformStateFactory, size, Integer.parseInt(ruleTextField.getText()));
        Map<Coords1D, CellState> structureToInsert = new HashMap<>();
        int centerPos = size / 2;
        structureToInsert.put(new Coords1D(centerPos), BinaryState.ALIVE);
        currentGame.insertStructure(structureToInsert);
        cellsMap = currentGame.getCells();
        mapArrayList.add(cellsMap);
        mainFrame.revalidate();
        mainFrame.repaint();
    }


    class GPanel extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            if (cellsMap.size() != 0) {
                for (int i = 0; i < mapArrayList.size(); i++) {
                    for (int j = 0; j < mapArrayList.get(i).size(); j++) {
                        CellState state = mapArrayList.get(i).get(new Coords1D(j));
                        if (state == DEAD)
                            g.setColor(Color.WHITE);
                        else if (state == ALIVE)
                            g.setColor(Color.BLACK);
                        g.fillRect(j * ZOOM, i * ZOOM, ZOOM, ZOOM);
                    }
                }
            }
        }
    }

    private static boolean isInteger(String s, int radix) {
        if (s.isEmpty()) return false;
        for (int i = 0; i < s.length(); i++) {
            if (i == 0 && s.charAt(i) == '-') {
                if (s.length() == 1) return false;
                else continue;
            }
            if (Character.digit(s.charAt(i), radix) < 0) return false;
        }
        return true;
    }
}