package it.polimi.ingsw.view;

import java.util.concurrent.Callable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadFromInput implements Callable<String> {
    private BufferedReader bufferedReader;

    public ReadFromInput() {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public String call() throws IOException, InterruptedException {
        String in;
        while(!bufferedReader.ready()) {
            Thread.sleep(200);
        }
        in = bufferedReader.readLine();
        return in;
    }
}

