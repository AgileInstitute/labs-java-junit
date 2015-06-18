package Tests;

import StarTrek.Klingon;


public class MockKlingon extends Klingon {
    private int overrideDistance;
    private boolean deleteCalled = false;

    public MockKlingon(int distance) {
        overrideDistance = distance;
    }

    public MockKlingon(int distance, int energy) {
        overrideDistance = distance;
        setEnergy(energy);
    }

    public int distance() {
        return overrideDistance;
    }

    public void delete() {
        deleteCalled = true;
    }

    boolean deleteWasCalled() {
        return deleteCalled;
    }
}
