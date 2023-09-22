package MsPatients.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Gendre {
    FEMME ,HOMME ,AUTRE;


    private static final List<Gendre> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static Gendre randomGendre()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

}
