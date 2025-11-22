CREATE TABLE BAI_TAP
(
    ma_bt          INT PRIMARY KEY,
    ten_bt         VARCHAR(255) NOT NULL,
    kieu_bt        VARCHAR(50)  NOT NULL,
    tong_thoi_gian INT          NOT NULL
);

CREATE TABLE SINH_VIEN
(
    ma_sv      INT PRIMARY KEY,
    ho_ten     VARCHAR(255) NOT NULL,
    dien_thoai VARCHAR(15)  NOT NULL,
    lop        VARCHAR(50)  NOT NULL
);

CREATE TABLE PHAN_CONG
(
    ma_sv            INT,
    ma_bt            INT,
    so_ngay_tham_gia INT          NOT NULL,
    vi_tri_cong_viec VARCHAR(100) NOT NULL,
    PRIMARY KEY (ma_sv, ma_bt, vi_tri_cong_viec),
    FOREIGN KEY (ma_sv) REFERENCES SINH_VIEN (ma_sv),
    FOREIGN KEY (ma_bt) REFERENCES BAI_TAP (ma_bt),
    CONSTRAINT chk_thoi_gian CHECK (so_ngay_tham_gia > 0)
);