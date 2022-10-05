package com.yunusakin.saff.repository;

import com.yunusakin.saff.model.SaffFile;

public interface CustomSAFFRepository {
    SaffFile getByFileCode(String fileCode);
}
