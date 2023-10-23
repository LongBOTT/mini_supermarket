package com.supermarket.BLL;

import com.supermarket.DAL.Export_noteDAL;
import com.supermarket.DTO.Export;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class Export_noteBLL extends Manager<Export> {
    private Export_noteDAL exportNoteDAL;
    private List<Export> exportList;

    public Export_noteBLL() {
        exportNoteDAL = new Export_noteDAL();
        exportList = searchExport("deleted = 0");
    }

    public Export_noteDAL getExportNoteDAL() {
        return exportNoteDAL;
    }

    public void setExportNoteDAL(Export_noteDAL exportNoteDAL) {
        this.exportNoteDAL = exportNoteDAL;
    }

    public List<Export> getImportList() {
        return exportList;
    }

    public void setExportList(List<Export> exportList) {
        this.exportList = exportList;
    }

    public Object[][] getData() {
        return getData(exportList);
    }

    public boolean addExport(Export export) {
        exportList.add(export);
        return exportNoteDAL.addExport(export) != 0;
    }

    public boolean updateExport(Export export) {
        exportList.set(getIndex(export, "id", exportList), export);
        return exportNoteDAL.updateExport_note(export) != 0;
    }

    public boolean deleteExport(Export export) {
        exportList.remove(getIndex(export, "id",exportList));
        return exportNoteDAL.deleteExport_note("id = " + export.getId()) != 0;
    }

    public List<Export> searchExport(String... conditions) {
        return exportNoteDAL.searchExport_note(conditions);
    }

    public List<Export> findExport(String key, String value) {
        List<Export> list = new ArrayList<>();
        for (Export export : exportList) {
            if (getValueByKey(export, key).toString().toLowerCase().contains(value.toLowerCase())) {
                list.add(export);
            }
        }
        return list;
    }

    public List<Export> findExportBy(Map<String, Object> conditions) {
        List<Export> exports = exportList;
        for (Map.Entry<String, Object> entry : conditions.entrySet())
            exports = findObjectsBy(entry.getKey(), entry.getValue(), exports);
        return exports;
    }

    public boolean exists(Export exports) {
        return !findExportBy(Map.of(
            "staff id", exports.getStaff_id(),
            "invoice date", exports.getInvoice_date(),
            "total", exports.getTotal(),
            "reason", exports.getReason()
        )).isEmpty();
    }

    public boolean exists(Map<String, Object> conditions) {
        return !findExportBy(conditions).isEmpty();
    }

    @Override
    public Object getValueByKey(Export exports, String key) {
        return switch (key) {
            case "id" -> exports.getId();
            case "staff id" -> exports.getStaff_id();
            case "invoice date" -> exports.getInvoice_date();
            case "total" -> exports.getTotal();
            case "reason" -> exports.getReason();
            default -> null;
        };
    }
}
