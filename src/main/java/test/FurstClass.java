package test;

import java.io.Serializable;

public class FurstClass implements Serializable, FurstInterface {
    private static final long serialVersionUID = 5404700101559301712L;

    @Override
    public int method1(int a) throws Exception {
	return 0;
    }

    @Override
    public double method2(double a) throws Exception {
	return a + 5.2;
    }

    @Override
    public float method2(float a) throws Exception {
	return a + 5.2f;
    }
}
