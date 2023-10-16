package com.supermarket.DAL;

import com.supermarket.DTO.Export;
import com.supermarket.utils.Date;
import org.apache.poi.ddf.EscherColorRef;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Export_noteDAL extends Manager{
    public Export_noteDAL() {
        super("export_note",
            List.of("id",
                "staff_id",
                "invoice_date",
                "total",
                "reason",
                "deleted"));
    }

    public List<Export> convertToExport_note(List<List<String>> data) {
        return convert(data, row -> {
            try {
                return new Export(
                    Integer.parseInt(row.get(0)),
                    Integer.parseInt(row.get(1)),
                    Date.parseDate(row.get(2)),
                    Double.parseDouble(row.get(3)),
                    row.get(4),
                    Boolean.parseBoolean(row.get(5))
                );
            } catch (Exception e) {
                System.out.println("Error occurred in Export_noteDAL.convertToExport_note(): " + e.getMessage());
            }
            return new Export();
        });
    }

    public int addExport(Export exportNote) {
        try {
            return create(exportNote.getId(),
                exportNote.getStaff_id(),
                exportNote.getInvoice_date(),
                exportNote.getTotal(),
                exportNote.getReason(),
                false
            ); // export_detail khi tạo mặc định deleted = 0
        } catch (SQLException | IOException e) {
            System.out.println("Error occurred in Export_noteDAL.addExport_note(): " + e.getMessage());
        }
        return 0;
    }

    public int updateExport_note(Export export) {
        try {
            List<Object> updateValues = new ArrayList<>();
            updateValues.add(export.getId());
            updateValues.add(export.getStaff_id());
            updateValues.add(export.getInvoice_date());
            updateValues.add(export.getTotal());
            updateValues.add(export.getReason());
            return update(updateValues, "id = " + export.getId());
        } catch (SQLException | IOException e) {
            System.out.println("Error occurred in Export_noteDAL.updateExport_note(): " + e.getMessage());
        }
        return 0;
    }

    public int deleteExport_note(String... conditions) {
        try {
            List<Object> updateValues = new ArrayList<>();
            updateValues.add(true);
            return  update(updateValues,conditions);
        } catch (SQLException | IOException e) {
            System.out.println("Error occurred in Export_noteDAL.deleteExport_note(): " + e.getMessage());
        }
        return 0;
    }
    public List<Export> searchExport_note(String... conditions) {
        try {
            return convertToExport_note(read(conditions));
        } catch (SQLException | IOException e) {
            System.out.println("Error occurred in Export_noteDAL.searchExport_note(): " + e.getMessage());
        }
        return new ArrayList<>();
    }
}
