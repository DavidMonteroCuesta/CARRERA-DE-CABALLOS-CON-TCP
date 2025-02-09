package es.etg.psp.dmc.cblls.server.core;

import java.io.IOException;
import java.net.Socket;

import es.etg.psp.dmc.cblls.util.DataTransferTCP;
import es.etg.psp.dmc.cblls.util.Randomizer;
import es.etg.psp.dmc.cblls.util.Responses;

public class Function implements DataTransferTCP, Randomizer{
    public static final int FINISH = 100;
    private final int MAX = 10;
    private final int MIN = 1;

    private int counter = 0;
    private boolean finished = false;

    private final Socket client;

    public Function(Socket client) {
        this.client = client;
    }

    public void move() throws Exception{
        setCounter(this.counter + Randomizer.getRandom(MIN, MAX));
        DataTransferTCP.send(client, Integer.toString(getCounter()));
    }

    public void finish() throws Exception{
        DataTransferTCP.send(client, ((isFinished())? Responses.WIN.getValue() : Responses.LOSE.getValue()));
        close();
    }
    
    private void close(){
        try { this.client.close();
        } catch (IOException e) {throw new RuntimeException(e);}
    }

    public Socket getCliente() {
        return client;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        if (this.counter < FINISH) this.counter = counter;
        else setFinished(finished);
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = true;
    }
}