import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ParsingFromZip {

    private ClassLoader cl = ParsingFromZip.class.getClassLoader();

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
                if (entry.getName().endsWith(".xlsx"))
                {
                    XLS xls = new XLS (zis);
                    String actualValue = xls.excel.getSheetAt(0).getRow(2).getCell(2).getStringCellValue();
                    Assertions.assertTrue(actualValue.contains("Петрович"));
                    String actualValueTwo = xls.excel.getSheetAt(0).getRow(2).getCell(1).getStringCellValue();
                    Assertions.assertTrue(actualValueTwo.contains("Петр"));

                }
            }
        }
    }
}

