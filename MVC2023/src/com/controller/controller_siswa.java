/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;
import com.view.form_siswa;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Keytzh
 */
public interface controller_siswa {
    public void Simpan(form_siswa siswa) throws SQLException;
    public void Ubah(form_siswa siswa) throws SQLException;
    public void Hapus(form_siswa siswa) throws SQLException;
    public void Tampil(form_siswa siswa) throws SQLException;
    public void Baru(form_siswa siswa) throws SQLException;
    public void KlikTabel(form_siswa siswa) throws SQLException;
}
