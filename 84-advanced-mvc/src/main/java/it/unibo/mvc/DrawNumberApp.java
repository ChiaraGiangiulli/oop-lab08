package it.unibo.mvc;

import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 */
public final class DrawNumberApp implements DrawNumberViewObserver {

    private DrawNumber model;
    private final List<DrawNumberView> views;

    /**
     * @param views
     *            the views to attach
     */
    public DrawNumberApp(final DrawNumberView... views) throws IOException {
        /*
         * Side-effect proof
         */
        this.views = Arrays.asList(Arrays.copyOf(views, views.length));
        for (final DrawNumberView view: views) {
            view.setObserver(this);
            view.start();
        }
        Configuration.Builder builder= new Configuration.Builder();
        File file = new File("src/main/resources/config.yml");
        try(final BufferedReader in =  new BufferedReader(new FileReader(file.getAbsolutePath()))) {
            /*final BufferedReader in =  new BufferedReader(new InputStreamReader(
                                        ClassLoader.getSystemResourceAsStream("config.yml"))) // NullPointerException*/

            StringTokenizer min = new StringTokenizer(in.readLine(), ": ");
            StringTokenizer max = new StringTokenizer(in.readLine(), ": ");
            StringTokenizer attempts = new StringTokenizer(in.readLine(), ": ");
            
            min.nextToken();
            max.nextToken();
            attempts.nextToken();
            
            builder.setMin(Integer.parseInt(min.nextToken()));
            builder.setMax(Integer.parseInt(max.nextToken()));
            builder.setAttempts(Integer.parseInt(attempts.nextToken()));
            
            final Configuration configuration = builder.build();
            if (configuration.isConsistent()) {
                this.model = new DrawNumberImpl(configuration);
            } 
            else {
                for (final DrawNumberView view: views){
                    view.displayError("Error, using default configuration");
                    this.model = new DrawNumberImpl(new Configuration.Builder().build());
                }
            }
        }
        catch(IOException exc){
            for (final DrawNumberView view: views){
                view.displayError(exc.getMessage());
            }
            
        }
    }

    @Override
    public void newAttempt(final int n) {
        try {
            final DrawResult result = model.attempt(n);
            for (final DrawNumberView view: views) {
                view.result(result);
            }
        } catch (IllegalArgumentException e) {
            for (final DrawNumberView view: views) {
                view.numberIncorrect();
            }
        }
    }

    @Override
    public void resetGame() {
        this.model.reset();
    }

    @Override
    public void quit() {
        /*
         * A bit harsh. A good application should configure the graphics to exit by
         * natural termination when closing is hit. To do things more cleanly, attention
         * should be paid to alive threads, as the application would continue to persist
         * until the last thread terminates.
         */
        System.exit(0);
    }

    /**
     * @param args
     *            ignored
     * @throws IOException
     */
    public static void main(final String... args) throws IOException {
        new DrawNumberApp(new DrawNumberViewImpl());
        
    }

}
