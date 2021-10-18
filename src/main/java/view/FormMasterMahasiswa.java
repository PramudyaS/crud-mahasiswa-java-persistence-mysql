package view;

import controller.DosenJpaController;
import controller.FakultasJpaController;
import controller.MahasiswaJpaController;
import controller.exceptions.NonexistentEntityException;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Dosen;
import model.Fakultas;
import model.Mahasiswa;
import model.TableModelMahasiswa;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FormMasterMahasiswa {
    private JTextField fieldNRP;
    private JTextField fieldNama;
    private JTextField fieldAngkatan;
    private JComboBox fieldFakultas;
    private JComboBox fieldDosen;
    private JButton exitButton;
    private JTable tableMahasiswa;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton saveButton;
    private JPanel mainPanel;
    DefaultTableModel tableModel;

    public TableModelMahasiswa getTableModelMahasiswa() {
        return tableModelMahasiswa;
    }

    private TableModelMahasiswa tableModelMahasiswa;

    public FormMasterMahasiswa() {
        setOptionDosenBox();
        setOptionFakultasBox();
        setUpTableData();

        EntityManagerFactory objFactory = Persistence.createEntityManagerFactory("Tm6MhsaPU");
        MahasiswaJpaController mahasiswaController = new MahasiswaJpaController(objFactory);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mahasiswa mhs = new Mahasiswa();
                mhs.setNrp(fieldNRP.getText());
                mhs.setNama(fieldNama.getText());
                mhs.setAngkatan(fieldAngkatan.getText());
                mhs.setKodeFakultas(fieldFakultas.getSelectedItem().toString());
                mhs.setKodeDosen(fieldDosen.getSelectedItem().toString());
                try {
                    mahasiswaController.create(mhs);

                } catch (Exception ex) {
                    Logger.getLogger(FormMasterMahasiswa.class.getName()).log(Level.SEVERE, null, ex);
                }
                setUpTableData();
            }
        });

        tableMahasiswa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                tableMahasiswaMouseClicked(e);
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EntityManagerFactory manager = Persistence.createEntityManagerFactory("Tm6MhsaPU");

                Mahasiswa tbMahasiswa = mahasiswaController.findMahasiswa(fieldNRP.getText().toString());
                tbMahasiswa.setNrp(getFieldNRP().getText());
                tbMahasiswa.setNama(getFieldNama().getText());
                tbMahasiswa.setAngkatan(getFieldAngkatan().getText());
                tbMahasiswa.setKodeDosen(getFieldDosen().getSelectedItem().toString());
                tbMahasiswa.setKodeFakultas(getFieldFakultas().getSelectedItem().toString());

                getFieldNRP().setText("");
                getFieldNama().setText("");
                getFieldAngkatan().setText("");

                try {
                    mahasiswaController.edit(tbMahasiswa);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                setUpTableData();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mahasiswa tbMahasiswa = mahasiswaController.findMahasiswa(fieldNRP.getText().toString());
                tbMahasiswa.setNrp(getFieldNRP().getText());

                try {
                    mahasiswaController.destroy(getFieldNRP().getText());
                } catch (NonexistentEntityException ex) {
                    ex.printStackTrace();
                }
                setUpTableData();
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public JTable getTableMahasiswa() {
        return tableMahasiswa;
    }


    public JTextField getFieldNRP() {
        return fieldNRP;
    }

    public JTextField getFieldNama() {
        return fieldNama;
    }

    public JTextField getFieldAngkatan() {
        return fieldAngkatan;
    }

    public JComboBox getFieldDosen() {
        return fieldDosen;
    }

    public JComboBox getFieldFakultas() {
        return fieldFakultas;
    }

    private void tableMahasiswaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMahasiswaMouseClicked
        // TODO add your handling code here:
        JTable source = (JTable)evt.getSource();
        int row = source.rowAtPoint(evt.getPoint());
        fieldNama.setText(source.getModel().getValueAt(row, 0)+"");
        fieldNRP.setText(source.getModel().getValueAt(row, 1)+"");
        fieldAngkatan.setText(source.getModel().getValueAt(row, 2)+"");
        fieldDosen.setSelectedItem(source.getModel().getValueAt(row, 3)+"");
        fieldFakultas.setSelectedItem(source.getModel().getValueAt(row, 4)+"");
    }

    private void setOptionDosenBox()
    {
        EntityManagerFactory manager = Persistence.createEntityManagerFactory("Tm6MhsaPU");
        DosenJpaController dosenController = new DosenJpaController(manager);
        List<Dosen> item = dosenController.findDosenEntities();
        for(int i = 0 ;i < item.size();i++)
        {
            fieldDosen.addItem(item.get(i).getKoded());
        }

    }

    private void setOptionFakultasBox() {
        EntityManagerFactory manager = Persistence.createEntityManagerFactory("Tm6MhsaPU");
        FakultasJpaController fakultasController = new FakultasJpaController(manager);
        List<Fakultas> item = fakultasController.findFakultasEntities();
        for(int i = 0 ;i < item.size();i++)
        {
            fieldFakultas.addItem(item.get(i).getKode());
        }
    }

    public void setUpTableData() {
        EntityManagerFactory objFactory = Persistence.createEntityManagerFactory("Tm6MhsaPU");
        MahasiswaJpaController mahasiswaController = new MahasiswaJpaController(objFactory);
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Nama");
        tableModel.addColumn("NRP");
        tableModel.addColumn("Angkatan");
        tableModel.addColumn("Kode Dosen");
        tableModel.addColumn("Kode Fakultas");
        tableMahasiswa.setModel(tableModel);
        List<Mahasiswa> listMahasiswa = mahasiswaController.findMahasiswaEntities();
        for(int i = 0; i < listMahasiswa.size();i++ )
        {
            tableModel.addRow(new String[]{
                    listMahasiswa.get(i).getNama(),
                    listMahasiswa.get(i).getNrp(),
                    listMahasiswa.get(i).getAngkatan(),
                    listMahasiswa.get(i).getKodeDosen(),
                    listMahasiswa.get(i).getKodeFakultas()
            });
        }
        tableMahasiswa.setModel(tableModel);
        tableModel.fireTableDataChanged();
    }

    public static void main(String args[]) {
        JFrame frame = new JFrame("APP");
        frame.setContentPane(new FormMasterMahasiswa().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}
