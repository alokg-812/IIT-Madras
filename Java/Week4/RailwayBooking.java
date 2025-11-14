class BookingDB{}
interface QIF{
    abstract int getStatus(int trainno, Date d);
}

public class RailwayBooking {
    private BookingDB railwaydb;
    public QIF login(String u, String p){
        // ... (login logic)
        return new QueryObject(); // returns an object of the private class
    }

    private class QueryObject implements QIF {
        private int numqueries = 0; // Tracks the state of the interaction
        private static final int QLIM = 10; // Query limit

        @Override
        public int getStatus(int trainno, Date d) {
            if (numqueries < QLIM) {
                // Look up railwaydb (has access to outer class's private members)
                // respond, increment numqueries
                numqueries++;
                // ...
            }
            // else: return an error or unavailable status
            return 0;
        }
    }
}
