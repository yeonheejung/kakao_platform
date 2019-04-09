package com.kakaopay.platform.api_server.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
@Component
public class ExcelUtil {

    public static<T> List<T> readFileToList(final MultipartFile multipartFile,
                                      final Function<Row, T> rowFunc) throws IOException, InvalidFormatException {

        final Workbook workbook = readWorkbook(multipartFile);
        final Sheet sheet = workbook.getSheetAt(0);
        final int rowCount = sheet.getPhysicalNumberOfRows();

        // TODO: null 일때 처
        return IntStream
                .range(1, rowCount)
                .mapToObj(rowIndex -> rowFunc.apply(sheet.getRow(rowIndex)))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public static Workbook readWorkbook(MultipartFile multipartFile) throws IOException, InvalidFormatException {
        verifyFileExtension(multipartFile);
        return multipartFileToWorkbook(multipartFile);
    }

    public static void verifyFileExtension(MultipartFile multipartFile) throws InvalidFormatException {
        if (!isExcelExtension(multipartFile.getOriginalFilename())) {
            throw new InvalidFormatException("This file extension is not verify");
        }
    }

    private static boolean isExcelExtension(String fileName) {
        return fileName.endsWith("xls") || fileName.endsWith("xlsx");
    }

    private static boolean isExcelXls(String fileName) {
        return fileName.endsWith("xls");
    }

    private boolean isExcelXlsx(String fileName) {
        return fileName.endsWith("xlsx");
    }

    private static Workbook multipartFileToWorkbook(MultipartFile multipartFile)
            throws IOException {
        if (isExcelXls(multipartFile.getOriginalFilename())) {
            return new HSSFWorkbook(multipartFile.getInputStream());
        } else {
            return new XSSFWorkbook(multipartFile.getInputStream());
        }
    }


}
