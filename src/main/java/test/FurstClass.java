package test;

import java.io.Serializable;

public class FurstClass implements Serializable, FurstInterface {
    private static final long serialVersionUID = 5404700101559301712L;

    @Override
    public double method1(double a) throws Exception {
	return a;
    }

    @Override
    public int method1(int a) throws Exception {
	return 0;
    }

    @Override
    public long method1(long a) throws Exception {
	return a + 4;
    }
}
