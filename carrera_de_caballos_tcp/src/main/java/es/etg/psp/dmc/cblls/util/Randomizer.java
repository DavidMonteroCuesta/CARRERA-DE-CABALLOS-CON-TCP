package es.etg.psp.dmc.cblls.util;

import java.util.Random;

public interface Randomizer {
    static final int RANDOM_INDEX = 1;

    static int getRandom(int min, int max){
        Random random = new Random();
        return min + random.nextInt(max - RANDOM_INDEX);
    }

}
