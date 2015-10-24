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

@SuppressWarnings("all")
public class FileController {

    private String addToSaveFile = "";

    /**
     *  Save data function
     */
    void saveInformationFromFormToTextFile(double[][] data) {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialDirectory(new File("C:/data/"));

        //Show save file dialog
        File file = fileChooser.showSaveDialog(MainScreen.getStage());

        saveInformationFromFormToTextFile(data, file);

    }

    void saveInformationFromFormToTextFile(double[][] data, String file) {

        saveInformationFromFormToTextFile(data, new File(file));

    }

    void saveInformationFromFormToTextFile(double[][] data, boolean saveLastMatrix) {

        if (saveLastMatrix)
            saveInformationFromFormToTextFile(data, new File("C:/data/lastMatrix.txt"));
        else saveInformationFromFormToTextFile(data);

    }

    private void saveInformationFromFormToTextFile(double[][] data, File file) {

        if (file == null) return;
        int size = data.length;

        java.util.Date date = new java.util.Date(System.currentTimeMillis());

        StringBuilder builder = new StringBuilder();
        String delimeter = "==========================";

        builder.append(System.lineSeparator());
        builder.append(delimeter);
        builder.append(System.lineSeparator());
        builder.append(date.toString());
        builder.append(System.lineSeparator());
        builder.append(delimeter);

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
        builder.append(delimeter);
        builder.append(System.lineSeparator());

        SaveFile(builder.toString(), file);

    }

    /**
     *  Save multi data function
     */
    void saveMultiInformationFromFormToTextFile(ControllerMain controllerMain) {
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
        String delimeter = "==========================";

        String matrixSizeStr = controllerMain.matrixSize.textProperty().getValue();
        int matrixSizeInt = Integer.parseInt(matrixSizeStr);
        builder.append(multiSolveCountInt);
        builder.append(System.lineSeparator());

        int size = controllerMain.getTableViewController().getMatrixSize();
        for (int tempI = 0; tempI < multiSolveCountInt; tempI++) {
            controllerMain.btnFill.fire();
            double[][] data = controllerMain.getMatrix();
            builder.append(delimeter);
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

        if (file != null) {
            SaveFile(builder.toString(), file);
        }

    }

    private void SaveFile(String content, File file) {
        try {
            FileWriter fileWriter = null;

            fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException ex) {
            System.out.println("Ошибка! Нет такого файла!");
            ex.printStackTrace();
            //Logger.getLogger(JavaFX_Text.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void autoSaveInformationFromFormToTextFile(double[][] data) {
        int size = data.length;

        //Show save file dialog
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        String localTimeString = localTime.getHour() + "-" +
                localTime.getMinute() + "-" + localTime.getSecond() + "-" + localTime.getNano();
        String localDateTime = localDate.toString() + " T " + localTimeString;
        File file = new File("C:/data/" + size);
        if (!file.exists()) file.mkdir();
        file = new File(file, size + "  " + localDateTime + getAddToSaveFile() + ".txt");
        System.out.println(file);
        if (file == null) return;

        java.util.Date date = new java.util.Date(System.currentTimeMillis());

        StringBuilder builder = new StringBuilder();
        String delimeter = "==========================";

        builder.append(System.lineSeparator());
        builder.append(delimeter);
        builder.append(System.lineSeparator());
        builder.append(date.toString());
        builder.append(System.lineSeparator());
        builder.append(delimeter);

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
        builder.append(delimeter);
        builder.append(System.lineSeparator());

        SaveFile(builder.toString(), file);
    }


    /**
     *  Load data from file
     */
    List<String> loadDataFromFile() {

        FileChooser fileChooser = new FileChooser();
        String delimeter = "==========================";

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
    List<String> loadDataFromFile(String fileString) {
        File file = new File(fileString);

        if (file != null) {
            return loadOneFile(file);
        }
        return null;
    }

    List<String> loadDataFromFile(boolean loadLastMatrix) {
        if (loadLastMatrix) {
            File file = new File("C:/data/lastMatrix.txt");

            if (file != null) {
                return loadOneFile(file);
            }
            return null;
        } else {
            return loadDataFromFile();
        }

    }

    private List<String> loadOneFile(File file) {
        String delimeter = "==========================";
        List<String> newLines = new ArrayList<String>();
        try {

            java.util.List<String> fileContent = Files.readAllLines(file.toPath(), Charset.forName("UTF-8"));

            int delimeterCounter = 0;

            for (String fileContent1 : fileContent) {

                //System.out.println(fileContent1);

                if (fileContent1.equals(delimeter)) {
                    delimeterCounter++;
                    continue;
                }

                if (delimeterCounter == 2) {
                    newLines.add(fileContent1);
                }

                if (delimeterCounter > 2) {
                    break;
                }
            }

            return newLines;
        } catch (Exception ex) {
            //System.out.println(fileChooser.getInitialDirectory().toString());
            //JOptionPane.showMessageDialog(this, "ERROR!!!");
            System.out.println(ex);
            return null;
        }
    }




    /**
     *  Load multi data from file
     */
    List<List<String>> loadMultiDataFromFile(ControllerMain controllerMain) {

        FileChooser fileChooser = new FileChooser();
        String delimeter = "==========================";

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
        String delimeter = "==========================";
        List<List<String>> newLines = new ArrayList<List<String>>();
        try {
            java.util.List<String> fileContent = Files.readAllLines(file.toPath(), Charset.forName("UTF-8"));
            List<String> nextList = new ArrayList<>();

            for (int i = 0; i < fileContent.size(); i++) {
                String thisLine = fileContent.get(i);

                //System.out.println(thisLine);

                if (thisLine.equals(delimeter)) {
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
            System.out.println(ex);
            return null;
        }
    }

    /**
     *  Additional function
     */
    double[][] parseStringFromFile(List<String> fileContent1) {

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

    public String getAddToSaveFile() {
        String returnString = addToSaveFile;
        addToSaveFile = "";
        return returnString;
    }
}
