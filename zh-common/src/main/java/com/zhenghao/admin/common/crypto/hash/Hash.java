package com.zhenghao.admin.common.crypto.hash;

import com.zhenghao.admin.common.crypto.util.ByteSource;
import com.zhenghao.admin.common.crypto.util.ByteSource;

public interface Hash extends ByteSource {

    String getAlgorithmName();

    ByteSource getSalt();

    int getIterations();
}
