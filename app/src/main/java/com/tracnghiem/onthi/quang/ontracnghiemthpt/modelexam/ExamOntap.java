package com.tracnghiem.onthi.quang.ontracnghiemthpt.modelexam;

public class ExamOntap {
    private String nameontap;
    private String img;

    public ExamOntap(String nameontap, String img) {
        this.nameontap = nameontap;
        this.img = img;
    }
    public String getNameontap() {
        return nameontap;
    }

    public void setNameontap(String nameontap) {
        this.nameontap = nameontap;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
