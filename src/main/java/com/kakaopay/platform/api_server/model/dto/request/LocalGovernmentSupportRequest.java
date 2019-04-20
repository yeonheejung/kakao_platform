package com.kakaopay.platform.api_server.model.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.poi.ss.usermodel.Row;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocalGovernmentSupportRequest {

    @ApiModelProperty(value = "지자체명(기관명)")
    private String region;
    @ApiModelProperty(value = "지원대상")
    private String target;
    @ApiModelProperty(value = "용도")
    private String usage;
    @ApiModelProperty(value = "지원한도")
    private String limit;
    @ApiModelProperty(value = "이차보전")
    private String rate;
    @ApiModelProperty(value = "추천기관")
    private String institute;
    @ApiModelProperty(value = "관리점")
    private String mgmt;
    @ApiModelProperty(value = "취급점")
    private String reception;

    public static LocalGovernmentSupportRequest getLocalGovernmentSupportRequestByRow(Row row) {

        if (row == null) {
            return null;
        }

        String row5;
        try {
            row5 = row.getCell(5).getStringCellValue();
        } catch (Exception e) {
            row5 = String.valueOf(row.getCell(5).getNumericCellValue() *100);
        }
        return new LocalGovernmentSupportRequest(
                row.getCell(1).getStringCellValue(),
                row.getCell(2).getStringCellValue(),
                row.getCell(3).getStringCellValue(),
                row.getCell(4).getStringCellValue(),
                row5,
                row.getCell(6).getStringCellValue(),
                row.getCell(7).getStringCellValue(),
                row.getCell(8).getStringCellValue());
    }
}
