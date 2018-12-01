package com.tracnghiem.onthi.quang.ontracnghiemthpt.modeltracnghiem;

import java.io.Serializable;

public class CauHoi implements Serializable {
    private int _id;
    private String cauhoi;
    private String dapan_a;
    private String dapan_b;
    private String dapan_c;
    private String dapan_d;
    private String ketqua;
    private String image;
    private int masodekiemtra;
    private String monhoc;
    private String traloi ="";
    public int choiceId =  -1;

    public CauHoi(int _id, String cauhoi, String dapan_a, String dapan_b, String dapan_c, String dapan_d, String ketqua, String image, int masodekiemtra, String monhoc, String traloi, int choiceId) {
        this._id = _id;
        this.cauhoi = cauhoi;
        this.dapan_a = dapan_a;
        this.dapan_b = dapan_b;
        this.dapan_c = dapan_c;
        this.dapan_d = dapan_d;
        this.ketqua = ketqua;
        this.image = image;
        this.masodekiemtra = masodekiemtra;
        this.monhoc = monhoc;
        this.traloi = traloi;
        this.choiceId = choiceId;
    }

    public CauHoi(int _id, String cauhoi, String dapan_a, String dapan_b, String dapan_c, String dapan_d, String ketqua, String image, int masodekiemtra, String monhoc, String traloi) {
        this._id = _id;
        this.cauhoi = cauhoi;
        this.dapan_a = dapan_a;
        this.dapan_b = dapan_b;
        this.dapan_c = dapan_c;
        this.dapan_d = dapan_d;
        this.ketqua = ketqua;
        this.image = image;
        this.masodekiemtra = masodekiemtra;
        this.monhoc = monhoc;
        this.traloi = traloi;
    }

    public CauHoi(int _id, String cauhoi, String dapan_a, String dapan_b, String dapan_c, String dapan_d, String ketqua, String image, int masodekiemtra, String monhoc) {
        this._id = _id;
        this.cauhoi = cauhoi;
        this.dapan_a = dapan_a;
        this.dapan_b = dapan_b;
        this.dapan_c = dapan_c;
        this.dapan_d = dapan_d;
        this.ketqua = ketqua;
        this.image = image;
        this.masodekiemtra = masodekiemtra;
        this.monhoc = monhoc;
    }

    public CauHoi() {

    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getCauhoi() {
        return cauhoi;
    }

    public void setCauhoi(String cauhoi) {
        this.cauhoi = cauhoi;
    }

    public String getDapan_a() {
        return dapan_a;
    }

    public void setDapan_a(String dapan_a) {
        this.dapan_a = dapan_a;
    }

    public String getDapan_b() {
        return dapan_b;
    }

    public void setDapan_b(String dapan_b) {
        this.dapan_b = dapan_b;
    }

    public String getDapan_c() {
        return dapan_c;
    }

    public void setDapan_c(String dapan_c) {
        this.dapan_c = dapan_c;
    }

    public String getDapan_d() {
        return dapan_d;
    }

    public void setDapan_d(String dapan_d) {
        this.dapan_d = dapan_d;
    }

    public String getKetqua() {
        return ketqua;
    }

    public void setKetqua(String ketqua) {
        this.ketqua = ketqua;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getMasodekiemtra() {
        return masodekiemtra;
    }

    public void setMasodekiemtra(int masodekiemtra) {
        this.masodekiemtra = masodekiemtra;
    }

    public String getMonhoc() {
        return monhoc;
    }

    public void setMonhoc(String monhoc) {
        this.monhoc = monhoc;
    }

    public String getTraloi() {
        return traloi;
    }

    public void setTraloi(String traloi) {
        this.traloi = traloi;
    }

    public int getChoiceId() {
        return choiceId;
    }

    public void setChoiceId(int choiceId) {
        this.choiceId = choiceId;
    }
}
