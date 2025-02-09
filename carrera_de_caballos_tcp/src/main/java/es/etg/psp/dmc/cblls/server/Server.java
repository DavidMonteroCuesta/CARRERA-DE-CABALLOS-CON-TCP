package es.etg.psp.dmc.cblls.server;

import java.net.ServerSocket;

import es.etg.psp.dmc.cblls.server.core.Function;
import es.etg.psp.dmc.cblls.util.PortData;
import es.etg.psp.dmc.cblls.util.Randomizer;

public class Server implements PortData, Randomizer{
    private static final int MAX = 4;
    private static final int MIN = 0;
    private static final Function[] functions = new Function[MAX];

    public static void main(String[] args) throws Exception{
        try (ServerSocket server = new ServerSocket(PORT)) {
            for (int i = 0; i < functions.length; i++) functions[i] = new Function(server.accept());
            
            do { 
                functions[Randomizer.getRandom(MIN, MAX + 1)].move();
            } while (!isFinished());

            for (Function function : functions) function.finish();
        }
    }

    private static boolean isFinished(){
        boolean finished = false;
        for (Function function : functions) if (function.isFinished()) finished = true;
        return finished;
    }
}