package model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TableModelMahasiswa extends AbstractTableModel {
    private static final long serialVersionUID = 1L;

    private List<Mahasiswa> list = new ArrayList();

    public Mahasiswa select(int index) {
        return (Mahasiswa) list.get(index);
    }

    public void updateAll(Collection mahasiswa) {
        list.clear();
        list.addAll(mahasiswa);
        fireTableDataChanged();
    }

    public void insert(Mahasiswa tbMahasiswa) {
        list.add(tbMahasiswa);
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }

    public void delete(int index) {
        list.remove(index);
        fireTableRowsDeleted(index, index);
    }

    public void update(int index, Mahasiswa tbMahasiswa) {
        list.set(index, tbMahasiswa);
        fireTableRowsUpdated(index, index);
    }

    public int getRowCount() {
        return list.size();
    }

    public int getColumnCount() {
        return 4;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return list.get(rowIndex).getNrp();
            case 1:
                return list.get(rowIndex).getNama();
            case 2:
                return list.get(rowIndex).getAngkatan();
            case 3:
                return list.get(rowIndex).getKodeDosen();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Nrp";
            case 1:
                return "Nama";
            case 2:
                return "Angkatan";
            case 3:
                return "Asal Sekolah";
            default:
                return null;
        }
    }
}
