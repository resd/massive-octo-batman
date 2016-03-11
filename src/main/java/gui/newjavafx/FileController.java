package gui.newjavafx;

import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Admin
 * @since 11.08.15
 * lastModify on 29.02.16
 */
public class FileController {

    private String addToSaveFile = "";
    private final String delimiter = "==========================";

    /**
     * Save data function
     */
    public void saveInformationFromFormToTextFile(double[][] data) {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialDirectory(new File("C:/data/"));

        //Show save file dialog
        File file = fileChooser.showSaveDialog(MainScreen.getStage());

        saveInformationFromFormToTextFile(data, file);

    }

    @SuppressWarnings("unused") // TODO Safe delete
    public void saveInformationFromFormToTextFile(double[][] data, String file) {

        saveInformationFromFormToTextFile(data, new File(file));

    }

    public void saveInformationFromFormToTextFile(double[][] data, boolean saveLastMatrix) {

        if (saveLastMatrix)
            saveInformationFromFormToTextFile(data, new File("C:/data/lastMatrix.txt"));
        else saveInformationFromFormToTextFile(data);

    }

    private void saveInformationFromFormToTextFile(double[][] data, File file) {

        if (file == null) return;
        int size = data.length;

        java.util.Date date = new java.util.Date(System.currentTimeMillis());

        StringBuilder builder = new StringBuilder();

        builder.append(System.lineSeparator());
        builder.append(delimiter);
        builder.append(System.lineSeparator());
        builder.append(date.toString());
        builder.append(System.lineSeparator());
        builder.append(delimiter);

        for (int i = 0; i < size; i++) {

            String s = "";

            for (int j = 0; j < size; j++) {
                if (i != j) {
                    s = s + " " + data[i][j];
                } else {
                    s = s + " -";
                }

            }

            builder.append(System.lineSeparator());
            builder.append(s);

        }

        builder.append(System.lineSeparator());
        builder.append(delimiter);
        builder.append(System.lineSeparator());

        SaveFile(builder.toString(), file);

    }

    /**
     * Save multi data function
     */
    public void saveMultiInformationFromFormToTextFile(ControllerMain controllerMain) {
        // Получение массива строк методов в заданном пользователем порядке
        ArrayList<String> methodsOrder = controllerMain.getMethodOrder();
        if (methodsOrder.size() == 0) {
            return;
        }

        String str = controllerMain.multiSolveCount.textProperty().getValue();
        int multiSolveCountInt = Integer.parseInt(str);

        FileChooser fileChooser = new FileChooser();
        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialDirectory(new File("C:/data/"));
        //Show save file dialog
        File file = fileChooser.showSaveDialog(MainScreen.getStage());
        if (file == null) return;

        StringBuilder builder = new StringBuilder();

        String matrixSizeStr = controllerMain.matrixSize.textProperty().getValue();
        int matrixSizeInt = Integer.parseInt(matrixSizeStr);
        builder.append(multiSolveCountInt);
        builder.append(System.lineSeparator());

        //noinspection unused // TODO Safe delete?
        int size = ControllerMain.getTableViewController().getMatrixSize();
        for (int tempI = 0; tempI < multiSolveCountInt; tempI++) {
            controllerMain.btnFill.fire();
            double[][] data = controllerMain.getMatrix();
            builder.append(delimiter);
            builder.append(System.lineSeparator());

            for (int i = 0; i < matrixSizeInt; i++) {
                String s = "";
                for (int j = 0; j < matrixSizeInt; j++) {

                    if (i != j) {
                        s = s + " " + data[i][j];
                    } else {
                        s = s + " -";
                    }

                }
                builder.append(s);
                builder.append(System.lineSeparator());
            }

        }

        SaveFile(builder.toString(), file);

    }

    private void SaveFile(String content, File file) {
        try {
            FileWriter fileWriter;

            fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException ex) {
            System.out.println("Ошибка! Нет такого файла!");
            ex.printStackTrace();
            //Logger.getLogger(JavaFX_Text.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void autoSaveInformationFromFormToTextFile(double[][] data) {
        int size = data.length;

        //Show save file dialog
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        String localTimeString = localTime.getHour() + "-" +
                localTime.getMinute() + "-" + localTime.getSecond() + "-" + localTime.getNano();
        String localDateTime = localDate.toString() + " T " + localTimeString;
        File file = new File("C:/data/" + size);
        if (!file.exists()) //noinspection ResultOfMethodCallIgnored
            file.mkdir(); // Don't need to know result
        file = new File(file, size + "  " + localDateTime + getAddToSaveFile() + ".txt");
        System.out.println(file);

        java.util.Date date = new java.util.Date(System.currentTimeMillis());

        StringBuilder builder = new StringBuilder();

        builder.append(System.lineSeparator());
        builder.append(delimiter);
        builder.append(System.lineSeparator());
        builder.append(date.toString());
        builder.append(System.lineSeparator());
        builder.append(delimiter);

        for (int i = 0; i < size; i++) {
            String s = "";
            for (int j = 0; j < size; j++) {
                if (i != j) {
                    s = s + " " + data[i][j];
                } else {
                    s = s + " -";
                }
            }
            builder.append(System.lineSeparator());
            builder.append(s);
        }
        builder.append(System.lineSeparator());
        builder.append(delimiter);
        builder.append(System.lineSeparator());

        SaveFile(builder.toString(), file);
    }


    /**
     * Load data from file
     */
    public List<String> loadDataFromFile() {

        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialDirectory(new File("C:/data//"));

        //Show save file dialog

        File file = fileChooser.showOpenDialog(MainScreen.getStage());

        if (file != null) {
            return loadOneFile(file);
        }
        return null;
    }

    //
    @SuppressWarnings("unused") // In case it will be need
    public List<String> loadDataFromFile(String fileString) {
        File file = new File(fileString);

        return loadOneFile(file);
    }

    public List<String> loadDataFromFile(boolean loadLastMatrix) {
        if (loadLastMatrix) {
            File file = new File("C:/data/lastMatrix.txt");

            return loadOneFile(file);
        } else {
            return loadDataFromFile();
        }

    }

    private List<String> loadOneFile(File file) {
        List<String> newLines = new ArrayList<>();
        try {

            java.util.List<String> fileContent = Files.readAllLines(file.toPath(), Charset.forName("UTF-8"));

            int delimiterCounter = 0;

            for (String fileContent1 : fileContent) {

                //System.out.println(fileContent1);

                if (fileContent1.equals(delimiter)) {
                    delimiterCounter++;
                    continue;
                }

                if (delimiterCounter == 2) {
                    newLines.add(fileContent1);
                }

                if (delimiterCounter > 2) {
                    break;
                }
            }

            return newLines;
        } catch (Exception ex) {
            //System.out.println(fileChooser.getInitialDirectory().toString());
            //JOptionPane.showMessageDialog(this, "ERROR!!!");
            ex.printStackTrace();
            return null;
        }
    }


    /**
     * Load multi data from file
     */
    public List<List<String>> loadMultiDataFromFile() {

        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialDirectory(new File("C:/data/"));

        //Show save file dialog

        File file = fileChooser.showOpenDialog(MainScreen.getStage());

        if (file != null) {
            return loadMultiFile(file);
        }
        return null;
    }

    private List<List<String>> loadMultiFile(File file) {
        List<List<String>> newLines = new ArrayList<>();
        try {
            java.util.List<String> fileContent = Files.readAllLines(file.toPath(), Charset.forName("UTF-8"));
            List<String> nextList = new ArrayList<>();

            for (String thisLine : fileContent) {
                //System.out.println(thisLine);

                if (thisLine.equals(delimiter)) {
                    newLines.add(nextList);
                    nextList = new ArrayList<>();
                } else {
                    nextList.add(thisLine);
                }

            }
            newLines.add(nextList);

            return newLines;
        } catch (Exception ex) {
            //System.out.println(fileChooser.getInitialDirectory().toString());
            //JOptionPane.showMessageDialog(this, "ERROR!!!");
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Additional function
     */
    public double[][] parseStringFromFile(List<String> fileContent1) {

        String[] lines = fileContent1.toArray(new String[fileContent1.size()]);

        int i = 0;
        int j = 0;
        double[][] newValues = new double[lines.length][lines.length];

        for (String line : lines) {

            String values[] = line.split(" ");
            for (String value : values) {

                if (value.equals(" ") || value.equals("")) {
                    continue;
                }

                double newValue = 0;

                if (value.equals("-")) {
                    newValue = 0;
                } else {
                    newValue = Double.parseDouble(value);
                }

                newValues[i][j] = newValue;
                j++;
            }
            j = 0;
            i++;
        }

        return newValues;
    }

    public void setAddToSaveFile(String addToSaveFile) {
        this.addToSaveFile = addToSaveFile;
    }

    private String getAddToSaveFile() {
        String returnString = addToSaveFile;
        addToSaveFile = "";
        return returnString;
    }
}
