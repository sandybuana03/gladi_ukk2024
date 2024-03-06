/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.model;
import com.controller.controller_siswa;
import com.koneksi.koneksi;
import com.view.form_siswa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;


public class model_siswa implements controller_siswa {
    String jk;

    @Override
    public void Simpan(form_siswa siswa) throws SQLException {
        if(siswa.rbLaki.isSelected()){
            jk = "Laki-Laki";
        }
        else {
            jk = "Perempuan";
        }
        try {
            Connection con = koneksi.getcon();
            String sql = "insert into siswa values(?,?,?,?)";
            PreparedStatement prepare = con.prepareStatement(sql);
            prepare.setString(1, siswa.txtNIS.getText());
            prepare.setString(2, siswa.txtNama.getText());
            prepare.setString(3, jk);
            prepare.setString(4, (String) siswa.cbJurusan.getSelectedItem());
            prepare.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Tersimpan");
            prepare.close();
            Baru(siswa);
        }
        catch (Exception e){
            System.out.println(e);
        }
        finally{
            Tampil(siswa);
            siswa.setLebarKolom();
        }
    }

    @Override
    public void Ubah(form_siswa siswa) throws SQLException {
        if(siswa.rbLaki.isSelected()){
            jk = "Laki-Laki";
        }
        else {
            jk = "Perempuan";
        }
        try {
            Connection con = koneksi.getcon();
            String sql = "update siswa set nama=?, jenis_kelamin=?,"
                    + "jurusan=? where NIS=?";
            PreparedStatement prepare = con.prepareStatement(sql);
            prepare.setString(4, siswa.txtNIS.getText());
            prepare.setString(1, siswa.txtNama.getText());
            prepare.setString(2, jk);
            prepare.setString(3, (String) siswa.cbJurusan.getSelectedItem());
            prepare.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
            prepare.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
        finally{
            Tampil(siswa);
            siswa.setLebarKolom();
            Baru(siswa);
        }
    }

    @Override
    public void Hapus(form_siswa siswa) throws SQLException {
        try {
            Connection con = koneksi.getcon();
            String sql = "delete from siswa where NIS = ?";
            PreparedStatement prepare = con.prepareStatement(sql);
            prepare.setString(1, siswa.txtNIS.getText());
            prepare.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
            prepare.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        finally {
            Tampil(siswa);
            siswa.setLebarKolom();
            Baru(siswa);
        }
    }

    @Override
    public void Tampil(form_siswa siswa) throws SQLException {
        siswa.tblmodel.getDataVector().removeAllElements();
        siswa.tblmodel.fireTableDataChanged();
        try {
            Connection con = koneksi.getcon();
            Statement stt = con.createStatement();
            String sql = "select * from siswa order by NIS asc";
            ResultSet res = stt.executeQuery(sql);
            while(res.next()){
                Object[] ob = new Object[8];
                ob[0] = res.getString(1);
                ob[1] = res.getString(2);
                ob[2] = res.getString(3);
                ob[3] = res.getString(4);
                siswa.tblmodel.addRow(ob);
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    @Override
    public void Baru(form_siswa siswa) throws SQLException {
        siswa.txtNIS.setText("");
        siswa.txtNama.setText("");
        siswa.rbLaki.setSelected(true);
        siswa.cbJurusan.setSelectedIndex(0);
    }

    @Override
    public void KlikTabel(form_siswa siswa) throws SQLException {
        try {
            int pilih = siswa.tabel.getSelectedRow();
            if (pilih == -1){
                return;
            }
siswa.txtNIS.setText(siswa.tblmodel.getValueAt(pilih, 0).toString());
siswa.txtNama.setText(siswa.tblmodel.getValueAt(pilih, 1).toString());
siswa.cbJurusan.setSelectedItem(siswa.tblmodel.getValueAt(pilih, 3).
        toString());
        jk = String.valueOf(siswa.tblmodel.getValueAt(pilih, 2));
        }
        catch (Exception e){
        }
        if (siswa.rbLaki.getText().equals(jk)){
            siswa.rbLaki.setSelected(true);
        }
        else {
            siswa.rbPerempuan.setSelected(true);
        }
    }
}
