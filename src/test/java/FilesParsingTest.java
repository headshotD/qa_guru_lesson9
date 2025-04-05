//import com.codeborne.pdftest.PDF;
//import com.opencsv.CSVReader;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//
//import java.io.File;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.List;
//import java.util.zip.ZipEntry;
//import java.util.zip.ZipInputStream;
//
//import static com.codeborne.selenide.Selenide.$;
//import static com.codeborne.selenide.Selenide.open;
//
//class FilesParsingTest {
//
//    private ClassLoader cl = FilesParsingTest.class.getClassLoader();
//
//    @Test
//    void pdfParsingTest() throws Exception {
//        open("https://junit.org/junit5/docs/current/user-guide/");
//        File downloaded = $("[href*='junit-user-guide-5.12.1.pdf']").download();
//        PDF pdf = new PDF(downloaded);
//        Assertions.assertEquals("Stefan Bechtold, Sam Brannen, Johannes Link, Matthias Merdes, Marc Philipp, Juliette de Rancourt, Christian Stein", pdf.author);
//
//    }
//
////    @Test
////    void xlsParsingTest() throws Exception {
////        open("");
////        File downloaded = $("[href*='']").download();
////        XLS xls = new XLS(downloaded);
////        //Assertions.assertEquals("", XLS.containsText());
////    }
//
//    @Test
//    void csvFileParsingTest() throws Exception {
//        try (InputStream is = getClass().getResourceAsStream("example.csv");
//             CSVReader csvReader = new CSVReader(new InputStreamReader(is))) {
//            List<String[]> data = csvReader.readAll();
//            Assertions.assertEquals(2, data.size());
//            Assertions.assertArrayEquals(new String[]{"1", "String"}, data.get(0));
//            Assertions.assertArrayEquals(new String[]{"1", "Selenide"}, data.get(1));
//        }
//    }
//
//
//    @Test
//    void zipFileParsingTest() throws Exception {
//        try (ZipInputStream zis = new ZipInputStream(
//                cl.getResourceAsStream("Dekstop.zip")
//        )) {
//            ZipEntry entry;
//
//            while ((entry = zis.getNextEntry()) != null) {
//                System.out.println(entry.getName());
//            }
//        }
//    }
//}
//
//
//
//
//
//@Test
//void zipFileParsingTest() throws Exception {
//    try (InputStream is = getClass().getResourceAsStream("example.csv");
//         CSVReader csvReader = new CSVReader(new InputStreamReader(is))) {
//        List<String[]> data = csvReader.readAll();
//        Assertions.assertEquals(2, data.size());
//        Assertions.assertArrayEquals(new String[]{"1", "String"}, data.get(0));
//        Assertions.assertArrayEquals(new String[]{"1", "Selenide"}, data.get(1));
//    }
//}
//@Test
//void jsonFilePasringTest() throws Exception {
//
//}
//
//}
