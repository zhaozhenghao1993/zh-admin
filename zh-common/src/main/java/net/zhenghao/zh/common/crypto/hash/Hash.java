package net.zhenghao.zh.common.crypto.hash;

import net.zhenghao.zh.common.crypto.util.ByteSource;

public interface Hash extends ByteSource {

    String getAlgorithmName();

    ByteSource getSalt();

    int getIterations();
}
