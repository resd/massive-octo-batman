package gui.newjavafx;

import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class FileController {

    void saveInformationFromFormToTextFile(double[][] data) {

        int size = data.length;
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialDirectory(new File("C:/"));

        //Show save file dialog
        File file = fileChooser.showSaveDialog(MainScreen.getStage());
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


        if (file != null) {
            SaveFile(builder.toString(), file);
        }

    }

    private void SaveFile(String content, File file){
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


    List<String> loadDataFromFile() {

        FileChooser fileChooser = new FileChooser();
        String delimeter = "==========================";

        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialDirectory(new File("C:/"));

        //Show save file dialog

        File file = fileChooser.showOpenDialog(MainScreen.getStage());
        List<String> newLines = new ArrayList<String>();

        if (file != null) {

            try {

                java.util.List<String> fileContent = Files.readAllLines(file.toPath(), Charset.forName("UTF-8"));

                int delimeterCounter = 0;

                for (String fileContent1 : fileContent) {

                    System.out.println(fileContent1);

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
                System.out.println(fileChooser.getInitialDirectory().toString());
                //JOptionPane.showMessageDialog(this, "ERROR!!!");
                System.out.println(ex);
                return null;
            }
        }
        return null;
    }

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



}
