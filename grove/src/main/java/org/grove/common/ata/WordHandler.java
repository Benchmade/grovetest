package org.grove.common.ata;

public interface WordHandler {
    public void join() throws InterruptedException;
    public String[] getWords();
}

