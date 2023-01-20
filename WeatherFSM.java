import java.util.Random;

public class WeatherFSM {
    // Enumerations for states and events
    enum State {
        Clear, Cloudy, Raining, SevereWeather
    }
    enum Event {
        GettingWarmer, GettingColder, HumidityIncreasing, WindIncreasing
    }
    private State currentState;
    private Random random;
    public WeatherFSM() {
        currentState = State.Clear;
        random = new Random();
    }

    public void update(Event event) {
        int transition = random.nextInt(3); // generates a random number between 0 and 2
        if (transition == 0) {
            // Move to the left in the flow
            switch (currentState) {
                case Clear:
                    // No left state to move to
                    break;
                case Cloudy:
                    currentState = State.Clear;
                    break;
                case Raining:
                    currentState = State.Cloudy;
                    break;
                case SevereWeather:
                    currentState = State.Raining;
                    break;
            }
        } else if (transition == 1) {
            // State does not change
        } else {
            // Move to the right in the flow
            switch (currentState) {
                case Clear:
                    currentState = State.Cloudy;
                    break;
                case Cloudy:
                    currentState = State.Raining;
                    break;
                case Raining:
                    currentState = State.SevereWeather;
                    break;
                case SevereWeather:
                    // No right state to move to
                    break;
            }
        }
    }

    public static void main(String[] args) {
        WeatherFSM weatherFSM = new WeatherFSM();
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 5; j++) {
                Event randomEvent = Event.values()[new Random().nextInt(Event.values().length)];
                weatherFSM.update(randomEvent);
                System.out.println("Day " + (i+1) + ", Event " + (j+1) + ": " + weatherFSM.currentState);
            }
        }
    }
}