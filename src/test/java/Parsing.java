import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Parsing {

    private ClassLoader cl = Parsing.class.getClassLoader();
    String csvFileName = "example.csv";
    String xlsxFileName = "easy.xlsx";
    String pdfFileName = "junit-user-guide-5.12.1.pdf";

    @Test
    void csvParsingFromZipFileTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("archive.zip")
        )) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(".csv"))
                {
                    CSVReader csvReader = new CSVReader(new InputStreamReader(zis, StandardCharsets.UTF_8));
                    List<String[]> data = csvReader.readAll();
                    Assertions.assertEquals(2, data.size());
                    Assertions.assertArrayEquals(
                            new String[]{"string", "stroka"},
                            data.get(0)
                    );
                    Assertions.assertArrayEquals(
                            new String[]{"selenide", "power"},
                            data.get(1)
                    );
                }
            }
        }
    }
    @Test
    void pdfParsingFromZipFileTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("archive.zip")
        )) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(".pdf"))
                {
                    PDF pdf = new PDF (zis);
                    Assertions.assertEquals("JUnit 5 User Guide", pdf.title);
                }
            }
        }
    }
    @Test
    void xlsxParsingFromZipFileTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("archive.zip")
        )) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(".xls"))
                {
                    XLS xls = new XLS (zis);
                    Assertions.assertEquals("JUnit 5 User Guide", xls.name);
                }
            }
        }
    }
}

//if (entry.getName().equals(csvFileName)) {
//  System.out.println("Найден CSV-файл: " + entry.getName());
//if (entry.getName().equals(xlsxFileName)) {
//    System.out.println("Найден XLSX-файл: " + entry.getName());
//}
//if (entry.getName().equals(pdfFileName)) {
//    System.out.println("Найден PDF-файл: " + entry.getName());
//}

