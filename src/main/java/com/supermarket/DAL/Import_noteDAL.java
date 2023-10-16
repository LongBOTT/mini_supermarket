package com.supermarket.DAL;

import com.supermarket.DTO.Import;
import com.supermarket.utils.Date;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Import_noteDAL extends Manager{
    public Import_noteDAL() {
        super("import_note",
            List.of("id",
                "staff_id",
                "received_date",
                "total",
                "supplier_id",
                "deleted"));
    }

    public List<Import> convertToImport_note(List<List<String>> data) {
        return convert(data, row -> {
            try {
                return new Import(
                    Integer.parseInt(row.get(0)),
                    Integer.parseInt(row.get(1)),
                    Date.parseDate(row.get(2)),
                    Double.parseDouble(row.get(3)),
                    Integer.parseInt(row.get(4)),
                    Boolean.parseBoolean(row.get(5))
                );
            } catch (Exception e) {
                System.out.println("Error occurred in Import_noteDAL.convertToImport_note(): " + e.getMessage());
            }
            return new Import();
        });
    }

    public int addImport(Import importNote) {
        try {
            return create(importNote.getId(),
                importNote.getStaff_id(),
                importNote.getReceived_date(),
                importNote.getTotal(),
                importNote.getSupplier_id(),
                false
            ); // import_note khi tạo mặc định deleted = 0
        } catch (SQLException | IOException e) {
            System.out.println("Error occurred in Import_noteDAL.addImport_note(): " + e.getMessage());
        }
        return 0;
    }

    public int updateImportnote(Import importnote) {
        try {
            List<Object> updateValues = new ArrayList<>();
            updateValues.add(importnote.getId());
            updateValues.add(importnote.getStaff_id());
            updateValues.add(importnote.getReceived_date());
            updateValues.add(importnote.getTotal());
            updateValues.add(importnote.getSupplier_id());
            return update(updateValues, "id = " + importnote.getId());
        } catch (SQLException | IOException e) {
            System.out.println("Error occurred in Import_noteDAL.updateImportnote(): " + e.getMessage());
        }
        return 0;
    }

    public int deleteImport_note(String... conditions) {
        try {
            List<Object> updateValues = new ArrayList<>();
            updateValues.add(true);
            return update(updateValues, conditions);
        } catch (SQLException | IOException e) {
            System.out.println("Error occurred in Import_noteDAL.deleteImportnote(): " + e.getMessage());
        }
        return 0;
    }

    public List<Import> searchImport_note(String... conditions) {
        try {
            return convertToImport_note(read(conditions));
        } catch (SQLException | IOException e) {
            System.out.println("Error occurred in Import_noteDAL.searchImport_note(): " + e.getMessage());
        }
        return new ArrayList<>();
    }
}
