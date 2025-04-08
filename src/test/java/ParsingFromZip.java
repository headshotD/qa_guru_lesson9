import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.junit.jupiter.api.Assertions.*;


public class ParsingFromZip {

    private ClassLoader cl = ParsingFromZip.class.getClassLoader();

    private InputStream getFileFromZip(String zipFileName, String fileExtension) throws IOException {
        try (InputStream is = cl.getResourceAsStream(zipFileName);
             ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(fileExtension)) {
                    return new ByteArrayInputStream(zis.readAllBytes());
                }
            }
        }
        return null;
    }

    @DisplayName("Проверка контента в PDF файле")
    @Test
    void pdfParsingFromZipFileTest() throws Exception {
        try (InputStream is = getFileFromZip("archive.zip", ".pdf")) {
            assertNotNull(is, "Файл .pdf не найден");
            PDF pdf = new PDF(is);
            Assertions.assertEquals("JUnit 5 User Guide", pdf.title);
        }
    }

    @DisplayName("Проверка контента в XLSX файле")
    @Test
    void xlsxParsingFromZipFileTest() throws Exception {
        try (InputStream is = getFileFromZip("archive.zip", ".xlsx")) {
            assertNotNull(is, "Файл .xlsx не найден");

            XLS xls = new XLS(is.readAllBytes());

            String actualValue = xls.excel.getSheetAt(0).getRow(2).getCell(2).getStringCellValue();
            assertTrue(actualValue.contains("Петрович"));
            String actualValueTwo = xls.excel.getSheetAt(0).getRow(2).getCell(1).getStringCellValue();
            assertTrue(actualValueTwo.contains("Петр"));

        }
    }

    @DisplayName("Проверка контента в CSV файле")
    @Test
    void parseCsvWithTest() throws Exception {
        try (InputStream is = getFileFromZip("archive.zip", ".csv");
             InputStreamReader reader = new InputStreamReader(is, StandardCharsets.UTF_8);
             CSVReader csvReader = new CSVReader(reader)) {
            assertNotNull(is, "Файл .csv не найден");

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

    @DisplayName("Проверка, что файла XLSX нет в архиве")
    @Test
    void xlsxParsingFromEmptyZipFileTest() throws Exception {
        try (InputStream is = getFileFromZip("pysto.zip", ".xlsx")) {
            assertNull(is, "Файл .xlsx не должен быть в архиве");
        }
    }

    @DisplayName("Проверка, что файла PDF нет в архиве")
    @Test
    void pdfParsingFromEmptyZipFileTest() throws Exception {
        try (InputStream is = getFileFromZip("pysto.zip", ".pdf")) {
            assertNull(is, "Файл .pdf не должен быть в архиве");
        }
    }

    @DisplayName("Проверка, что файла CSV нет в архиве")
    @Test
    void csvParsingFromEmptyZipFileTest() throws Exception {
        try (InputStream is = getFileFromZip("pysto.zip", ".xlsx")) {
            assertNull(is, "Файл .xlsx не должен быть в архиве");
        }
    }
}